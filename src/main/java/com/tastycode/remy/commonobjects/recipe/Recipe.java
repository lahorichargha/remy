package com.tastycode.remy.commonobjects.recipe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class Recipe
{
    private String name;
    private List<Step> steps;
    private List<Ingredient> ingredients;

    public Recipe(String name, List<Step> steps, 
        List<Ingredient> ingredients)
    {
        this(name, steps, ingredients, new ArrayList<Tool>());
    }

    public Recipe(String name, List<Step> steps, 
        List<Ingredient> ingredients, List<Tool> tools)
    {
        this.name = name;
        this.steps = steps;
        this.ingredients = ingredients;
    }

    public String getName()
    {
        return name;
    }
    
    public List<Step> getSteps()
    {
        return steps;
    }

    public List<Ingredient> getIngredients()
    {
        return ingredients;
    }
    
}
