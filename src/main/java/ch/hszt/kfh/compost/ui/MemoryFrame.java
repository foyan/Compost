package ch.hszt.kfh.compost.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MemoryFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public MemoryFrame() {
		super();
		setTitle("Memory");
		setBounds(0, 0, 800, 800);
		
		JTable table = new JTable();
		table.setModel(new MemoryTableModel());
		JScrollPane scroll = new JScrollPane(table);
		
		getContentPane().add(scroll);
		
		/*
		getContentPane().setLayout(new GridLayout(0, 5));
		
		getContentPane().add(new JLabel(""));
		
		getContentPane().add(new JLabel("+ 0 1"));
		getContentPane().add(new JLabel("+ 2 3"));
		getContentPane().add(new JLabel("+ 4 5"));
		getContentPane().add(new JLabel("+ 6 7"));
		
		for (int i = 0; i < Compost.TOTAL_MEM; i++) {
			if (i % 4 == 0) {
				getContentPane().add(new JLabel(i + ""));
			}
			getContentPane().add(new MemCellField(Program.instance().getCompost().getMem(i)));
		}
		*/
		
	}

}
