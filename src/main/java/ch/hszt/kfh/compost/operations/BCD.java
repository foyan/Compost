package ch.hszt.kfh.compost.operations;

import ch.hszt.kfh.compost.Compost;

public class BCD extends AbstractDirectBranch {

	@Override
	protected String opCodeString() {
		return "00111xxxxxxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "BCD";
	}

	@Override
	public void exec(Compost compost, boolean[] argument) throws Exception {
		if (compost.getCarryBit()) {
			compost.jumpAbsolute(getAddress(argument));
		}
	}

}
