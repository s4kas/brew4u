package com.bmp.xanga.old;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class RecipePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static final String TITLE = "Receita";
	
	private static final float TITLE_TEXT_SIZE = 32f;
	
	private final JLabel titleLabel;
	private final JTable table;
	
	public RecipePanel() {
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//titulo
		titleLabel = new JLabel(TITLE);
		titleLabel.setFont(titleLabel.getFont().deriveFont(TITLE_TEXT_SIZE));
		this.add(titleLabel, "center, wrap, gaptop 15, gapbottom -25");
		
		//cenas
		table = new JTable();
		this.add(table);
	}
	
}
