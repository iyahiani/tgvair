package com.avancial.tgvair.metier;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ITrain {

	public boolean compare(ITrainCatalogue train);
	public boolean compare(ITrain train);
	public String getGareOrigine() ;
	public String getGareDestination() ;
	public ITrainCatalogue adapt(TrainCatalogue train) ;
	public void addCirculation (Circulation circultation );
	public void setCirculation(List<Circulation> circulation); 

	public String getChaineCompare();
	
	public List<Circulation> getCirculations();
	public ITrain getTrainAPartirDuCatalogue(ITrainCatalogue trainCatalogue);
    
   public Map<Date, Circulation> getDateJourCirculMap();    //sont pareils
   
		
}
