package com.tastycode.remy.reciperepository;

import static com.tastycode.remy.util.CookingUnits.COUNT;
import static com.tastycode.remy.util.CookingUnits.CUP;
import static com.tastycode.remy.util.CookingUnits.ONE;
import static com.tastycode.remy.util.CookingUnits.TABLESPOON;
import static com.tastycode.remy.util.CookingUnits.TEASPOON;
import static javax.measure.Measure.valueOf;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.ReflectionUtils;

import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.commonobjects.recipe.Step;
import com.tastycode.remy.commonobjects.recipe.Tool;

/**
 * 
 * 
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class HardcodedRecipeRepository implements RecipeRepository {
	public static final String OAT_COOKIE = "OatCookies";
	public static final String CHOC_COOKIE = "Chocolate Cookies";
	public static final String RATA = "Ratatouille";

	public List<Recipe> getRecipies() {
		return Arrays.asList(getChocolateCookie(), getOatCookie(),
				getRatatouille());
	}

	public Recipe getRecipe(String name) {
		for (Recipe recipe : getRecipies()) {
			if (recipe.getName().equalsIgnoreCase(name)) {
				return recipe;
			}
		}
		return null;
	}

	public static Recipe getChocolateCookie() {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();

		Ingredient wFlour = new Ingredient(valueOf(1, CUP),
				"unbleached white flour");
		ingredients.add(wFlour);
		Ingredient sugar = new Ingredient(valueOf(1, CUP), "sugar");
		ingredients.add(sugar);
		Ingredient choc = new Ingredient(valueOf(1, CUP), "chocolate chips");
		ingredients.add(choc);
		Ingredient coco = new Ingredient(valueOf(0.75, CUP),
				"unsweetened cocoa powder");
		ingredients.add(coco);
		Ingredient bs = new Ingredient(valueOf(0.5, TEASPOON), "baking soda");
		ingredients.add(bs);
		Ingredient salt = new Ingredient(valueOf(.125, TEASPOON), "salt");
		ingredients.add(salt);
		Ingredient marg = new Ingredient(valueOf(0.5, CUP), "margarine");
		ingredients.add(marg);
		Ingredient egg = new Ingredient(valueOf(1, ONE), "egg replacer");
		ingredients.add(egg);
		Ingredient van = new Ingredient(valueOf(1.0, TEASPOON),
				"vanilla extract");
		ingredients.add(van);
		Ingredient psugar = new Ingredient(valueOf(0.5, TEASPOON),
				"powdered sugar");
		ingredients.add(psugar);

		List<Step> steps = new ArrayList<Step>();
		steps.add(new Step(0, "Preheat oven to 350."));
		steps.add(new Step(1,
				"Gather your ingredients and lightly grease a cookie sheet."));
		steps.add(new Step(2, "Mix sugar, " + marg + " and " + van
				+ " in a medium to large mixing bowl.", Arrays.asList(sugar,
				marg, van)));
		steps.add(new Step(3, "Mix flour, " + coco + ", " + bs + "," + salt
				+ " and " + egg + " together in a smaller mixing bowl.", Arrays
				.asList(wFlour, coco, bs, salt)));
		steps
				.add(new Step(4,
						"Slowly pour dry ingredients into wet ingredients while stirring."));
		steps.add(new Step(5, "Add " + choc + ", stir.", Arrays.asList(choc)));
		steps.add(new Step(6,
				"Drop spoonfuls of the dough onto greased cookie sheet."));
		steps.add(new Step(7, "Sprinkle powdered sugar on top of cookies",
				Arrays.asList(psugar)));
		steps.add(new Step(8, "Bake for 12-15 minutes each batch."));

		List<Tool> tools = new ArrayList<Tool>();

		Recipe recipe = new Recipe(CHOC_COOKIE, steps, ingredients, tools);
		return recipe;
	}

	public static Recipe getRatatouille() {

		List<Step> steps = new ArrayList<Step>();
		steps.add(new Step(0, "drain the canned tomatos. "));
		steps.add(new Step(1, "Wash and scrub all the vegetables. "));
		steps.add(new Step(2,
				"Remove the parsley and basil leaves from their stems"));
		steps
				.add(new Step(3,
						"Dice the green bell pepper, eggplant, and onion"));
		steps
				.add(new Step(4,
						"Quarter the zucchini lengthwise then slice into 1/4-in. (1/2 cm) segments. "));
		steps.add(new Step(5, "Quarter the mushrooms"));
		steps.add(new Step(6, "Chop the parsley and the basil."));

		steps
				.add(new Step(7,
						"Start cooking by heating olive oil in a large pot over medium heat."));
		steps
				.add(new Step(
						8,
						"Add the minced garlic and saute until the garlic smell intensifies, about one minute."));
		steps
				.add(new Step(
						9,
						"Add the diced onion and continue to saute until they turn translucent, about 4 more minutes."));
		steps
				.add(new Step(
						10,
						"Once the onions are translucent, add 1 Tbs. tomato paste. The tomato paste will be in a clump and will take a bit of stirring and pressing to get it to spread out and cover the onions and garlic."));
		steps
				.add(new Step(
						11,
						"As you work at spreading the paste out and mixing it with the onions and garlic, the paste will cook and darken in color. Once some of the paste starts to stick to the pan and brown, it's time to add the stock (about one minute)."));
		steps
				.add(new Step(
						12,
						"Pour in 3/4 cup chicken or vegetable stock and stir until the broth begins to simmer. Using your spatula or utensil, scrub the bottom of the pan to release any browned bits of garlic, onion, or tomato paste."));
		steps
				.add(new Step(
						13,
						"Add the diced eggplant, zucchini, bell peppers, and mushrooms. Stir to combine thoroughly and cook for 10 to 12 minutes, stirring every couple minutes to promote even heating."));
		steps
				.add(new Step(
						14,
						"The eggplant will release a lot of liquid (slowly) into the pot and it's in this liquid that you'll want to simmer the other ingredients in. To evenly cook all the ingredients, you'll have to stir it to make sure the vegetables spend time touching eggplant liquid. As a bonus, the flavors mix amazingly well during this process."));
		steps
				.add(new Step(
						15,
						"The eggplant will mostly be falling apart at this point, but the zucchini, bell peppers, and mushrooms should be tender but not yet mushy."));
		steps
				.add(new Step(
						16,
						"Add the diced tomatoes and stir in. After about a minute, the tomatoes will have heated through. Turn the heat down to the lowest setting."));
		steps
				.add(new Step(17,
						"Stir in the chopped parsley and basil. Add salt and pepper to taste."));

		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(new Ingredient(valueOf(6, COUNT), "garlic cloves"));
		ingredients.add(new Ingredient(valueOf(5, COUNT), "medium mushrooms"));
		ingredients.add(new Ingredient(valueOf(5, COUNT),
				"sprigs of italian parsley"));
		ingredients.add(new Ingredient(valueOf(4, COUNT), "sprigs of basil"));
		ingredients.add(new Ingredient(valueOf(1, COUNT), "medium onion"));
		ingredients.add(new Ingredient(valueOf(1, COUNT), "can diced tomatos"));
		ingredients.add(new Ingredient(valueOf(0.75, CUP), "vegetable stock"));
		ingredients.add(new Ingredient(valueOf(1, TABLESPOON), "tomato paste"));
		ingredients.add(new Ingredient(valueOf(1, COUNT),
				"medium green bell pepper"));
		ingredients.add(new Ingredient(valueOf(1, COUNT), "large eggplant"));

		List<Tool> tools = new ArrayList<Tool>();

		Recipe recipe = new Recipe(RATA, steps, ingredients, tools);

		return recipe;

	}

	public static Recipe getOatCookie() {
		List<Step> steps = new ArrayList<Step>();
		steps.add(new Step(0,
				"Mix the vegan brown sugar, vegan sugar, oil, and water."));
		steps.add(new Step(1, "Add the banana and vanilla. "));
		steps
				.add(new Step(
						2,
						"then mix in the already pre-mixed together mixture of flour, baking soda, cinnamon, and salt."));
		steps.add(new Step(3, "Stir in the oats."));
		steps
				.add(new Step(4,
						"If it is too thin, let stand a bit so the oats can absorb the excess liquid."));

		steps
				.add(new Step(5,
						"Bake at 350 degrees Fahrenheit for 13 minutes."));
		steps
				.add(new Step(
						6,
						"To check if they are done, flip one over and it should be browned.  Rip off a chunk to see it it is still raw.  I usually let them sit a bit before I take them off the cookie sheet because, well, I do not really know, but it makes me not cook them too long because they finish cooking outside of the oven.  I should mention to drop them by the tablespoon."));

		List<Ingredient> ingredients = new ArrayList<Ingredient>();

		ingredients.add(new Ingredient(valueOf(1, ONE), "mashed banana"));
		ingredients.add(new Ingredient(valueOf(1, CUP), "brown sugar"));
		ingredients.add(new Ingredient(valueOf(0.5, CUP), "regular sugar"));
		ingredients.add(new Ingredient(valueOf(0.33, CUP), "oil"));
		ingredients.add(new Ingredient(valueOf(1.5, TEASPOON),
				"vanilla extract"));
		ingredients.add(new Ingredient(valueOf(1.5, TEASPOON), "cinnamon"));
		ingredients.add(new Ingredient(valueOf(1.5, CUP), "flour"));
		ingredients.add(new Ingredient(valueOf(1, TEASPOON), "baking soda"));
		ingredients.add(new Ingredient(valueOf(0.5, TEASPOON), "salt"));
		ingredients.add(new Ingredient(valueOf(2.25, CUP), "oats"));
		ingredients.add(new Ingredient(valueOf(3, TABLESPOON), "water"));

		List<Tool> tools = new ArrayList<Tool>();

		Recipe recipe = new Recipe(OAT_COOKIE, steps, ingredients, tools);
		return recipe;
	}

	public static List<Recipe> getAll() {
		ReturnCollectingMethodCallback recipeCollector = new ReturnCollectingMethodCallback(
				Recipe.class);
		ReflectionUtils.doWithMethods(HardcodedRecipeRepository.class, recipeCollector,
				new ModifierMethodMatcher(Modifier.STATIC));
		return new ArrayList<Recipe>(recipeCollector.getResultMap().values());
	}

	public static List<Recipe> get(String... names) {
		ArrayList<Recipe> matched = new ArrayList<Recipe>();
		List<Recipe> recipes = getAll();
		for (Recipe recipe : recipes) {
			if (matches(recipe, names)) {
				matched.add(recipe);
			}
		}
		return matched;
	}

	private static boolean matches(Recipe recipe, String[] names) {
		String recipeName = recipe.getName().toLowerCase();
		for (String name : names) {
			if (recipeName.contains(name.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	private static final class ReturnCollectingMethodCallback<T> implements
			ReflectionUtils.MethodCallback {
		private Class<?> clazz;
		private Map<String, T> results = new HashMap<String, T>();

		public ReturnCollectingMethodCallback(Class<Recipe> class1) {
			clazz = class1;
		}

		public void doWith(Method method) throws IllegalArgumentException,
				IllegalAccessException {
			if (method.getReturnType().isAssignableFrom(clazz)
					&& method.getParameterTypes().length == 0) {
				Object result = ReflectionUtils.invokeMethod(method, null);
				if (result != null) {
					results.put(method.getName(), (T) result);
				}
			}
		}

		public Map<String, T> getResultMap() {
			return Collections.unmodifiableMap(results);
		}
	}

	private static final class ModifierMethodMatcher implements
			ReflectionUtils.MethodFilter {
		int[] expectedModifiers;

		public ModifierMethodMatcher(int... modifiers) {
			this.expectedModifiers = modifiers;
		}

		public boolean matches(Method method) {
			int modifiers = method.getModifiers();
			for (int mod : expectedModifiers) {
				if ((modifiers & mod) == 0) {
					return false;
				}
			}
			return true;
		}
	}
}
