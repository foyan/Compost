package ch.hszt.kfh.compost.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MemoryFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public MemoryFrame() {
		super();
		setTitle("Memory");
		setBounds(0, 0, 800, 800);
		
		final MemoryTableModel model = new MemoryTableModel();
		JTable table = new JTable();
		table.setModel(model);
		JScrollPane scroll = new JScrollPane(table);
		
		getContentPane().add(scroll);
		
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
 			private static final long serialVersionUID = 1L;

			@Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
 
                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
 
                if (model.isCurrentInstructionCell(row, column)) {
                    c.setBackground(Color.BLUE);
                    c.setForeground(Color.WHITE);
                } else if (model.isCurrentCellWritten(row, column)) {
                	c.setBackground(Color.RED);
                	c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });
 
		
	}

}
