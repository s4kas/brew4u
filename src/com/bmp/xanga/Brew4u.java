package com.bmp.xanga;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.bmp.xanga.ui.MainFrame;

public class Brew4u {

	public static void main(String[] args) {

		// Set System L&F
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//start UI
				MainFrame mainFrame = new MainFrame();
				mainFrame.startUI();
			}
		});
	}
}
