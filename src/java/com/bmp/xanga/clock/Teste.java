package com.bmp.xanga.clock;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.controlsfx.control.SegmentedButton;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import sun.audio.AudioPlayer;
import de.jensd.fx.fontawesome.AwesomeDude;
import de.jensd.fx.fontawesome.AwesomeIcon;
import de.jensd.fx.fontawesome.AwesomeStyle;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Teste extends Application {

	private static final MediaPlayer ALERT_AUDIOCLIP = new MediaPlayer(new Media(Teste.class.getClassLoader().getResource("tornado.mp3").toString()));
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		/*
		Canvas canvas = new Canvas(1280, 800);

	    GraphicsContext gc = canvas.getGraphicsContext2D();
	    gc.setLineCap( StrokeLineCap.ROUND );
	    gc.setLineJoin( StrokeLineJoin.ROUND );
	    gc.setLineWidth( 1 );
		Group root = new Group(canvas);
		root.getChildren().add(getTopPath(Color.DODGERBLUE, Color.DODGERBLUE, 2));
		root.getChildren().add(getTopRightPath(Color.DODGERBLUE, Color.DODGERBLUE, 2));
		root.getChildren().add(getTopLeftPath(Color.DODGERBLUE, Color.DODGERBLUE, 2));
		PerspectiveTransform e = new PerspectiveTransform();
		e.setUlx(10);    // Upper left
		e.setUly(10);
		e.setUrx(52);    // Upper right
		e.setUry(10);
		e.setLlx(0);      // Lower left
		e.setLly(38);
		e.setLrx(42);    // Lower right
		e.setLry(38);
		root.setEffect(e);
		*/
		
		Button startStep = AwesomeDude.createIconButton(AwesomeIcon.PLAY, "Nice!");
        Button stopStep = AwesomeDude.createIconButton(AwesomeIcon.STOP, "Download");
        Button nextStep = AwesomeDude.createIconButton(AwesomeIcon.LONG_ARROW_RIGHT, "Download");
        
        //Teste.ALERT_AUDIOCLIP.play();
        
        final Font font = Font.loadFont(Teste.class.getClassLoader().getResource("digital-7.ttf").toExternalForm(), 200);
        Text text = new Text("00:00:00");
        text.setFont(font);
        text.setFill(Color.DODGERBLUE);
        
		HBox root = new HBox();
		root.getChildren().addAll(startStep, stopStep, nextStep, text);
		root.setStyle("-fx-background-color: #222222;");
		Scene scene = new Scene(root, 1280, 800);
		scene.getStylesheets().addAll(AwesomeStyle.BLUE.getStylePath());
		
		stage.setScene(scene);
		stage.show();
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(
			new KeyFrame(Duration.seconds(1), 
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						text.setText(sdf.format(Calendar.getInstance().getTime()));
					}
				}
			)
		);
		timeline.playFromStart();
		
	}
	
	/*
	private Path getTopPath(Color fill, Color stroke, float times) {
		Path path = new Path();
		path.setFill(fill);
		path.setStroke(stroke);
		// drawing top -> right -> bottom -> left
		MoveTo moveTo = new MoveTo();
		moveTo.setX(12.0f * times);
		moveTo.setY(11.0f * times);
		
		ArcTo arcTo = new ArcTo();
		arcTo.setX(13.0f * times);
		arcTo.setY(10.0f * times);
		arcTo.setRadiusX(1.0f * times);
		arcTo.setRadiusY(1.0f * times);
		arcTo.setLargeArcFlag(false);
		arcTo.setSweepFlag(true);
		
		HLineTo hLineTo = new HLineTo();
		hLineTo.setX(47.0f * times);
		
		ArcTo arcTo2 = new ArcTo();
		arcTo2.setX(48.0f * times);
		arcTo2.setY(11.0f * times);
		arcTo2.setRadiusX(1.0f * times);
		arcTo2.setRadiusY(1.0f * times);
		arcTo2.setLargeArcFlag(false);
		arcTo2.setSweepFlag(true);
		
		LineTo lineTo = new LineTo();
		lineTo.setX(42.0f * times);
		lineTo.setY(16.0f * times);
		
		HLineTo hLineTo2 = new HLineTo();
		hLineTo2.setX(18.0f * times);
		
		LineTo lineTo2 = new LineTo();
		lineTo2.setX(12.0f * times);
		lineTo2.setY(11.0f * times);

		path.getElements().add(moveTo);
		path.getElements().add(arcTo);
		path.getElements().add(hLineTo);
		path.getElements().add(arcTo2);
		path.getElements().add(lineTo);
		path.getElements().add(hLineTo2);
		path.getElements().add(lineTo2);
		
		return path;
	}
	
	private Path getTopRightPath(Color fill, Color stroke, float times) {
		Path path = new Path();
		path.setFill(fill);
		path.setStroke(stroke);
		// drawing left -> bottom -> right -> top
		MoveTo moveTo = new MoveTo();
		moveTo.setX(48.0f * times);
		moveTo.setY(12.0f * times);
		
		LineTo lineTo = new LineTo();
		lineTo.setX(42.5f * times);
		lineTo.setY(17.0f * times);
		
		LineTo lineTo2 = new LineTo();
		lineTo2.setX(42.5f * times);
		lineTo2.setY(38.0f * times);
		
		LineTo lineTo3 = new LineTo();
		lineTo3.setX(45.2f * times);
		lineTo3.setY(41.0f * times);
		
		LineTo lineTo4 = new LineTo();
		lineTo4.setX(48.0f * times);
		lineTo4.setY(38.0f * times);
		
		VLineTo vlineTo = new VLineTo();
		vlineTo.setY(12.0f * times);
		
		path.getElements().add(moveTo);
		path.getElements().add(lineTo);
		path.getElements().add(lineTo2);
		path.getElements().add(lineTo3);
		path.getElements().add(lineTo4);
		path.getElements().add(vlineTo);
		
		return path;
	}
	
	private Path getTopLeftPath(Color fill, Color stroke, float times) {
		Path path = new Path();
		path.setFill(fill);
		path.setStroke(stroke);
		// drawing bottom -> right -> top -> left
		MoveTo moveTo = new MoveTo();
		moveTo.setX(10.0f * times);
		moveTo.setY(11.0f * times);
		
		VLineTo vLineTo = new VLineTo();
		vLineTo.setY(38.0f * times);
		
		LineTo lineTo = new LineTo();
		lineTo.setX(13.5f * times);
		lineTo.setY(41.0f * times);
		
		LineTo lineTo2 = new LineTo();
		lineTo2.setX(16.5f * times);
		lineTo2.setY(38.0f * times);
		
		VLineTo vlineTo = new VLineTo();
		vlineTo.setY(16.0f * times);
		
		LineTo lineTo3 = new LineTo();
		lineTo3.setX(10.0f * times);
		lineTo3.setY(11.0f * times);
		
		path.getElements().add(moveTo);
		path.getElements().add(vLineTo);
		path.getElements().add(lineTo);
		path.getElements().add(lineTo2);
		path.getElements().add(vlineTo);
		path.getElements().add(lineTo3);
		
		return path;
	}
	*/
}
