package com.avancial.app.business.train;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ITrain {

   public List<String> getTrainNumeros();

   public String getGareOrigine();

   public String getGareDestination();

   public void adapt(ITrain train, Date date_deb_SSIM, Date date_fin_SSIM);

   public void addCirculation(Circulation circultation);

   // public ITrain getTrainAPartirDuCatalogue(ITrainCatalogue trainCatalogue);

   // public Map<Date, Circulation> getDateJourCirculMap(); // sont pareils
   public Map<Date, JourCirculation> getJoursCirculation(); // sont pareils

   boolean compare(ITrain train);

   public Train getTrainAPartirDuCatalogue(TrainCatalogue trainCatalogue); 
   public Train getTrainSSIMRestreint(Train trainCatalogue)  ;

}
