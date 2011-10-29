package ch.hszt.kfh.compost.ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class CompostFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public CompostFrame() {
		super();
		setTitle("Compost");
		setBounds(0, 0, 800, 100);
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JButton registersButton = new JButton("Registers");
		registersButton.addActionListener(Program.instance().getShowRegisters());
		getContentPane().add(registersButton);
		
		JButton memoryButton = new JButton("Memory");
		memoryButton.addActionListener(Program.instance().getShowMemory());
		getContentPane().add(memoryButton);
		
		JButton scriptButton = new JButton("Code");
		scriptButton.addActionListener(Program.instance().getShowScript());
		getContentPane().add(scriptButton);
		
	
	}

}
