package com.tastycode.remy.dialogmanager.testcases.ingredientquery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialParameterValue;

import com.tastycode.remy.dialogmanager.testcases.UserSaysAndExpecteResponse;


/**
 * generate questions and answers
 */
public class IngredientQuestionsOatCookies extends ParameterSupplier
{
    @Override
    public List<PotentialParameterValue> getValueSources(Object test,
        ParameterSignature sig)
    {
        List<PotentialParameterValue> list = new ArrayList<PotentialParameterValue>();

        //just for easy authoring
        Map<String, String> saidAndResponse = 
            new HashMap<String, String>();

        //TODO: how should we test the response?
        saidAndResponse.put("How much sugar?", "cup");
        saidAndResponse.put("How much water?", "tablespoon");
        saidAndResponse.put("How much oil?", "cup");
        saidAndResponse.put("How many banana?", "mashed");
        saidAndResponse.put("How much vanilla extract?", "teaspoon vanilla");
        saidAndResponse.put("How much cinnamon?", "teaspoon cinnamon");

        //actually make the test objects
        for (Map.Entry<String, String> sr : saidAndResponse.entrySet())
        {
            list.add(PotentialParameterValue
                .forValue(new UserSaysAndExpecteResponse(
                    sr.getKey(), sr.getValue())));
        }
        
        return list;
    }

}



