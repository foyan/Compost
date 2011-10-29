package ch.hszt.kfh.compost.operations;

import java.util.List;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.RegisterId;

public abstract class Operation {
	
	protected abstract String opCodeString();
		
	public abstract String mnemonic();
	
	public abstract void exec(Compost compost, boolean[] argument) throws Exception;
	
	public abstract boolean[] addArguments(boolean[] opCode, List<String> arguments) throws Exception;

	private static boolean[] stringToInstrBitArray(String s, char magicChar, boolean not) {
		boolean[] arr = new boolean[Compost.INSTR_SIZE];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = s.charAt(i) == magicChar;
			if (not) {
				arr[i] = !arr[i];
			}
		}
		return arr;
	}
	
	public boolean[] opCode() {
		return stringToInstrBitArray(opCodeString(), '1', false);
	}
	
	public boolean[] opCodeMask() {
		return stringToInstrBitArray(opCodeString(), 'x', true);
	}
	
	protected MemCell getRegister(Compost compost, boolean[] id) {
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
		return null;
	}
	
	protected boolean[] getRegisterIdArgumentFromMnemonic(String id) throws Exception {
		RegisterId regId = getMnemonicRegisterId(id);
		if (regId == RegisterId.ACCUM) {
			return new boolean[] { false, false };
		}
		if (regId == RegisterId.REG_1) {
			return new boolean[] { false, true };
		}
		if (regId == RegisterId.REG_2) {
			return new boolean[] { true, false };
		}
		if (regId == RegisterId.REG_3) {
			return new boolean[] { true, true };
		}
		return null;
	}
	
	protected RegisterId getMnemonicRegisterId(String id) throws Exception {
		if (id.equals("00")) {
			return RegisterId.ACCUM;
		}
		if (id.equals("01")) {
			return RegisterId.REG_1;
		}
		if (id.equals("10")) {
			return RegisterId.REG_2;
		}
		if (id.equals("11")) {
			return RegisterId.REG_3;
		}
		if (id.equals("ACCUM")) {
			return RegisterId.ACCUM;
		}
		if (id.equals("REG_1")) {
			return RegisterId.REG_1;
		}
		if (id.equals("REG_2")) {
			return RegisterId.REG_2;
		}
		if (id.equals("REG_3")) {
			return RegisterId.REG_3;
		}
		throw new Exception("Unknown Register \"" + id + "\".");
	}
	
}
