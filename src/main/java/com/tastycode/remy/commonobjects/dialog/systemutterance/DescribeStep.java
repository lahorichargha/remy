package com.tastycode.remy.commonobjects.dialog.systemutterance;

import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class DescribeStep implements SystemUtterance
{
    protected Step step;
    
    public DescribeStep(Step step)
    {
        this.step = step;
    }

    /**
     * generate a numbered reference to a step
     */
    public String getWhatToSay()
    {
//        return "Step: " + step.getStepNumber() + " is " + 
        return step.getText();
    }    

    @Override
    public String toString() {
        return getClass().getSimpleName() 
            + "[text='" + getWhatToSay() +"']";
    }

}
