package com.tastycode.remy.dialogmanager;

import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.recipe.ThingWithText;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class WhatToSayLiterally extends ThingWithText implements WhatToSay
{
	public WhatToSayLiterally(String text)
    {
        super(text);
    }
}
