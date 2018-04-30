package com.tastycode.remy.commonobjects.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * this is just a piece of text with a bunch of metadata and
 * things you can try to extract from it.
 * 
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class Step extends ThingWithText
{
    public static final Step UNKNOWN_STEP = new Step(-1, "UNKNOWN");
    
    private int stepNumber;
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();

    public Step(String text)
    {
        super(text);
        this.stepNumber = -1;
    }
    
    public Step(int stepNumber, String text)
    {
        super(text);
        this.stepNumber = stepNumber;
    }

    public Step(int stepNumber, String text, List<Ingredient> ingredients)
    {
        super(text);
        this.stepNumber = stepNumber;
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients()
    {
    	return Collections.unmodifiableList(ingredients);
    }
    
    public int getStepNumber()
    {
        return stepNumber;
    }
}
