package com.bmp.xanga;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Brew4u extends Application {

	private ClassPathXmlApplicationContext context;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		//all major beans are created in the spring xml so to be available in all app by DI.
		String[] contextPaths = new String[] {"classpath:spring-context.xml"};
		context = new ClassPathXmlApplicationContext(contextPaths);
		
		//start ui
		((MainApp)context.getBean("mainApp")).init(stage);
	}
	
	@Override
	public void stop() {
		try {
			super.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (context != null) {
			context.close();
		}
	}
}