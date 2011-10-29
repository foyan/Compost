package ch.hszt.kfh.compost.operations;

import static ch.hszt.kfh.compost.Asserts.assertArrayEquals;

import org.junit.Test;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.RegisterId;
import ch.hszt.kfh.compost.Tools;

public class SWDDTest {
	
	private SWDD instance = new SWDD();
	
	@Test
	public void test() throws Exception {
		Compost compost = new Compost();
		MemCell reg = compost.getRegister(RegisterId.ACCUM);
		
		reg.setBit(3, true);
		reg.setBit(4, true);
		reg.setBit(8+0, true);
		reg.setBit(8+1, true);
		
		boolean[] address = Tools.toBinary(107, 10);
		boolean[] argument = new boolean[13];
		for (int i = 0; i < 10; i++) {
			argument[i + 3] = address[i];
		}
		
		instance.exec(compost, argument);
		
		assertArrayEquals(new boolean[] {
				false, false, false, true, true, false, false, false 
			}, compost.getMem(107).getBits());

		assertArrayEquals(new boolean[] {
				true, true, false, false, false, false, false, false
			}, compost.getMem(108).getBits());
	}

}
