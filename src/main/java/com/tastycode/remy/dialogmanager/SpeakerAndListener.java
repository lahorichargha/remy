package com.tastycode.remy.dialogmanager;

import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.dialogmanager.dialogobserver.SpokenDialogObserver;
import com.tastycode.remy.input.SpeechInput;


/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public interface SpeakerAndListener
{
    public void startDialog(RemyDialog dialog);

    public void startDialog(Recipe recipe);

    public void say(String whatToSay);

    public void say(WhatToSay whatToSay);

    public SpeechInput getInput();
    
    public void setObserver(SpokenDialogObserver observer);
}