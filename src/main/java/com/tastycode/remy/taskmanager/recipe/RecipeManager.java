package com.tastycode.remy.taskmanager.recipe;

import com.tastycode.remy.commonobjects.dialog.systemutterance.CurrentStepAnswer;
import com.tastycode.remy.commonobjects.dialog.systemutterance.SystemUtterance;
import com.tastycode.remy.commonobjects.dialog.userutterance.RecipeQuestion;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.commonobjects.recipe.Step;
import com.tastycode.remy.taskmanager.TaskManager;

public class RecipeManager implements TaskManager<RecipeQuestion> {
	Recipe recipe;
	
	public SystemUtterance respond(RecipeQuestion userUtter) {
        //match what was said, to each part of the recipe.
        //the ingredients, the steps, and the tools.
        //find the highest matching line and then talk about that
        String wasSaid = userUtter.getText();

        //which ingredient?
        
        //which step?
        Step likelyStep = whichStep(userUtter);
        
        CurrentStepAnswer sysUtter = new CurrentStepAnswer(likelyStep);
        
        return sysUtter;
	}
	
    private Step whichStep(RecipeQuestion userUtter)
    {
    	userUtter.getStep();
        return null;
    }
}
