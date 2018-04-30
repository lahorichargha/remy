package com.tastycode.remy.input;

import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;

public interface Input {
	
	public WhatWasSaid recordUtterance();
	public WhatWasSaid parseUtterance(String utterance);
}
