package ch.hszt.kfh.compost.ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.Tools;

public class MemoryTableModel extends AbstractTableModel implements Observer {

	private static final long serialVersionUID = 1L;
	
	public MemoryTableModel() {
		MemCell.addChangeObserver(this);
	}

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
		MemCell cell1 = Program.instance().getCompost().getMem(row * 4 * 2 + (col - 1) * 2);
		MemCell cell2 = Program.instance().getCompost().getMem(row * 4 * 2 + (col - 1) * 2 + 1);
		return Tools.fromBooleanArray(cell1.getBits(), false) + " " + Tools.fromBooleanArray(cell2.getBits(), false);
	}
	
	@Override
	public String getColumnName(int col) {
		if (col == 0) {
			return "Address";
		}
		return "+" + ((col-1) * 2) + " +" + ((col-1) * 2 + 1);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		MemCell memCell = (MemCell) arg1;
		int row = memCell.getAddress() / 4 / 2;
		int col = (memCell.getAddress() % 8) / 2 + 1;
		fireTableCellUpdated(row, col);
	}

}
