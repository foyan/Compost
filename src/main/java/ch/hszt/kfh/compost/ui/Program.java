package ch.hszt.kfh.compost.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.parsing.CompostParser;
import ch.hszt.kfh.compost.parsing.MnemonicStringParser;

public class Program {

	public static void main(String[] args) {
		CompostFrame frame = new CompostFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private Compost compost = new Compost();
	
	public Compost getCompost() {
		return compost;
	}
	
	private static Program inst = new Program();
	
	public static Program instance() {
		return inst;
	}
	
	private ActionListener showRegisters = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new RegistersFrame().setVisible(true);
		}
	};
	
	private ActionListener showMemory = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new MemoryFrame().setVisible(true);
		}
	};
	
	private ActionListener showScript = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new ScriptFrame().setVisible(true);
		}
	};
	
		
	public ActionListener getShowRegisters() {
		return showRegisters;
	}
	
	public ActionListener getShowMemory() {
		return showMemory;
	}
	
	public ActionListener getShowScript() {
		return showScript;
	}
	
	public ActionListener getInitFromScript(final ScriptProvider provider) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CompostParser parser = new MnemonicStringParser(provider.provideScript());
				parser.setCompost(compost);
				try {
					parser.parse();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		};
	}

}
