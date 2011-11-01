package ch.hszt.kfh.compost.parsing;

import java.util.ArrayList;
import java.util.HashMap;

public class MnemonicStringParser extends CompostParser {
	
	private String string;
	
	public MnemonicStringParser(String string) {
		this.string = string;
	}
	
	@Override
	public void parse() throws Exception {
		
		getCompost().clear();
		
		int address = 100;
		HashMap<String, Integer> labels = new HashMap<String, Integer>();
		
		for (String l : string.split("\n")) {
			String line = l;
			if (line.contains(";")) {
				line = line.substring(0, line.indexOf(";"));
			}
			line = line.trim();
			
			// nothing
			if (line.isEmpty()) {
				continue;
			}
			
			// label
			if (line.endsWith(":")) {
				String label = line.substring(0, line.length()-1);
				if (labels.containsKey(label)) {
					throw new Exception("Label \"" + label + "\" already defined.");
				}
				labels.put(label, address);
				continue;
			}
			
			// data
			if (line.startsWith("@")) {
				//String 
			}
			
		}
		
		for (String l : string.split("\n")) {
			
			String line = l.trim();
			
			// comments
			if (line.startsWith(";")) {
				continue;
			}
			
			// empty lines
			if (line.equals("")) {
				continue;
			}
			
			// lines
			String[] tokens = line.split("\t");
			
			//int address = Integer.parseInt(tokens[0].trim());
			String mnemonic = tokens[1].trim();
			
			// arguments
			ArrayList<String> arguments = new ArrayList<String>();
			if (tokens.length == 3) {
				String[] args = tokens[2].split(",");
				for (String arg : args) {
					arguments.add(arg.trim());
				}
			}
			
			getCompost().initOperation(address, mnemonic, arguments);
		}
		
	}

}
