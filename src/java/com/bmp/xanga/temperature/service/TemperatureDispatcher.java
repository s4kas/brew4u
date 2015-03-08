package com.bmp.xanga.temperature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import com.bmp.xanga.temperature.beans.TemperatureList;

public class TemperatureDispatcher implements Observer {

	private List<Observer> tempOneObs = new ArrayList<Observer>();
	private List<Observer> tempTwoObs = new ArrayList<Observer>();
	
	public void addTempOneObs(Observer e) {
		this.tempOneObs.add(e);
	}
	
	public void addTempTwoObs(Observer e) {
		this.tempTwoObs.add(e);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof TemperatureList) {
			TemperatureList tempList = (TemperatureList) arg;
			if (tempList.getTemperatures().size() == 2) {
				String temp1 = tempList.getTemperatures().get(0).getTemp();
				alertObservers(o, tempOneObs, temp1);
				
				String temp2 = tempList.getTemperatures().get(1).getTemp();
				alertObservers(o, tempTwoObs, temp2);
			} else if (tempList.getTemperatures().size() == 1) {
				String temp1 = tempList.getTemperatures().get(0).getTemp();
				alertObservers(o, tempOneObs, temp1);
			}
		}
	}
	
	private void alertObservers(Observable o, List<Observer> list, String msg) {
		for (Observer e : list) {
			e.update(o, msg);
		}
	}
}
