package ch.hszt.kfh.compost.parsing;

import java.util.ArrayList;

public class MnemonicStringParser extends CompostParser {
	
	private String string;
	
	public MnemonicStringParser(String string) {
		this.string = string;
	}
	
	@Override
	public void parse() throws Exception {
		
		getCompost().clear();
		
		for (String l : string.split("\n")) {
			
			String line = l.trim();
			
			// comments
			if (line.startsWith("#")) {
				continue;
			}
			
			// empty lines
			if (line.equals("")) {
				continue;
			}
			
			// lines
			String[] tokens = line.split("\t");
			
			int address = Integer.parseInt(tokens[0].trim());
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
