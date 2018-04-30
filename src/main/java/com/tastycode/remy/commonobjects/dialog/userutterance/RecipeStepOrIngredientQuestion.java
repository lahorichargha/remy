package com.tastycode.remy.commonobjects.dialog.userutterance;

import java.util.Arrays;
import java.util.List;

import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class RecipeStepOrIngredientQuestion extends AbstractUserUtterance
{
	public enum QueryType {
		AMOUNT,
		WHEN,
		ALL {
			@Override
			public boolean shouldReturn(QueryType arg0) {
				return true;
			}
		};
		
		// Default, override for special cases
		public boolean shouldReturn(QueryType type) {
			return this.equals(type);
		}
	};
	
    private List<Step> steps;
    private List<Ingredient> ingredients;
	private QueryType queryType;
    
    public RecipeStepOrIngredientQuestion(WhatWasSaid text, 
        Step theStep, Ingredient theIngredient) 
    {
        super(text);
        this.ingredients = Arrays.asList(theIngredient);
        this.steps = Arrays.asList(theStep);
    }
    
    public RecipeStepOrIngredientQuestion( WhatWasSaid whatWasSaid,
        List<Step> steps, List<Ingredient> ingredients )
    {
        super(whatWasSaid);
        this.steps = steps;
        this.ingredients = ingredients;
    }



    public boolean hasSteps()
    {
        return getQueryType().shouldReturn(QueryType.WHEN) 
        		&& getSteps().size() > 0;
    }

    public boolean hasIngredient()
    {
        return getQueryType().shouldReturn(QueryType.AMOUNT) 
        	&& getIngredients().size() > 0;
    }

    public List<Step> getSteps()
    {
        return steps;
    }

    public List<Ingredient> getIngredients()
    {
        return ingredients;
    }
    
    public Step getLikelyStep()
    {
        return getSteps().get(0);
    }

    public Ingredient getLikelyIngredient()
    {
        return getIngredients().get(0);
    }

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}
	
	public QueryType getQueryType() {
		return queryType;
	}
}
