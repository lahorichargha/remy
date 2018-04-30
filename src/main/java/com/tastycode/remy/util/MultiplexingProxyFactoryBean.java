package com.tastycode.remy.util;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class MultiplexingProxyFactoryBean<T> extends MultiplexingProxy<T> implements FactoryBean, InitializingBean {
	private T serviceProxy;
	
	public Object getObject() throws Exception {
		return serviceProxy;
	}

	@SuppressWarnings("unchecked")
	public Class getObjectType() {
		return this.getServiceInterface();
	}

	public boolean isSingleton() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public void afterPropertiesSet() throws Exception {
		this.serviceProxy = (T) ProxyFactory.getProxy(getObjectType(), this);
	}
	
	
}
