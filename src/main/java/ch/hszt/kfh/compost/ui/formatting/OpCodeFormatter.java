package ch.hszt.kfh.compost.ui.formatting;

import ch.hszt.kfh.compost.Decoder;

public class OpCodeFormatter implements DataFormatter {

	private Decoder decoder = new Decoder();
	
	private DataFormatter fallbackFormatter;
	
	public OpCodeFormatter(DataFormatter fallbackFormatter) {
		this.fallbackFormatter = fallbackFormatter;
	}
	
	@Override
	public String format(boolean[] msb, boolean[] lsb, boolean isDataCell) {
		boolean[] b = new boolean[msb.length + lsb.length];
		for (int i = 0; i < msb.length; i++) {
			b[i] = msb[i];
		}
		for (int i = 0; i < lsb.length; i++) {
			b[msb.length + i] = lsb[i];
		}
		return format(b, isDataCell);
	}

	@Override
	public String format(boolean[] word, boolean isDataCell) {
		if (!isDataCell && decoder.canDecode(word)) {
			decoder.decode(word);
			return decoder.getCurrentOperation().mnemonic() + " " + decoder.getCurrentOperation().decodeArgument(decoder.getCurrentArgument());
		}
		return fallbackFormatter.format(word, isDataCell);
	}

	@Override
	public String getName() {
		return "Op Codes or " + fallbackFormatter.getName();
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
