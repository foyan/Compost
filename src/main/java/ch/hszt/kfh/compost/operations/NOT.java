package ch.hszt.kfh.compost.operations;

import java.util.List;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.RegisterId;

public class NOT extends Operation {

	@Override
	protected String opCodeString() {
		return "00000000xxxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "NOT";
	}

	@Override
	public int getArgumentCount() {
		return 0;
	}

	@Override
	public void exec(Compost compost, boolean[] argument) throws Exception {
		MemCell reg1 = compost.getRegister(RegisterId.ACCUM);
		for (int i = 0; i < reg1.getSize(); i++) {
			reg1.setBit(i, !reg1.getBit(i));
		}
	}

	@Override
	public boolean[] addArguments(boolean[] opCode, List<String> arguments) throws Exception {
		return opCode;
	}

	@Override
	public String decodeArgument(boolean[] argument) {
		return "";
	}

}
