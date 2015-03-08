package com.bmp.xanga.ui.arc;

import java.util.Random;

import javafx.scene.paint.Color;

public abstract class AbstractArc implements IArc {

	public static Color BLUE1 = Color.rgb(126, 166, 212, 0.6);
    public static Color BLUE2 = Color.rgb(126, 166, 222, 0.5);
    public static Color BLUE3 = Color.rgb(130, 166, 230, 0.5);
    public static Color GREEN1 = Color.rgb(130, 230, 166, 0.5);
    public static Color RED1 = Color.rgb(230, 130, 166, 0.5);
	
	public ArcPiece[] createRandomArcs(int num, Color color, double radius) {
        final ArcPiece[] manyPieces = new ArcPiece[num];
        for (int i=0; i<num; i++) {
            manyPieces[i] = randomArcPiece(color, radius);
        }
        return manyPieces;
    }
    
    public ArcPiece randomArcPiece(Color color, double radius) {
 
        int width =  randomIntRange(60, (int) radius * 2);
        int randomStrokeWidth = randomIntRange(1,10);
        int randomStartAngle = randomIntRange(1, 270);
        int randomExtentAngle = randomIntRange(10, 360-randomStartAngle);
        long randomMillis = randomIntRange(0, 33);
        Color someColor = color;
        if (color == null) {
            someColor =  BLUE1;
        }
        final ArcPiece arcPiece = ArcPieceBuilder.create()
                .strokeColor(someColor)
                .strokeWidth(randomStrokeWidth)
                .x(radius - (width/2))
                .y(radius - (width/2))
                .w(width)
                .h(width)
                .startAngle(randomStartAngle)
                .arcExtent(randomExtentAngle)
                .displayTimePerFrameMillis(randomMillis)
                .pixelsToMove(2)
                .build();
        arcPiece.clockwise = new Random().nextBoolean();
 
        return arcPiece;
    }
    
    public int randomIntRange(int min, int max) {
        Random rand = new Random();
        int range = max - min + 1;
        return rand.nextInt(range) + min;
    }
    
}
