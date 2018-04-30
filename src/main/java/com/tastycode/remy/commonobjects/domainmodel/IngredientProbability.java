package com.tastycode.remy.commonobjects.domainmodel;

import com.tastycode.remy.commonobjects.recipe.Ingredient;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class IngredientProbability extends ThingWithProbability<Ingredient>
{
    public IngredientProbability(Ingredient ingredient, double probability)
    {
        super(ingredient, probability);
    }
    
}
