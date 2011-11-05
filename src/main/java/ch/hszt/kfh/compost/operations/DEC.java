package ch.hszt.kfh.compost.operations;

import java.util.List;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.Tools;

public class DEC extends AbstractAddition {

	@Override
	public boolean[] getOperand(Compost compost, boolean[] argument) {
		return Tools.toComplement(-1, 16);
	}

	@Override
	protected String opCodeString() {
		return "00000100xxxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "DEC";
	}

	@Override
	public int getArgumentCount() {
		return 0;
	}

	@Override
	public boolean[] addArguments(boolean[] opCode, List<String> arguments) throws Exception {
		return opCode;
	}

	@Override
	public String decodeArgument(boolean[] argument) {
		return getMnemonicRegisterId(new boolean[] { argument[0], argument[1] }).name();
	}

}
