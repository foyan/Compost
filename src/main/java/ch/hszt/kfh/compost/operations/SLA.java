package ch.hszt.kfh.compost.operations;

import java.util.List;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.RegisterId;

public class SLA extends Operation {

	@Override
	protected String opCodeString() {
		return "00001000xxxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "SLA";
	}

	@Override
	public void exec(Compost compost, boolean[] argument) throws Exception {
		MemCell reg = compost.getRegister(RegisterId.ACCUM);
		compost.setCarryBit(reg.getBit(1));
		for (int i = 1; i < reg.getSize()-1; i++) {
			reg.setBit(i, reg.getBit(i+1));
		}
		reg.setBit(reg.getSize() - 1, false);
	}

	@Override
	public boolean[] addArguments(boolean[] opCode, List<String> arguments) throws Exception {
		if (arguments.size() != 0) {
			throw new Exception("SLA does not require any arguments.");
		}
		return opCode;
	}

}
