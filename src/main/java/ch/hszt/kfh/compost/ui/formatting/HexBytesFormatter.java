package ch.hszt.kfh.compost.ui.formatting;

import ch.hszt.kfh.compost.Tools;


public class HexBytesFormatter implements DataFormatter {

	@Override
	public String format(boolean[] msb, boolean[] lsb) {
		return Tools.toHex(msb, false) + " " + Tools.toHex(lsb, false);
	}

	@Override
	public String format(boolean[] word) {
		return Tools.toHex(word, true);
	}

	@Override
	public String getName() {
		return "Hexadecimal";
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
		
}
