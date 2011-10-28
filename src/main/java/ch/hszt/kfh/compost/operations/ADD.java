package ch.hszt.kfh.compost.operations;

import ch.hszt.kfh.compost.Compost;

public class ADD extends AbstractAddition {

	@Override
	protected String opCodeString() {
		return "0000xx111xxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "ADD";
	}

	public boolean[] getOperand(Compost compost, boolean[] argument) {
		return getRegister(compost, new boolean[] { argument[0], argument[1] }).getBits();
	}
	
}
