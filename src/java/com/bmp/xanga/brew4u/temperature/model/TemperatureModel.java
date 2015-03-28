package com.bmp.xanga.brew4u.temperature.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TemperatureModel {

	private StringProperty temp = new SimpleStringProperty();
	
	public String getTemp() {
		return temp.get();
	}

	public void setTemp(String temp) {
		this.temp.set(temp);
	}
	
	public StringProperty getTempProperty() {
		return temp;
	}

	public void setTempProperty(StringProperty temp) {
		this.temp = temp;
	}
	
}
