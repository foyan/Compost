package ch.hszt.kfh.compost.operations;

import java.util.List;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.RegisterId;

public class SRL extends Operation {

	@Override
	protected String opCodeString() {
		return "00001001xxxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "SRL";
	}

	@Override
	public void exec(Compost compost, boolean[] argument) throws Exception {
		MemCell reg = compost.getRegister(RegisterId.ACCUM);
		compost.setCarryBit(reg.getBit(reg.getSize() - 1));
		for (int i = reg.getSize() - 2; i >= 0; --i) {
			reg.setBit(i + 1, reg.getBit(i));
		}
		reg.setBit(0, false);
	}

	@Override
	public boolean[] addArguments(boolean[] opCode, List<String> arguments) throws Exception {
		if (arguments.size() != 0) {
			throw new Exception("SRL does not require any arguments.");
		}
		return opCode;
	}

}
