package ch.hszt.kfh.compost.ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

import ch.hszt.kfh.compost.MemCell;

public class MemCellField extends JTextField implements Observer {
	
	private static final long serialVersionUID = 1L;

	private MemCell memCell;
	private FormatterBoxModel formatterBoxModel;
	private boolean isDataCell;
	
	public MemCellField(FormatterBoxModel formatterBoxModel, MemCell memCell, boolean isDataCell) {
		super();
		this.formatterBoxModel = formatterBoxModel;
		this.isDataCell = isDataCell;
		formatterBoxModel.getChangedObservable().addObserver(this);
		this.memCell = memCell;
		MemCell.getChangeObservable().addObserver(this);
		updateView();
	}
		
	private void updateView() {
		setText(formatterBoxModel.getFormatter().format(memCell.getBits(), isDataCell));
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == formatterBoxModel.getChangedObservable()) {
			updateView();
			return;
		}
		MemCell memCell = (MemCell) arg;
		if (this.memCell == memCell) {
			updateView();
		}
	}

}
