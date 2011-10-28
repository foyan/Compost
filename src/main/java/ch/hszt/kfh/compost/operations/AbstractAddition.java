package ch.hszt.kfh.compost.operations;

import ch.hszt.kfh.compost.AdditionResult;
import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.RegisterId;
import ch.hszt.kfh.compost.Tools;

public abstract class AbstractAddition extends Operation {
	
	public abstract boolean[] getOperand(Compost compost, boolean[] argument);
	
	@Override
	public final void exec(Compost compost, boolean[] argument) throws Exception {
		
		boolean[] operand = getOperand(compost, argument);
		
		MemCell accum = compost.getRegister(RegisterId.ACCUM);
		AdditionResult r = Tools.add(accum.getBits(), operand);
		accum.setBits(r.getData());
		compost.setCarryBit(r.getCarryBit());
		
	}

}
