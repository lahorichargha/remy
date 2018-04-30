package com.tastycode.remy.commonobjects.domainmodel;

import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class StepProbability extends ThingWithProbability<Step>
{
    public StepProbability(Step step, double probability)
    {
        super(step, probability);
    }
    
    public Step getStep()
    {
        return getThing();
    }
    
}
