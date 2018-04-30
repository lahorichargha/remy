package com.tastycode.remy.output;

import java.beans.PropertyVetoException;
import java.util.Locale;

import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.SpeakableListener;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory;
import com.sun.speech.freetts.jsapi.FreeTTSEngineCentral;
import com.tastycode.remy.commonobjects.dialog.SayNothing;
import com.tastycode.remy.commonobjects.dialog.WhatToSay;

public class SpeechOutput implements Output, InitializingBean, DisposableBean {
    /* log4j logger instance */
    private static final Logger logger = Logger.getLogger(SpeechOutput.class
                    .getName());
    
	public final static String DEFAULT_VOICE = "kevin16";
	private String voiceName = DEFAULT_VOICE;
	private Synthesizer synthesizer;
	private SpeakableListener speakableListener;

	public void say(WhatToSay utterance)
	{
		if (utterance == null || utterance == SayNothing.NOTHING)
			return;
		
		synchronized (synthesizer) {
	        SpeakListener listener = new SpeakListener();

			logger.info("Saying: " + utterance);
	        synthesizer.speakPlainText(utterance.getText(), listener);
	        
	        // listener.waitFor(SpeakableEvent.SPEAKABLE_ENDED);
		}
	}

	public void afterPropertiesSet() throws Exception {
		allocate();
	}
	
	public void destroy() throws Exception {
		deallocate();
	}

	private void deallocate() throws EngineException, EngineStateError {
		if (synthesizer != null) {
			synthesizer.deallocate();
		}
	}

	private void allocate() throws PropertyVetoException, IllegalArgumentException, EngineException {
		if (synthesizer != null)
			return; // allow for synthesizer to be set programatically 

		SynthesizerModeDesc  required = new SynthesizerModeDesc(Locale.US);
		if (voiceName != null) {
			Voice voice = new Voice();
			voice.setName(voiceName);
			required.addVoice(voice);
		}
		
		synthesizer = Central.createSynthesizer(required);
		synthesizer.allocate();
	}
	
	public String getVoiceName() {
		return voiceName;
	}

	public void setVoiceName(String voiceName) {
		this.voiceName = voiceName;
	}

	public Synthesizer getSynthesizer() {
		return synthesizer;
	}

	public void setSynthesizer(Synthesizer synthesizer) {
		this.synthesizer = synthesizer;
	}

	public SpeakableListener getSpeakableListener() {
		return speakableListener;
	}

	public void setSpeakableListener(SpeakableListener speakableListener) {
		this.speakableListener = speakableListener;
	}

	private static void setSystemPropertyIfNotSet(String key, String value) {
		if (System.getProperty(key) == null) {
			System.setProperty(key, value);
		}
	}

	static {
		// Set default TTS voice 
		setSystemPropertyIfNotSet("freetts.voices", KevinVoiceDirectory.class.getName());
		
		// Register FreeTTS engine
		try {
			Central.registerEngineCentral(FreeTTSEngineCentral.class.getName());
		} catch (EngineException e) {
		}
	}
}
