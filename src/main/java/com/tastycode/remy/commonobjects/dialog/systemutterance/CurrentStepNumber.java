package com.tastycode.remy.commonobjects.dialog.systemutterance;

import com.tastycode.remy.commonobjects.recipe.Step;

public class CurrentStepNumber extends DescribeStep {

	public CurrentStepNumber(Step step) {
		super(step);
	}

	@Override
	public String getWhatToSay() {
		return "You are currently on step " + step.getStepNumber();
	}
}
