package com.tastycode.remy.commonobjects.stepmodel;

import java.util.List;

import com.tastycode.remy.commonobjects.domainmodel.IngredientProbability;
import com.tastycode.remy.commonobjects.domainmodel.StepProbability;
import com.tastycode.remy.commonobjects.recipe.Step;

/**
 * models where the cook is in the recipe
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public interface StepModel
{
    public boolean isFinishedLastStep();
    public Step getLastStep();
    public void moveToNextStep();
    public Step getFirstStep();
    public Step getStepNumber(int stepNumber);
    public Step getNextStep();
    public Step getCurrentStep();
    public List<StepProbability> getCurrentStepProbabilities();
    public List<IngredientProbability> getCurrentIngredientProbabilities();
	public void moveToStep(int index);
	public void moveToStep(Step step);
	public void moveToPreviousStep();
}
