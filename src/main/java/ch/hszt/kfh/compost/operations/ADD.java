package ch.hszt.kfh.compost.operations;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.RegisterId;

public class ADD extends Operation {

	@Override
	protected String opCodeString() {
		return "0000xx111xxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "ADD";
	}

	@Override
	public void exec(Compost compost, boolean[] argument) throws Exception {
		MemCell reg = getRegister(compost, new boolean[] { argument[0], argument[1] });
		boolean[] a = compost.getRegister(RegisterId.ACCUM).getBits();
		boolean[] b = reg.getBits();
	}

}
