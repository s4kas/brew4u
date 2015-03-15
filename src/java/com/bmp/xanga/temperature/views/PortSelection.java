package com.bmp.xanga.temperature.views;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.bmp.xanga.temperature.service.TemperatureService;

import javafx.scene.control.ChoiceDialog;

@Component
public class PortSelection {

	@Autowired
	private MessageSource messageSource;
	
	private ChoiceDialog<String> dialog;
	
	public PortSelection() {
		this.dialog = new ChoiceDialog<>("", TemperatureService.getPorts());
		this.dialog.setTitle(messageSource.getMessage("test.cenas", null, null));
	}
	
	public String showAndGetResponse() {
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}
	
}
