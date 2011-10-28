package ch.hszt.kfh.compost;

public class AdditionResult {
	
	private boolean[] data;
	private boolean carryBit;
	
	public AdditionResult(boolean[] data, boolean carryBit) {
		this.data = data;
		this.carryBit = carryBit;
	}
	
	public boolean[] getData() {
		return data;
	}
	
	public boolean getCarryBit() {
		return carryBit;
	}

}
