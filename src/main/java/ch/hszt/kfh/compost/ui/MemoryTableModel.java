package ch.hszt.kfh.compost.ui;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hszt.kfh.compost.Compost;
import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.Tools;

public class MemoryTableModel extends AbstractTableModel implements Observer {

	private static final long serialVersionUID = 1L;
	
	private int instructionPointer = -1;

	private ArrayList<Integer> cellsWritten = new ArrayList<Integer>();
	private ArrayList<Integer> cellsRead = new ArrayList<Integer>();
	
	public MemoryTableModel() {
		MemCell.getChangeObservable().addObserver(this);
		Program.instance().getCompost().getInstructionPointerChangedObservable().addObserver(this);
		Program.instance().getCompost().getCycleStartedObservable().addObserver(this);
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
		if (arg0 == MemCell.getChangeObservable()) {
			MemCell memCell = (MemCell) arg1;
			cellsWritten.add(memCell.getAddress());
			fireTableCellUpdated(memCell.getAddress());
			return;
		}
		if (arg0 == Program.instance().getCompost().getInstructionPointerChangedObservable()) {
			fireTableCellUpdated(instructionPointer);
			instructionPointer = Program.instance().getCompost().getInstructionPointer();
			fireTableCellUpdated(instructionPointer);
			return;
		}
		if (arg0 == Program.instance().getCompost().getCycleStartedObservable()) {
			ArrayList<Integer> w = new ArrayList<Integer>(cellsWritten);
			ArrayList<Integer> r = new ArrayList<Integer>(cellsRead);
			cellsWritten.clear();
			cellsRead.clear();
			for (int i : w) {
				fireTableCellUpdated(i);
			}
			for (int i : r) {
				fireTableCellUpdated(i);
			}
			int ip = instructionPointer;
			instructionPointer = -1;
			fireTableCellUpdated(ip);
			return;
		}
	}
	
	private void fireTableCellUpdated(int address) {
		int row = address / 4 / 2;
		int col = (address % 8) / 2 + 1;
		fireTableCellUpdated(row, col);
	}
	
	public boolean isCurrentInstructionCell(int row, int col) {
		int address = row * 4 * 2 + (col - 1) * 2;
		return address == Program.instance().getCompost().getInstructionPointer();
	}
	public boolean isCurrentCellWritten(int row, int col) {
		int address = row * 4 * 2 + (col - 1) * 2;
		return cellsWritten.contains(address);
	}

}
