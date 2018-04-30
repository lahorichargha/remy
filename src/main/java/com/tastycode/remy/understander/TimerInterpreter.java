package com.tastycode.remy.understander;

import java.util.Arrays;
import java.util.List;

import com.tastycode.remy.commonobjects.dialog.UserUtterance;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.dialog.userutterance.TimerCommand;
import com.tastycode.remy.commonobjects.dialog.userutterance.TimerQuery;
import com.tastycode.remy.commonobjects.dialog.userutterance.TimerSet;
import com.tastycode.remy.commonobjects.dialog.userutterance.TimerStop;

public class TimerInterpreter implements Interpreter {
	private static List<CommandMatcher<TimerCommand>> matchers = Arrays.asList(
			new CommandMatcher<TimerCommand>("(set|start).*timer", TimerSet.class),
			new CommandMatcher<TimerCommand>("(stop|end).*timer", TimerStop.class),
			new CommandMatcher<TimerCommand>("(how much|what).*timer", TimerQuery.class));
	
	public UserUtterance interpret(WhatWasSaid userUtter) {
		String wasSaid = userUtter.getText();
		TimerCommand cmd = null;
		for (CommandMatcher<TimerCommand> matcher : matchers) {
			if (matcher.matches(wasSaid)) {
				cmd = matcher.create(wasSaid);
				break;
			}
		}
		if (cmd != null) {
			// TODO Determine name of timer
			
			if (cmd instanceof TimerSet) {
				parseDuration((TimerSet)cmd);
			}
		}
		return cmd;
	}

	protected void parseDuration(TimerSet cmd) {
		// TODO Auto-generated method stub
		
	}
}
