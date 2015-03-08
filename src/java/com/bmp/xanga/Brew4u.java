package com.bmp.xanga;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Brew4u {

	private ClassPathXmlApplicationContext context;
	
	public static void main(String[] args) {
		Brew4u mainApp = new Brew4u();
		mainApp.init();
	}
	    
	public void init() {
		String[] contextPaths = new String[] {"classpath:spring-context.xml"};
		context = new ClassPathXmlApplicationContext(contextPaths);
	}
	
	public void shutdown() {
		if (context != null) {
			context.close();
		}
	}
	
}