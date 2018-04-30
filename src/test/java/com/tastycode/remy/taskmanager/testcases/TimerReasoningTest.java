package com.tastycode.remy.taskmanager.testcases;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.List;

import org.joda.time.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tastycode.remy.taskmanager.timer.TimerInfo;
import com.tastycode.remy.taskmanager.timer.TimerListener;
import com.tastycode.remy.taskmanager.timer.TimerReasoning;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TimerReasoningTest {
	final private static long TIMER_LENGTH_MILLIS = 1000;
	
	private final class CollectingTimerListener implements TimerListener {
		volatile private TimerInfo finishedTimer;

		public void timerFinished(TimerInfo timer) {
			finishedTimer = timer;
		}

		public void timerStarted(TimerInfo timer) {
			// nothing
		}
	}


//	@Autowired private ChannelResolver resolver;
	@Autowired private TimerReasoning reasoning;
	
	@DirtiesContext
	@Test public void testTimerInfo() throws InterruptedException {
		TimerInfo timer = startTimer();
		List<TimerInfo> info = reasoning.getInfoAboutTimer("name");
		assertEquals(1,     info.size());
		assertEquals(timer, info.get(0));
		Thread.sleep(TIMER_LENGTH_MILLIS);
	}


	protected TimerInfo startTimer() {
		TimerInfo timer = new TimerInfo("name", null, new Duration(TIMER_LENGTH_MILLIS));
		reasoning.startTimer(timer);
		return timer;
	}
	

	
	@DirtiesContext
	@Test(timeout=2*TIMER_LENGTH_MILLIS) public void testTimeFires() {
		CollectingTimerListener listener = new CollectingTimerListener();
		reasoning.addListener(listener);
		TimerInfo timer = startTimer();
		try {
			Thread.sleep(timer.getDuration().getMillis());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (listener.finishedTimer == null) {
			try {
				System.out.println("....");
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertNotNull(listener.finishedTimer);
	}
	
}
