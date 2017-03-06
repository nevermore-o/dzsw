package com.open.spring;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SpringContext {
	
	private static WebApplicationContext appContext;
	
	public static WebApplicationContext getInstance(){
		if(appContext == null)
			appContext = ContextLoader.getCurrentWebApplicationContext();
		return appContext;
	}
}
