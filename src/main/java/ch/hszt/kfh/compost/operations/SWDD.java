package ch.hszt.kfh.compost.operations;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.Tools;

public class SWDD extends Operation {

	@Override
	protected String opCodeString() {
		return "011xxxxxxxxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "SWDD";
	}

	@Override
	public void exec(Compost compost, boolean[] argument) throws Exception {
		MemCell reg = getRegister(compost, new boolean[] { argument[1], argument[2] });
		boolean[] addr = new boolean[argument.length - 3];
		for (int i = 3; i < argument.length; i++) {
			addr[i - 3] = argument[i];
		}
		int address = Tools.fromBinary(addr);
		boolean[] cellData = reg.getBits();
		for (int i = 0; i < reg.getSize() / Compost.MEM_SIZE; i++) {
			MemCell cell = compost.getMem(address + i);
			for (int j = 0; j < Compost.MEM_SIZE; j++) {
				cell.setBit(j, cellData[j + i * Compost.MEM_SIZE]);
			}
		}
		
	}

}
