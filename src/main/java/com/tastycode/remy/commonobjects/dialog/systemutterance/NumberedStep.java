package com.tastycode.remy.commonobjects.dialog.systemutterance;

import com.tastycode.remy.commonobjects.recipe.Step;

public class NumberedStep extends DescribeStep {

	public NumberedStep(Step step) {
		super(step);
	}
	
	@Override
	public String getWhatToSay() {
		return "Step " + step.getStepNumber() + ": " + step.getText();
	}

}
