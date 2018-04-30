package com.tastycode.remy.commonobjects.timermodel;

import java.util.List;

import com.tastycode.remy.commonobjects.recipe.Step;
import com.tastycode.remy.taskmanager.timer.TimerInfo;

public interface TimerModel {
	List<TimerInfo> getTimers();
	List<TimerInfo> getTimers(Step step);
	void removeTimer(TimerInfo timer);
	void addTimer(TimerInfo timer);
}
