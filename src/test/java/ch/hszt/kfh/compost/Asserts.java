package ch.hszt.kfh.compost;

import org.junit.Assert;

public final class Asserts {

	public static void assertArrayEquals(boolean[] expected, boolean[] actual) {
		int[] exp = new int[expected.length];
		for (int i = 0; i < exp.length; i++) {
			exp[i] = expected[i] ? 1 : 0;
		}
		
		int[] act = new int[actual.length];
		for (int i = 0; i < act.length; i++) {
			act[i] = actual[i] ? 1 : 0;
		}
		
		Assert.assertArrayEquals(exp, act);
	}
	
}
