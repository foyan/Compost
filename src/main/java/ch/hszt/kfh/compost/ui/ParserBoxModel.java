package ch.hszt.kfh.compost.ui;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import ch.hszt.kfh.compost.parsing.BinaryParser;
import ch.hszt.kfh.compost.parsing.CompostParser;
import ch.hszt.kfh.compost.parsing.MnemonicParser;

public class ParserBoxModel implements ComboBoxModel {

	private ArrayList<CompostParser> parsers = new ArrayList<CompostParser>();
	
	private CompostParser parser;
	
	public ParserBoxModel() {
		parsers.add(new MnemonicParser());
		parsers.add(new BinaryParser());
		parser = parsers.get(0);
	}
	
	public CompostParser getParser() {
		return parser;
	}
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
	}

	@Override
	public Object getElementAt(int arg0) {
		return parsers.get(arg0);
	}

	@Override
	public int getSize() {
		return parsers.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {

	}

	@Override
	public Object getSelectedItem() {
		return parser;
	}

	@Override
	public void setSelectedItem(Object arg0) {
		parser = (CompostParser)arg0;
	}

}
