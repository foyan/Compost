package ch.hszt.kfh.compost.operations;

import static ch.hszt.kfh.compost.Asserts.assertArrayEquals;

import org.junit.Test;
import static org.junit.Assert.*;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.Decoder;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.RegisterId;

public class CLRTest {
	
	private CLR instance = new CLR();
	private Compost compost = new Compost();
	
	private static boolean[] SIXTEEN_ZEROS = new boolean[16];
	private static boolean[] SIXTEEN_ONES = new boolean[] {
		true, true, true, true,
		true, true, true, true,
		true, true, true, true,
		true, true, true, true
	};
	
	@Test
	public void accum() throws Exception {
		MemCell reg = compost.getRegister(RegisterId.ACCUM);
		reg.setBits(SIXTEEN_ONES);
		instance.exec(compost, new boolean[] { false, false, false, true });
		assertArrayEquals(SIXTEEN_ZEROS, reg.getBits());
	}

	@Test
	public void reg1() throws Exception {
		MemCell reg = compost.getRegister(RegisterId.REG_1);
		reg.setBits(SIXTEEN_ONES);
		instance.exec(compost, new boolean[] { false, true, false, true });
		assertArrayEquals(SIXTEEN_ZEROS, reg.getBits());
	}

	@Test
	public void reg2() throws Exception {
		MemCell reg = compost.getRegister(RegisterId.REG_2);
		reg.setBits(SIXTEEN_ONES);
		instance.exec(compost, new boolean[] { true, false, false, true });
		assertArrayEquals(SIXTEEN_ZEROS, reg.getBits());
	}

	@Test
	public void reg3() throws Exception {
		MemCell reg = compost.getRegister(RegisterId.REG_3);
		reg.setBits(SIXTEEN_ONES);
		instance.exec(compost, new boolean[] { true, true, false, true });
		assertArrayEquals(SIXTEEN_ZEROS, reg.getBits());
	}
	
	@Test
	public void decode() throws Exception {
		MemCell instr = new MemCell(16);
		instr.setBits(new boolean[] {false, false, false, false, false, false, true, false, true, false, false, false, false, false, false, false});
		Decoder decoder = new Decoder();
		decoder.register(instance);
		decoder.decode(instr);
		
		assertEquals(decoder.getCurrentOperation(), instance);
		assertArrayEquals(decoder.getCurrentArgument(), new boolean[] { false, false, false, false, false, false, false, false, false });
	}

}
