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
	
	@Test
	public void toHex4Bit0000() {
		assertEquals("0", Tools.toHex(new boolean[] {false, false, false, false}, false));
	}
	
	@Test
	public void toHex4Bit0001() {
		assertEquals("1", Tools.toHex(new boolean[] {false, false, false, true}, false));
	}
	
	@Test
	public void toHex4Bit0010() {
		assertEquals("2", Tools.toHex(new boolean[] {false, false, true, false}, false));
	}
	
	@Test
	public void toHex4Bit0011() {
		assertEquals("3", Tools.toHex(new boolean[] {false, false, true, true}, false));
	}

	@Test
	public void toHex4Bit0100() {
		assertEquals("4", Tools.toHex(new boolean[] {false, true, false, false}, false));
	}
	
	@Test
	public void toHex4Bit0101() {
		assertEquals("5", Tools.toHex(new boolean[] {false, true, false, true}, false));
	}
	
	@Test
	public void toHex4Bit0110() {
		assertEquals("6", Tools.toHex(new boolean[] {false, true, true, false}, false));
	}
	
	@Test
	public void toHex4Bit0111() {
		assertEquals("7", Tools.toHex(new boolean[] {false, true, true, true}, false));
	}

	@Test
	public void toHex4Bit1000() {
		assertEquals("8", Tools.toHex(new boolean[] {true, false, false, false}, false));
	}
	
	@Test
	public void toHex4Bit1001() {
		assertEquals("9", Tools.toHex(new boolean[] {true, false, false, true}, false));
	}
	
	@Test
	public void toHex4Bit1010() {
		assertEquals("A", Tools.toHex(new boolean[] {true, false, true, false}, false));
	}
	
	@Test
	public void toHex4Bit1011() {
		assertEquals("B", Tools.toHex(new boolean[] {true, false, true, true}, false));
	}

	@Test
	public void toHex4Bit1100() {
		assertEquals("C", Tools.toHex(new boolean[] {true, true, false, false}, false));
	}
	
	@Test
	public void toHex4Bit1101() {
		assertEquals("D", Tools.toHex(new boolean[] {true, true, false, true}, false));
	}
	
	@Test
	public void toHex4Bit1110() {
		assertEquals("E", Tools.toHex(new boolean[] {true, true, true, false}, false));
	}
	
	@Test
	public void toHex4Bit1111() {
		assertEquals("F", Tools.toHex(new boolean[] {true, true, true, true}, false));
	}



	
	@Test
	public void toHex8Bit00000110() {
		assertEquals("06", Tools.toHex(new boolean[] {false, false, false, false, false, true, true, false}, false));
	}
	
	@Test
	public void toHex8Bit00010110() {
		assertEquals("16", Tools.toHex(new boolean[] {false, false, false, true, false, true, true, false}, false));
	}
	
	@Test
	public void toHex8Bit00100110() {
		assertEquals("26", Tools.toHex(new boolean[] {false, false, true, false, false, true, true, false}, false));
	}
	
	@Test
	public void toHex8Bit00110110() {
		assertEquals("36", Tools.toHex(new boolean[] {false, false, true, true, false, true, true, false}, false));
	}

	@Test
	public void toHex8Bit01000110() {
		assertEquals("46", Tools.toHex(new boolean[] {false, true, false, false, false, true, true, false}, false));
	}
	
	@Test
	public void toHex8Bit01010110() {
		assertEquals("56", Tools.toHex(new boolean[] {false, true, false, true, false, true, true, false}, false));
	}
	
	@Test
	public void toHex8Bit01100110() {
		assertEquals("66", Tools.toHex(new boolean[] {false, true, true, false, false, true, true, false}, false));
	}
	
	@Test
	public void toHex8Bit01110110() {
		assertEquals("76", Tools.toHex(new boolean[] {false, true, true, true, false, true, true, false}, false));
	}

	@Test
	public void toHex8Bit10000110() {
		assertEquals("86", Tools.toHex(new boolean[] {true, false, false, false, false, true, true, false}, false));
	}
	
	@Test
	public void toHex8Bit10010110() {
		assertEquals("96", Tools.toHex(new boolean[] {true, false, false, true, false, true, true, false}, false));
	}
	
	@Test
	public void toHex8Bit10100110() {
		assertEquals("A6", Tools.toHex(new boolean[] {true, false, true, false, false, true, true, false}, false));
	}
	
	@Test
	public void toHex8Bit10110110() {
		assertEquals("B6", Tools.toHex(new boolean[] {true, false, true, true, false, true, true, false}, false));
	}

	@Test
	public void toHex8Bit11000110() {
		assertEquals("C6", Tools.toHex(new boolean[] {true, true, false, false, false, true, true, false}, false));
	}
	
	@Test
	public void toHex8Bit11010110() {
		assertEquals("D6", Tools.toHex(new boolean[] {true, true, false, true, false, true, true, false}, false));
	}
	
	@Test
	public void toHex8Bit11100110() {
		assertEquals("E6", Tools.toHex(new boolean[] {true, true, true, false, false, true, true, false}, false));
	}
	
	@Test
	public void toHex8Bit11110110() {
		assertEquals("F6", Tools.toHex(new boolean[] {true, true, true, true, false, true, true, false}, false));
	}


	
	
}
