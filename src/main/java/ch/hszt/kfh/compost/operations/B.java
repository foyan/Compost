package ch.hszt.kfh.compost.operations;

import java.util.List;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.Tools;

public class B extends Operation {

	@Override
	protected String opCodeString() {
		return "0001xx00xxxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "B";
	}

	@Override
	public int getArgumentCount() {
		return 1;
	}

	@Override
	public void exec(Compost compost, boolean[] argument) throws Exception {
		MemCell reg2 = getRegister(compost, new boolean[] { argument[0], argument[1] });
		int address = Tools.fromComplement(reg2.getBits());
		compost.jumpAbsolute(address);
	}

	@Override
	public boolean[] addArguments(boolean[] opCode, List<String> arguments) throws Exception {
		boolean[] id = getRegisterIdArgumentFromMnemonic(arguments.get(0));
		opCode[4] = id[0];
		opCode[5] = id[1];
		return opCode;
	}

	@Override
	public String decodeArgument(boolean[] argument) {
		return getMnemonicRegisterId(new boolean[] { argument[0], argument[1] }).name();
	}

}
