package ch.hszt.kfh.compost.programs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.Tools;
import ch.hszt.kfh.compost.parsing.MnemonicParser;

public class BoothTest {
	
	@Test
	public void test1() throws Exception {
		calc(15, 27);
	}
	
	@Test
	public void test2() throws Exception {
		calc(0, 23456);
	}

	@Test
	public void test3() throws Exception {
		calc(-1234, 4321);
	}

	@Test
	public void test4() throws Exception {
		calc(-222, -333);
	}

	public void calc(int x, int y) throws Exception {
		
		// load program and parameters
		Compost compost = new Compost();
		MnemonicParser parser = new MnemonicParser();
		parser.setString(TestUtils.readFile("src/main/resources/Booth.compost"));
		parser.setCompost(compost);
		parser.setParamData(500, x);
		parser.setParamData(502, y);
		parser.parse();
		
		// run
		while (compost.oneOperation());
		
		int expected = x * y;
		int actual = Tools.fromComplement(
				compost.getMem(504).getBits(), compost.getMem(505).getBits(), 
				compost.getMem(506).getBits(), compost.getMem(507).getBits()); 
		assertEquals(expected, actual);
		
		System.out.println(x + "*" + y + ": " + compost.getCycles());
		
	}
		
}
