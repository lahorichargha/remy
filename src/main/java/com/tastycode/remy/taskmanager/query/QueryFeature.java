package com.tastycode.remy.taskmanager.query;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tastycode.remy.commonobjects.Feature;
import com.tastycode.remy.commonobjects.dialog.SayNothing;
import com.tastycode.remy.commonobjects.dialog.UserUtterance;
import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.dialog.systemutterance.CurrentStepNumber;
import com.tastycode.remy.commonobjects.dialog.systemutterance.DescribeIngredient;
import com.tastycode.remy.commonobjects.dialog.systemutterance.NumberedStep;
import com.tastycode.remy.commonobjects.dialog.systemutterance.SayMultipleThings;
import com.tastycode.remy.commonobjects.dialog.systemutterance.SystemUtterance;
import com.tastycode.remy.commonobjects.dialog.userutterance.RecipeStepOrIngredientQuestion;
import com.tastycode.remy.commonobjects.dialog.userutterance.RecipeStepOrIngredientQuestion.QueryType;
import com.tastycode.remy.commonobjects.domainmodel.RecipeDomainModel;
import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Step;
import com.tastycode.remy.dialogmanager.WhatToSayLiterally;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class QueryFeature extends Feature
{
    /* log4j logger instance */
    private static final Logger logger = Logger.getLogger(QueryFeature.class
                    .getName());
    
    public static final String GRAMMAR = "query";

    private RecipeDomainModel model;
    
    public QueryFeature(RecipeDomainModel model)
    {
        super(GRAMMAR);
        this.model = model;
    }
    
    @Override
    public WhatToSay howToSay(SystemUtterance utterance) {
        if (utterance.equals(SayNothing.NOTHING))
        {
            return SayNothing.NOTHING;
        }
        else
        {
            return new WhatToSayLiterally(utterance.getWhatToSay());
        }
    }
    public UserUtterance interpret(WhatWasSaid wasSaid)
    {
        //try to match this with a step or an ingredient

        //TODO: use tags to figure this out
//        if (wasSaid.getTags().contains("ingredient") {
//            return new NextStep(wasSaid);
//        }
//      Step mostLikelyStep = steps.get(0);
//      Ingredient mostLikelyIngredient = ingredients.get(0);
//      
//      RecipeStepOrIngredientQuestion question = new 
//          RecipeStepOrIngredientQuestion(wasSaid, 
//              mostLikelyStep, mostLikelyIngredient);
    	
    	if (!commandType(getGrammarName(), wasSaid))
    		return SayNothing.NOTHING;

    	String commandType = getCommandType(wasSaid);
    	
    	List<Step> steps = model.getLikelyMatches().
            getLikelyCurrentStepMatches(wasSaid);

        List<Ingredient> ingredients = model.getLikelyMatches().
            getLikelyIngredientMatches(wasSaid);

        //didn't recognize anything so don't say anything
        if (steps.size() == 0 && ingredients.size() == 0)
        {
            return SayNothing.NOTHING;
        }

        RecipeStepOrIngredientQuestion question = new 
        RecipeStepOrIngredientQuestion(wasSaid, 
            steps, ingredients);

        if (commandType("query amount", commandType)) {
        	question.setQueryType(QueryType.AMOUNT);
        }
        if (commandType("query when", commandType)) {
        	question.setQueryType(QueryType.WHEN);
        }
        if (commandType("query all", commandType)) {
        	question.setQueryType(QueryType.ALL);
        }
        
        
        
        logger.debug("recognized a recipe question: " + question);
        
        return question;
    }

	public SystemUtterance respond(UserUtterance utterance)
    {
        SystemUtterance whatToSay = SayNothing.NOTHING;
        
        if ((utterance instanceof RecipeStepOrIngredientQuestion))
        {
            RecipeStepOrIngredientQuestion recipeQ = 
                (RecipeStepOrIngredientQuestion)utterance;
            
            logger.debug("  it is a query question");

            List<SystemUtterance> utts = new ArrayList<SystemUtterance>();
            
            if (recipeQ.hasIngredient())
            {
                SystemUtterance sayAboutIngredients = 
                    new DescribeIngredient(recipeQ.getLikelyIngredient());
                utts.add(sayAboutIngredients);
            }
            
            if (recipeQ.hasSteps())
            {
                SystemUtterance sayAboutSteps = 
                    new NumberedStep(recipeQ.getLikelyStep());
                model.getStepModel().getCurrentStep().getStepNumber();
                SystemUtterance stepNumber = new CurrentStepNumber(model.getStepModel().getCurrentStep());
                utts.add(sayAboutSteps);
                utts.add(stepNumber);
            }
            
            if (utts.size() == 1) {
            	whatToSay = utts.get(0);
            } else {
            	whatToSay = new SayMultipleThings(utts.toArray(new SystemUtterance[utts.size()]));
            }
       }
        
        return whatToSay;
    }
    
    
}
