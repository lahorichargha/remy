package com.tastycode.remy.commonobjects;

import com.tastycode.remy.commonobjects.dialog.UserUtterance;
import com.tastycode.remy.commonobjects.dialog.systemutterance.SystemUtterance;

public interface TaskManager {
	SystemUtterance respond(UserUtterance utterance);
}
