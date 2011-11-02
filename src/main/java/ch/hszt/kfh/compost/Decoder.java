package ch.hszt.kfh.compost;

import java.util.ArrayList;
import java.util.List;

import ch.hszt.kfh.compost.operations.ADD;
import ch.hszt.kfh.compost.operations.ADDD;
import ch.hszt.kfh.compost.operations.AND;
import ch.hszt.kfh.compost.operations.B;
import ch.hszt.kfh.compost.operations.BC;
import ch.hszt.kfh.compost.operations.BCD;
import ch.hszt.kfh.compost.operations.BD;
import ch.hszt.kfh.compost.operations.BNZ;
import ch.hszt.kfh.compost.operations.BNZD;
import ch.hszt.kfh.compost.operations.BZ;
import ch.hszt.kfh.compost.operations.BZD;
import ch.hszt.kfh.compost.operations.CLR;
import ch.hszt.kfh.compost.operations.DEC;
import ch.hszt.kfh.compost.operations.INC;
import ch.hszt.kfh.compost.operations.LWDD;
import ch.hszt.kfh.compost.operations.NOT;
import ch.hszt.kfh.compost.operations.OR;
import ch.hszt.kfh.compost.operations.Operation;
import ch.hszt.kfh.compost.operations.SLA;
import ch.hszt.kfh.compost.operations.SLL;
import ch.hszt.kfh.compost.operations.SRA;
import ch.hszt.kfh.compost.operations.SRL;
import ch.hszt.kfh.compost.operations.SWDD;

public class Decoder {
	
	private List<Operation> operations = new ArrayList<Operation>();
	
	private Operation currentOperation;
	private boolean[] currentArgument;
	
	public Decoder() {
		// register operations
		register(new CLR());
		register(new ADD());
		register(new ADDD());
		register(new INC());
		register(new DEC());
		register(new LWDD());
		register(new SWDD());
		register(new SLA());
		register(new SLL());
		register(new SRA());
		register(new SRL());
		register(new AND());
		register(new OR());
		register(new NOT());
		register(new B());
		register(new BZ());
		register(new BC());
		register(new BNZ());
		register(new BZD());
		register(new BNZD());
		register(new BCD());
		register(new BD());
	}
	
	public void register(Operation op) {
		operations.add(op);
	}
	
	public boolean canDecode(boolean[] cell) {
		for (Operation op : operations) {
			boolean[] opCode = op.opCode();
			boolean[] opCodeMask = op.opCodeMask();
			boolean ok = true;
			for (int i = 0; i < Compost.INSTR_SIZE; i++) {
				if (opCodeMask[i] && (cell[i] != opCode[i])) {
					ok = false;
					break;
				}
			}
			if (ok) {
				return true;
			}
		}
		return false;
	}
	
	public void decode(MemCell cell) {
		// clean up
		currentOperation = null;
		currentArgument = null;
		// find in list
		for (Operation op : operations) {
			boolean[] opCode = op.opCode();
			boolean[] opCodeMask = op.opCodeMask();
			boolean ok = true;
			for (int i = 0; i < Compost.INSTR_SIZE; i++) {
				if (opCodeMask[i] && (cell.getBit(i) != opCode[i])) {
					ok = false;
					break;
				}
			}
			if (ok) {
				currentOperation = op;
				// prepare argument
				int c = 0;
				for (int i = 0; i < opCodeMask.length; i++) {
					if (!opCodeMask[i]) {
						c++;
					}
				}
				if (c > 0) {
					currentArgument = new boolean[c];
					c = 0;
					for (int i = 0; i < opCodeMask.length; i++) {
						if (!opCodeMask[i]) {
							currentArgument[c++] = cell.getBit(i);
						}
					}
				}
			}
		}
	}
	
	public Operation getOperation(String mnemonic) {
		for (Operation op : operations) {
			if (op.mnemonic().equals(mnemonic)) {
				return op;
			}
		}
		return null;
	}
	
	public Operation getCurrentOperation() {
		return currentOperation;
	}
	
	public boolean[] getCurrentArgument() {
		return currentArgument;
	}

}
