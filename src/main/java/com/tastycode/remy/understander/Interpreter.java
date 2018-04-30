package com.tastycode.remy.understander;

import com.tastycode.remy.commonobjects.dialog.UserUtterance;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;

public interface Interpreter {
	UserUtterance interpret(WhatWasSaid userUtter);
}
