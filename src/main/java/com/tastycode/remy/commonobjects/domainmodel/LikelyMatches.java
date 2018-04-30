package com.tastycode.remy.commonobjects.domainmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tastycode.remy.commonobjects.recipe.HasText;
import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *An important part of the understanding the user's utterance is to be able to  match what the user said to their context. Their context consists of what  recipe step they are currently likely to be in. When Remy knows this, it can  handle ambiguous inputs better.

To implement this matching function Remy first separates the user's  utterances into four classes.
1. Cooking words: These consist of:
 - ingredients (e.g. bread, carrot)
 - cooking verbs (e.g. cut, chop, break, mix)
 - cooking tools (e.g. oven, pan, spoon)
2. Timer words such as "Timer" "set" "Time"
3. Dialog words such as "After" "What" "Before"
4. Irrelevant words. These are stop words that don't matter such as "the"

For matching against steps and ingredients, the domain model will use  cooking words and ignore the rest. The domain model must also factor in the  user's current step into the process and it must be sensitive to various  forms of words. It does not, however, need to worry about misspellings  because the recognizer does not produce misspelled words.

Here is the algorithm: 

1. count matched stemmed utterance words with stemmed ingredient or step  words
2. the probability of a match is number of words in the utterance matched /  number of words in the step or ingredient. This is the base probability that  the user is asking about that step or ingredient.
3. Now the algorithm must take into account the current step. To do this, it  gets the current step from the domain model. For each step that has a  positive probability add that value to the base probability. If the  algorithm is evaluating ingredients, add the current step's probability to  each base probability for an ingredient in the current step
4. Return a ranked list of steps or ingredients.

