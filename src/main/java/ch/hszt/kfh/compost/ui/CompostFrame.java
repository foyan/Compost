package ch.hszt.kfh.compost.ui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
		getContentPane().add(Box.createRigidArea(new Dimension(3, 1)));
		
		JButton memoryButton = new JButton("Memory");
		memoryButton.addActionListener(Program.instance().getShowMemory());
		getContentPane().add(memoryButton);
		getContentPane().add(Box.createRigidArea(new Dimension(3, 1)));
		
		JButton scriptButton = new JButton("Code");
		scriptButton.addActionListener(Program.instance().getShowScript());
		getContentPane().add(scriptButton);
		getContentPane().add(Box.createRigidArea(new Dimension(3, 1)));
		
		final JSlider tempo = new JSlider();
		tempo.setMinimum(0);
		tempo.setMaximum(750);
		tempo.setValue(500);
		tempo.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				Program.instance().setTempo(tempo.getValue());
			}
		});
		getContentPane().add(tempo);
		
		getContentPane().add(Box.createHorizontalGlue());
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(Program.instance().getStart());
		getContentPane().add(startButton);
		getContentPane().add(Box.createRigidArea(new Dimension(3, 1)));

		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(Program.instance().getStop());
		getContentPane().add(stopButton);
		getContentPane().add(Box.createRigidArea(new Dimension(3, 1)));

		JButton stepButton = new JButton("Step");
		stepButton.addActionListener(Program.instance().getStep());
		getContentPane().add(stepButton);
				
	}

}
