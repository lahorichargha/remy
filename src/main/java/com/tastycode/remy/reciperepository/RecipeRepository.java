package com.tastycode.remy.reciperepository;

import java.util.List;

import com.tastycode.remy.commonobjects.recipe.Recipe;

/**
 *
 */
public interface RecipeRepository
{
    public List<Recipe> getRecipies();

    public Recipe getRecipe(String name);
//    
//    public void storeRecipe(Recipe recipe);
//
//    public void getRecipeWithIngredient(String ingredient);
    
}
