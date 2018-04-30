package com.tastycode.remy.commonobjects.dialog.systemutterance;

import com.tastycode.remy.commonobjects.recipe.Ingredient;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class RecipeAnswer extends AbstractSystemUtterance
{
    private Ingredient aboutThisIngredient;

    public RecipeAnswer( Ingredient aboutThisIngredient )
    {
        super();
        this.aboutThisIngredient = aboutThisIngredient;
    }

    public Ingredient getAboutThisIngredient()
    {
        return aboutThisIngredient;
    }



    public String toString()
    {
        return aboutThisIngredient.toString();
    }
}
