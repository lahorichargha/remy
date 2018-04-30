package com.tastycode.remy.taskmanager.timer;

import org.joda.time.Duration;
import org.joda.time.Period;

public class TimerUtils {

	public static String speakableDuration(Duration duration) {
		Period period = duration.toPeriod();
		String result = ifNonZero(period.getHours(), "hours ")
			+ ifNonZero(period.getMinutes(), "minutes ")
			+ ifNonZero(period.getSeconds(), "seconds ");
		return result;
	}

	public static String ifNonZero(int count, String unit) {
		return (count > 0) ? (count + " " + unit) : "";
	}

	public static String speakablePeriod(Period period) {
		return speakableDuration(period.toStandardDuration());
	}

}
