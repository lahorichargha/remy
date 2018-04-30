package com.tastycode.remy.commonobjects.dialog.systemutterance;

import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class DescribeFirstStep extends DescribeStep
{
    public DescribeFirstStep(Step step)
    {
        super(step);
    }

    /**
     * generate a numbered reference to a step
     */
    public String getWhatToSay()
    {
        return "The first step is: " + step.getText();
    }    

    @Override
    public String toString() {
        return getClass().getSimpleName() 
            + "[text='" + getWhatToSay() +"']";
    }

}
