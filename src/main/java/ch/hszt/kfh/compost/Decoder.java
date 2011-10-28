package ch.hszt.kfh.compost;

import java.util.ArrayList;
import java.util.List;

import ch.hszt.kfh.compost.operations.Operation;

public class Decoder {
	
	private List<Operation> operations = new ArrayList<Operation>();
	
	private Operation currentOperation;
	private boolean[] currentArgument;
	
	public void register(Operation op) {
		operations.add(op);
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
	
	public Operation getCurrentOperation() {
		return currentOperation;
	}
	
	public boolean[] getCurrentArgument() {
		return currentArgument;
	}

}
