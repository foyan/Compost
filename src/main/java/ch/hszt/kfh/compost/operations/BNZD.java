package ch.hszt.kfh.compost.operations;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.RegisterId;
import ch.hszt.kfh.compost.Tools;

public class BNZD extends AbstractDirectBranch {

	@Override
	protected String opCodeString() {
		return "00101xxxxxxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "BNZD";
	}

	@Override
	public void exec(Compost compost, boolean[] argument) throws Exception {
		MemCell reg = compost.getRegister(RegisterId.ACCUM);
		if (Tools.fromBinary(reg.getBits()) != 0) {
			compost.jumpAbsolute(getAddress(argument));
		}
	}

}
