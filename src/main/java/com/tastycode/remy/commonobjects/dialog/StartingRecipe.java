package com.tastycode.remy.commonobjects.dialog;

import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.dialogmanager.WhatToSayLiterally;

public class StartingRecipe extends WhatToSayLiterally {

	public StartingRecipe(Recipe recipe) {
		super("Starting recipe: " + recipe.getName());
	}

}
