package com.tastycode.remy.gui;

import java.util.List;

import org.apache.log4j.Logger;

import com.tastycode.remy.commonobjects.dialog.SayNothing;
import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaidLiterally;
import com.tastycode.remy.commonobjects.domainmodel.IngredientProbability;
import com.tastycode.remy.commonobjects.domainmodel.StepProbability;
import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.commonobjects.recipe.Step;
import com.tastycode.remy.dialogmanager.DialogManager;
import com.tastycode.remy.dialogmanager.RemyDialog;
import com.tastycode.remy.reciperepository.HardcodedRecipeRepository;
import com.tastycode.remy.reciperepository.RecipeRepository;
import com.tastycode.remy.taskmanager.timer.TimerInfo;

/**
 * model needed for the GUI
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class RemyModel
{
    /* log4j logger instance */
    private static final Logger logger = Logger.getLogger(RemyModel.class
                    .getName());
    
    private DialogManager dialogManager;
    private RecipeRepository recipeRepository;
    private SpokenDialogTranscriber transcriber;
    
    public RemyModel(RemyDialog dialog)
    {
    	this.dialogManager = dialog;
        this.recipeRepository = new HardcodedRecipeRepository();
        transcriber = new SpokenDialogTranscriber();
        dialog.getSpeaker().setObserver(transcriber);
    }
    
    /**
     * retrieve the entire speech dialog
     */
    public String getSpeakingDialogTranscript()
    {
        return transcriber.getTranscription();
    }
    
    public String [] getRecipes()
    {
        List<Recipe> recipies = recipeRepository.getRecipies();
        String [] rec = new String[recipies.size()];
        int i = 0;
        for (Recipe recipe : recipeRepository.getRecipies())
        {
            rec[i] = recipe.getName();
            i++;
        } 
        return rec;
    }
    
    public void setRecipe(String recipe)
    {
        // TODO this will stop speech input 
        Recipe thisRecipe = recipeRepository.getRecipe(recipe);
        dialogManager.getModel().setRecipe(HardcodedRecipeRepository.get(recipe).get(0));
//        this.dialogManager = 
//            new RemyDialog(thisRecipe);
    }

    /**
     * return what Remy said
     */
    public String say(String thingToSay)
    {
        logger.debug("trying to say: " + thingToSay);
        WhatToSay remySaid = 
            dialogManager.getWhatToSayNext(thingToSay);
        return remySaid.getText();
    }

    public String getStepDescription()
    {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (Step step : dialogManager.getModel().getRecipe().getSteps())
        {
            sb.append("Step ").append(i).append(": ");
            sb.append(step.toString()).append("\n");
            i++;
        }
        
        return sb.toString();
    }
    
    public String getCurrentStepDescription()
    {
        StringBuffer sb = new StringBuffer();
        
        List<StepProbability> probs = 
            dialogManager.getModel().getStepModel().getCurrentStepProbabilities();
        
        for (StepProbability stepProbability : probs)
        {
            Step step = stepProbability.getStep();
            double prob = stepProbability.getProbability();
            
            sb.append(step.toString()).append("\n"); 
            
        }
        return sb.toString();
    }
    
    public String getIngredientDescription()
    {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (Ingredient ingredient : dialogManager.getModel().getRecipe().getIngredients())
        {
            sb.append(i).append(": ");
            sb.append(ingredient.toString()).append("\n");
            i++;
        }
        
        return sb.toString();
    }
    
    public String getTimersDescription()
    {
        StringBuffer sb = new StringBuffer();
        
        List<TimerInfo> timers = 
            dialogManager.getModel().getTimerModel().getTimers();
        
        if (timers.size() > 0)
        {
            sb.append("timers: ");
            for (TimerInfo timerInfo : timers)
            {
                sb.append("timer: ");
                sb.append(timerInfo.getPrintableRepresentation());
                sb.append("\n");
            }
        }
        else
        {
            sb.append("no timers set");
        }
        return sb.toString();
    }

    public String getCurrentIngredientDescription()
    {
        StringBuffer sb = new StringBuffer();
        List<IngredientProbability> probs = 
            dialogManager.getModel().getStepModel().getCurrentIngredientProbabilities();
        
        for (IngredientProbability iProb : probs)
        {
            Ingredient ingredient = iProb.getThing();
            sb.append(ingredient.toString()).append(",");
        }
        
        return sb.toString();
    }

	public String getCurrentRecipeName() {
		return dialogManager.getModel().getRecipe().getName();
	}

}
