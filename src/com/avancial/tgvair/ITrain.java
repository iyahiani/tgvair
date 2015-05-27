package com.avancial.tgvair;

import java.util.List;

public interface ITrain {

	public int compare() ;
	public Train getTrainByID() ; 	
	public List<ITrain> getAllTrains() ; 
	public List<Train> getTrainByPeriodes() ;
	public ITrain findTrainByID(ITrain train ) ; 
	public ITrain findTrainByPerdiode(String periode) ;
	public ITrain findTrainByPerdiode(ITrain train);	
	}
	

