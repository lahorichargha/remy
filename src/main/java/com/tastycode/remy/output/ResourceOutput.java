package com.tastycode.remy.output;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

/**
 * Outputs utterances to any Spring resource that can be
 * represented as a {@link URL} and opened via 
 * {@link URL#openConnection()}. 
 * 
 * Automatically opens output stream to resource on initialization
 * and closes it on destruction.  
 * 
 * @author broberts
 */
public class ResourceOutput extends TextOutput implements InitializingBean, DisposableBean {
	private Resource resource;
	
	public void afterPropertiesSet() throws Exception {
		openResource();
	}

	protected void openResource() throws IOException {
		Assert.notNull(resource, "Must set resource");
		
		URL url = resource.getURL();
		URLConnection connection = url.openConnection();
		OutputStream outputStream = connection.getOutputStream();
		setOut(new PrintStream(outputStream));
	}

	public void destroy() throws Exception {
		getOut().close();
	}
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
