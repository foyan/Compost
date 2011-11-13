package ch.hszt.kfh.compost.ui;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import ch.hszt.kfh.compost.Converter;
import ch.hszt.kfh.compost.ui.formatting.DataFormatter;

public class ConverterBoxModel implements ComboBoxModel {

	private ArrayList<Converter> converters = new ArrayList<Converter>();
	
	private Converter converter;
	
	public ConverterBoxModel() {
		FormatterBoxModel formatters = new FormatterBoxModel();
		for (DataFormatter formatter : formatters.getFormatters()) {
			converters.add(new Converter(formatter));
		}
		converter = converters.get(0);
	}
	
	public Converter getConverter() {
		return converter;
	}
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
	}

	@Override
	public Object getElementAt(int index) {
		return converters.get(index);
	}

	@Override
	public int getSize() {
		return converters.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
	}

	@Override
	public Object getSelectedItem() {
		return converter;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		converter = (Converter) anItem;
	}

}