For example, Say we have a recipe with step 1 = "cut the carrot" and 2 =  "steam the carrots", and the current step is 2. If the user says "what about  the carrot?" The algorithm matches "carrot" with both steps. resulting in  1/3 probability for step 1 and step 2, however since the current step is 2,  the resulting probability for step 1 and 2 would be 1/3 and 1 and 1/3, and  the algorithm would return step 2 as being more likely than step 1.
 * 
 * TODO: I need to modify this to use a distance metric to say, that if the ingredient is 
 * not in the current step, but is in fact N steps in future or N steps in the past give it
 * some weight. Steps in the future should be weighted more heavily than steps in the past.
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class LikelyMatches
{
    /* log4j logger instance */
    private static final Logger logger = Logger.getLogger(LikelyMatches.class
                    .getName());
    
    private RecipeDomainModel model;
    
    LikelyMatches(RecipeDomainModel model)
    {
        this.model = model;
    }
    
    public List<Step> getLikelyCurrentStepMatches(
        HasText utterance)
    {
        List<Step> steps = new ArrayList<Step>();
        steps = matchingSteps(utterance);
        return steps;
    }

    public List<Ingredient> getLikelyIngredientMatches(
        HasText utterance)
    {
        List<Ingredient> ingredients;
        ingredients = matchingIngredients(utterance);
        return ingredients;
    }

    /**
     */
    private List<Step> matchingSteps(HasText utterance)
    {
        logger.debug("matching steps with utterance: " + utterance);
        Map<Step, StepProbability> stepMatchProbabilities = 
            new HashMap<Step, StepProbability>();

        //get the match for each step
        //don't include steps with no matches 
        //remove any steps that have 0 matches
        for (Step step : model.getRecipe().getSteps())
        {
            double numMatching = step.numMatchingCookingWords(utterance);

            if (numMatching > 0)
            {
                double percentOfWords = numMatching / 
                    (double)utterance.getCookingWords().size(); 
                stepMatchProbabilities.put(step, 
                    new StepProbability(step, percentOfWords));
                logger.debug("step: " + step
                    + " matched with probability: " + percentOfWords);
            }
            else
            {
                logger.debug("step: " + step + " did not match");
            }
        }
        
        //now modify the probabilites based on the likely current steps
        List<StepProbability> stepProbabilities = 
            model.getStepModel().getCurrentStepProbabilities();
        for (StepProbability stepProbability : stepProbabilities)
        {
            Step likelyStep = stepProbability.getThing();
            StepProbability stepProb = stepMatchProbabilities.get(likelyStep);
            //this means the utterance was not about the current step
            if (stepProb != null)
            {
                logger.debug("increased: " + stepProb.getThing() + " by: "
                    + stepProbability.getProbability() + 
                    " because it matches current step: " + likelyStep);
                stepProb.addProbability(stepProbability.getProbability());
            }
        }
        
        //now return a sorted list
        List<StepProbability> likelyStepProbabilities = 
            new ArrayList<StepProbability>(stepMatchProbabilities.values());
        
        Collections.sort(likelyStepProbabilities, 
            new ProbabilitySorter());
        
        List<Step> stepsInOrder = new ArrayList<Step>();
        
        logger.debug("ordered steps:");
        int stepCount = 0;
        for (StepProbability stepProbability : likelyStepProbabilities)
        {
            stepsInOrder.add(stepProbability.getThing());
            logger.debug("step " + stepCount + " " + stepProbability.getThing() + 
                " prob: " + stepProbability.getProbability());
            stepCount++;
        }

        return stepsInOrder;
    }
    
    /**
     * this should help answer the ingredient questions
     * @param utterance
     * @return
     */
    private List<Ingredient> matchingIngredients(
        HasText utterance)
    {
        //TODO: also check on measure type 
        
        //ingredients in current step.
        Map<Ingredient, IngredientProbability> 
            matchProbablities = 
                new HashMap<Ingredient, IngredientProbability>();

        //for this the trick is different
        //find matching ingredients
        
        //get the match for each step
        //don't include steps with no matches 
        //remove any steps that have 0 matches
        for (Ingredient ingredient : model.getRecipe().getIngredients())
        {
            double numMatching = ingredient.numMatchingCookingWords(utterance);

            if (numMatching > 0)
            {
                double percentOfWords = numMatching / 
                    (double)utterance.getCookingWords().size(); 
                matchProbablities.put(ingredient, 
                    new IngredientProbability(ingredient, percentOfWords));
                logger.debug("ingredient: " + ingredient
                    + " matched with probability: " + percentOfWords);
            }
            else
            {
                logger.debug("step: " + ingredient + " did not match");
            }
        }

        //for ingredients this is a little different
        //for each ingredient mentioned in the recipe 
        //raise its probability
        List<IngredientProbability> ingredientProbabilities = 
            model.getStepModel().getCurrentIngredientProbabilities();
        for (IngredientProbability iProbability : ingredientProbabilities)
        {
            Ingredient likelyIngredient = iProbability.getThing();
            logger.debug("current ingredient (" + likelyIngredient.getText() + 
                ")  probability: " + iProbability.getProbability());
            if (matchProbablities.containsKey(likelyIngredient))
            {
                logger.debug("  added " + iProbability.getProbability());
                IngredientProbability ingredientProb = 
                    matchProbablities.get(likelyIngredient);
                ingredientProb.addProbability(iProbability.getProbability());
            }
        }
        
        //now return a sorted list
        List<IngredientProbability> likelyIngredientProbabilities = 
            new ArrayList<IngredientProbability>(matchProbablities.values());
        Collections.sort(likelyIngredientProbabilities, 
            new ProbabilitySorter());
        List<Ingredient> ingredientsInOrder = 
            new ArrayList<Ingredient>();
        
        logger.debug("ordered ingredients:");
        int ingredientCount = 0;
        for (IngredientProbability iProbability : likelyIngredientProbabilities)
        {
            ingredientsInOrder.add(iProbability.getThing());
            logger.debug("ingredient: " + ingredientCount + " " + 
                iProbability.getThing() + " prob: " + iProbability.getProbability());
            ingredientCount++;
        }
        
        return ingredientsInOrder;
    }
    
    //trying despirately to get the generics to work, but can't do it well.
    
//    private List<ThingWithProbability> 
//        sortCollection(Collection<ThingWithProbability<?>> items)
//    {
//        List<ThingWithProbability<?>> itemList = 
//            new ArrayList<ThingWithProbability<?>>(items);
//        
//        Collections.sort(itemList, new ProbabilitySorter());
//
//        List<ThingWithProbability> ingredientsInOrder = 
//            new ArrayList<ThingWithProbability<ThingWithProbability>>();
//        
//        for (ThingWithProbability<?> iProbability : itemList)
//        {
//            ingredientsInOrder.add(iProbability.getThing());
//        }
//        
//        return ingredientsInOrder;
//
//    }

//    private Map<HasText, ThingWithProbability<?>> getProbabilities(
//        List<HasText> toMatch, UserUtterance utterance)
//    {
//        Map<HasText, ThingWithProbability> 
//            matchProbabilities = 
//                new HashMap<HasText, ThingWithProbability>();
//
//        for (HasText text : toMatch)
//        {
//            double numMatching = text.numMatchingCookingWords(utterance);
//
//            if (numMatching > 0)
//            {
//                double percentOfWords = numMatching / 
//                    (double)utterance.getCookingWords().size(); 
//                matchProbabilities.put(text, 
//                    new TextProbability(text, percentOfWords));
//                logger.debug("thing: " + text
//                    + " matched with probability: " + percentOfWords);
//            }
//            else
//            {
//                logger.debug("thing: " + text + " did not match");
//            }
//        }
//    }
//    

}
