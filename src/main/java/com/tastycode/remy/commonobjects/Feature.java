package com.tastycode.remy.commonobjects;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.tastycode.remy.commonobjects.dialog.UserUtterance;
import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.dialog.systemutterance.SystemUtterance;
import com.tastycode.remy.dialogmanager.DialogManager;

public abstract class Feature implements Generator, Understander, TaskManager {
	abstract public WhatToSay howToSay(SystemUtterance utterance);
	abstract public SystemUtterance respond(UserUtterance utterance);

	private String grammarName;
	private DialogManager dialog;
	
	public Feature(String grammarName) {
		super();
		this.grammarName = grammarName;
		logger.info("Started feature: " + grammarName);
	}
	
	
	protected String getCommandType(WhatWasSaid wasSaid) {
		String tags = wasSaid.getTags();
		if (tags != null) {
			Matcher cmdMatcher = CMD_PATTERN.matcher(tags);
			if (cmdMatcher.matches()) {
				String cmd = cmdMatcher.group(1);
				return cmd;
			}
		}
		return "";
	}

	/**
	 * Returns true if expected command type.
	 * @param expectedCommandType either with or without space
	 * @param wasSaid
	 * @return
	 */
    protected boolean commandType(String expectedCommandType, WhatWasSaid wasSaid) {
    	String commandType = getCommandType(wasSaid);
		return commandType(expectedCommandType, commandType);
	}
    
	protected boolean commandType(String expectedCommandType, String commandType) {
		if (commandType == null || commandType.length() == 0) 
			return false;
		
		if (expectedCommandType.contains(" ")) {
    		return commandType.contains(expectedCommandType);
    	} else {
			String[] cmdTags = StringUtils.split(commandType, " ");
			if (cmdTags == null) {
				return false;
			}
			List<String> cmdTagList = Arrays.asList(cmdTags);
			return cmdTagList
						.contains(expectedCommandType);
    	}
	}
	
	public String getGrammarName() {
		return grammarName;
	}
	public void setGrammarName(String grammarName) {
		this.grammarName = grammarName;
	}

	private static Logger logger = Logger.getLogger(Feature.class);

	public void setDialog(DialogManager dialog) {
		this.dialog = dialog;
	}
	
	public DialogManager getDialog() {
		return this.dialog;
	}
	
	
	static Pattern CMD_PATTERN = Pattern.compile(".*cmd\\((.*?)\\).*");
}
