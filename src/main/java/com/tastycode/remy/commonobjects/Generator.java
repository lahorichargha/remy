package com.tastycode.remy.commonobjects;

import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.dialog.systemutterance.SystemUtterance;

public interface Generator {
	WhatToSay howToSay(SystemUtterance utterance);
}
