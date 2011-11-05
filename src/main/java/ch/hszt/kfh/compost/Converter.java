package ch.hszt.kfh.compost;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import ch.hszt.kfh.compost.parsing.CompostParser;
import ch.hszt.kfh.compost.ui.formatting.DataFormatter;

public class Converter implements Observer {
	
	private CompostParser parser;
	private DataFormatter formatter;
	private boolean directAddressing;
	
	private HashMap<Integer, String> result = new HashMap<Integer, String>();

	private Compost compost = new Compost();
	
	public Converter() {
		MemCell.getChangeObservable().addObserver(this);
	}
	
	public CompostParser getParser() {
		return parser;
	}
	
	public DataFormatter getFormatter() {
		return formatter;
	}
	
	public void setParser(CompostParser parser) {
		this.parser = parser;
	}
	
	public void setFormatter(DataFormatter formatter) {
		this.formatter = formatter;
	}
	
	public boolean getDirectAddressing() {
		return directAddressing;
	}
	
	public void setDirectAddressing(boolean directAddressing) {
		this.directAddressing = directAddressing;
	}
	
	public String convert() throws Exception {
		MemCell.getChangeObservable().addObserver(this);
		try {
			result.clear();
			parser.parse();
			int min = findFirstAddress();
			int max = findLastAddress();
			String zero = formatter.format(new boolean[16], false);
			if (min != 100) {
				throw new Exception("First instruction needs to be at #100.");
			}
			StringBuilder sb = new StringBuilder();
			for (int i = min; i <= max; i++) {
				if (result.containsKey(i)) {
					if (directAddressing) {
						sb.append(i + ": ");
					}
					sb.append(result.get(i));
				} else if (!directAddressing) {
					sb.append(zero);
				}
				sb.append("\n");
			}
			return sb.toString();
		} finally {
			MemCell.getChangeObservable().deleteObserver(this);
		}
	}
	
	private int findFirstAddress() {
		int min = Integer.MAX_VALUE;
		for (int i : result.keySet()) {
			min = Math.min(i, min);
		}
		return min;
	}
	
	private int findLastAddress() {
		int max = Integer.MIN_VALUE;
		for (int i : result.keySet()) {
			max = Math.max(i,  max);
		}
		return max;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 == MemCell.getChangeObservable()) {
			MemCell cell = (MemCell) arg1;
			int address = cell.getAddress();
			if (address % 2 == 0) {
				result.put(address, formatter.format(cell.getBits(), compost.getMem(address + 1).getBits(), address >= 200));
			}
		}
	}

}
