package com.tastycode.remy.commonobjects.dialog.systemutterance;

import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class CurrentStepAnswer extends AbstractSystemUtterance
{
    private Step step;

    public CurrentStepAnswer(Step step)
    {
        this.step = step;
    }
    
    public Step getStep()
    {
        return step;
    }
    
    public String toString()
    {
        return step.toString();
    }
}
