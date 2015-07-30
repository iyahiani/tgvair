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
    *           : date de d�but de la p�rioide de circulation
    * @param dateFin
    *           : date de fin de la p�riodce de circulation
    * @return cette methode verifie si les dates des exceptions de circulations des train catalogue, sont inclusent dans les periodes fournit par la SSIM
    */

   List<String> getTrainNumeros();

   /**
    * 
    * @return cette m�thode extrait tout les trains du catalogue impact�s par la SSIM
    */

}
