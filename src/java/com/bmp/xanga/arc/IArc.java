package com.bmp.xanga.arc;

import javafx.scene.canvas.GraphicsContext;

public interface IArc {

	void update(long now);
	void draw(GraphicsContext gc);
	
}
