package ch.hszt.kfh.compost.parsing;

import ch.hszt.kfh.compost.Tools;

public class BinaryParser extends CompostParser {

	@Override
	public void parse() throws Exception {

		getCompost().clear();

		int address = 100;
		
		for (String l : getString().split("\n")) {
			String line = l;
			if (line.contains(";")) {
				line = line.substring(0, line.indexOf(";"));
			}
			line = line.trim();
			
			if (!line.isEmpty()) {
				
				String[] bytes = line.split(" ");
				for (int i = 0; i < bytes.length; i++) {
					boolean[] b = Tools.toBooleanArray(bytes[i]);
					getCompost().getMem(address + i).setBits(b);
				}
				
				
			}
			address = address + 2;
			
		}		
	}

	@Override
	public String toString() {
		return "Binary";
	}

}
