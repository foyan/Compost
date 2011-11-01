package ch.hszt.kfh.compost.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScriptFrame extends JFrame implements ScriptProvider {

	private static final long serialVersionUID = 1L;

	/*
	 * private JTextArea code = new JTextArea("; Direct Addressing\n"); private
	 * JTextArea code = new
	 * JTextArea("; [Address:] Mnemonic Argument1[, Argument2]\n\n");
	 * 
	 * private static final String initialString = "; Direct Addressing\n:" +
	 * "; Address:"
	 */

	private JTextArea code = new JTextArea(
			"; [Address:] Mnemonic Argument1[, Argument2]\n\n");

	private String fileName;

	public ScriptFrame() {
		super();

		setBounds(0, 0, 600, 600);

		JScrollPane scroll = new JScrollPane(code);
		getContentPane().add(scroll, BorderLayout.CENTER);

		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		getContentPane().add(buttons, BorderLayout.NORTH);

		JButton loadButton = new JButton("Load...");
		loadButton.addActionListener(load);
		buttons.add(loadButton);
		buttons.add(Box.createHorizontalStrut(3));

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(save);
		buttons.add(saveButton);
		buttons.add(Box.createHorizontalStrut(3));

		JButton saveAsButton = new JButton("Save as...");
		saveAsButton.addActionListener(saveAs);
		buttons.add(saveAsButton);

		buttons.add(Box.createHorizontalGlue());

		JButton initButton = new JButton("Init");
		initButton
				.addActionListener(Program.instance().getInitFromScript(this));
		buttons.add(initButton);

		updateTitle();
	}

	private void updateTitle() {
		if (fileName == null) {
			setTitle("Code - Untitled");
		} else {
			setTitle("Code - " + fileName);
		}
	}

	@Override
	public String provideScript() {
		return code.getText();
	}

	private ActionListener load = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser ch = new JFileChooser();
			if (ch.showOpenDialog(ScriptFrame.this) == JFileChooser.APPROVE_OPTION) {
				try {
					File f = ch.getSelectedFile();
					BufferedReader r = new BufferedReader(new FileReader(f));
					StringBuffer sb = new StringBuffer();

					String line;
					while (!((line = r.readLine()) == null)) {
						sb.append(line + "\n");
					}
					r.close();
					code.setText(sb.toString());
					fileName = f.getPath();
					ScriptFrame.this.updateTitle();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		}
	};
	private ActionListener save = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (fileName == null) {
				saveAs.actionPerformed(arg0);
			}
		}
	};
	private ActionListener saveAs = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

		}
	};

}
