package com.bmp.xanga.ui;

import javax.annotation.PostConstruct;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.springframework.stereotype.Component;

@Component
public class MainApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		StackPane stack = new StackPane();
		
		Scene scene = new Scene(stack, 300, 250);
		stage.setTitle("Welcome to JavaFX!");
		stage.setScene(scene);
		stage.show();
	}

	@Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }

    public static void main(String[] args) {
        launch(args);
    }
	
}
