package com.bmp.xanga.brew4u.menubar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.bmp.xanga.brew4u.menubar.portselection.PortSelectionController;
import com.bmp.xanga.brew4u.temperature.model.TemperatureService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MenuBarController implements Initializable {
	
	@FXML
	private Menu file;
	@FXML
	private MenuItem openRecipe;
	@FXML
	private MenuItem close;
	
	@FXML
	private Menu options;
	@FXML
	private MenuItem selectTerm;
	
	@FXML
	private Menu help;
	@FXML
	private MenuItem about;
	
	private ResourceBundle bundle;
	private TemperatureService tempService;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.bundle = rb;
	}
	
	public void initData(TemperatureService tempService) {
		this.tempService = tempService;
	}
	
	public void handleOpenRecipe(ActionEvent event) {
		System.out.println("handleOpenRecipe");
	}
	
	public void handleClose(ActionEvent event) {
		System.out.println("handleClose");
	}
	
	public void handleSelectTerm(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setResources(bundle);
	        loader.setLocation(PortSelectionController.class.getResource("views/PortSelection.fxml"));
	        loader.load();
	        ((PortSelectionController)loader.getController()).initData(tempService);
	        ((PortSelectionController)loader.getController()).showAndTreatResult();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void handleAbout(ActionEvent event) {
		System.out.println("handleAbout");
	}


}
