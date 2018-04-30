package com.tastycode.remy.taskmanager.timer;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;

import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class TimerInfo
{
    private String name = "";
    private DateTime startTime;
    private Duration duration;
	private Step referringStep;
    private List<Ingredient> referringIngredients;

    public TimerInfo(String name, 
    					Step referringStep, 
    					Duration duration) {
    	this.startTime = new DateTime();
    	this.name = name;
    	this.referringStep = referringStep;
    	this.duration = duration;
    }
    
	public DateTime getEndTime() {
		return getStartTime().plus(getDuration().getMillis());
	}

	public Period getTimeLeft(DateTime from) {
		Interval interval = new Interval(from, getEndTime());
		return interval.toPeriod();
	}
	
	public Period getTimeLeft() {
		return getTimeLeft(null);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Step getReferringStep() {
		return referringStep;
	}

	public void setReferringStep(Step referringStep) {
		this.referringStep = referringStep;
	}

	public List<Ingredient> getReferringIngredients() {
		return referringIngredients;
	}

	public void setReferringIngredients(List<Ingredient> referringIngredients) {
		this.referringIngredients = referringIngredients;
	}
	
	public String getPrintableRepresentation() {
		return TimerUtils.speakablePeriod(getTimeLeft()) + " left";
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[name=" + getName() + ", duration=" + duration +"]";
	}
}
	