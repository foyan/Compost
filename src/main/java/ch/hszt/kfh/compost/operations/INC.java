package ch.hszt.kfh.compost.operations;

import java.util.List;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.Tools;

public class INC extends AbstractAddition {

	@Override
	public boolean[] getOperand(Compost compost, boolean[] argument) {
		return Tools.toComplement(1, 16);
	}
	
	@Override
	public int getArgumentCount() {
		return 0;
	}

	@Override
	protected String opCodeString() {
		return "00000001xxxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "INC";
	}
	
	@Override
	public boolean[] addArguments(boolean[] opCode, List<String> arguments) throws Exception {
		return opCode;
	}
	

}
