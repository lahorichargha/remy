package com.tastycode.remy.commonobjects.dialog.systemutterance;

import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class FinishedLastStep extends DescribeStep
{
    public FinishedLastStep(Step lastStep)
    {
    	super(lastStep);
    }

    /**
     * generate a numbered reference to a step
     */
    public String getWhatToSay()
    {
        return "You finished the last step which is " + step.getText();
    }    

    @Override
    public String toString() {
        return getClass().getSimpleName() 
            + "[text='" + getWhatToSay() +"']";
    }

}
