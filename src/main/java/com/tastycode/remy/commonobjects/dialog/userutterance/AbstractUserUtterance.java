package com.tastycode.remy.commonobjects.dialog.userutterance;

import com.tastycode.remy.commonobjects.dialog.UserUtterance;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.recipe.ThingWithText;

public abstract class AbstractUserUtterance extends ThingWithText implements UserUtterance {
	private WhatWasSaid whatWasSaid;

	public AbstractUserUtterance(WhatWasSaid whatWasSaid) {
		super(whatWasSaid.getText());
		this.whatWasSaid = whatWasSaid;
	}

	public boolean sameMeaningAs(UserUtterance otherUtterance) {
		return getText().equals(otherUtterance.getText());
	}

	public WhatWasSaid getWhatWasSaid() {
		return whatWasSaid;
	}
	
	public void setWhatWasSaid(WhatWasSaid whatWasSaid) {
		this.whatWasSaid = whatWasSaid;
	}
}

