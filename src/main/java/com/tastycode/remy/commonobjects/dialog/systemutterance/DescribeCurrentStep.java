package com.tastycode.remy.commonobjects.dialog.systemutterance;

import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class DescribeCurrentStep extends DescribeStep
{
    public DescribeCurrentStep(Step currentStep)
    {
        super(currentStep);
    }

    /**
     * generate a numbered reference to a step
     */
    public String getWhatToSay()
    {
        return "Step " + step.getStepNumber() + ": "  + step.getText();
    }    
}
