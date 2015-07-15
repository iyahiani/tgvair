package com.avancial.app.business.train;

import java.util.List;

import com.avancial.app.business.train.circulation.Circulation;

public interface ITrainCatalogue {

   String getGareOrigine();

   String getGareDestination();

   public List<Circulation> getCirculations();

   public void addCirculation(Circulation circulation);

   public String[] getAllTrainID();

   public void compareSSIM(ITrain train);

   /**
    * 
    * @param dateDebut
    *           : date de début de la périoide de circulation
    * @param dateFin
    *           : date de fin de la périodce de circulation
    * @return cette methode verifie si les dates des exceptions de circulations des train catalogue, sont inclusent dans les periodes fournit par la SSIM
    */

   List<String> getTrainNumeros();

   /**
    * 
    * @return cette méthode extrait tout les trains du catalogue impactés par la SSIM
    */

}
