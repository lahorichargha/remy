package com.tastycode.remy.taskmanager.timer;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.joda.time.Duration;

import com.tastycode.remy.commonobjects.Feature;
import com.tastycode.remy.commonobjects.dialog.SayNothing;
import com.tastycode.remy.commonobjects.dialog.UserUtterance;
import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.dialog.systemutterance.SystemUtterance;
import com.tastycode.remy.commonobjects.dialog.systemutterance.TimerAnswer;
import com.tastycode.remy.commonobjects.dialog.systemutterance.TimerFinished;
import com.tastycode.remy.commonobjects.dialog.systemutterance.TimerInfoAnswer;
import com.tastycode.remy.commonobjects.dialog.systemutterance.TimerStarted;
import com.tastycode.remy.commonobjects.dialog.systemutterance.TimerStopped;
import com.tastycode.remy.commonobjects.dialog.userutterance.TimerCommand;
import com.tastycode.remy.commonobjects.dialog.userutterance.TimerQuery;
import com.tastycode.remy.commonobjects.dialog.userutterance.TimerSet;
import com.tastycode.remy.commonobjects.dialog.userutterance.TimerStop;
import com.tastycode.remy.commonobjects.domainmodel.RecipeDomainModel;
import com.tastycode.remy.commonobjects.timermodel.TimerModel;
import com.tastycode.remy.dialogmanager.WhatToSayLiterally;

public class TimerFeature extends Feature implements TimerListener {
	@Resource TimerReasoning reasoning;
	private RecipeDomainModel model;

	public TimerFeature(RecipeDomainModel model) {
		super("timer");
		this.model = model;
	}
	
	public TimerReasoning getReasoning() {
		return reasoning;
	}

	public void setReasoning(TimerReasoning reasoning) {
		if (this.reasoning != null) {
			this.reasoning.removeListener(this);
		}
		this.reasoning = reasoning;
		reasoning.addListener(this);
	}

	@Override
	public WhatToSay howToSay(SystemUtterance utterance) {
		if (utterance instanceof TimerAnswer) {
			TimerAnswer answer = (TimerAnswer) utterance;
			if (utterance instanceof TimerStarted) {
				return new WhatToSayLiterally("Timer " + answer.getTimerName() + " was started for " + TimerUtils.speakableDuration(answer.getDuration()));
			}
			if (utterance instanceof TimerStopped) {
				return new WhatToSayLiterally("Timer " + answer.getTimerName() + " was stopped");
			}
			if (utterance instanceof TimerInfoAnswer) {
				return new WhatToSayLiterally("Timer " + answer.getTimerName() + " has " + TimerUtils.speakableDuration(answer.getDuration()) + " left.");
			}
			if (utterance instanceof TimerFinished) {
				return new WhatToSayLiterally("Timer " + answer.getTimerName() + " has finished after " + TimerUtils.speakableDuration(answer.getDuration()));
			}
		}
		return SayNothing.NOTHING;
	}

	@Override
	public SystemUtterance respond(UserUtterance utterance) {
		if (!(utterance instanceof TimerCommand)) 
			return SayNothing.NOTHING;
		
		TimerInfo timerInfo = toTimerInfo(utterance);
		if (utterance instanceof TimerSet) {
			logger.info("Setting timer: " + utterance);
			reasoning.startTimer(timerInfo);
			return new TimerStarted(timerInfo);
		}
		else if (utterance instanceof TimerStop) {
			reasoning.stopTimer(timerInfo);
			return new TimerStopped(timerInfo);
		}
		else if (utterance instanceof TimerQuery) {
			reasoning.stopTimer(timerInfo);
			return new TimerInfoAnswer(timerInfo);
		}
		return SayNothing.NOTHING;
	}

	private TimerInfo toTimerInfo(UserUtterance utterance) {
		if (utterance instanceof TimerSet) {
			TimerSet set = (TimerSet) utterance;
			return new TimerInfo(set.getName(), set.getStep(), set.getDuration());
		}
		if (utterance instanceof TimerCommand) { 
			return new TimerInfo(null, null, null);
		}
		return null;
	}

	public UserUtterance interpret(WhatWasSaid wasSaid) {
		if (!commandType(getGrammarName(), wasSaid)) {
			return SayNothing.NOTHING;
		}
		
		String command = getCommandType(wasSaid);
		
			if (commandType("set timer", command)) {
				TimerSet set = new TimerSet(wasSaid);
				Duration timerDuration = parseDuration(wasSaid);
				if (timerDuration.getMillis() == 0)
				{
				    logger.debug("didn't hear the duration correctly, " +
				    		"so not setting the time");
				    return SayNothing.NOTHING;
				}
				set.setDuration(timerDuration);
				return set;
			} else if ((commandType("stop timer", command))) {
				TimerStop stop = new TimerStop(wasSaid);
				return stop;
			} else if ((commandType("about timer", command))) {
				TimerQuery query = new TimerQuery(wasSaid);
				return query;
			}
		
		return SayNothing.NOTHING;
	}

	protected Duration parseDuration(WhatWasSaid wasSaid) {
		int length = 0;
		String tags = wasSaid.getTags();
		Matcher numberMatch = Pattern.compile("[^0-9]*([0-9]+ ?)+ number.*").matcher(tags);
		if (numberMatch.matches()) {
			System.out.println("tags: " + tags);
			String numberStr = numberMatch.group(1);
			String[] numbers = numberStr.split(" ");
			for (String numStr : numbers) {
				try {
					int number = Integer.parseInt(numStr);
					length += number;
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
		
		TimeUnit unit = TimeUnit.MINUTES;
		Matcher unitsMatcher = Pattern.compile(".*? ([a-z]+) units.*").matcher(tags);
		if (unitsMatcher.matches()) {
			String unitStr = unitsMatcher.group(1);
			if ("minute".equals(unitStr)) {
				unit = TimeUnit.MINUTES;
			}
			else if ("second".equals(unitStr)) {
				unit = TimeUnit.SECONDS;
			}
			else if ("hours".equals(unitStr)) {
				unit = TimeUnit.HOURS;
			}
		}
		
		
		Duration duration = new Duration(TimeUnit.MILLISECONDS
				.convert(length, unit));
		
		return duration;
	}

	public void timerFinished(TimerInfo timer) {
		logger.info("Timer finished: " + timer);
		getTimerModel().removeTimer(timer);
		getDialog().say(new TimerFinished(timer));
	}

	public void timerStarted(TimerInfo timer) {
		logger.info("Timer started: " + timer);
		getTimerModel().addTimer(timer);
	}
	
	private static Logger logger = Logger.getLogger(TimerFeature.class);

	public TimerModel getTimerModel() {
		return model.getTimerModel();
	}
}
