package com.tastycode.remy.input;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineList;
import javax.speech.EngineModeDesc;
import javax.speech.EngineStateError;
import javax.speech.recognition.GrammarException;
import javax.speech.recognition.Recognizer;
import javax.speech.recognition.RecognizerAudioEvent;
import javax.speech.recognition.RecognizerAudioListener;
import javax.speech.recognition.ResultEvent;
import javax.speech.recognition.ResultListener;
import javax.speech.recognition.Rule;
import javax.speech.recognition.RuleAlternatives;
import javax.speech.recognition.RuleGrammar;
import javax.speech.recognition.RuleParse;
import javax.speech.recognition.RuleSequence;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.sun.speech.engine.recognition.BaseRuleName;
import com.tastycode.remy.GrammarLoader;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;

import edu.cmu.sphinx.jsapi.SphinxEngineCentral;
import edu.cmu.sphinx.jsapi.SphinxRecognizerModeDesc;

/**
 * Example of using the speech input.
 * Not a testcase, so that you don't have to speak during
 * each test run.
 *
 * NOTE: Use -Xmx512m VM arg for running due to memory usage.
 * @author broberts
 */
public class SpeechInputExample {
	/**
	 * Use -Xmx512m VM arg to increase memory 
	 */
	public static void main(String[] args) throws Exception {
		SpeechInput input = new SpeechInput();
		input.afterPropertiesSet();
		RuleGrammar ruleGrammar = input.getGrammar().getRuleGrammar();
		System.out.println("Adding import");
		
		Recognizer recognizer = ruleGrammar.getRecognizer();
		System.out.println("Loading: " + new ClassPathResource("/grammar/hello.gram"));
		loadGrammar(recognizer, new ClassPathResource("/grammar/cooking.gram"));
		loadGrammar(recognizer, new ClassPathResource("/grammar/ingredient.gram"));
		loadGrammar(recognizer, new ClassPathResource("/grammar/recipe.gram"));
		loadGrammar(recognizer, new ClassPathResource("/grammar/step.gram"));
		loadGrammar(recognizer, new ClassPathResource("/grammar/timer.gram"));
		loadGrammar(recognizer, new ClassPathResource("/grammar/remy.gram"));
		
//		RuleGrammar helloRules = loadGrammar(recognizer, new ClassPathResource("/grammar/hello.gram"));
//		String name = helloRules.getName();
//		System.out.println("Adding grammar: " + name);
//		ruleGrammar.addImport(new RuleName("hello.hello"));
		System.out.println("Added");
		System.out.println("Committing changes");
		
		input.getGrammar().commitChanges();
		System.out.println("Committed");
		Rule rule = ruleGrammar.getRule("command");
		showRule(rule, "");
		addGrammar(rule, "hello");
		List<String> ingredients = Arrays.asList(
				"carrot",
				"broccoli",
				"kale"
				);
//		String ingredientRuleStr = StringUtils.collectionToDelimitedString(ingredients, "|")
//			+ ";";
//		Rule ingredientsRule = ruleGrammar.ruleForJSGF(ingredientRuleStr);
//		RuleGrammar recipeGrammar = recognizer.getRuleGrammar("recipe");
//		recipeGrammar.setRule("ingredient", ingredientsRule, true);
		RuleGrammar ingredientGrammar = recognizer.getRuleGrammar("ingredient");
		GrammarLoader.loadWords("ingredient", ingredients, ingredientGrammar);

//		recipeGrammar.getRule("ingredient");
		
		//ruleGrammar.getRecognizer().getRuleGrammar(s)
		input.getGrammar().commitChanges();
		if (rule instanceof RuleAlternatives) {
			RuleAlternatives alts = (RuleAlternatives) rule;
			for(Rule r: alts.getRules()) {
				showRule(r, "");
				if (r instanceof RuleSequence) {
					for (Rule rs : ((RuleSequence)r).getRules()) {
						showRule(rs, "  ");
					}
				}
			}
		}
		//System.out.println("Alternatives: " + r.getRules()
		
//		System.out.println("Random sentence:");
//		String randomSentence = input.getGrammar().getRandomSentence();
//		System.out.println("\t" + randomSentence);
		System.out.println("Parsing...");
		RuleParse result = ingredientGrammar.parse("kale", "ingredient");
		System.out.println("Parse: " + result);
		
		WhatWasSaid utterance = null;
		do {
			System.out.println("Say something:");
			utterance = input.recordUtterance();
			System.out.println("Got utterance: " + utterance);
		} while (!utterance.getText().equals("quit"));
		
		System.out.println("Quitting");
		input.destroy();
	}

	private static RuleGrammar loadGrammar(Recognizer recognizer,
			Resource resource) throws GrammarException, IOException,
			EngineStateError {
		return recognizer.loadJSGF(new InputStreamReader(resource.getInputStream()));
	}

	protected static void addGrammar(Rule command, String grammar) {
		RuleAlternatives alts = (RuleAlternatives) command;
		alts.append(new RuleSequence(new BaseRuleName(grammar + "." + grammar)));
	}

	private static void showRule(Rule rule, String prefix) {
		System.out.println(prefix + "Rule("+(rule != null ? rule.getClass().getSimpleName():null)+"): " + rule);
	}
}
