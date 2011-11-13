package ch.hszt.kfh.compost.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import ch.hszt.kfh.compost.ui.formatting.DataFormatter;
import ch.hszt.kfh.compost.ui.formatting.DecimalBytesFormatter;
import ch.hszt.kfh.compost.ui.formatting.DecimalWordFormatter;
import ch.hszt.kfh.compost.ui.formatting.HexBytesFormatter;
import ch.hszt.kfh.compost.ui.formatting.OpCodeFormatter;
import ch.hszt.kfh.compost.ui.formatting.TwoComplementFormatter;

public class FormatterBoxModel implements ComboBoxModel {

	private List<DataFormatter> formatters = new ArrayList<DataFormatter>();
	private DataFormatter formatter;
	private ManualObservable changedObservable = new ManualObservable();
	
	public FormatterBoxModel() {
		formatters.add(new TwoComplementFormatter());
		formatters.add(new DecimalBytesFormatter());
		formatters.add(new DecimalWordFormatter());
		formatters.add(new HexBytesFormatter());
		formatters.add(new OpCodeFormatter(new TwoComplementFormatter()));
		formatters.add(new OpCodeFormatter(new DecimalBytesFormatter()));
		formatters.add(new OpCodeFormatter(new DecimalWordFormatter()));
		formatters.add(new OpCodeFormatter(new HexBytesFormatter()));
		formatter = formatters.get(0);
	}
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
	}

	@Override
	public Object getElementAt(int arg0) {
		return formatters.get(arg0);
	}

	@Override
	public int getSize() {
		return formatters.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
	}

	@Override
	public Object getSelectedItem() {
		return formatter;
	}

	@Override
	public void setSelectedItem(Object arg0) {
		formatter = (DataFormatter)arg0;
		changedObservable.notifyObservers();
	}
	
	public DataFormatter getFormatter() {
		return formatter;
	}
	
	public List<DataFormatter> getFormatters() {
		return formatters;
	}
	
	public Observable getChangedObservable() {
		return changedObservable;
	}

}
