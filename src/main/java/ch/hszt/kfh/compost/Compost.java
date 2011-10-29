package ch.hszt.kfh.compost;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import ch.hszt.kfh.compost.operations.ADD;
import ch.hszt.kfh.compost.operations.ADDD;
import ch.hszt.kfh.compost.operations.CLR;
import ch.hszt.kfh.compost.operations.DEC;
import ch.hszt.kfh.compost.operations.INC;
import ch.hszt.kfh.compost.operations.LWDD;
import ch.hszt.kfh.compost.operations.Operation;
import ch.hszt.kfh.compost.operations.SWDD;
import ch.hszt.kfh.compost.ui.ManualObservable;

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
	
	private ManualObservable instructionPointerChangedObservable = new ManualObservable();
	private ManualObservable cycleStartedObservable = new ManualObservable();
		
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
			memory[i].setAddress(i);
		}
		// register operations
		decoder.register(new CLR());
		decoder.register(new ADD());
		decoder.register(new ADDD());
		decoder.register(new INC());
		decoder.register(new DEC());
		decoder.register(new LWDD());
		decoder.register(new SWDD());
		setInstructionPointer(ENTRY_POINT);
	}
	
	public Set<RegisterId> getRegisterIds() {
		return registers.keySet();
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
	
	public void setInstructionPointer(int instructionPointer) {
		this.instructionPointer = instructionPointer;
		instructionPointerChangedObservable.notifyObservers();
	}
	
	public void jumpRelative(int delta) {
		instructionPointer += delta;
	}
	
	public void jumpAbsolute(int address) {
		instructionPointer = address;
	}
	
	public void clear() {
		setCarryBit(false);
		for (RegisterId regId : getRegisterIds()) {
			getRegister(regId).clear();
		}
		for (int i = 0; i < TOTAL_MEM; i++) {
			getMem(i).clear();
		}
		cycleStartedObservable.notifyObservers();
		setInstructionPointer(ENTRY_POINT);
	}
	
	public void initOperation(int address, String mnemonic, List<String> arguments) throws Exception {
		Operation op = decoder.getOperation(mnemonic);
		if (op == null) {
			throw new Exception("Unsupported operation: " + mnemonic);
		}
		MemCell msb = getMem(address);
		MemCell lsb = getMem(address + 1);
		
		boolean[] opCode = op.opCode();
		opCode = op.addArguments(opCode, arguments);
		for (int i = 0; i < msb.getSize(); i++) {
			msb.setBit(i, opCode[i]);
			lsb.setBit(i, opCode[i + msb.getSize()]);
		}
	}
	
	public void oneOperation() throws Exception {
		cycleStartedObservable.notifyObservers();
		transferInstruction();
		decoder.decode(getRegister(RegisterId.INSTR));
		if (decoder.getCurrentOperation() != null) {
			decoder.getCurrentOperation().exec(this, decoder.getCurrentArgument());
			setInstructionPointer(instructionPointer + INSTR_SIZE / 8);
		}
	}
		
	private void transferInstruction() {
		boolean[] cell1 = getMem(instructionPointer).getBits();
		boolean[] cell2 = getMem(instructionPointer + 1).getBits();
		MemCell reg = getRegister(RegisterId.INSTR);
		for (int i = 0; i < cell1.length; i++) {
			reg.setBit(i, cell1[i]);
			reg.setBit(cell1.length + i, cell2[i]);
		}
	}
	
	public Observable getInstructionPointerChangedObservable() {
		return instructionPointerChangedObservable;
	}
	public Observable getCycleStartedObservable() {
		return cycleStartedObservable;
	}
	
}
