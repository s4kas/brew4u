package com.bmp.xanga.brew4u.temperature;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.bmp.xanga.arc.AbstractArc;
import com.bmp.xanga.brew4u.temperature.model.Temperature;
import com.bmp.xanga.brew4u.temperature.model.TemperatureModel;
import com.bmp.xanga.brew4u.temperature.views.ArcTemperature;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;

public class TemperatureController implements Initializable, Observer {
	
	private ArcTemperature temperatureArc;
	private TemperatureModel tempModel = new TemperatureModel();
	
	@FXML
    public void initialize(URL url, ResourceBundle rb) {
    }
	
	public void initData(Color color1, Color color2) {
		//init temperature view
		temperatureArc = new ArcTemperature(15, AbstractArc.BLUE1, AbstractArc.BLUE2,250);
				
		//bind the view to the model
		temperatureArc.getTemperature().textProperty().bind(tempModel.getTempProperty());
	}
	
	public Temperature getTemperatureModel() {
		return this.getTemperatureModel();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String)
			this.tempModel.setTemp(String.valueOf(arg));
	}
	
	
}
