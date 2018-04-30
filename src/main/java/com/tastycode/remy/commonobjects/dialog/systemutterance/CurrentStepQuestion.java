package com.tastycode.remy.commonobjects.dialog.systemutterance;

import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class CurrentStepQuestion extends AbstractSystemUtterance
{
    private Step step;

    public CurrentStepQuestion(Step step)
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
