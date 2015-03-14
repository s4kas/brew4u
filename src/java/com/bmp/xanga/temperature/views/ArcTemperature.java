package com.bmp.xanga.temperature.views;

import com.bmp.xanga.arc.AbstractArc;
import com.bmp.xanga.arc.ArcPieceBuilder;
import com.bmp.xanga.arc.IArc;
import com.bmp.xanga.temperature.service.TemperatureModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ArcTemperature extends AbstractArc {

	private IArc longPiece;
	private IArc[] arcPieces;
	private int maxDiameter;
	private double radius;

	private TemperatureModel model;
	public void setTemperatureModel(TemperatureModel model) {
		this.model = model;
	}
 
    public ArcTemperature(int numArcs, Color longPieceColor, Color manyPieceColor, int maxDiameter) {
    	this.maxDiameter = maxDiameter;
        radius = maxDiameter / 2;
        longPiece = ArcPieceBuilder.create()
                .strokeColor(longPieceColor)
                .strokeWidth(5)
                .x(0)
                .y(0)
                .w(maxDiameter)
                .h(maxDiameter)
                .startAngle(45)
                .arcExtent(240)
                .displayTimePerFrameMillis(1000)
                .pixelsToMove(1)
                .build();
 
        arcPieces = createRandomArcs(numArcs, manyPieceColor, maxDiameter / 2);
    }
 
    @Override
    public void update(long now){
        longPiece.update(now);
        for (IArc ap:arcPieces) {
            ap.update(now);
        }
    }
    
    @Override
    public void draw(GraphicsContext gc) {
        longPiece.draw(gc);
        for (IArc ap:arcPieces) {
            ap.draw(gc);
        }
        
        // draw temp
        gc.setFont(Font.font("Calibri", 70));
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText(model.getTemp(), radius, radius + 18 );
        
        gc.setFont(Font.font("Calibri", 25));
        gc.fillText("º C" , maxDiameter - 50, radius - 50);
 
    }
}
