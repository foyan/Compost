package ch.hszt.kfh.compost;

import static ch.hszt.kfh.compost.Asserts.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class ToolsTest {
	
	@Test
	public void toBooleanArrayEmpty() {
		assertArrayEquals(new boolean[0], Tools.toBooleanArray(""));
	}
	
	@Test
	public void toBooleanArray10() {
		assertArrayEquals(new boolean[] { true, false }, Tools.toBooleanArray("10"));
	}
	
	@Test
	public void toComplementMinus4Size8() {
		boolean[] exp = Tools.toBooleanArray("11111100");
		assertArrayEquals(exp, Tools.toComplement(-4, 8));
	}
	
	@Test
	public void toComplement26Size8() {
		boolean[] exp = Tools.toBooleanArray("00011010");
		assertArrayEquals(exp, Tools.toComplement(26, 8));
	}
	
	@Test
	public void toComplementMinus102Size8() {
		boolean[] exp = Tools.toBooleanArray("10011010");
		assertArrayEquals(exp, Tools.toComplement(-102, 8));
	}
	
	@Test
	public void addMinus4Plus3() {
		boolean[] a = Tools.toComplement(-4, 8);
		boolean[] b = Tools.toComplement(3, 8);
		AdditionResult r = Tools.add(a, b);
		assertArrayEquals(Tools.toBooleanArray("11111111"), r.getData());
	}
	
	@Test
	public void fromComplementMinus4() {
		boolean[] c = Tools.toBooleanArray("11111100");
		int n = Tools.fromComplement(c);
		assertEquals(-4, n);
	}
	
	@Test
	public void all8Bit() {
		for (int i = -128; i <= 127; i++) {
			boolean[] c = Tools.toComplement(i, 8);
			int n = Tools.fromComplement(c);
			assertEquals(i, n);
		}
	}

	@Test
	public void all8BitAdditions() {
		for (int i = -64; i <= 63; i++) {
			for (int j = -64; j <= 64; j++) {
				boolean[] a = Tools.toComplement(i, 8);
				boolean[] b = Tools.toComplement(j, 8);
				boolean[] r = Tools.add(a, b).getData();
				int n = Tools.fromComplement(r);
				assertEquals(n, i + j);
			}
		}
	}

	@Test
	public void all16Bit() {
		for (int i = -32768; i <= 32767; i++) {
			boolean[] c = Tools.toComplement(i, 16);
			int n = Tools.fromComplement(c);
			assertEquals(i, n);
		}
	}
	
	@Ignore
	@Test
	public void all16BitAdditions() {
		for (int i = -16384; i <= 16383; i++) {
			for (int j = -16384; j <= 16383; j++) {
				boolean[] a = Tools.toComplement(i, 16);
				boolean[] b = Tools.toComplement(j, 16);
				boolean[] r = Tools.add(a, b).getData();
				int n = Tools.fromComplement(r);
				assertEquals(n, i + j);
			}
		}
	}
	
	

}
