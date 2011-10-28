package ch.hszt.kfh.compost.operations;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.Tools;

public class DEC extends AbstractAddition {

	@Override
	public boolean[] getOperand(Compost compost, boolean[] argument) {
		return Tools.toComplement(-1, 16);
	}

	@Override
	protected String opCodeString() {
		return "00000100xxxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "DEC";
	}

}
