package ch.hszt.kfh.compost.ui.formatting;

import ch.hszt.kfh.compost.Tools;

public class HexBytesFormatter implements DataFormatter {

	@Override
	public String format(boolean[] msb, boolean[] lsb) {
		return padHex(Integer.toHexString(Tools.fromComplement(msb))) + " " + padHex(Integer.toHexString(Tools.fromComplement(lsb)));
	}

	@Override
	public String format(boolean[] word) {
		boolean[][] bytes = new boolean[word.length / 8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < word.length / 8; j++) {
				bytes[j][i] = word[8 * j + i];
			}
		}
		String r = "";
		for (int i = 0; i < word.length / 8; i++) {
			r += " " + padHex(Integer.toHexString(Tools.fromComplement(bytes[i])));
		}
		return r.substring(1);
	}

	@Override
	public String getName() {
		return "Hexadecimal";
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	private String padHex(String h) {
		String r = "0000" + h.toUpperCase();
		return r.substring(r.length() - 4, r.length());
	}

}
