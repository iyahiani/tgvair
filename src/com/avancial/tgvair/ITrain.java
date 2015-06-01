package com.avancial.tgvair;

import java.util.List;

public interface ITrain {

	public boolean compare(ITrain train) ;
	public Train getTrainByID() ; 	
	public List<ITrain> getAllTrains() ; 
	public List<Train> getTrainByPeriodes() ;
	public ITrain findTrainByID(ITrain train ) ; 
	public ITrain findTrainByPerdiode(String periode) ; 
	public ITrain findTrainByPerdiode(ITrain train);	
	public void setCirculation(List<Circulation> circulation) ;
	public String getChaineCompare();
	}
	

