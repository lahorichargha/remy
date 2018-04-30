package com.tastycode.remy.dialogmanager;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tastycode.remy.commonobjects.dialog.SayNothing;
import com.tastycode.remy.commonobjects.dialog.StartingRecipe;
import com.tastycode.remy.commonobjects.dialog.Welcome;
import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.dialogmanager.dialogobserver.SpokenDialogObserver;
import com.tastycode.remy.input.SpeechInput;
import com.tastycode.remy.output.Output;


/**
 * controls the speaking and listening
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
@Component
public class Speaker implements SpeakerAndListener
{
    /* log4j logger instance */
    private static final Logger logger = Logger.getLogger(Speaker.class
                    .getName());
    
    @Resource private Output output;
    @Resource private SpeechInput input;

    private SpokenDialogObserver observer;

	public Speaker()
    {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpeechOutputExample-context.xml", SpeechOutputExample.class);
//        output = (Output) context.getBean("output");
    }
    
    /* (non-Javadoc)
     * @see com.tastycode.remy.dialogmanager.SpeakerInterface#startDialog(com.tastycode.remy.dialogmanager.RemyDialog)
     */
    public void startDialog(RemyDialog dialog) {
    	dialog.setSpeaker(this);

    	say(dialog.getWelcome());
    	
    	// Model is updated, so Remy will say recipe and first step.
    	dialog.updatedRecipeDomainModel(dialog.getModel());
    	
        //TODO: figure
        for (int i = 0; i < 250; i++)
        {
            logger.debug("getting new utterance");
            WhatWasSaid whatWasSaid = getWhatWasSaid();
            observer.heard(whatWasSaid);

            WhatToSay sayThis = dialog.getWhatToSayNext(whatWasSaid);
            observer.said(sayThis);

            if (sayThis.equals(SayNothing.NOTHING))
            {
                logger.debug("decided to say nothing..");
            }
            else
            {
                logger.debug("saying");
                say(sayThis);
            }
        }
    }
    
    /* (non-Javadoc)
     * @see com.tastycode.remy.dialogmanager.SpeakerInterface#startDialog(com.tastycode.remy.commonobjects.recipe.Recipe)
     */
    public void startDialog(Recipe recipe)
    {
        startDialog(new RemyDialog(recipe));
    }
    
    private WhatWasSaid getWhatWasSaid()
    {
        WhatWasSaid whatWasSaid = null;
        do
        {
            logger.debug("Waiting for you to say something:");
            whatWasSaid = input.recordUtterance();
            logger.debug("Got utterance: " + whatWasSaid);
        }
        while ((whatWasSaid == null) || whatWasSaid.getText().equals("quit"));

        return whatWasSaid;
    }
    
    /* (non-Javadoc)
     * @see com.tastycode.remy.dialogmanager.SpeakerInterface#say(java.lang.String)
     */
    public void say(String whatToSay) {
    	say(new WhatToSayLiterally(whatToSay));
    }
    
    /* (non-Javadoc)
     * @see com.tastycode.remy.dialogmanager.SpeakerInterface#say(com.tastycode.remy.commonobjects.dialog.WhatToSay)
     */
    public void say(WhatToSay whatToSay)
    {
    	try {
    		output.say(whatToSay);
    	} catch (Exception e) {
    	    logger.error("error trying to speak " + whatToSay, e);
    	}
    }
    
    /* (non-Javadoc)
     * @see com.tastycode.remy.dialogmanager.SpeakerInterface#getInput()
     */
    public SpeechInput getInput() {
		return input;
	}

    public void setObserver(SpokenDialogObserver observer)
    {
        this.observer = observer;
    }
}
