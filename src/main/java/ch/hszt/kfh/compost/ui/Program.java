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
	
	private ActionListener step = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				compost.oneOperation();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
	};
	
	private Runner runner;
	
	private ActionListener start = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			runner = new Runner();
			new Thread(runner).start();
		}
	};
	
	private ActionListener stop = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (runner != null) {
				runner.setStop();
			}
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
					compost.getCycleStartedObservable().notifyObservers();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		};
	}
	
	public ActionListener getStep() {
		return step;
	}
	
	public ActionListener getStart() {
		return start;
	}
	
	public ActionListener getStop() {
		return stop;
	}
	
	private class Runner implements Runnable {

		private boolean stop;
		
		public void setStop() {
			stop = true;
		}
		
		@Override
		public void run() {
			try {
				while (!stop && compost.oneOperation()) {
					Thread.sleep(500);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		
	}

}