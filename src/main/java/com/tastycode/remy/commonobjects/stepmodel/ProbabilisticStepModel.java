package com.tastycode.remy.commonobjects.stepmodel;

import java.util.List;

import com.tastycode.remy.commonobjects.domainmodel.StepProbability;
import com.tastycode.remy.commonobjects.recipe.Recipe;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class ProbabilisticStepModel
{
    private Recipe recipe;
    private int stepIndex;
    
    ProbabilisticStepModel(Recipe recipe)
    {
        this.recipe = recipe;
        stepIndex = 0;
    }    
    
    public List<StepProbability> getCurrentStepProbabilities()
    {
        return null;
    }
}
