package com.bmp.xanga.temperature.service;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureList {
	
	@JsonProperty("temperatures")
	private List<Temperature> temperatures;

	public List<Temperature> getTemperatures() {
		return temperatures;
	}

	public void setTemperatures(List<Temperature> temperatures) {
		this.temperatures = temperatures;
	}
	
}
