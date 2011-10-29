package ch.hszt.kfh.compost.ui;

import javax.swing.table.AbstractTableModel;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.Tools;

public class MemoryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return Compost.TOTAL_MEM / 4 / 2;
	}

	@Override
	public Object getValueAt(int row, int col) {
		if (col == 0) {
			return (row * 4 * 2) + "-" + (row * 4 * 2 + 7);
		}
		MemCell cell1 = Program.instance().getCompost().getMem(row * 4 * 2 + col - 1);
		MemCell cell2 = Program.instance().getCompost().getMem(row * 4 * 2 + col - 1 + 1);
		return Tools.fromBooleanArray(cell1.getBits(), false) + " " + Tools.fromBooleanArray(cell2.getBits(), false);
	}
	
	@Override
	public String getColumnName(int col) {
		if (col == 0) {
			return "Address";
		}
		return "+" + ((col-1) * 2) + " +" + ((col-1) * 2 + 1);
	}

}
