package com.avancial.tgvair;

import java.util.ArrayList;
import java.util.List;

public class Train implements ITrain {

	private String periode ; 
	private List<String> circulation ;
	
	public List<String> getCirculation() {
		return circulation;
	}
	public void setCirculation(List<String> circulation) {
		this.circulation = circulation;
	}
	
	@Override
	public int compare() {
		// TODO Auto-generated method stub
		return 0;
	} 
	
	public Train() {
		this.circulation = new ArrayList<String>() ; 
	}
	public String getPeriode() {
		return periode;
	}
	public void setPeriode(String periode) {
		this.periode = periode;
	}
	@Override
	public Train getTrainByID() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Train> getAllTrains() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Train> getTrainByPeriodes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
