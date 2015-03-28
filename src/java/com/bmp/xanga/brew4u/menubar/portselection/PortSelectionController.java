package com.bmp.xanga.brew4u.menubar.portselection;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ButtonBar.ButtonData;

import com.bmp.xanga.brew4u.temperature.model.TemperatureService;

public class PortSelectionController implements Initializable {

	private TemperatureService temperatureService;
	private ChoiceDialog<String> dialog;
	private ResourceBundle bundle;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.bundle = resources;
		
		List<String> ports = TemperatureService.getPorts();
		String selectedOption = "";
		if (!ports.isEmpty()) {
			selectedOption = ports.get(0);
		}
		
		this.dialog = new ChoiceDialog<>(selectedOption, ports);
		this.dialog.setTitle(bundle.getString("PortSelection.title"));
		this.dialog.setHeaderText(bundle.getString("PortSelection.header"));
		
		ButtonType buttonTypeCancel = new ButtonType(bundle.getString("PortSelection.button.cancel"), ButtonData.CANCEL_CLOSE);
		ButtonType buttonTypeOk = new ButtonType(bundle.getString("PortSelection.button.ok"), ButtonData.OK_DONE);

		this.dialog.getDialogPane().getButtonTypes().setAll(buttonTypeCancel, buttonTypeOk);
		
		this.dialog.setResultConverter((dialogButton) -> {
		    ButtonBar.ButtonData data = dialogButton == null ? null : dialogButton.getButtonData(); 
		    return data == ButtonBar.ButtonData.OK_DONE ? dialog.getSelectedItem() : null; 
		});
	}
	
	public void initData(TemperatureService temperatureService) {
		this.temperatureService = temperatureService;
	}
	
	public void showAndTreatResult() {
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			temperatureService.initialize(dialog.getSelectedItem());
		}
	}
}
