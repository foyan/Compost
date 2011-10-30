package ch.hszt.kfh.compost.ui;

import javax.swing.Box;
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
		
		getContentPane().add(Box.createHorizontalGlue());
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(Program.instance().getStart());
		getContentPane().add(startButton);

		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(Program.instance().getStop());
		getContentPane().add(stopButton);

		JButton stepButton = new JButton("Step");
		stepButton.addActionListener(Program.instance().getStep());
		getContentPane().add(stepButton);
				
	}

}
