package ch.hszt.kfh.compost;

import java.util.HashMap;

public class Compost {
	
	private HashMap<RegisterId, MemCell> registers = new HashMap<RegisterId, MemCell>();
	
	private boolean carryBit;
	
	private MemCell[] memory = new MemCell[1024];
	
	public Compost() {
		// create registers
		registers.put(RegisterId.INSTR, new MemCell(16));
		registers.put(RegisterId.ACCUM, new MemCell(16));
		registers.put(RegisterId.REG_1, new MemCell(16));
		registers.put(RegisterId.REG_2, new MemCell(16));
		registers.put(RegisterId.REG_3, new MemCell(16));
		// initialize memory
		for (int i = 0; i < memory.length; i++) {
			memory[i] = new MemCell(8);
		}
	}
	
	public MemCell getRegister(RegisterId id) {
		return registers.get(id);
	}
	
	public boolean getCarryBit() {
		return carryBit;
	}
	
	public void setCarryBit(boolean carryBit) {
		this.carryBit = carryBit;
	}
	
	public MemCell getMem(int address) {
		return memory[address];
	}
	
}
