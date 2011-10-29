package ch.hszt.kfh.compost.parsing;

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
			
			getCompost().initOperation(address, mnemonic);
		}
		
	}

}
