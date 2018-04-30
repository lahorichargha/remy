package com.tastycode.remy.input;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.speech.recognition.GrammarException;
import javax.speech.recognition.RuleGrammar;
import javax.speech.recognition.RuleParse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaidLiterally;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.jsapi.JSGFGrammar;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.ConfidenceResult;
import edu.cmu.sphinx.result.ConfidenceScorer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;

public class SpeechInput implements Input, InitializingBean, DisposableBean {
	public static final int POLL_WAIT_TIME_SECS = 100000;
//	private static final long JOIN_WAIT_TIME_MILIS = 1000;
	
	private String headRule = "command";
	
	private Resource configResource;
	private Recognizer recognizer;
	private Microphone microphone;
	private JSGFGrammar grammar;

	protected boolean shouldRecord = true;
	// protected Thread recordingThread;
	protected BlockingQueue<String> utterances = new LinkedBlockingQueue<String>();
	private ConfidenceScorer confidenceScorer;

	public SpeechInput() {
		configResource = new ClassPathResource("Sphinx.config.xml", SpeechInput.class);
	}

	public WhatWasSaid parseUtterance(String input) {
		input = input.toLowerCase().replaceAll("[?.,']", "");
		WhatWasSaid said = WhatWasSaidLiterally.NOTHING;
		try {
			RuleParse parsed = grammar.getRuleGrammar().parse(input, headRule);
			if (parsed != null) {
				return new WhatWasSaidLiterally(input, this.getTagString(parsed));
			}
		} catch (GrammarException e) {
		}
		return said;
	}

	
	public WhatWasSaid recordUtterance() {
		try {
//			if (microphone.startRecording()) {
				Result result = recognizer.recognize();
				if (result == null) {
					return WhatWasSaidLiterally.NOTHING;
				}
				

				if (confidenceScorer != null) {
					ConfidenceResult score = confidenceScorer.score(result);
					System.out.println("Score: " + score);
				}
				return new WhatWasSaidLiterally(result.getBestFinalResultNoFiller(), getTagString(result));
//			} else {
//				throw new IllegalStateException("Unable to startRecording of microphone");
//			}
		} finally {
//			microphone.stopRecording();
		}
	}

    
	
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(configResource, "Must set configResource");
		
		startConfiguration();
		startRecognizing();
	}

	private void startRecognizing() throws IllegalStateException, IOException {
        /* allocate the resource necessary for the recognizer */
        recognizer.allocate();

        /* the microphone will keep recording until the object destroy()ed */
//        if (microphone.startRecording()) {
//        	recordingThread = new Thread(new Runnable() {
//        		public void run() {
//        			log.info("Starting to record");
//        			while (shouldRecord) {
//        			    Result result = recognizer.recognize();
//			    		log.debug("Got result: " + result);
//        			    String resultText;
//        			    if (result != null) {
//        			    		resultText = result.getBestFinalResultNoFiller();
//        						log.debug("You said: " + resultText + "\n");
//        						try {
//									utterances.put(resultText);
//								} catch (InterruptedException e) {
//									// Don't care if I was interrupted
//								}
//        					} else {
//        						log.debug("I can't hear what you said.\n");
//        					}
//        			}
//				}
//        	});
//        	 recordingThread.start();
//	    } else {
//	    	recognizer.deallocate();
//	    	throw new IllegalStateException("Cannot start microphone");
//	    }
	}

	protected void startConfiguration() throws IOException, PropertyException,
			InstantiationException {
		if (recognizer != null && microphone != null)
			return;
		
		ConfigurationManager cm = new ConfigurationManager(configResource.getURL());
		if (recognizer == null)
			recognizer = (Recognizer) cm.lookup("recognizer");
		
		if (microphone == null)
			microphone = (Microphone) cm.lookup("microphone");
		microphone.startRecording();
		
		if (grammar == null)
			grammar = (JSGFGrammar) cm.lookup("jsgfGrammar");
		
		if (confidenceScorer == null) {
			confidenceScorer = (ConfidenceScorer) cm.lookup("confidenceScorer");
		}
	}

	public void destroy() throws Exception {
		shouldRecord = false;
		microphone.stopRecording();
		recognizer.deallocate();
		// recordingThread.join(JOIN_WAIT_TIME_MILIS);
	}

	public JSGFGrammar getGrammar() {
		return grammar;
	}

	public void setGrammar(JSGFGrammar grammar) {
		this.grammar = grammar;
	}
	
	public Recognizer getRecognizer() {
		return recognizer;
	}

	public void setRecognizer(Recognizer recognizer) {
		this.recognizer = recognizer;
	}

	private static String NO_TAGS = "";
	
    protected String getTagString(Result result) {
        RuleParse ruleParse;
		try {
			ruleParse = getRuleParse(result);
	        return getTagString(ruleParse);
		} catch (GrammarException e) {
			return NO_TAGS;
		}
    }

	protected String getTagString(RuleParse ruleParse) {
		String tagString = NO_TAGS;
		if (ruleParse != null) {
		    String[] tags = ruleParse.getTags();
		    StringBuffer sb = new StringBuffer();
		    for (int i = 0; tags != null && i < tags.length; i++) {
		        sb.append(tags[i]);
		        if (i < tags.length -1) {
		            sb.append(" ");
		        }
		    }
		    tagString = sb.toString().trim();
		}
		return tagString;
	}

    /**
     * Retrieves the rule parse for the given result
     *
     * @param the recognition result
     * @return the rule parse for the result
     * @throws GrammarException if there is an error while parsing the
     * result
     */
    protected RuleParse getRuleParse(Result result) throws GrammarException {
        String resultText = result.getBestFinalResultNoFiller();
        RuleGrammar ruleGrammar = getGrammar().getRuleGrammar();
        RuleParse ruleParse = ruleGrammar.parse(resultText, null);
        return ruleParse;
    }
	
	private static final Logger log = Logger.getLogger(SpeechInput.class);
}
