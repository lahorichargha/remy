package com.tastycode.remy.generator;

import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.dialog.systemutterance.CurrentStepAnswer;
import com.tastycode.remy.commonobjects.dialog.systemutterance.SystemUtterance;
import com.tastycode.remy.dialogmanager.WhatToSayLiterally;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class Generator
{
    public WhatToSay howToSay(SystemUtterance utter)
    {
        WhatToSay systemSays;

        if (utter instanceof CurrentStepAnswer)
        {
            CurrentStepAnswer stepQ = 
                (CurrentStepAnswer) utter;
            
            systemSays = new WhatToSayLiterally("for that step you should: " 
                + stepQ.getStep().getText());
        }
        else
        {
            systemSays = new WhatToSayLiterally("I don't know");
        }
        
        return systemSays;
    }

}
