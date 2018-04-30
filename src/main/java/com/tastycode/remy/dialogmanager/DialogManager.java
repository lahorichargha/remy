package com.tastycode.remy.dialogmanager;

import com.tastycode.remy.commonobjects.Feature;
import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.dialog.systemutterance.SystemUtterance;
import com.tastycode.remy.commonobjects.domainmodel.RecipeDomainModel;

/**
 *
 * maybe different sub classes depending on which task the user
 * is doing
 * 
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public interface DialogManager
{
    public WhatToSay getWhatToSayNext(WhatWasSaid utterance);
    public WhatToSay getWhatToSayNext(String utterance);
    public void say(SystemUtterance utterance);

	public boolean addFeature(Feature feature);
	public boolean removeFeature(Feature feature);
	
	public RecipeDomainModel getModel();
	public SpeakerAndListener getSpeaker();
	
}
