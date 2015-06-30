package com.avancial.tgvair.metier;

import java.util.List;

public interface ITrain {

	public boolean compare(TrainCatalogue train);
	public boolean compare(ITrain train);
	public String getGareOrigine() ;
	public String getGareDestination() ;
	public void adapt(TrainCatalogue train) ;
	public void addCirculation (Circulation circultation );
	public void setCirculation(List<Circulation> circulation); 

	public String getChaineCompare();
	
	public List<Circulation> getCirculations();
	public ITrain getTrainAPartirDuCatalogue(ITrainCatalogue trainCatalogue);
		
}
