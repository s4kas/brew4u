package com.bmp.xanga.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PortChoosePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final String HEADER_TEXT = "Escolha o dispositivo:";
	private static final float HEADER_TEXT_SIZE = 32f;
	private static final String ACCEPT_TEXT = "Aceitar";
	private static final float ACCEPT_TEXT_SIZE = 12f;
	
	private static final Dimension PORT_COMBO_SIZE = new Dimension(400,25);
	private static final Dimension VERTICAL_GAP_SIZE = new Dimension(0,25);
	
	private JLabel headerLabel;
	private JComboBox<String> ports;
	private JButton acceptButton;
	private AppPanel appPanel;
	
	public PortChoosePanel(AppPanel appPanel) {
		this.appPanel = appPanel;
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public void startTemperaturePortChoose(List<String> modelPorts) {
		this.add(Box.createRigidArea(VERTICAL_GAP_SIZE));
		this.headerLabel = new JLabel(HEADER_TEXT);
		this.headerLabel.setFont(this.headerLabel.getFont().deriveFont(HEADER_TEXT_SIZE));
		this.headerLabel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(headerLabel);
		this.add(Box.createRigidArea(VERTICAL_GAP_SIZE));

		this.ports = new JComboBox<String>(modelPorts.toArray(new String[modelPorts.size()]));
		this.ports.setAlignmentX(CENTER_ALIGNMENT);
		this.ports.setMaximumSize(PORT_COMBO_SIZE);
		this.add(ports);
		this.add(Box.createRigidArea(VERTICAL_GAP_SIZE));
		
		this.acceptButton = new JButton(ACCEPT_TEXT);
		this.acceptButton.setFont(this.acceptButton.getFont().deriveFont(ACCEPT_TEXT_SIZE));
		this.acceptButton.setAlignmentX(CENTER_ALIGNMENT);
		this.acceptButton.addActionListener(this);
		this.add(acceptButton);
		this.add(Box.createRigidArea(VERTICAL_GAP_SIZE));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.acceptButton) {
			String port = (String) this.ports.getSelectedItem();
			appPanel.startTemperatureReading(port);
		}
	}

}
