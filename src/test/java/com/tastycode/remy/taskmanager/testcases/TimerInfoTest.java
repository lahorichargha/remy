package com.tastycode.remy.taskmanager.testcases;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.joda.time.Period;
import org.joda.time.Seconds;
import org.junit.Test;

import com.tastycode.remy.taskmanager.timer.TimerInfo;

public class TimerInfoTest {

	@Test
	public void testGetTimeLeft() {
		TimerInfo info = new TimerInfo(null, null, Seconds.TWO.toStandardDuration());
		logger.info("Start: " + info.getStartTime());
		logger.info("End:   " + info.getEndTime());
		try {
 			Thread.sleep(100);
		} catch (InterruptedException e) {
			// No need to catch anything
		}
		Period p = info.getTimeLeft();
		assertEquals(0, p.getMinutes());
		assertEquals(1, p.getSeconds());
		assertTrue(1000 > p.getMillis());
	}

	
	Logger logger = Logger.getLogger(TimerInfoTest.class);
}
