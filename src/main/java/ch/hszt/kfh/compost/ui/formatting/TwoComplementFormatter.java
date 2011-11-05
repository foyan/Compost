package ch.hszt.kfh.compost.ui.formatting;

import ch.hszt.kfh.compost.Tools;

public class TwoComplementFormatter implements DataFormatter {

	@Override
	public String getName() {
		return "Binary (Two's Complement)";
	}
	
	@Override
	public String format(boolean[] msb, boolean[] lsb, boolean isDataCell) {
		return Tools.fromBooleanArray(msb, false) + " " + Tools.fromBooleanArray(lsb, false);
	}
	
	@Override
	public String format(boolean[] word, boolean isDataCell) {
		return Tools.fromBooleanArray(word, true);
	}

	@Override
	public String toString() {
		return getName();
	}

}
