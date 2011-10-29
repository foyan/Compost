package ch.hszt.kfh.compost.ui;

import javax.swing.JTextField;

import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.Tools;

public class MemCellField extends JTextField {
	
	private static final long serialVersionUID = 1L;

	private MemCell memCell;
	
	public MemCellField(MemCell memCell) {
		super();
		this.memCell = memCell;
		updateView();
	}
	
	private void updateView() {
		setText(Tools.fromBooleanArray(memCell.getBits(), true));		
	}

}
