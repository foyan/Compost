package ch.hszt.kfh.compost.parsing;

import java.util.ArrayList;
import java.util.HashMap;

import ch.hszt.kfh.compost.Tools;

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
				String[] tok = line.split("[\\s,]");

				ArrayList<String> tokens = new ArrayList<String>();
				for (String t : tok) {
					String to = t.trim();
					if (!to.isEmpty()) {
						tokens.add(to);
					}
				}
				
				int dataAddress = Integer.parseInt(tokens.get(0).substring(1));
				
				int dataToken = Integer.parseInt(tokens.get(1));
				if (paramData.containsKey(dataAddress)) {
					dataToken = paramData.get(dataAddress);
				}
				
				boolean[] dataValue = Tools.toComplement(dataToken, 16);
				boolean[] msb = new boolean[] {
						dataValue[0], dataValue[1], dataValue[2], dataValue[3], 
						dataValue[4], dataValue[5], dataValue[6], dataValue[7]
				};
				boolean[] lsb = new boolean[] {
						dataValue[8], dataValue[9], dataValue[10], dataValue[11], 
						dataValue[12], dataValue[13], dataValue[14], dataValue[15]
				};
				getCompost().getMem(dataAddress).setBits(msb);
				getCompost().getMem(dataAddress+1).setBits(lsb);
				
				continue;
			}
			
			// code
			{
				String[] tok = line.split("[\\s,]");
				
				ArrayList<String> tokens = new ArrayList<String>();
				for (String t : tok) {
					String to = t.trim();
					if (!to.isEmpty()) {
						tokens.add(to);
					}
				}
				
				boolean directAddressing = isInteger(tokens.get(0));
				int addressTokenCount = directAddressing ? 1 : 0;
				
				if (directAddressing) {
					address = Integer.parseInt(tokens.get(0));
				}
				
				String  mnemonic = tokens.get(addressTokenCount).trim();
	
				// arguments
				ArrayList<String> arguments = new ArrayList<String>();
				for (int i = 1 + addressTokenCount; i < tokens.size(); i++) {
					String to = tokens.get(i);
					// literal?
					if (to.startsWith("#")) {
						to = to.substring(1);
					}
					// label?
					if (!isInteger(to) && labels.containsKey(to)) {
						to = labels.get(to).toString();
					}
					arguments.add(to);
				}
				
				getCompost().initOperation(address, mnemonic, arguments);
				
				address = address + 2;
			}

		}
		
		getCompost().setHalt(address);
				
	}
	
	private HashMap<Integer, Integer> paramData = new HashMap<Integer, Integer>();
	
	public void setParamData(int address, int data) {
		paramData.put(address, data);
	}
	
	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
