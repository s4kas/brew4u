package com.bmp.xanga.ui.arc.clock;

import java.util.Calendar;

import com.bmp.xanga.arc.AbstractArc;
import com.bmp.xanga.arc.ArcPieceBuilder;
import com.bmp.xanga.arc.IArc;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/** This class is a container class that maintains
 * all of the parts that comprised of a clock. It is
 * also responsible for updating and drawing itself
 * at each animation frame cycle.
 * The ArcClock contains zero to many ArcPiece objects
 * to be drawn. The clock will also display the time
 * of day.
 *
 * User: cdea
 */
public class ArcClock extends AbstractArc {
 
	private IArc longPiece;
	private IArc[] arcPieces;
	private int maxDiameter;
	private double radius;
 
    public ArcClock(int numArcs, Color longPieceColor, Color manyPieceColor, int maxDiameter) {
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
        
        // draw hour
        gc.setFont(Font.font("Calibri", 40));
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
 
        Calendar dateTime = Calendar.getInstance();
        gc.fillText(String.valueOf(dateTime.get(Calendar.HOUR)), radius, radius + 18 );
        gc.setFont(Font.font("Calibri", 20));
        gc.fillText(String.valueOf(dateTime.get(Calendar.MINUTE)) , maxDiameter - 40, radius - 40 );
        gc.fillText(String.valueOf(dateTime.get(Calendar.SECOND)) , maxDiameter - 40, maxDiameter - 40 );
 
    }
}