package com.tastycode.remy.reciperepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 */
public class InMemoryRecipeRepository implements RecipeRepository
{
    private Map<String, Recipe> recipes;

    public InMemoryRecipeRepository()
    {
        recipes = new HashMap<String, Recipe>();
        
        List<Step> steps = new ArrayList<Step>();
        steps.add(new Step("Preheat your oven to 350 degrees."));
        steps.add(new Step("Wash and cut up a bunch of kale."));
        steps.add(new Step("Place on a baking sheet and drizzle with olive oil."));
        steps.add(new Step("Top with seasonings you like — Mrs. Dash, salt, pepper, BBQ seasonings"));
        steps.add(new Step("Bake for 10 minutes"));
        Recipe kale = new Recipe("kale chips", steps, new ArrayList<Ingredient>());
        storeRecipe(kale);
    }
    
    public List<Recipe> getRecipies()
    {
        return new ArrayList<Recipe>(recipes.values());
    }
    
    public void getRecipeWithIngredient(String ingredient)
    {
        // TODO Auto-generated method stub
        
    }
    
    public void storeRecipe(Recipe recipe)
    {
        recipes.put(recipe.getName(), recipe);
    }
    
    public Recipe getKale()
    {
        return recipes.get("kale chips");
    }
    
    public Recipe getRecipe(String name)
    {
        return recipes.get(name);
    }
}
