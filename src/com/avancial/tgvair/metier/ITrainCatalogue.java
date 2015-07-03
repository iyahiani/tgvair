package com.avancial.tgvair.metier;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ITrainCatalogue {

	String getGareOrigine();

	String getGareDestination();
	public List<Circulation> getCirculations();
	public void addCirculation(Circulation circulation); 
	public String[] getAllTrainID() ;
/**
 * 
 * @param dateDebut : date de d�but de la p�rioide de circulation 
 * @param dateFin : date de fin de la p�riodce de circulation 
 * @return
 * cette methode verifie si les dates des exceptions de circulations des train catalogue,  
 * sont inclusent dans les periodes fournit par la SSIM  
 */

   

	
	/**
	 * 
	 * @return 
	 * cette m�thode extrait tout les trains du catalogue impact�s par la SSIM 
	 */
	
}
