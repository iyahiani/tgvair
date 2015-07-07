package com.avancial.app.business.train;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ITrain {

	public boolean compare(Map<Date, Circulation> cat, Map<Date, Circulation> ssim) ; 
	public boolean compare(ITrain train) ; 
	
	
	public String getGareOrigine() ;
	public String getGareDestination() ;
	public Map<Date,Circulation> adapt(TrainCatalogue train) ;
	public void addCirculation (Circulation circultation );
	public void setCirculation(List<Circulation> circulation); 

	public String getChaineCompare();
	
	public List<Circulation> getCirculations();
	public ITrain getTrainAPartirDuCatalogue(TrainCatalogue trainCatalogue);
    
   public Map<Date, Circulation> getDateJourCirculMap();    //sont pareils
  
   public void modifierCircul(TrainCatalogue trainCat) ;
		
}
