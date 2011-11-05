package ch.hszt.kfh.compost.caching;

import ch.hszt.kfh.compost.Compost;

public abstract class Cache {
	
	private Compost compost;
	
	public Compost getCompost() {
		return compost;
	}
	
	public void setCompost(Compost compost) {
		this.compost = compost;
	}

	public abstract boolean[] get(int address);
	
}
