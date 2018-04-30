package com.tastycode.remy.dialogmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.speech.recognition.RuleGrammar;

import org.apache.log4j.Logger;

import com.tastycode.remy.GrammarLoader;
import com.tastycode.remy.commonobjects.Feature;
import com.tastycode.remy.commonobjects.Generator;
import com.tastycode.remy.commonobjects.TaskManager;
import com.tastycode.remy.commonobjects.Understander;
import com.tastycode.remy.commonobjects.dialog.SayNothing;
import com.tastycode.remy.commonobjects.dialog.StartingRecipe;
import com.tastycode.remy.commonobjects.dialog.UserUtterance;
import com.tastycode.remy.commonobjects.dialog.Welcome;
import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.dialog.systemutterance.SystemUtterance;
import com.tastycode.remy.commonobjects.domainmodel.RecipeDomainListener;
import com.tastycode.remy.commonobjects.domainmodel.RecipeDomainModel;
import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.input.Input;
import com.tastycode.remy.taskmanager.step.StepFeature;
import com.tastycode.remy.taskmanager.timer.TimerFeature;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class RemyDialog implements DialogManager, RecipeDomainListener
{
    /* log4j logger instance */
    private static final Logger logger = Logger.getLogger(RemyDialog.class);
    
    private List<Feature> features = new ArrayList<Feature>();

    private RecipeDomainModel domainModel;
    private SpeakerAndListener speaker;

	public RemyDialog(Recipe recipe) {
    	this(new RecipeDomainModel(recipe));
        addFeature(new StepFeature(domainModel));
        addFeature(new TimerFeature(domainModel));
    }
    
    public RemyDialog(RecipeDomainModel model)
    {
        domainModel = model;
        model.addListener(this);
    }
    
    public RecipeDomainModel getModel()
    {
        return domainModel;
    }
    
	public WhatToSay getWhatToSayNext(String utterance) {
		return getWhatToSayNext(getInput().parseUtterance(utterance));
	}

	public WhatToSay getWhatToSayNext(WhatWasSaid wasSaid)
    {
        logger.debug("user said: " + wasSaid);
        UserUtterance utterance = interpret(wasSaid);

        logger.debug("interpreted as: " + utterance);
        
        SystemUtterance sysUtterance = respond(utterance);
        
        WhatToSay toSay = howToSay(sysUtterance);

        logger.debug("decided to say: " + toSay);

        return toSay;
    }
    
	protected WhatToSay howToSay(SystemUtterance utterance) {
		WhatToSay say = SayNothing.NOTHING;
		for (Generator generator : features) {
			try {
				say = generator.howToSay(utterance);
			} catch (Exception e) {
				logger.debug("Error understanding", e);
			}
			if (say != null && say != SayNothing.NOTHING)
				break;
		}
		
		if (say == null)
			say = SayNothing.NOTHING;
		
		return say;
	}

	protected SystemUtterance respond(UserUtterance utterance) {
		SystemUtterance response = SayNothing.NOTHING;
		for (TaskManager taskMgr : features) {
			try {
				response = taskMgr.respond(utterance);
			} catch (Exception e) {
				logger.debug("Error understanding", e);
			}
			if (response != null && response != SayNothing.NOTHING)
				break;
		}
		
		if (response == null)
			response = SayNothing.NOTHING;
		
		return response;
	}

	protected UserUtterance interpret(WhatWasSaid wasSaid) {
		UserUtterance utterance = SayNothing.NOTHING;
		
		for (Understander understander : features) {
			try {
				logger.info("Interpreting : '" + wasSaid + " with tags [" + wasSaid.getTags()+ "]");
				utterance = understander.interpret(wasSaid);
			} catch (Exception e) {
				logger.debug("Error understanding", e);
			}
			if (utterance != SayNothing.NOTHING && utterance != null)
				break;
		}
		
		if (utterance == null) 
			utterance = SayNothing.NOTHING;
		
		return utterance;
	}

	public boolean addFeature(Feature feature) {
		feature.setDialog(this);
		return features.add(feature);
	}

	public boolean removeFeature(Feature obj) {
		return features.remove(obj);
	}
	
	public void setFeatures(List<Feature> features) {
		this.features.clear();
		for (Feature feature : features) {
			addFeature(feature);
		}
	}
	
	public List<Feature> getFeatures() {
		return Collections.unmodifiableList(features);
	}

    public Input getInput() {
		return speaker.getInput();
	}

    public void setSpeakerWithNoGrammer(SpeakerAndListener speaker) {
        this.speaker = speaker;
    }
    
	public void setSpeaker(SpeakerAndListener speaker) {
		this.speaker = speaker;
		updateGrammar();
	}
	
	protected void updateGrammar() {
        List<Ingredient> ingredients = domainModel.getRecipe().getIngredients();
        Set<String> words = new HashSet<String>();
        for (Ingredient ingredient : ingredients) {
        	String ingredientStr = ingredient.getIngredient();
			words.add(ingredientStr);
			String[] ingredientParts = WHITESPACE.split(ingredientStr);
			
        	if (ingredientParts != null) {
        		words.addAll(Arrays.asList(ingredientParts));
        	}
		}
        logger.info("Loading ingredients: " + words);
		RuleGrammar ruleGrammar = speaker.getInput().getGrammar().getRuleGrammar();
		RuleGrammar ingredientGrammar = ruleGrammar.getRecognizer().getRuleGrammar("ingredient");
		GrammarLoader.loadWords("ingredient", words, ingredientGrammar);
	}

	private static Pattern WHITESPACE = Pattern.compile("\\s");
	
	public SpeakerAndListener getSpeaker() {
		return speaker;
	}

	public void say(SystemUtterance utterance) {
		getSpeaker().say(howToSay(utterance));
	}

	public void updatedRecipeDomainModel(RecipeDomainModel model) {
		speaker.say(new StartingRecipe(model.getRecipe()));
		speaker.say(getWhatToSayNext("First Step."));
		updateGrammar();
	}

	public WhatToSay getWelcome() {
		return new Welcome();
	}
	
}
