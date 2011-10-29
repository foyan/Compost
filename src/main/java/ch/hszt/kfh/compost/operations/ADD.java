package ch.hszt.kfh.compost.operations;

import java.util.List;

import ch.hszt.kfh.compost.Compost;

public class ADD extends AbstractAddition {

	@Override
	protected String opCodeString() {
		return "0000xx111xxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "ADD";
	}

	@Override
	public boolean[] getOperand(Compost compost, boolean[] argument) {
		return getRegister(compost, new boolean[] { argument[0], argument[1] }).getBits();
	}
	
	@Override
	public boolean[] addArguments(boolean[] opCode, List<String> arguments) throws Exception {
		if (arguments.size() != 1) {
			throw new Exception("ADD requires one argument.");
		}
		boolean[] id = getRegisterIdArgumentFromMnemonic(arguments.get(0));
		opCode[4] = id[0];
		opCode[5] = id[1];
		return opCode;
	}
	
}
