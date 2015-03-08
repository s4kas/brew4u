package com.bmp.xanga.ui;

import javafx.application.Platform;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;

import org.springframework.stereotype.Component;

@Component
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1621480104856117251L;

	private final MainPanel mainPanel = new MainPanel();
	
	@PostConstruct
	public void init() {
		setName("Nome");
		add(mainPanel);
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	mainPanel.init();
            }
       });
	}
	
}
