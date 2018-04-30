package com.tastycode.remy.commonobjects.stepmodel;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.commonobjects.recipe.Step;
import com.tastycode.remy.commonobjects.recipe.Tool;
import com.tastycode.remy.dialogmanager.testcases.recipes.RecipeDefinitions;
import com.tastycode.remy.reciperepository.HardcodedRecipeRepository;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class TestStepModel
{
    @Test
    public void testMoveToNext()
    {
        DiscreteStepModel stepModel = new DiscreteStepModel(HardcodedRecipeRepository.getOatCookie());
        
        Step current = stepModel.getCurrentStep();
        stepModel.moveToNextStep();
        Step next = stepModel.getCurrentStep();
        
        assertThat("should have advanced a step", 
            current, not(equalTo(next)));
    }

    @Test
    public void testFirst()
    {
        Recipe r = getRecipeWithSteps("s1", "s2");
        DiscreteStepModel stepModel = new DiscreteStepModel(r);
        Step firstStep = stepModel.getFirstStep();
        assertThat("should be the first step", 
            firstStep.getStepNumber(), equalTo(0));
        assertThat("should be the first step", 
            firstStep.getText(), equalTo("s1"));
    }

    public void testMovePrevious()
    {
//      public void moveToPreviousStep() {
        
    }
    
    //and test these
//    public Step getStepNumber(int stepNumber)
//    public void moveToStep(Step step) {
//    public void moveToStep(int index) {
//    public void moveToNextStep()
//    public Step getNextStep()
//    public Step getCurrentStep()
//    public Step getLastStep()
//    public boolean isFinishedLastStep()
    
    
    @Test
    public void testMoveToEnd()
    {
        Recipe r = getRecipeWithSteps("s1", "s2");
        DiscreteStepModel stepModel = new DiscreteStepModel(r);
 
        //move to step2
        stepModel.moveToNextStep();
        Step current = stepModel.getCurrentStep();
        assertFalse(stepModel.isFinishedLastStep());
        stepModel.moveToNextStep();
        Step next = stepModel.getCurrentStep();
        
        assertThat("can't advance past the end", 
            current, equalTo(next));
        
        assertTrue("and it is finished", 
            stepModel.isFinishedLastStep());
    }
    
    private Recipe getRecipeWithSteps(String step0, String step1)
    {
        List<Step> steps = new ArrayList<Step>();
        steps.add(new Step(0, step0));
        steps.add(new Step(1, step1));
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        List<Tool> tools = new ArrayList<Tool>();
        Recipe recipe = 
            new Recipe("testrecipe", steps, ingredients, tools);
        return recipe;
    }

}
