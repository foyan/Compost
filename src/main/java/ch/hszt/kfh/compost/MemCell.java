package ch.hszt.kfh.compost;

public class MemCell {
	
	private int size;
	private boolean[] bits;
	
	public MemCell(int size) {
		this.size = size;
		bits = new boolean[size];
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
	}
	
	public boolean getBit(int index) {
		return bits[index];
	}
	
	public void setBit(int index, boolean bit) {
		bits[index] = bit;
	}

}
