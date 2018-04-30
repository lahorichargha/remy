package com.tastycode.remy.commonobjects.dialog;

import com.tastycode.remy.commonobjects.recipe.ThingWithText;

/**
 *
 *
 */
public class WhatWasSaidLiterally extends ThingWithText implements WhatWasSaid
{
	public static WhatWasSaidLiterally NOTHING = new WhatWasSaidLiterally("");
	
	private String tags;
	
    public WhatWasSaidLiterally(String text)
    {
        super(text);
        tags = "";
    }

    public WhatWasSaidLiterally(String text, String tags) {
    	super(text);
    	this.tags = tags;
    }
    
    public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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
