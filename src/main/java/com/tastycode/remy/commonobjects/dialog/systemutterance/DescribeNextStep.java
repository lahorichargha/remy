package com.tastycode.remy.commonobjects.dialog.systemutterance;

import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class DescribeNextStep extends DescribeStep
{
    public DescribeNextStep(Step step)
    {
    	super(step);
    }

    /**
     * generate a numbered reference to a step
     */
    public String getWhatToSay()
    {
        return "The next step is: " + step.getText();
    }    

    @Override
    public String toString() {
        return getClass().getSimpleName() 
            + "[text='" + getWhatToSay() +"']";
    }

}
