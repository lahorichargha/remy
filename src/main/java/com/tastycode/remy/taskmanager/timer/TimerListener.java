package com.tastycode.remy.taskmanager.timer;

public interface TimerListener {
	void timerStarted(TimerInfo timer);
	void timerFinished(TimerInfo timer);
}
