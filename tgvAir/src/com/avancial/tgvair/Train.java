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
	public List<ITrain> getAllTrains() {
		List<ITrain> allTrains = new ArrayList<ITrain>() ;
		return allTrains ;
 	}
	@Override
	public List<Train> getTrainByPeriodes() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ITrain findTrainByID(ITrain train) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ITrain findTrainByPerdiode(ITrain train) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ITrain findTrainByPerdiode(String periode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
