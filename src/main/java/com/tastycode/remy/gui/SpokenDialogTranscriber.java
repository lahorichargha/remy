package com.tastycode.remy.gui;

import org.apache.log4j.Logger;

import com.tastycode.remy.commonobjects.dialog.SayNothing;
import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.dialogmanager.dialogobserver.SpokenDialogObserver;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class SpokenDialogTranscriber implements SpokenDialogObserver
{
    /* log4j logger instance */
    private static final Logger logger = Logger
                    .getLogger(SpokenDialogTranscriber.class);
//    private List<WhatWasSaid> said;
//    private List<WhatToSay> sayThis;
    private StringBuffer transcription;
    
    public SpokenDialogTranscriber()
    {
        transcription = new StringBuffer();
    }
     
    public void heard(WhatWasSaid whatWasSaid)
    {
        if (whatWasSaid.getText().length() == 0)
        {
            logger.debug("*Remy didn't understand what you said*");
//            transcription.append("*Remy didn't understand what you said*").append("\n");
        }
        else
        {
            transcription.append("You: ");
            transcription.append(whatWasSaid.getText() + " [" + whatWasSaid.getTags() + "]").append("\n");
        }
    }
    
    public void said(WhatToSay sayThis)
    {
        //only record this if remy said something
        if (!sayThis.equals(SayNothing.NOTHING))
        {
            transcription.append("Remy: ");
            transcription.append(sayThis.getText()).append("\n");
        }
    }

    public String getTranscription()
    {
        return transcription.toString();
    }
}
