package com.tastycode.remy.commonobjects.dialog;

import com.tastycode.remy.commonobjects.recipe.HasText;

/**
 *
 * literally, what the user said
 * 
 */
public interface UserUtterance extends HasText
{
    public boolean sameMeaningAs(UserUtterance otherUtterance);

}
