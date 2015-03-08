package com.bmp.xanga.ui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;

public class MainPanel extends JFXPanel {

	private static final long serialVersionUID = 8749602106624576344L;

	public void init() {
		// This method is invoked on the JavaFX thread
        Scene scene = createScene();
        setScene(scene);
	}

	private Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root);
		scene.getStylesheets().add
		 	(MainPanel.class.getResource("MainPanel.css").toExternalForm());

        Text text = new Text();
        text.setX(40);
        text.setY(100);
        text.setText("Welcome JavaFX!");

        root.getChildren().add(text);

        return scene;
    }
	
}
