package com.tastycode.remy.commonobjects.dialog;

import com.tastycode.remy.commonobjects.recipe.ThingWithText;

/**
 *
 *
 */
public class LiteralUserUtterance extends ThingWithText implements UserUtterance
{
    public LiteralUserUtterance(String text)
    {
        super(text);
    }

    public boolean sameMeaningAs(UserUtterance otherUtterance)
    {
        return false;
    }

    @Override
    public String toString() {
    	return getClass().getSimpleName() 
    		+ "[text='" + getText() +"']";
    }
}
