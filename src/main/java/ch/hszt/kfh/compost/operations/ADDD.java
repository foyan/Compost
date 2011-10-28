package ch.hszt.kfh.compost.operations;

import ch.hszt.kfh.compost.Compost;

public class ADDD extends AbstractAddition {

	@Override
	protected String opCodeString() {
		return "1xxxxxxxxxxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "ADDD";
	}
	
	@Override
	public boolean[] getOperand(Compost compost, boolean[] argument) {
		boolean[] operand = new boolean[argument.length + 1];
		for (int i = 0; i < argument.length; i++) {
			operand[i+1] = argument[i];
		}
		operand[0] = argument[0];
		return operand;
	}

}
