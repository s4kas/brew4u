package com.bmp.xanga.brew4u.temperature;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import com.bmp.xanga.brew4u.temperature.model.Temperature;
import com.bmp.xanga.brew4u.temperature.model.TemperatureModel;
import com.bmp.xanga.brew4u.temperature.views.ArcTemperature;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TemperatureController implements Initializable, Observer {
	
	private final static int TEMP_RADIUS = 250;
	private final static int CANVAS_RADIUS = TEMP_RADIUS + 20;
	private ArcTemperature temperatureArc;
	private TemperatureModel tempModel = new TemperatureModel();
	
	@FXML
    public void initialize(URL url, ResourceBundle rb) {
    }
	
	public void initData(Color color1, Color color2, Pane pane) {
		//init temperature view
		temperatureArc = new ArcTemperature(15, color1, color2, TEMP_RADIUS);
		
		// create a canvas node
        Canvas canvas = new Canvas(CANVAS_RADIUS, CANVAS_RADIUS);
        // obtain the GraphicsContext (drawing surface)
        final GraphicsContext gc = canvas.getGraphicsContext2D();
		
		// create an animation (update & render loop)
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // update clocks
            	temperatureArc.update(now);
 
                // clear screen
                gc.clearRect(0, 0, CANVAS_RADIUS, CANVAS_RADIUS);

                // save the origin or the current state
                // of the Graphics Context.
                gc.save();
 
                // shift x coord position
                gc.translate(5, 5);
                temperatureArc.draw(gc);
 
                // reset Graphics Context to last saved point.
                // Translate x, y to (0,0)
                gc.restore();
 
            }
        }.start();
        
        pane.getChildren().add(canvas);
				
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
