package com.tastycode.remy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import javax.speech.recognition.GrammarException;
import javax.speech.recognition.Rule;
import javax.speech.recognition.RuleGrammar;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class GrammarLoader {
	private GrammarLoader() { /* Static utility methods only */	}
	
	/* log4j logger instance */
    private static final Logger logger = Logger.getLogger(GrammarLoader.class);

	/**
	 * @param wordStream line-delimited list of words
	 */
	@SuppressWarnings("unchecked")
	public static void loadWords(String ruleName, InputStream wordStream, RuleGrammar grammar) {
		try {
			List<String> lines = (List<String>)IOUtils.readLines(wordStream);
			loadWords(ruleName, lines, grammar);
		} catch (IOException e) {
			throw new RuntimeException("Error loading words for rule: " + ruleName, e);
		} 
	}

	public static void loadWords(String ruleName, Collection<String> words,
			RuleGrammar grammar) {
		Rule rule = ruleForWords(ruleName, words, grammar);
		grammar.deleteRule(ruleName);
		grammar.setRule(ruleName, rule, true);
	}

	public static Rule ruleForWords(String ruleName, Collection<String> words,
			RuleGrammar grammar) {
		String wordRuleString = StringUtils.collectionToDelimitedString(words, "|");
        logger.debug("loaded rule: " + ruleName + " rule: " + wordRuleString);
		try {			
			return grammar.ruleForJSGF(wordRuleString);
		} catch (GrammarException e) {
			return null;
		}
	}
	
}
