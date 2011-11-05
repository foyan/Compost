package ch.hszt.kfh.compost.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.hszt.kfh.compost.RegisterId;

public class RegistersFrame extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField instructionPointer = new JTextField();
	private JCheckBox carryBit = new JCheckBox("Carry");
	private JLabel cycles = new JLabel("# Cycles");
	
	public RegistersFrame() {
		super();
		setTitle("Registers");
		setBounds(0, 0, 400, 200);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 3));
		
		FormatterBoxModel fmb = new FormatterBoxModel();
		JComboBox formatters = new JComboBox();
		formatters.setModel(fmb);
		getContentPane().add(formatters, BorderLayout.NORTH);
		
		//getContentPane().setLayout(new GridLayout(0, 3));
		
		getContentPane().add(panel, BorderLayout.CENTER);
		
		panel.add(new JLabel("Instruction Pointer:"));
		panel.add(instructionPointer);
		panel.add(cycles);
		
		updateInstructionPointer();
		updateCarryBit();
		updateCycles();
		
		Program.instance().getCompost().getCarryBitChangedObservable().addObserver(this);
		Program.instance().getCompost().getCycleFinishedObservable().addObserver(this);
		Program.instance().getCompost().getInstructionPointerChangedObservable().addObserver(this);
		
		ArrayList<RegisterId> regIds = new ArrayList<RegisterId>();
		regIds.addAll(Program.instance().getCompost().getRegisterIds());
		Collections.sort(regIds);
		
		for (RegisterId regId : regIds) {
			panel.add(new JLabel(regId.toString() + ":"));
			MemCellField f = new MemCellField(fmb, Program.instance().getCompost().getRegister(regId), regId != RegisterId.INSTR);
			panel.add(f);
			if (regId == RegisterId.ACCUM) {
				panel.add(carryBit);
			} else {
				panel.add(new JLabel());
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 == Program.instance().getCompost().getInstructionPointerChangedObservable()) {
			updateInstructionPointer();
			return;
		}
		if (arg0 == Program.instance().getCompost().getCarryBitChangedObservable()) {
			updateCarryBit();
		}
		if (arg0 == Program.instance().getCompost().getCycleFinishedObservable()) {
			updateCycles();
		}
	}
	
	private void updateInstructionPointer() {
		instructionPointer.setText(Program.instance().getCompost().getInstructionPointer() + "");
	}
	private void updateCarryBit() {
		carryBit.setSelected(Program.instance().getCompost().getCarryBit());
	}
	private void updateCycles() {
		int c = Program.instance().getCompost().getCycles();
		cycles.setText(c == 1 ? "1 Cycle" : (c + " Cycles"));
	}

}
