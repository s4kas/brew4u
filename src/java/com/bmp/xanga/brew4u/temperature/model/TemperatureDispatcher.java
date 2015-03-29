package com.bmp.xanga.brew4u.temperature.model;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TemperatureDispatcher implements Observer {

	private List<Observer> tempOneObs = new ArrayList<Observer>();
	private List<Observer> tempTwoObs = new ArrayList<Observer>();
	private List<Observer> errorObs = new ArrayList<Observer>();
	private Observer mainObserver;
	private boolean firstTime = true;
	
	public void addTempOneObs(Observer e) {
		this.tempOneObs.add(e);
	}
	
	public void addTempTwoObs(Observer e) {
		this.tempTwoObs.add(e);
	}
	
	public void addErrorObs(Observer e) {
		this.errorObs.add(e);
	}
	
	public void setMainObserver(Observer e) {
		this.mainObserver = e;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof TemperatureList) { //temp ok
			TemperatureList tempList = (TemperatureList) arg;
			if (tempList.getTemperatures().size() == 2) {
				String temp1 = tempList.getTemperatures().get(0).getTemp();
				alertObservers(o, tempOneObs, temp1);
				
				String temp2 = tempList.getTemperatures().get(1).getTemp();
				alertObservers(o, tempTwoObs, temp2);
				
				//alert the main observer with open Temperature
				if (firstTime) {
					mainObserver.update(o, DispatcherEvent.OPEN_LEFT_TEMP);
					mainObserver.update(o, DispatcherEvent.OPEN_RIGHT_TEMP);
					firstTime = false;
				}
				
			} else if (tempList.getTemperatures().size() == 1) {
				String temp1 = tempList.getTemperatures().get(0).getTemp();
				alertObservers(o, tempOneObs, temp1);
				
				//alert the main observer with open Temperature
				if (firstTime) {
					mainObserver.update(o, DispatcherEvent.OPEN_LEFT_TEMP);
					firstTime = false;
				}
			}
			
		} else if (arg instanceof Exception) { //error
			Exception ex = (Exception)arg;
	    	StringWriter sw = new StringWriter();
	    	PrintWriter pw = new PrintWriter(sw);
	    	ex.printStackTrace(pw);
	    	String error = sw.toString();
			alertObservers(o, errorObs, error);
			
			//alert the main observer with show Error
			mainObserver.update(o, DispatcherEvent.SHOW_ERROR_MESSAGE);
		}
	}
	
	private void alertObservers(Observable o, List<Observer> list, String msg) {
		for (Observer e : list) {
			e.update(o, msg);
		}
	}
}
