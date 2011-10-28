package ch.hszt.kfh.compost.operations;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.Tools;

public class LWDD extends Operation {

	@Override
	protected String opCodeString() {
		return "010xxxxxxxxxxxxx";
	}

	@Override
	public String mnemonic() {
		return "LWDD";
	}

	@Override
	public void exec(Compost compost, boolean[] argument) throws Exception {
		MemCell reg = getRegister(compost, new boolean[] { argument[1], argument[2] });
		boolean[] addr = new boolean[argument.length - 3];
		for (int i = 3; i < argument.length; i++) {
			addr[i - 3] = argument[i];
		}
		int address = Tools.fromBinary(addr);
		for (int i = 0; i < reg.getSize() / Compost.MEM_SIZE; i++) {
			boolean[] cellData = compost.getMem(address + i).getBits();
			for (int j = 0; j < Compost.MEM_SIZE; j++) {
				reg.setBit(j + i * Compost.MEM_SIZE, cellData[j]);
			}
		}
	}
	
	

}
