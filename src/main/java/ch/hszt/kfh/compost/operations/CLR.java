package ch.hszt.kfh.compost.operations;

import ch.hszt.kfh.compost.*;

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
	public void exec(Compost compost, boolean[] argument) throws Exception {
		MemCell reg = getRegister(compost, new boolean[] { argument[0], argument[1] });
		reg.setBits(new boolean[reg.getSize()]);
	}
	
}
