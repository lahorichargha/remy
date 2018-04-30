package com.tastycode.remy.dialogmanager.testcases.recipes;

import java.util.ArrayList;
import java.util.List;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialParameterValue;

import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.reciperepository.HardcodedRecipeRepository;

/**
 *  1 mashed banana
    1 cup of brown sugar
    1/2 of a cup of regular sugar
    slightly less than 1/3 of a cup (about 1/4 of a cup plus 1 teaspoon) of oil
    1 1/2 teaspoons of vanilla extract
    1 1/2 teaspoons of cinnamon
    1 1/2 cups of flour
    1 teaspoon of baking soda
    1/2 of a teaspoon of salt
    2 cups plus one my-sized handful (about 1/4 of a cup) of oats
    3 tablespoons of water plus maybe a few more drops

Directions:

Mix the vegan brown sugar, vegan sugar, oil, and water.  
Add the banana and vanilla.  
then mix in the already pre-mixed together mixture of flour, baking soda, cinnamon, and salt.  
Stir in the oats.  
If it is too thin, let stand a bit so the oats can absorb the excess liquid.
  Bake at 350 degrees Fahrenheit for 13 minutes.  
  To check if they are done, flip one over and it should be browned.  Rip off a chunk to see it it is still raw.  I usually let them sit a bit before I take them off the cookie sheet because, well, I do not really know, but it makes me not cook them too long because they finish cooking outside of the oven.  I should mention to drop them by the tablespoon.
 * 
 * @author <a href="mailto:greg.milette@baesystems.com">Greg Milette</a>
 */
public class OatCookiesRecipe extends ParameterSupplier 
{
  @Override
  public List<PotentialParameterValue> getValueSources(Object test, 
      ParameterSignature sig)
    {
      Recipe recipe = HardcodedRecipeRepository.getOatCookie();
      List<PotentialParameterValue> list = new ArrayList<PotentialParameterValue>(); 
      
      list.add(PotentialParameterValue.forValue(recipe));

      return list;
    }
}
