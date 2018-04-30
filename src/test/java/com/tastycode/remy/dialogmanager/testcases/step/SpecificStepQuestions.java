package com.tastycode.remy.dialogmanager.testcases.step;

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
public class SpecificStepQuestions extends ParameterSupplier
{
    @Override
    public List<PotentialParameterValue> getValueSources(Object test,
        ParameterSignature sig)
    {
        List<PotentialParameterValue> list = new ArrayList<PotentialParameterValue>();

        //just for easy authoring
        Map<String, String> saidAndResponse = 
            new HashMap<String, String>();
        
        saidAndResponse.put("Tell me about the eggplant in the recipe, whatto I have to do to it, err.", "eggplant");

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



