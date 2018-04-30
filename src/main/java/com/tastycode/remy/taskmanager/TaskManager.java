package com.tastycode.remy.taskmanager;

import com.tastycode.remy.commonobjects.dialog.UserUtterance;
import com.tastycode.remy.commonobjects.dialog.systemutterance.SystemUtterance;

public interface TaskManager<T extends UserUtterance> {
	public SystemUtterance respond(T utterance);
}