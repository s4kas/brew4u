package com.bmp.xanga.controllers;

import com.bmp.xanga.views.Error;

public class ErrorController {

	private Error error;
	
	public void showError() {
		this.error.showAndWait();
	}
}
