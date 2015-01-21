package com.bmp.xanga.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Observable;
import com.bmp.xanga.beans.TemperatureList;
import com.fasterxml.jackson.databind.ObjectMapper;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class TemperatureService extends Observable implements SerialPortEventListener {

	private static final String ERROR = "ERRO";
	
	private SerialPort serialPort;
	
	/**
	 * A BufferedReader which will be fed by a InputStreamReader 
	 * converting the bytes into characters 
	 * making the displayed results codepage independent
	 */
	private BufferedReader input;
	
	/** The output stream to the port */
	@SuppressWarnings("unused")
	private OutputStream output;
	
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;
	
	public void initialize(String port) {
        try {
        	// open serial port, and use class name for the appName.
        	//"/dev/tty.usbmodem1421"
        	CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(port);
        	serialPort = (SerialPort) portId.open(this.getClass().getName(),
        			TIME_OUT);
	
        	// set port parameters
        	serialPort.setSerialPortParams(DATA_RATE,
        			SerialPort.DATABITS_8,
        			SerialPort.STOPBITS_1,
        			SerialPort.PARITY_NONE);
	
        	// open the streams
        	input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        	output = serialPort.getOutputStream();
	
        	// add event listeners
        	serialPort.addEventListener(this);
        	serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
        	alertObservers(ERROR);
        }
	}
	
	public List<String> getPorts() {
		List<String> ports = new ArrayList<String>();
		Enumeration<CommPortIdentifier> enumer = CommPortIdentifier.getPortIdentifiers();
		while (enumer.hasMoreElements()) {
			ports.add(enumer.nextElement().getName());
		}
		return ports;
	}
	
	/**
	* This should be called when you stop using the port.
	* This will prevent port locking on platforms like Linux.
	*/
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}
	
	/**
	* Handle an event on the serial port. Read the data and print it.
	*/
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine=input.readLine();
				ObjectMapper mapper = new ObjectMapper();
				TemperatureList temp = mapper.readValue(inputLine,TemperatureList.class);
				alertObservers(temp);
			} catch (Exception e) {
				alertObservers(e.toString());
			}
		}
		
	// Ignore all the other eventTypes, but you should consider the other ones.
	}
	
	private void alertObservers(Object message) {
		setChanged();
		notifyObservers(message);
	}
}
