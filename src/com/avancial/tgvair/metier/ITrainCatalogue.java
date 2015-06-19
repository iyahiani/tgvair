package com.avancial.tgvair.metier;

import java.util.Date;
import java.util.List;

public interface ITrainCatalogue {
	
/**
 * 
 * @param dateDebut : date de début de la périoide de circulation 
 * @param dateFin : date de fin de la périodce de circulation 
 * @return
 * cette methode verifie si les dates des exceptions de circulations des train catalogue,  
 * sont inclusent dans les periodes fournit par la SSIM  
 */
	public boolean verifyTrainCatalogueExceptionInPeriode(Date dateDebut, Date dateFin) ; 
	
	/**
	 * 
	 * @return 
	 * cette méthode extrait tout les trains du catalogue impactés par la SSIM 
	 */
	public List<TrainCatalogue> trainsImpactéInPeriode();
}
