package com.tastycode.remy.dialogmanager;

import org.junit.Ignore;
import org.junit.Test;

import com.tastycode.remy.dialogmanager.testcases.recipes.RecipeDefinitions;
import com.tastycode.remy.reciperepository.HardcodedRecipeRepository;


/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class TestSpeaker
{
    @Ignore
    @Test
    public void runRecipe()
    {
        SpeakerAndListener speaker = new Speaker();
        speaker.startDialog(HardcodedRecipeRepository.getOatCookie());
        
    }

}
