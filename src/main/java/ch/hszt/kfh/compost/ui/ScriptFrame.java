package ch.hszt.kfh.compost.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.Converter;
import ch.hszt.kfh.compost.parsing.CompostParser;
import ch.hszt.kfh.compost.parsing.MnemonicParser;
import ch.hszt.kfh.compost.ui.formatting.TwoComplementFormatter;

public class ScriptFrame extends JFrame implements ScriptProvider {

	private static final long serialVersionUID = 1L;

	private JTextArea code = new JTextArea(
			"; [Address:] Mnemonic Argument1[, Argument2]\n\n");

	private String fileName;
	
	private ParserBoxModel parserBoxModel = new ParserBoxModel();

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
		
		buttons.add(Box.createHorizontalStrut(10));
		
		JButton convertToBinaryButton = new JButton("=> bin");
		convertToBinaryButton.addActionListener(convertToBinary);
		buttons.add(convertToBinaryButton);
		
		buttons.add(Box.createHorizontalGlue());

		JComboBox parserBox = new JComboBox();
		parserBox.setModel(parserBoxModel);
		buttons.add(parserBox);
		
		JButton initButton = new JButton("Parse");
		initButton.addActionListener(getInitFromScript());
		buttons.add(initButton);

		updateTitle();
	}

	private ActionListener getInitFromScript() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CompostParser parser = parserBoxModel.getParser();
				parser.setString(code.getText());
				Compost compost = Program.instance().getCompost();
				parser.setCompost(compost);
				try {
					parser.parse();
					compost.getCycleStartedObservable().notifyObservers();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		};
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
			} else {
				File f = new File(fileName);
				try {
					BufferedWriter w = new BufferedWriter(new FileWriter(f));
					w.write(code.getText());
					w.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		}
	};
	private ActionListener saveAs = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

		}
	};
	
	private ActionListener convertToBinary = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Converter conv = new Converter();
			conv.setDirectAddressing(false);
			conv.setFormatter(new TwoComplementFormatter());
			CompostParser parser = new MnemonicParser();
			parser.setString(provideScript());
			conv.setParser(parser);
			try {
				code.setText(conv.convert());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
	};

}
