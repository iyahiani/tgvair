package com.avancial.tgvair.metier;

import java.util.List;

public interface ITrain {

	public boolean compare(ITrain train);

	public Train getTrainByID();
	
	public void addCirculation (Circulation circultation );

	public List<ITrain> getAllTrains();

	public ITrain findTrainByID(int[] train, String ligne);

	public void setCirculation(List<Circulation> circulation); 

	public String getChaineCompare();
	
	public List<Circulation> getCirculations();
		
}
