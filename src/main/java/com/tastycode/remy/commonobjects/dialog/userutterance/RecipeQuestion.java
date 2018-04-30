package com.tastycode.remy.commonobjects.dialog.userutterance;

import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class RecipeQuestion extends AbstractUserUtterance
{
	private Recipe recipe;
	private Ingredient ingredient;
	private Step step;
	
	public RecipeQuestion(WhatWasSaid text) {
		super(text);
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}
}
