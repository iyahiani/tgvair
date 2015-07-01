package com.avancial.tgvair.metier;

import java.util.Date;
import java.util.List;

public interface ITrainCatalogue {

	String getGareOrigine();

	String getGareDestination();
	public List<Circulation> getCirculations();
	public void addCirculation(Circulation circulation); 
	public String[] getAllTrainID() ;
/**
 * 
 * @param dateDebut : date de début de la périoide de circulation 
 * @param dateFin : date de fin de la périodce de circulation 
 * @return
 * cette methode verifie si les dates des exceptions de circulations des train catalogue,  
 * sont inclusent dans les periodes fournit par la SSIM  
 */

	
	/**
	 * 
	 * @return 
	 * cette méthode extrait tout les trains du catalogue impactés par la SSIM 
	 */
	
}
