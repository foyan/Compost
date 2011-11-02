package ch.hszt.kfh.compost.operations;

import java.util.List;

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
	public int getArgumentCount() {
		return 2;
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

	@Override
	public boolean[] addArguments(boolean[] opCode, List<String> arguments) throws Exception {
		boolean[] reg = getRegisterIdArgumentFromMnemonic(arguments.get(0));
		int addr = Integer.parseInt(arguments.get(1));
		boolean[] address = Tools.toBinary(addr, 10);
		opCode[4] = reg[0];
		opCode[5] = reg[1];
		for (int i = 0; i < address.length; i++) {
			opCode[6 + i] = address[i];
		}
		return opCode;
	}

}
