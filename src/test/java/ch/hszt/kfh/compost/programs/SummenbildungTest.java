package ch.hszt.kfh.compost.programs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.Tools;
import ch.hszt.kfh.compost.parsing.MnemonicParser;

public class SummenbildungTest {
	
	@Test
	public void oneToX() throws Exception {
		for (int start = 1; start <= 255; start++) {
			for (int end = start + 1; end <= 255; end++) {
				buildSum(start, end);
			}
		}
	}
	
	public void buildSum(int start, int end) throws Exception {
		
		// load program and parameters
		Compost compost = new Compost();
		MnemonicParser parser = new MnemonicParser();
		parser.setString(TestUtils.readFile("src/main/resources/Summenbildung.compost"));
		parser.setCompost(compost);
		parser.setParamData(200, start);
		parser.setParamData(202, end);
		parser.parse();
		
		// run
		while (compost.oneOperation());
		
		// assert => "Der kleine Gauss!"
		int expected = end * (end + 1) / 2 - (start - 1) * start / 2;
		int actual = Tools.fromComplement(compost.getMem(204).getBits(), compost.getMem(205).getBits());
		assertEquals(expected, actual);
		
	}
		
}
