package com.tastycode.remy.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.remoting.support.RemoteAccessor;

public class MultiplexingProxy<T> extends RemoteAccessor implements MethodInterceptor {
	List<T> receivers = new CopyOnWriteArrayList<T>();
	boolean raiseExceptions = false;
	
	public void addReceiver(T r) {
		receivers.add(r);
	}
	
	public void setReceivers(List<T> rs) {
		receivers.clear();
		receivers.addAll(rs);
	}
	
	public List<T> getReceivers() {
		return Collections.unmodifiableList(receivers);
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object finalResult = null;
		DONE: for (T r : receivers) {
			Object result = doInvoke(r, invocation);
			if (isStopValue(result)) {
				finalResult = result;
				break DONE; 
			}
			if (isAssignableValue(result)) {
				finalResult = result;
			}
		}
		return finalResult;
	}

	protected boolean isAssignableValue(Object result) {
		return result != null;
	}

	private boolean isStopValue(Object result) {
		return false;
	}

	private Object doInvoke(T r, MethodInvocation invocation) {
		try {
			return invocation.getMethod().invoke(r, invocation.getArguments());
		} catch (IllegalArgumentException e) {
			handleException(e, r);
		} catch (IllegalAccessException e) {
			handleException(e, r);
		} catch (InvocationTargetException e) {
			handleException(e, r);
		}
		return null;
	}

	private void handleException(Exception e, T r) {
		if (raiseExceptions) {
			throw new RuntimeException("Error invoking method on: " + r, e);
		}
	}
}
