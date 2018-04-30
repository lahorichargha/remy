package com.tastycode.remy.commonobjects;

import static com.tastycode.remy.util.CookingUnits.COUNT;
import static javax.measure.Measure.valueOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tastycode.remy.commonobjects.dialog.LiteralUserUtterance;
import com.tastycode.remy.commonobjects.dialog.UserUtterance;
import com.tastycode.remy.commonobjects.domainmodel.LikelyMatches;
import com.tastycode.remy.commonobjects.domainmodel.RecipeDomainModel;
import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.commonobjects.recipe.Step;
import com.tastycode.remy.commonobjects.recipe.Tool;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class TestMatchingIngredients
{
    @Test
    public void testEasyMatches()
    {
        checkIngredientMatches("carrots", 
            "cut the brocolli", "cut the carrots", 
            "brocolli", "carrots", 
            "How much carrots?", "easy", 2);

        checkIngredientMatches("brocolli", 
            "cut the brocolli", "cut the carrots", 
            "brocolli", "carrots", 
            "How much brocolli?", "easy", 2);
    }

    @Test
    public void testCurrentStepMatters()
    {
        final int CURRENT_STEP = 1;

        //This doesn't really test it,
        //but there really should be two amounts
        //of carrots and it should return the second one.
        checkIngredientMatches("carrots", 
            "steam the carrots and brocolli", "cut the brocolli and carrots", 
            "brocolli", "carrots", 
            "How much carrots?", 
            "carrots is in both, but the current step is" +
            " to cut so return that one", CURRENT_STEP);
    }

    private void checkIngredientMatches(String ingredientShouldMatch, 
        String step0, String step1, 
        String ingredient0, String ingredient1, 
        String utterance, String why, int advanceStepThisManyTimes)
    {
        Recipe r = getRecipeWithIngredients(step0, step1, 
            ingredient0, ingredient1);
        RecipeDomainModel model = new RecipeDomainModel(r);
        UserUtterance utter = new LiteralUserUtterance(utterance);
        
        for (int i = 0; i < advanceStepThisManyTimes; i++)
        {
            model.getStepModel().moveToNextStep();
        }
        
        LikelyMatches matcher = model.getLikelyMatches();
        List<Ingredient> ingredientMatches = 
            matcher.getLikelyIngredientMatches(utter);
        
        assertThat("failed because of: " + why, 
            ingredientMatches.get(0).getIngredient(),
            equalTo(ingredientShouldMatch));
    }
    
    private Recipe getRecipeWithIngredients(
        String step0, String step1,
        String ingredient0, String ingredient1)
    {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(new Ingredient(valueOf(6, COUNT), ingredient0));
        ingredients.add(new Ingredient(valueOf(5, COUNT), ingredient1));
        
        List<Step> steps = new ArrayList<Step>();
        steps.add(new Step(0, step0));
        steps.add(new Step(1, step1));
        steps.add(new Step(2, "")); //add an empty step so I can disable current step checking

        List<Tool> tools = new ArrayList<Tool>();
        
        Recipe recipe = 
            new Recipe("testrecipe", steps, 
                ingredients, tools);
        
        return recipe;
        
    }

}

