package ch.hszt.kfh.compost.operations;

import java.util.List;

import ch.hszt.kfh.compost.Tools;

public abstract class AbstractDirectBranch extends Operation {
	
	@Override
	public final boolean[] addArguments(boolean[] opCode, List<String> arguments)
			throws Exception {

		int n = Integer.parseInt(arguments.get(0));
		boolean[] addr = Tools.toBinary(n, 10);
		
		for (int i = 0; i < addr.length; i++) {
			opCode[i + 6] = addr[i];
		}
		
		return opCode;
	}

	@Override
	public final int getArgumentCount() {
		return 1;
	}
	
	protected int getAddress(boolean[] arg) {
		boolean[] a = new boolean[arg.length - 1];
		for (int i = 1; i < arg.length; i++) {
			a[i-1] = arg[i];
		}
		return Tools.fromBinary(a);
	}

	@Override
	public String decodeArgument(boolean[] argument) {
		return getAddress(argument) + "";
	}

}
