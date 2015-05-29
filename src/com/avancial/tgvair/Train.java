package com.avancial.tgvair;

import java.util.ArrayList;
import java.util.List;

public class Train implements ITrain {
	///////////////////
	
	//private String periode ; 
	private List<Circulation> circulation ;
	
	///////////////////
	
	public List<Circulation> getCirculation() {
		return circulation;
	}
	public void setCirculation(List<Circulation> circulation) {
		this.circulation = circulation;
	}
	
	public String getChaineCompare() {
		StringBuilder sb = new StringBuilder() ; 
		
		for (Circulation circulation : circulation) {
			sb.append(circulation.getChaineCircu());
		} 
		
		
		return sb.toString();
	}
	
	@Override
	public boolean compare(ITrain train) {
		
		return (this.getChaineCompare().equalsIgnoreCase(train.getChaineCompare()));
	}
	
	
	public Train() {
		this.circulation = new ArrayList<Circulation>() ; 
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
