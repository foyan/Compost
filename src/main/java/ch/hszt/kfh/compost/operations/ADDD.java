package ch.hszt.kfh.compost.operations;

import java.util.List;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.Tools;

public class ADDD extends AbstractAddition {

	@Override
	protected String opCodeString() {
		return "1xxxxxxxxxxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "ADDD";
	}
	
	@Override
	public boolean[] getOperand(Compost compost, boolean[] argument) {
		boolean[] operand = new boolean[argument.length + 1];
		for (int i = 0; i < argument.length; i++) {
			operand[i+1] = argument[i];
		}
		operand[0] = argument[0];
		return operand;
	}

	@Override
	public boolean[] addArguments(boolean[] opCode, List<String> arguments) throws Exception {
		if (arguments.size() != 1) {
			throw new Exception("ADDD requires one argument.");
		}
		int n = Integer.parseInt(arguments.get(0));
		boolean[] b = Tools.toComplement(n, 15);
		for (int i = 0; i < b.length; i++) {
			opCode[i+1] = b[i];
		}
		return opCode;
	}

}
