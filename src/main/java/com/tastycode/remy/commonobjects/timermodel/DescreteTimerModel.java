package com.tastycode.remy.commonobjects.timermodel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.tastycode.remy.commonobjects.recipe.Step;
import com.tastycode.remy.taskmanager.timer.TimerInfo;

public class DescreteTimerModel implements TimerModel {
	List<TimerInfo> timers = new CopyOnWriteArrayList<TimerInfo>();
	
	public List<TimerInfo> getTimers() {
		// Return copy
		return new ArrayList<TimerInfo>(timers);
	}

	public List<TimerInfo> getTimers(Step step) {
		List<TimerInfo> stepTimers = new ArrayList<TimerInfo>();
		for (TimerInfo timerInfo : timers) {
			if (step.equals(timerInfo.getReferringStep())) {
				stepTimers.add(timerInfo);
			}
		}
		return stepTimers;
	}

	public void addTimer(TimerInfo timer) {
		timers.add(timer);
	}

	public void removeTimer(TimerInfo timer) {
		timers.remove(timer);
	}

}
