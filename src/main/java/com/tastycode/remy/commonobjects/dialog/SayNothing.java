package com.tastycode.remy.commonobjects.dialog;

import com.tastycode.remy.commonobjects.dialog.systemutterance.DontUnderstand;
import com.tastycode.remy.commonobjects.dialog.systemutterance.SystemUtterance;
import com.tastycode.remy.commonobjects.recipe.HasText;
import com.tastycode.remy.commonobjects.recipe.ThingWithText;
/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class SayNothing extends ThingWithText implements SystemUtterance, UserUtterance, WhatToSay 
{
    public static SayNothing NOTHING = new SayNothing();
    
    //singleton class
    private SayNothing()
    {
        super(DontUnderstand.DONT_UNDERSTAND.getWhatToSay());
        
    }
    
    public String getWhatToSay()
    {
        return DontUnderstand.DONT_UNDERSTAND.getWhatToSay();
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() 
            + "[text='" + getWhatToSay() +"']";
    }

	public boolean sameMeaningAs(UserUtterance otherUtterance) {
		return otherUtterance instanceof SayNothing;
	}

	public int numMatch(HasText otherText) {
		// TODO Auto-generated method stub
		return 0;
	}

}
