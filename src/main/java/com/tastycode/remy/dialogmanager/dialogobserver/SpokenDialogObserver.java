package com.tastycode.remy.dialogmanager.dialogobserver;

import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;

/**
 * Observes spoken dialog
 * 
 * Note: I used "observer" instead of "listener"
 * so as not to confuse the speech recognition terminology
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public interface SpokenDialogObserver
{
    /**
     * when the speaker hears something
     */
    public void heard(WhatWasSaid whatWasSaid);

    /**
     * what the speaker decides to say
     */
    public void said(WhatToSay sayThis);

}
