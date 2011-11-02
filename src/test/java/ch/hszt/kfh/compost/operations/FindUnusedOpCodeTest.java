package ch.hszt.kfh.compost.operations;

import org.junit.Test;

import ch.hszt.kfh.compost.Decoder;
import ch.hszt.kfh.compost.Tools;

public class FindUnusedOpCodeTest {
	
	@Test
	public void findEm() {
		Decoder dec = new Decoder();
		
		for (int i = 0; i < Math.pow(2, 16)-1; i++) {
			boolean[] b = Tools.toBinary(i, 16);
			if (!dec.canDecode(b)) {
				System.out.println(Tools.fromBooleanArray(b, true));
			}
		}
	}

}
