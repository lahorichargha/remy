package com.tastycode.remy.dialogmanager.taskmanager.step;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.Ignore;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.domainmodel.RecipeDomainModel;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.commonobjects.recipe.Step;
import com.tastycode.remy.dialogmanager.DialogManager;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
@Ignore
public class TestNextStep
{
    @Test
    public void testGetNextStep()
    {
        runRecipe("/context/Oat-context.xml", "What's next");
        runRecipe("/context/Ratatouille-context.xml", "What's next");
    }
    
    private void runRecipe(String recipeContextFile, String toSay) {
    	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"/context/Remy-context.xml", recipeContextFile});
    	
        DialogManager dm = (DialogManager) context.getBean("dialog");
        RecipeDomainModel model = (RecipeDomainModel) context.getBean("model");
        Recipe recipe = model.getRecipe();
        
        int numSteps = recipe.getSteps().size();
        for (int i = 0; i < numSteps - 1; i++) //don't ask next if there is no next
        {
            System.out.println("In step: " + recipe.getSteps().get(i));
            System.out.println("Asking:  " + toSay);
            System.out.println("Expect:  " + recipe.getSteps().get(i + 1));
            WhatToSay next = 
            	dm.getWhatToSayNext(toSay);
            
            Step step = recipe.getSteps().get(i + 1);
            
            assertThat(next.getText(), 
                containsString(step.getText()));
        }
	context.close();
        // Sleep enough time for context to close properly
        try {
		Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

}
