package ch.hszt.kfh.compost.operations;

import java.util.List;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;

public class CLR extends Operation {

	@Override
	protected String opCodeString() {
		return "0000xx101xxxxxxx";
	}
		
	@Override
	public String mnemonic() {
		return "CLR";
	}
	
	@Override
	public int getArgumentCount() {
		return 1;
	}

	@Override
	public void exec(Compost compost, boolean[] argument) throws Exception {
		MemCell reg = getRegister(compost, new boolean[] { argument[0], argument[1] });
		reg.setBits(new boolean[reg.getSize()]);
	}
	
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
