package ch.hszt.kfh.compost.ui.formatting;

public interface DataFormatter {

	public String format(boolean[] msb, boolean[] lsb);
	
	public String format(boolean[] word);
	
	public String getName();
	
}
