package com.tastycode.remy.commonobjects.dialog.systemutterance;

import com.tastycode.remy.commonobjects.recipe.Ingredient;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class DescribeIngredient implements SystemUtterance
{
    private Ingredient ingredient;
    
    public DescribeIngredient(Ingredient ingredient)
    {
        this.ingredient = ingredient;
    }

    /**
     * generate a numbered reference to a step
     */
    public String getWhatToSay()
    {
        return ingredient.getText();
    }    

    @Override
    public String toString() {
        return getClass().getSimpleName() 
            + "[text='" + getWhatToSay() +"']";
    }

}
