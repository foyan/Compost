package ch.hszt.kfh.compost;

import static ch.hszt.kfh.compost.Asserts.assertArrayEquals;

import static org.junit.Assert.*;

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

}
