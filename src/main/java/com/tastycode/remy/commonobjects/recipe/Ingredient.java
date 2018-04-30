package com.tastycode.remy.commonobjects.recipe;

import java.text.NumberFormat;

import javax.measure.Measure;
import javax.measure.MeasureFormat;
import javax.measure.quantity.Quantity;

import com.tastycode.remy.util.CookingUnitFormat;

/**
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class Ingredient extends ThingWithText
{
    private Measure<? extends Number, ? extends Quantity> amount;
    private String ingredient;
    
    public <E> Ingredient(Measure<? extends Number, ? extends Quantity> amount,
        String ingredient)
    {
        super(ingredient);
        this.amount = amount;
        this.ingredient = ingredient;
    }

    public Measure<? extends Number, ? extends Quantity> getAmount()
    {
        return amount;
    }
    
    public String getIngredient()
    {
        return ingredient;
    }

    @Override
    public String getText()
    {
        return toString();
    }
    
    public String toString()
    {
        return format(amount) + " " + ingredient;
    }

	protected String format(Measure<? extends Number, ? extends Quantity> amount) {
		return getFormatter().format(amount);
	}

	protected MeasureFormat getFormatter() {
		return MeasureFormat.getInstance(NumberFormat.getNumberInstance(), CookingUnitFormat.DEFAULT);
	}
}
