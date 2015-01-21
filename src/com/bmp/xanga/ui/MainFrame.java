package com.bmp.xanga.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class MainFrame implements ComponentListener {
	
	private static final String FRAME_TITLE = "XANGA";
	private static final int FRAME_WIDTH = 700;
	private static final int FRAME_HEIGHT = 500;
	
    private AppPanel appPanel;
    private Container pane;
    private JFrame frame;
	
	public void startUI() {
		//-------------------------------------------------------
        frame = new JFrame(FRAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setBackground(Color.BLACK);
        frame.setPreferredSize(d);
        frame.pack();
        frame.setVisible(true);
        frame.addComponentListener(this);
        pane = frame.getContentPane();
        pane.setBackground(Color.RED);
        //-------------------------------------------------------
        
        appPanel = new AppPanel();
        appPanel.setLayout(new BoxLayout(appPanel, BoxLayout.Y_AXIS));
        pane.add(appPanel);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
