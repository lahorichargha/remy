package com.tastycode.remy.reciperepository;

import static com.tastycode.remy.util.CookingUnits.BUNCH;
import static javax.measure.Measure.valueOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class TestRecipeRepository
{
    private static final String KALE_RECIPE = "kale chips";
    private static final String KALE_RECIPE_2 = "kale chips marvelous";
    
    @Test
    public void testStore()
    {
        InMemoryRecipeRepository recipes = new 
            InMemoryRecipeRepository();
        
        Recipe kale = recipeKale();
        
        recipes.storeRecipe(kale);
        
        Recipe recipeGot = recipes.getRecipe(KALE_RECIPE);
        
        assertThat(recipeGot, notNullValue());
    }

    public static Recipe recipeKale()
    {
        List<Step> steps = new ArrayList<Step>();
        steps.add(new Step("Preheat your oven to 350 degrees."));
        steps.add(new Step("Wash and cut up a bunch of kale."));
        steps.add(new Step("Place on a baking sheet and drizzle with olive oil."));
        steps.add(new Step("Top with seasonings you like — Mrs. Dash, salt, pepper, BBQ seasonings"));
        steps.add(new Step("Bake for 10 minutes"));

        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(new Ingredient(valueOf(1, BUNCH), "kale"));

        Recipe kale = new Recipe(KALE_RECIPE, steps, ingredients);
        
        return kale;
    }

    public static Recipe recipeKale2()
    {
//        Ingredients:
//            1-2 big bunches of kale*
//            1 tablespoon apple cider vinegar
//            1 tablespoon olive oil
//            Salt (to taste) or favorite seasoning blend (i.e. hot n' spicy Spike)

        List<Step> steps = new ArrayList<Step>();
        steps.add(new Step("Pre-heat oven to 250 degrees."));
        steps.add(new Step("Wash and de-stem kale."));
        steps.add(new Step("Chop or tear into \"chip\" size pieces and toss in a bowl with the rest of the ingredients to coat."));
        steps.add(new Step("Spread kale onto baking sheet."));
        steps.add(new Step("Bake for 15 minutes or until crispy. Serve immediately."));
        
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(new Ingredient(valueOf(1, BUNCH), "kale"));

        //recipe comment: 
        //Potato chip-lovers. Baked kale is a great swap for chips. 
        Recipe kale = new Recipe(KALE_RECIPE_2, steps, ingredients);
        
        return kale;

    }

}
