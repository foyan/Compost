package ch.hszt.kfh.compost;

import java.util.Observable;

import ch.hszt.kfh.compost.ui.ManualObservable;

public class MemCell {
	
	private int size;
	private boolean[] bits;
	private int address = -1;
	
	public MemCell(int size) {
		this.size = size;
		bits = new boolean[size];
	}
	
	public int getAddress() {
		return address;
	}
	
	public void setAddress(int address) {
		this.address = address;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean[] getBits() {
		return bits.clone();
	}
	
	public void setBits(boolean[] bits) throws Exception {
		if (bits.length != size) {
			throw new Exception("Size does not match.");
		}
		this.bits = bits;
		notifyChangeObservers();
	}
	
	public boolean getBit(int index) {
		return bits[index];
	}
	
	public void setBit(int index, boolean bit) {
		bits[index] = bit;
		notifyChangeObservers();
	}
	
	public void clear() {
		bits = new boolean[size];
		notifyChangeObservers();
	}
	
	private static ManualObservable changeObservable = new ManualObservable();
		
	protected void notifyChangeObservers() {
		changeObservable.notifyObservers(this);
	}
	
	public static Observable getChangeObservable() {
		return changeObservable;
	}

}
