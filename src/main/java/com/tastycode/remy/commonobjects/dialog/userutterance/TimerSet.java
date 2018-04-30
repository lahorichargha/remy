package com.tastycode.remy.commonobjects.dialog.userutterance;

import org.joda.time.Duration;

import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class TimerSet extends TimerCommand
{
	public TimerSet(WhatWasSaid text) {
		super(text);
	}

	private Duration duration;
	private Step step;

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}
}
