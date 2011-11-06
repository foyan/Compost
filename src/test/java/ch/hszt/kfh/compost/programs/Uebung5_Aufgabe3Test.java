package ch.hszt.kfh.compost.programs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.Tools;
import ch.hszt.kfh.compost.parsing.MnemonicStringParser;

public class Uebung5_Aufgabe3Test {

	@Test
	public void test1() throws Exception {
		buildSum(14, 7, 66, false);
	}
	
	@Test
	public void test2() throws Exception {
		buildSum(25, -14, 123, false);
	}

	@Test
	public void test3() throws Exception {
		buildSum(-125, 10000, 16, true);
	}

	@Test
	public void test4() throws Exception {
		buildSum(1000, 10000, -2000, true);
	}

	public void buildSum(int a, int b, int c, boolean overflow) throws Exception {
		
		// load program and parameters
		Compost compost = new Compost();
		MnemonicStringParser parser = new MnemonicStringParser(TestUtils.readFile("src/main/resources/Uebung5_Aufgabe3.compost"));
		parser.setCompost(compost);
		parser.setParamData(200, a);
		parser.setParamData(202, b);
		parser.setParamData(204, c);
		parser.parse();
		
		// run
		while (compost.oneOperation());
		
		int expected = overflow ? 0 : (a + 4 * b + 8 * c);
		
		int actual = Tools.fromComplement(compost.getMem(206).getBits(), compost.getMem(207).getBits());
		assertEquals(expected, actual);
		
	}

}
