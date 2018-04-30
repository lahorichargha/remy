package com.tastycode.remy.commonobjects.dialog.systemutterance;

import org.joda.time.Duration;

import com.tastycode.remy.taskmanager.timer.TimerInfo;

public class TimerAnswer extends AbstractSystemUtterance {
	private TimerInfo timerInfo;

	public TimerInfo getTimerInfo() {
		return timerInfo;
	}

	public void setTimerInfo(TimerInfo timerInfo) {
		this.timerInfo = timerInfo;
	}

	public TimerAnswer(TimerInfo timerInfo) {
		this.timerInfo = timerInfo;
	}

	public Duration getDuration() {
		return timerInfo.getDuration();
	}

	public String getTimerName() {
		return timerInfo.getName();
	}
}
