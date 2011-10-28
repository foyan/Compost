package ch.hszt.kfh.compost.Operations;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.RegisterId;

public abstract class Operation {
	
	protected abstract String opCodeString();
		
	public abstract String mnemonic();
	
	public abstract void exec(Compost compost, boolean[] argument) throws Exception;

	private static boolean[] stringToInstrBitArray(String s, char magicChar) {
		boolean[] arr = new boolean[Compost.INSTR_SIZE];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = s.charAt(i) == magicChar;
		}
		return arr;
	}
	
	public boolean[] opCode() {
		return stringToInstrBitArray(opCodeString(), '1');
	}
	
	public boolean[] opCodeMask() {
		return stringToInstrBitArray(opCodeString(), 'x');
	}
	
	protected MemCell getRegister(Compost compost, boolean[] id) throws Exception {
		if (id.length == 2) {
			if (!id[0] && !id[1]) {
				return compost.getRegister(RegisterId.ACCUM);
			}
			if (!id[0] && id[1]) {
				return compost.getRegister(RegisterId.REG_1);
			}
			if (id[0] && !id[1]) {
				return compost.getRegister(RegisterId.REG_2);
			}
			if (id[0] && id[1]) {
				return compost.getRegister(RegisterId.REG_3);
			}
		}
		throw new Exception("Not implemented.");
	}
	
}
