package ch.hszt.kfh.compost.ui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JLabel;

import ch.hszt.kfh.compost.RegisterId;

public class RegistersFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public RegistersFrame() {
		super();
		setTitle("Registers");
		setBounds(0, 0, 400, 200);
		
		getContentPane().setLayout(new GridLayout(0, 2));
		
		ArrayList<RegisterId> regIds = new ArrayList<RegisterId>();
		regIds.addAll(Program.instance().getCompost().getRegisterIds());
		Collections.sort(regIds);
		
		for (RegisterId regId : regIds) {
			getContentPane().add(new JLabel(regId.toString()));
			getContentPane().add(new MemCellField(Program.instance().getCompost().getRegister(regId)));
		}
	}

}
