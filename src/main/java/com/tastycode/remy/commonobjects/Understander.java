package com.tastycode.remy.commonobjects;

import com.tastycode.remy.commonobjects.dialog.UserUtterance;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;

public interface Understander {
	UserUtterance interpret(WhatWasSaid wasSaid);
}
