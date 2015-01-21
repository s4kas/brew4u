package com.bmp.xanga.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.bmp.xanga.service.TemperatureService;

public class AppPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static final Dimension VERTICAL_GAP_SIZE = new Dimension(0,25);
	private static final Dimension PORT_CHOOSE_SIZE = new Dimension(650, 200);
	private static final Dimension TEMP_READ_SIZE = new Dimension(650, 200);
	
	@SuppressWarnings("unused")
	private MainFrame pane;
	private PortChoosePanel portChoosePanel;
	private TemperatureReadPanel tempReadPanel;
	private TemperatureService temperatureService;
	
	public AppPanel() {
		//space
        this.add(Box.createRigidArea(VERTICAL_GAP_SIZE));
		
        this.portChoosePanel = new PortChoosePanel(this);
        this.portChoosePanel.setLayout(new BoxLayout(this.portChoosePanel, 
        		BoxLayout.Y_AXIS));
        this.portChoosePanel.setPreferredSize(PORT_CHOOSE_SIZE);
        this.portChoosePanel.setMaximumSize(PORT_CHOOSE_SIZE);
        this.add(portChoosePanel);
        this.temperatureService = new TemperatureService();
        this.portChoosePanel.startTemperaturePortChoose(temperatureService.getPorts());
        
        //space
        this.add(Box.createRigidArea(VERTICAL_GAP_SIZE));
        
        //start temperature panel
      	this.tempReadPanel = new TemperatureReadPanel();
      	this.tempReadPanel.setLayout(new GridLayout(2,2));
      	this.tempReadPanel.setPreferredSize(TEMP_READ_SIZE);
        this.tempReadPanel.setMaximumSize(TEMP_READ_SIZE);
      	this.add(tempReadPanel);
      	this.tempReadPanel.startTemperatureReading();
      	//start observing the temperature service
      	temperatureService.addObserver((Observer) tempReadPanel);
	}
	
	public void startTemperatureReading(String port) {
		//start temperature service
		this.temperatureService.initialize(port);
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		t.start();
	}
}
