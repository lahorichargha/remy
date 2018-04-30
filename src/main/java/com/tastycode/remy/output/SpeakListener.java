package com.tastycode.remy.output;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import javax.speech.synthesis.SpeakableEvent;
import javax.speech.synthesis.SpeakableListener;

import org.apache.log4j.Logger;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class SpeakListener implements SpeakableListener
{
    /* log4j logger instance */
    private static final Logger logger = Logger.getLogger(SpeakListener.class
                    .getName());
    
	private BlockingQueue<SpeakableEvent> events = new SynchronousQueue<SpeakableEvent>();

	public void reset() {
		this.events.clear();
	}

	public void waitFor(int eventIdType) {
		try {
			SpeakableEvent event = null;
			do {
				event = events.poll(1000, TimeUnit.DAYS);
			} while (event != null && event.getId() != eventIdType);
			
		} catch (InterruptedException e) {
		}
		logger.trace("Finished waiting for speakable event.");
	}
    
    public void topOfQueue(SpeakableEvent arg0)
    {
        logger.trace("topOfQueue");
		events.offer(arg0);
    }
    public void wordStarted(SpeakableEvent arg0)
    {
        logger.trace("wordStarted");
		events.offer(arg0);
    }
    public void markerReached(SpeakableEvent arg0)
    {
        logger.trace("markerReached");
		events.offer(arg0);
    }
    public void speakableCancelled(SpeakableEvent arg0)
    {
        logger.trace("speakableCancelled");
		events.offer(arg0);
    }

	public void speakableEnded(SpeakableEvent speakableevent) {
		events.offer(speakableevent);
	}

	public void speakablePaused(SpeakableEvent arg0)
    {
        logger.trace("speakablePaused");
		events.offer(arg0);
    }
    public void speakableResumed(SpeakableEvent arg0)
    {
        logger.trace("speakableResumed");
		events.offer(arg0);
    }
    public void speakableStarted(SpeakableEvent arg0)
    {
        logger.trace("speech started");
		events.offer(arg0);
    }
}
