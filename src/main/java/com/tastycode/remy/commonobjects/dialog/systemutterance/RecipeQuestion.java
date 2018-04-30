package com.tastycode.remy.commonobjects.dialog.systemutterance;

import com.tastycode.remy.commonobjects.recipe.Ingredient;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class RecipeQuestion extends AbstractSystemUtterance
{
    private Ingredient aboutThisIngredient;

    public RecipeQuestion( Ingredient aboutThisIngredient )
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
