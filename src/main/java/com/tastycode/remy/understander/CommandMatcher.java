package com.tastycode.remy.understander;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

import com.tastycode.remy.commonobjects.dialog.UserUtterance;

public class CommandMatcher<T extends UserUtterance> {
	Pattern pattern;
	Class<? extends T> type;
	public CommandMatcher(String patternStr, Class<? extends T> type) {
		this(Pattern.compile(patternStr), type);
	}
	public CommandMatcher(Pattern pattern, Class<? extends T> type) {
		this.pattern = pattern;
		this.type = type;
	}
	public boolean matches(String wasSaid) {
		return pattern.matcher(wasSaid).matches();
	}
	public T create(String wasSaid) {
		try {
			Constructor<? extends T> constructor = type.getConstructor(String.class);
			return constructor.newInstance(wasSaid);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		} catch (IllegalArgumentException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}
		return null;
	}
}
