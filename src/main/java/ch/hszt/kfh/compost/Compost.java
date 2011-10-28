package ch.hszt.kfh.compost;

import java.util.HashMap;

import ch.hszt.kfh.compost.operations.*;

public class Compost {
	
	public static final int ENTRY_POINT = 100;
	public static final int INSTR_SIZE = 16;
	public static final int REG_SIZE = 16;
	public static final int MEM_SIZE = 8;
	public static final int TOTAL_MEM = 1024;
	
	private HashMap<RegisterId, MemCell> registers = new HashMap<RegisterId, MemCell>();
	
	private boolean carryBit;
	
	private MemCell[] memory = new MemCell[TOTAL_MEM];
	
	private int instructionPointer = ENTRY_POINT;
	
	private Decoder decoder = new Decoder();
		
	public Compost() {
		// create registers
		registers.put(RegisterId.INSTR, new MemCell(INSTR_SIZE));
		registers.put(RegisterId.ACCUM, new MemCell(REG_SIZE));
		registers.put(RegisterId.REG_1, new MemCell(REG_SIZE));
		registers.put(RegisterId.REG_2, new MemCell(REG_SIZE));
		registers.put(RegisterId.REG_3, new MemCell(REG_SIZE));
		// initialize memory
		for (int i = 0; i < memory.length; i++) {
			memory[i] = new MemCell(MEM_SIZE);
		}
		// register operations
		decoder.register(new CLR());
		decoder.register(new ADD());
		decoder.register(new ADDD());
		decoder.register(new INC());
		decoder.register(new DEC());
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
	
	public int getInstructionPointer() {
		return instructionPointer;
	}
	
	public void jumpRelative(int delta) {
		instructionPointer += delta;
	}
	
	public void jumpAbsolute(int address) {
		instructionPointer = address;
	}
	
}
