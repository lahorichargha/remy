package com.tastycode.remy.commonobjects.stepmodel;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tastycode.remy.commonobjects.domainmodel.IngredientProbability;
import com.tastycode.remy.commonobjects.domainmodel.StepProbability;
import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.commonobjects.recipe.Step;

/**
 * model of what the current step is. Currently 
 * this implements an explicit next step thing
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class DiscreteStepModel implements StepModel
{
    private static final Logger logger = Logger
                    .getLogger(DiscreteStepModel.class.getName());
    private Recipe recipe;
    private int stepIndex;
    
    public DiscreteStepModel(Recipe recipe)
    {
        this.recipe = recipe;
        stepIndex = 0;
        logger.debug("first step: " + getCurrentStep());
    }
    
    public Step getFirstStep()
    {
        return getStepNumber(0);
    }    
    
    public Step getStepNumber(int stepNumber)
    {
        return recipe.getSteps().get(stepNumber);
    }
    
    public void moveToStep(Step step) {
    	moveToStep(findStepIndex(step));
    }

	public void moveToPreviousStep() {
		moveToStep(stepIndex - 1);
	}

	public void moveToStep(int index) {
		this.stepIndex = index;
		if (stepIndex < 0) {
			stepIndex = 0;
		}
		
        logger.debug("the next step is now: " + getCurrentStep());
	}
    
    protected int findStepIndex(Step step) {
		return recipe.getSteps().indexOf(step);
	}

	public void moveToNextStep()
    {
		moveToStep(stepIndex + 1);
    }
    
    public Step getNextStep()
    {
        Step nextStep;
        if (stepIndex < recipe.getSteps().size() - 1)
        {
            nextStep = recipe.getSteps().get(stepIndex + 1);
        }
        else if (isFinishedLastStep())
        {
            nextStep = getLastStep();
        }
        else
        {
            nextStep = recipe.getSteps().get(stepIndex);
        }
        return nextStep;
    }

    public Step getCurrentStep()
    {
        Step currentStep;
        if (!isFinishedLastStep())
        {
            currentStep = recipe.getSteps().get(stepIndex);
        }
        else
        {
            currentStep = getLastStep();
        }
        return currentStep;
    }
    
    public Step getLastStep()
    {
        return recipe.getSteps().get(recipe.getSteps().size() - 1);
    }
    
    public boolean isFinishedLastStep()
    {
        return stepIndex >= recipe.getSteps().size();
    }
    
    public List<StepProbability> getCurrentStepProbabilities()
    {
        List<StepProbability> probs = new ArrayList<StepProbability>();
        probs.add(new StepProbability(getCurrentStep(), 1.0));
        return probs;
    }

    public List<IngredientProbability> getCurrentIngredientProbabilities()
    {
        logger.debug("looking for current ingredients, the current step is: " + 
            getCurrentStep().getText() + " it has " + getCurrentStep().getIngredients().size() + 
            " ingredients");
        List<IngredientProbability> probs = 
            new ArrayList<IngredientProbability>();
        for (Ingredient ingredient : getCurrentStep().getIngredients())
        {
            probs.add(new IngredientProbability(ingredient, 1.0));
        }
        return probs;
    }
}
