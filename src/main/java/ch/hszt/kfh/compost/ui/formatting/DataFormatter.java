package ch.hszt.kfh.compost.ui.formatting;

public interface DataFormatter {

	public String format(boolean[] msb, boolean[] lsb, boolean isDataCell);
	
	public String format(boolean[] word, boolean isDataCell);
	
	public String getName();
	
}
