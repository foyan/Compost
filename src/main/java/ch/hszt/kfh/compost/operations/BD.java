package ch.hszt.kfh.compost.operations;

import ch.hszt.kfh.compost.Compost;

public class BD extends AbstractDirectBranch {

	@Override
	protected String opCodeString() {
		return "00100xxxxxxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "BD";
	}

	@Override
	public void exec(Compost compost, boolean[] argument) throws Exception {
		compost.jumpAbsolute(getAddress(argument));
	}

}
