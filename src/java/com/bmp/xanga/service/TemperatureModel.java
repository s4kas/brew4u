package com.bmp.xanga.service;

import java.util.Observable;
import java.util.Observer;

public class TemperatureModel implements Observer {

	private String temp;
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			this.temp = (String) arg;
		}
		
	}
}
