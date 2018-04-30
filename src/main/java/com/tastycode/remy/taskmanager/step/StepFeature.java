package com.tastycode.remy.taskmanager.step;

import org.apache.log4j.Logger;

import com.tastycode.remy.commonobjects.Feature;
import com.tastycode.remy.commonobjects.dialog.SayNothing;
import com.tastycode.remy.commonobjects.dialog.UserUtterance;
import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.dialog.systemutterance.DescribeCurrentStep;
import com.tastycode.remy.commonobjects.dialog.systemutterance.DescribeFirstStep;
import com.tastycode.remy.commonobjects.dialog.systemutterance.DescribeNextStep;
import com.tastycode.remy.commonobjects.dialog.systemutterance.DescribePreviousStep;
import com.tastycode.remy.commonobjects.dialog.systemutterance.FinishedLastStep;
import com.tastycode.remy.commonobjects.dialog.systemutterance.SystemUtterance;
import com.tastycode.remy.commonobjects.dialog.userutterance.CurrentStep;
import com.tastycode.remy.commonobjects.dialog.userutterance.FirstStep;
import com.tastycode.remy.commonobjects.dialog.userutterance.NextStep;
import com.tastycode.remy.commonobjects.dialog.userutterance.PreviousStep;
import com.tastycode.remy.commonobjects.dialog.userutterance.StepCommand;
import com.tastycode.remy.commonobjects.domainmodel.RecipeDomainModel;
import com.tastycode.remy.commonobjects.recipe.Step;
import com.tastycode.remy.dialogmanager.WhatToSayLiterally;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class StepFeature extends Feature
{
    public static final String STEP_GRAMMAR = "step";

	/* log4j logger instance */
    private static final Logger logger = Logger.getLogger(StepFeature.class
                    .getName());
    
    private RecipeDomainModel model;
    
    public StepFeature(RecipeDomainModel model)
    {
    	super(STEP_GRAMMAR);
    	this.model = model;
    }
    /**
     * hear w
     * @param userSaid
     */
    public void hear(String userSaid)
    {
        //if 
        //which step?
//        Step likelyStep = whichStep(utterance);
//        return whatToSay;
        
    }

    public SystemUtterance respond(UserUtterance utterance)
    {
    	if (!(utterance instanceof StepCommand)) {
    		return SayNothing.NOTHING;
    	}
    	
        Step currentStep = model.getStepModel().getCurrentStep(); 
        Step nextStep = model.getStepModel().getNextStep();
        logger.debug(" current step: " + currentStep);
        logger.debug(" next step: " + nextStep);
        
        //default answer
        SystemUtterance whatToSay = SayNothing.NOTHING;

        //change the model
        if (utterance instanceof NextStep)
        {
            //if reached the last step already then describe it differently
            if (model.getStepModel().isFinishedLastStep())
            {
                whatToSay = new FinishedLastStep(model.getStepModel().getLastStep());
            }
            else //otherwise describe it as the next step
            {
                Step nextStepToSay = model.getStepModel().getNextStep();
                whatToSay = new DescribeNextStep(nextStepToSay);
            }
            //change the model
            model.getStepModel().moveToNextStep();
        }
        else if (utterance instanceof FirstStep) {
        	model.getStepModel().moveToStep(0);
            whatToSay = new DescribeFirstStep(model.getStepModel().getCurrentStep());
        }
        else if (utterance instanceof CurrentStep) {
            whatToSay = new DescribeCurrentStep(model.getStepModel().getCurrentStep());
        }
        else if (utterance instanceof PreviousStep) {
        	model.getStepModel().moveToPreviousStep();
            whatToSay = new DescribePreviousStep(model.getStepModel().getCurrentStep());
        }

        return whatToSay;
    }
        
	public UserUtterance interpret(WhatWasSaid wasSaid) {
		if (!commandType("step", wasSaid)) 
			return SayNothing.NOTHING;
		
        String commandType = getCommandType(wasSaid);
        
        if (commandType("first step", commandType)) {
        	return new FirstStep(wasSaid);
        }

        if (commandType("current step", commandType)) {
        	return new CurrentStep(wasSaid);
        }
        
        if (commandType("previous step", commandType)) {
        	return new PreviousStep(wasSaid);
        }

        if (commandType("goto step", commandType)) {
        	// TODO Not yet implemented
        }

        if (commandType("next step", commandType)) {
        	return new NextStep(wasSaid);
        }
        
        return SayNothing.NOTHING;
	}

   
    private Step whichStep(WhatWasSaid wasSaid)
    {
        int maxSoFar = -1;
        Step maxStep = Step.UNKNOWN_STEP;
        for (Step step : model.getRecipe().getSteps())
        {
            int thisNumMatch = step.numMatch(wasSaid);
            if (step.numMatch(wasSaid) > maxSoFar)
            {
                maxSoFar = thisNumMatch;
                maxStep = step;
            }
        }
        
        return maxStep;
    }
    
	@Override
	public WhatToSay howToSay(SystemUtterance utterance) {
		if (utterance instanceof DescribeNextStep) {
			return new WhatToSayLiterally(utterance.getWhatToSay());
		}
		else if (utterance instanceof FinishedLastStep)
		{
		    return new WhatToSayLiterally(utterance.getWhatToSay());
		}
		return SayNothing.NOTHING;
	}
    
}
