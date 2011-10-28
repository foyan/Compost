package ch.hszt.kfh.compost.operations;

import ch.hszt.kfh.compost.AdditionResult;
import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.RegisterId;
import ch.hszt.kfh.compost.Tools;

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
		MemCell accum = compost.getRegister(RegisterId.ACCUM);
		boolean[] a = accum.getBits();
		boolean[] b = reg.getBits();
		AdditionResult r = Tools.add(a, b);
		accum.setBits(r.getData());
		compost.setCarryBit(r.getCarryBit());
	}

}
