package com.tastycode.remy.taskmanager.timer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.tastycode.remy.commonobjects.recipe.Ingredient;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 * @author broberts
 */
@Component
public class TimerReasoning
{
	Set<TimerListener> listeners = new CopyOnWriteArraySet<TimerListener>();
	private Timer scheduler = new Timer();
	
	/**
	 * Using list, because expected number of timers is ~1-3 timer at a time
	 * so performance is trumped by ease of accessibility
	 */
	private List<CookingTimerTask> timers = new CopyOnWriteArrayList<CookingTimerTask>();
	
	private class CookingTimerTask extends TimerTask {
		private TimerInfo timer;

		public CookingTimerTask(TimerInfo timer) {
			this.timer = timer;
		}

		@Override
		public void run() {;
			fireTimerFinished(timer);
		}
		
		public TimerInfo getTimer() {
			return timer;
		}
	}
	
	
   public TimerInfo startTimer(TimerInfo timerToStart)
    {
		log.debug("Starting a timer: " + timerToStart);
    	fireTimerNotification(timerToStart, "Timer %s has been started.");
    	fireTimerStarted(timerToStart);
    	CookingTimerTask timerTask = new CookingTimerTask(timerToStart);
    	timers.add(timerTask);
		scheduler.schedule(timerTask, timerToStart.getEndTime().toDate());
        
       return timerToStart;
    }
 	
    public void stopTimer(TimerInfo timerToStop) {

		log.debug("Stopping timer: " + timerToStop);
    	fireTimerNotification(timerToStop, "Timer %s has been stopped.");

    	Iterable<CookingTimerTask> tasks = getTimerTasks(timerToStop);
    	for (CookingTimerTask task : tasks) {
        	task.cancel();
    	}
    	removeTimer(timerToStop);
    }

	private Iterable<CookingTimerTask> getTimerTasks(TimerInfo info) {
		return Iterables.filter(timers, new ByInfo(info));
	}

	protected void removeTimer(TimerInfo timerInfo) {
		removeTimer(new ByInfo(timerInfo));
	}

	protected void removeTimer(String name) {
		removeTimer(new ByName(name));
	}
	
	protected void removeTimer(Predicate<CookingTimerTask> predicate) {
		Iterable<CookingTimerTask> foundTasks = Iterables.filter(timers, predicate);
		for(CookingTimerTask task : foundTasks) {
			timers.remove(task);
		}
	}

	
	
	public void addListener(TimerListener listener) {
		listeners.add(listener);
	}

	public void removeListener(TimerListener listener) {
		listeners.remove(listener);
	}

    protected void fireTimerFinished(TimerInfo timer) {
    	log.info("Timer finished: " + timer);
    	for(TimerListener listener : listeners) {
    		listener.timerFinished(timer);
    	}
    }
    
    protected void fireTimerStarted(TimerInfo timer) {
    	log.info("Timer started: " + timer);
    	for(TimerListener listener : listeners) {
    		listener.timerStarted(timer);
    	}
    }
    
    protected void fireTimerNotification(TimerInfo timer, String messageFormat) {
    	// TODO Doesn't do anything.
    	String message = new Formatter().format(messageFormat, timer).toString();
	}

    
	public List<TimerInfo> getInfoAboutTimer(Ingredient ingredient) {
    	List<TimerInfo> foundTimers = new ArrayList<TimerInfo>();
    	for(CookingTimerTask task : timers){
    		TimerInfo timer = task.getTimer();
    		if (timer.getReferringIngredients().contains(ingredient)) {
    			foundTimers.add(timer);
    		}
    	}
    	return foundTimers;
    }

    public void displayInfo(TimerInfo... infos) {
    	System.err.println("Got timer: " + infos);
    }
    
    public void displayInfo(List<TimerInfo> timers) {
    	System.err.println("Timer info: " + timers);
    	for (TimerInfo cookingTimer : timers) {
			System.err.println("Got timer: " + cookingTimer);
		}
    }
    
    public List<TimerInfo> getInfoAboutTimer(String name)
    {
    	return getInfoAboutTimer(new ByName(name));
    }

	protected List<TimerInfo> getInfoAboutTimer(Predicate<CookingTimerTask> predicate) {
    	Iterable<CookingTimerTask> found = Iterables.filter(timers, predicate);
    	List<TimerInfo> infos = new ArrayList<TimerInfo>();
    	for (CookingTimerTask task : found) {
    		infos.add(task.getTimer());
    	}
    	return infos;
	}

	private static class ByName implements Predicate<CookingTimerTask> {
		String name;
		public ByName(String name) {
			this.name = name;
		}
		
		public boolean apply(CookingTimerTask task) {
			TimerInfo timer = task.getTimer();
			return timer != null && timer.getName().equals(name);
		}
		
		public String getName() {
			return name;
		}
	}

	// Warning, this is slow
	private static class ByField<T> implements Predicate<T> {
		String attributeName;
		Object expectedValue;
		
		public ByField(String attributeName, Object expectedValue) {
			super();
			this.attributeName = attributeName;
			this.expectedValue = expectedValue;
		}

		public boolean apply(T object) {
			if (object != null) {
				Field field = ReflectionUtils.findField(object.getClass(), attributeName);
				Object value;
				try {
					value = field.get(object);
					return ObjectUtils.nullSafeEquals(expectedValue, value); 
				} catch (IllegalArgumentException e) {
					// Fall through
				} catch (IllegalAccessException e) {
					// Fall through
				}
			}
			return false;
		}
		
	}
	
	private static class ByInfo implements Predicate<CookingTimerTask> {
		TimerInfo expectedInfo;
		public ByInfo(TimerInfo info) {
			this.expectedInfo = info;
		}
		
		public boolean apply(CookingTimerTask task) {
			TimerInfo timerInfo = task.getTimer();
			return ObjectUtils.nullSafeEquals(expectedInfo, timerInfo);
		}
	}
	
	private static Logger log = Logger.getLogger(TimerReasoning.class);
}
