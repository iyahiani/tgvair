package com.avancial.reader;

import java.util.List;

public class Train implements ITrain {

	private List<String> circulation ;
	
	
	public List<String> getCirculation() {
		return circulation;
	}
	public void setCirculation(List<String> circulation) {
		this.circulation = circulation;
	}
	@Override
	public int compare(ITrain train) {
		
		return 0;
		
	}	
	
	
	
	
}
