package ch.hszt.kfh.compost.ui;

import java.util.Observable;

public class ManualObservable extends Observable {

	@Override
	public void notifyObservers(Object obj) {
		setChanged();
		super.notifyObservers(obj);
	}
	
}
