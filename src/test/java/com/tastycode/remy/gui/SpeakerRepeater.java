package com.tastycode.remy.gui;

import java.io.IOException;

import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaidLiterally;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.dialogmanager.RemyDialog;
import com.tastycode.remy.dialogmanager.SpeakerAndListener;
import com.tastycode.remy.dialogmanager.dialogobserver.SpokenDialogObserver;
import com.tastycode.remy.input.SpeechInput;

import edu.cmu.sphinx.jsapi.JSGFGrammar;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.util.props.PropertyException;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class SpeakerRepeater implements SpeakerAndListener
{
    public SpeechInput getInput()
    {
        return new MockSpeechInput();
    }
    public void say(String whatToSay)
    {
        // TODO Auto-generated method stub
        
    }
    public void say(WhatToSay whatToSay)
    {
        // TODO Auto-generated method stub
        
    }
    public void startDialog(Recipe recipe)
    {
        // TODO Auto-generated method stub
        
    }
    public void startDialog(RemyDialog dialog)
    {
        // TODO Auto-generated method stub
        
    }
    
    public void setObserver(SpokenDialogObserver observer)
    {
        // TODO Auto-generated method stub
        
    }
}

class MockSpeechInput extends SpeechInput
{
    @Override
    public WhatWasSaid parseUtterance(String input)
    {
        return new WhatWasSaidLiterally(input);
    }
    
    @Override
    public void setGrammar(JSGFGrammar grammar)
    {
    }
    
    @Override
    public void setRecognizer(Recognizer recognizer)
    {
    }
    
    @Override
    protected void startConfiguration() throws IOException, PropertyException,
        InstantiationException
    {
    }
}
