package com.bmp.xanga.views;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class Error implements Observer {
	
	private Alert alert;
	private String exceptionText;
	
	public void showAndWait() {
		alert = new Alert(AlertType.ERROR);
    	alert.setTitle("");
    	alert.setHeaderText("");
    	alert.setContentText("");
    	
    	TextArea textArea = new TextArea(this.exceptionText);
    	textArea.setEditable(false);
    	textArea.setWrapText(true);

    	textArea.setMaxWidth(Double.MAX_VALUE);
    	textArea.setMaxHeight(Double.MAX_VALUE);
    	GridPane.setVgrow(textArea, Priority.ALWAYS);
    	GridPane.setHgrow(textArea, Priority.ALWAYS);

    	GridPane expContent = new GridPane();
    	expContent.setMaxWidth(Double.MAX_VALUE);
    	expContent.add(textArea, 0, 0);

    	// Set expandable Exception into the dialog pane.
    	alert.getDialogPane().setExpandableContent(expContent);
    	
    	alert.showAndWait();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			this.exceptionText = (String)arg;
		}
	}
}
