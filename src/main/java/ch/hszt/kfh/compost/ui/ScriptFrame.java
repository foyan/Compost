package ch.hszt.kfh.compost.ui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScriptFrame extends JFrame implements ScriptProvider {

	private static final long serialVersionUID = 1L;
	
	private JTextArea code = new JTextArea("# Address\tMnemonic\tArgument1, Argument2\n\n");

	public ScriptFrame() {
		super();
		
		setBounds(0, 0, 600, 600);
		
		JScrollPane scroll = new JScrollPane(code);
		getContentPane().add(scroll, BorderLayout.CENTER);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		getContentPane().add(buttons, BorderLayout.NORTH);
		
		JButton initButton = new JButton("Init");
		initButton.addActionListener(Program.instance().getInitFromScript(this));
		buttons.add(initButton);
	}
	
	@Override
	public String provideScript() {
		return code.getText();
	}

}
