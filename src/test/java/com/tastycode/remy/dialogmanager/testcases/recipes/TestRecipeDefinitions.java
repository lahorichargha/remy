package com.tastycode.remy.dialogmanager.testcases.recipes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Test;

import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.reciperepository.HardcodedRecipeRepository;

public class TestRecipeDefinitions {

	
	@Test public void findOatmeal() {
		List<Recipe> recipes = HardcodedRecipeRepository.get("oat");
		assertThat(recipes.size(), equalTo(1));
		assertThat(recipes.get(0).getName(), equalTo("OatCookies"));
	}
	
	@Test public void getAll() {
		List<Recipe> recipes = HardcodedRecipeRepository.getAll();
		// Regression: Update when this fails due to adding a new recipe
		assertThat(recipes.size(), equalTo(3));
	}
}
