package com.bmp.xanga.ui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bmp.xanga.beans.TemperatureList;

public class TemperatureReadPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	
	private static final String TEMP_TEXT_1 = "Temperatura 1";
	private static final String TEMP_TEXT_2 = "Temperatura 2";
	private static final float TEMP_TEXT_SIZE = 32f;
	private static final float TEMP_MEASURE_TEXT_SIZE = 64f;
	
	private JLabel tempLabel1, tempMeasureLabel1, tempLabel2, tempMeasureLabel2;
	
	public TemperatureReadPanel() {
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public void startTemperatureReading() {
		this.tempLabel1 = new JLabel(TEMP_TEXT_1);
		this.tempLabel1.setFont(this.tempLabel1.getFont().deriveFont(TEMP_TEXT_SIZE));
		this.tempLabel1.setHorizontalAlignment(JLabel.CENTER);
		this.add(tempLabel1);
		
		this.tempLabel2 = new JLabel(TEMP_TEXT_2);
		this.tempLabel2.setFont(this.tempLabel2.getFont().deriveFont(TEMP_TEXT_SIZE));
		this.tempLabel2.setHorizontalAlignment(JLabel.CENTER);
		this.add(tempLabel2);
		
		this.tempMeasureLabel1 = new JLabel("N/D");
		this.tempMeasureLabel1.setFont(this.tempMeasureLabel1.getFont().deriveFont(TEMP_MEASURE_TEXT_SIZE));
		this.tempMeasureLabel1.setHorizontalAlignment(JLabel.CENTER);
		this.add(tempMeasureLabel1);
		
		this.tempMeasureLabel2 = new JLabel("N/D");
		this.tempMeasureLabel2.setFont(this.tempMeasureLabel2.getFont().deriveFont(TEMP_MEASURE_TEXT_SIZE));
		this.tempMeasureLabel2.setHorizontalAlignment(JLabel.CENTER);
		this.add(tempMeasureLabel2);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof TemperatureList) {
			TemperatureList newTemperature = (TemperatureList) arg;
			if (newTemperature.getTemperatures().size() >= 2) {
				this.tempMeasureLabel1.setText(newTemperature.getTemperatures().get(0).getTemp());
				this.tempMeasureLabel2.setText(newTemperature.getTemperatures().get(1).getTemp());
			} else if (newTemperature.getTemperatures().size() >= 1) {
				this.tempMeasureLabel1.setText(newTemperature.getTemperatures().get(0).getTemp());
				this.tempMeasureLabel2.setText("N/D");
			}
			
		} else if (arg instanceof String) {
			String error = (String) arg;
			this.tempMeasureLabel1.setText(error);
			this.tempMeasureLabel2.setText(error);
		}
	}
}
