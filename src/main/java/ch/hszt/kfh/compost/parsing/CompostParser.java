package ch.hszt.kfh.compost.parsing;

import ch.hszt.kfh.compost.Compost;

public abstract class CompostParser {

	private String string;
	
	public String getString() {
		return string;
	}
	
	public void setString(String string) {
		this.string = string;
	}

	private Compost compost;
	
	public Compost getCompost() {
		return compost;
	}
	public void setCompost(Compost compost) {
		this.compost = compost;
	}
	
	public abstract void parse() throws Exception;
	
}
