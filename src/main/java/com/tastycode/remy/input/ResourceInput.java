package com.tastycode.remy.input;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

/**
 * Gets utterances from any Spring resource that can be
 * opened via {@link Resource#getInputStream()}. 
 * 
 * Automatically opens input stream to resource on initialization
 * and closes it on destruction.  
 * 
 * @author broberts
 */
public class ResourceInput extends TextInput implements InitializingBean, DisposableBean {
	private Resource resource;
	
	public void afterPropertiesSet() throws Exception {
		openResource();
	}

	protected void openResource() throws IOException {
		Assert.notNull(resource, "Must set resource");
		InputStream inputStream = resource.getInputStream();
		setIn(inputStream);
	}

	public void destroy() throws Exception {
		getIn().close();
	}
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
