package ch.hszt.kfh.compost.operations;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.RegisterId;
import ch.hszt.kfh.compost.Tools;

import static ch.hszt.kfh.compost.Asserts.*;

import org.junit.Test;

public class LWDDTest {
	
	private LWDD instance = new LWDD();
	
	@Test
	public void test() throws Exception {
		Compost compost = new Compost();
		compost.getMem(107).setBit(3, true);
		compost.getMem(107).setBit(4, true);
		compost.getMem(108).setBit(0, true);
		compost.getMem(108).setBit(1, true);
		
		boolean[] address = Tools.toBinary(107, 10);
		boolean[] argument = new boolean[13];
		for (int i = 0; i < 10; i++) {
			argument[i + 3] = address[i];
		}
		
		instance.exec(compost, argument);
		
		assertArrayEquals(new boolean[] {
				false, false, false, true, true, false, false, false, 
				true, true, false, false, false, false, false, false
			}, compost.getRegister(RegisterId.ACCUM).getBits());
	}

}
