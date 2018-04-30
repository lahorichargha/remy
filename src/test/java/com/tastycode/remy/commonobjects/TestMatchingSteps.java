package com.tastycode.remy.commonobjects;

import static com.tastycode.remy.util.CookingUnits.COUNT;
import static javax.measure.Measure.valueOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tastycode.remy.commonobjects.dialog.LiteralUserUtterance;
import com.tastycode.remy.commonobjects.dialog.UserUtterance;
import com.tastycode.remy.commonobjects.domainmodel.LikelyMatches;
import com.tastycode.remy.commonobjects.domainmodel.RecipeDomainModel;
import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.commonobjects.recipe.Step;
import com.tastycode.remy.commonobjects.recipe.Tool;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class TestMatchingSteps
{
    @Test
    public void testEasyMatches()
    {
        checkStepMatchesIgnoreCurrentStep(1, "cut the brocolli", "cut the carrots", 
            "What do I do with the carrots?", "easy");

        checkStepMatchesIgnoreCurrentStep(0, "cut the brocolli", "cut the carrots", 
            "What do I do with the brocolli?", "easy");
    }

    @Test
    public void testMoreOfOneMatches()
    {
        checkStepMatchesIgnoreCurrentStep(1, "cut the brocolli", "cut the carrots", 
            "What carrots do I cut?", "both match, but carrots matches more");

        checkStepMatchesIgnoreCurrentStep(0, "cut the brocolli", "cut the carrots", 
            "What brocolli do I cut?", "both match, but brocolli matches more");
    }

    @Test
    public void testLongUtteranceEffect()
    {
        checkStepMatchesIgnoreCurrentStep(0, 
            "cut the carrots",
            "steam the carrots", 
            "What carrots do I cut while I butter toast and bake a cake?",
            "the utterance is very long, but 0 should still be first");

        checkStepMatchesIgnoreCurrentStep(1, 
            "steam the carrots", 
            "cut the carrots",
            "What carrots do I cut while I butter toast and bake a cake?",
            "reversed");
    }

    @Test
    public void testLongStepEffect()
    {
        checkStepMatchesIgnoreCurrentStep(0, 
            "cut the carrots feast bread baked well with toast",
            "steam the carrots", "What carrots do I cut?",
            "step one is way longer, matches more words so it should still be selected");

        checkStepMatchesIgnoreCurrentStep(1, 
            "steam the carrots", 
            "cut the carrots feast bread baked well with toast",
            "What carrots do I cut?",
            "reversed previous");

    }

    @Test
    public void testVerbNounMatch()
    {
        checkStepMatchesIgnoreCurrentStep(0, "add water to the pan", "add carrots to the pan", 
            "What do I do after I add water?", "this has two parts, " +
            		"a verb match on adding and an ingredient match on water " + 
            		" the verb and noun match in step 0");

        checkStepMatchesIgnoreCurrentStep(1, "add carrots to the pan", "add water to the pan",  
            "What do I do after I add water?", "reverse previous");

        checkStepMatchesIgnoreCurrentStep(1, "chop carrots", "add carrots", 
            "What do I do after I add carrots?", "this has two parts, " +
                    "a noun match on carrots but the verbs are different " + 
                    " so it should match on step 1");

        checkStepMatchesIgnoreCurrentStep(0, "add carrots", "chop carrots",  
            "What do I do after I add carrots?", "reverse previous");

    }

    @Test
    public void testCurrentStepMatters()
    {
        final int CURRENT_STEP = 1;
        checkStepMatches(1, "chop the brocolli", "cut the carrots", 
            "What do I do with the carrots?", 
            "it could be either step, but the current step is 1 so return that one", 
            CURRENT_STEP);

        checkStepMatches(1, "chop the carrots", "chop the brocolli", 
            "What do I chop?", 
            "it could be either step, but the current step is 1 so return that one," +
            "match on the verb", 
            CURRENT_STEP);
    }

    @Test
    public void testAmbiguousMatches()
    {
        final int CURRENT_STEP = 1;
        
        checkStepMatches(1, "cut the carrots", "steam the carrots", 
            "What do I do with the carrots?", 
            "either step could match but prefer step 1",
            CURRENT_STEP);

        checkStepMatches(0, "cut the carrots", "steam the carrots", 
            "What do I do with the carrots?", 
            "either step could match but prefer step 0",
            0);

        checkStepMatches(1, "steam the potatos", "steam the carrots", 
            "What do I steam?", "easy", 
            CURRENT_STEP);
    }

    @Test
    public void testStemmingNouns()
    {
        checkStepMatchesIgnoreCurrentStep(1, "cut the brocolli", "cut the carrot", 
            "What do I do with the carrots?", "added an s");

        checkStepMatchesIgnoreCurrentStep(1, "cut the brocolli", "cut the carrot", 
            "What do I do with the carrots?", "added an s");

        checkStepMatchesIgnoreCurrentStep(1, "cut the brocolli", "cut the carrots", 
            "What do I do with the carrot?", "removed the s");
        
        //modify the verbs
    }

    @Test
    public void testStemmingVerb()
    {
        checkStepMatchesIgnoreCurrentStep(1, "cut the brocolli", "cutting the carrot", 
            "What carrot do I cut?", "added an ing");

        checkStepMatchesIgnoreCurrentStep(1, "cut the brocolli", "break the bread", 
            "What do I break?", "exact");

        checkStepMatchesIgnoreCurrentStep(1, "cut the brocolli", "breaks the bread", 
            "What do I break?", "adding an s");

        checkStepMatchesIgnoreCurrentStep(1, "cut the brocolli", "breaking the bread", 
            "What do I break?", "add ing");

        //Note: the stemmer does not understand past tense
//        checkStepMatchesIgnoreCurrentStep(1, "cut the brocolli", "broke the bread", 
//            "What do I break?", "past tense");
    }

    private void checkStepMatchesIgnoreCurrentStep(int stepShouldMatch0or1, 
        String step0, String step1, String utterance, String why)
    {
        checkStepMatches(stepShouldMatch0or1, step0, step1, utterance, why, 2);
    }

    private void checkStepMatches(int stepShouldMatch0or1, 
        String step0, String step1, String utterance, String why, 
        int advanceStepThisManyTimes)
    {
        Recipe r = getRecipeWithSteps(step0, step1);
        RecipeDomainModel model = new RecipeDomainModel(r);
        UserUtterance utter = new LiteralUserUtterance(utterance);
        LikelyMatches matcher = model.getLikelyMatches();
        
        for (int i = 0; i < advanceStepThisManyTimes; i++)
        {
            model.getStepModel().moveToNextStep();
        }

        //TODO: set step model to setCurrentStepTo
        List<Step> stepMatches = matcher.getLikelyCurrentStepMatches(utter);
        
        assertThat("failed because of: " + why, stepMatches.get(0).getStepNumber(),
            equalTo(stepShouldMatch0or1));
    }
    
    private Recipe getRecipeWithSteps(String step0, String step1)
    {
        List<Step> steps = new ArrayList<Step>();
        steps.add(new Step(0, step0));
        steps.add(new Step(1, step1));
        steps.add(new Step(2, "")); //add an empty step so I can disable current step checking
        
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(new Ingredient(valueOf(6, COUNT), "garlic cloves"));
        ingredients.add(new Ingredient(valueOf(5, COUNT), "medium mushrooms"));
        
        List<Tool> tools = new ArrayList<Tool>();
        
        Recipe recipe = 
            new Recipe("testrecipe", steps, ingredients, tools);
        
        return recipe;
        
    }

}
