package com.tastycode.remy.dialogmanager.testcases.recipes;

import java.util.ArrayList;
import java.util.List;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialParameterValue;

import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.reciperepository.HardcodedRecipeRepository;

public class RatatouilleRecipe extends ParameterSupplier 
{
    /**
     * To begin, assemble the ingredients: 
     * 6 garlic cloves, 5 medium button or brown mushrooms 
     * (I prefer brown for more flavor), 1 medium zucchini, 
     * 5 sprigs of Italian parsley, 4 sprigs of basil, 
     * 1 medium onion, 1 can diced tomatoes 
     * (or 2 tomatoes peeled, seeded, and diced), 
     * chicken or vegetable stock (we'll need 3/4 cup or 180mL), 
     * 1 Tbs. tomato paste, 1 medium green bell pepper, and 
     * 1 large eggplant (about 1 pound or 450 g). 
     * Drain the canned tomatoes.
     */
  @Override
  public List<PotentialParameterValue> getValueSources(Object test, 
      ParameterSignature sig)
    {
      Recipe recipe = HardcodedRecipeRepository.getRatatouille();

      List<PotentialParameterValue> list = new ArrayList<PotentialParameterValue>(); 
      
      list.add(PotentialParameterValue.forValue(recipe));

      return list;
    }
}
