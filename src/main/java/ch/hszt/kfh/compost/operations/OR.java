package ch.hszt.kfh.compost.operations;

import java.util.List;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.RegisterId;

public class OR extends Operation {

	@Override
	protected String opCodeString() {
		return "0000xx110xxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "OR";
	}

	@Override
	public void exec(Compost compost, boolean[] argument) throws Exception {
		MemCell reg1 = compost.getRegister(RegisterId.ACCUM);
		MemCell reg2 = getRegister(compost, new boolean[] { argument[0], argument[1] });
		for (int i = 0; i < reg1.getSize(); i++) {
			reg1.setBit(i, reg1.getBit(i) || reg2.getBit(i));
		}
	}

	@Override
	public boolean[] addArguments(boolean[] opCode, List<String> arguments) throws Exception {
		if (arguments.size() != 1) {
			throw new Exception("OR requires one argument.");
		}
		boolean[] id = getRegisterIdArgumentFromMnemonic(arguments.get(0));
		opCode[4] = id[0];
		opCode[5] = id[1];
		return opCode;
	}

}
