package ch.hszt.kfh.compost.ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

import ch.hszt.kfh.compost.MemCell;
import ch.hszt.kfh.compost.Tools;

public class MemCellField extends JTextField implements Observer {
	
	private static final long serialVersionUID = 1L;

	private MemCell memCell;
	
	public MemCellField(MemCell memCell) {
		super();
		this.memCell = memCell;
		MemCell.getChangeObservable().addObserver(this);
		updateView();
	}
	
	private void updateView() {
		setText(Tools.fromBooleanArray(memCell.getBits(), true));		
	}

	@Override
	public void update(Observable o, Object arg) {
		MemCell memCell = (MemCell) arg;
		if (this.memCell == memCell) {
			updateView();
		}
	}

}
