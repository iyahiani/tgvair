package com.avancial.app.business.train;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.avancial.app.business.compagnieAerienne.IObservableJoursCirculation;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.business.train.circulation.JourCirculation;

public interface ITrain {

   public List<String> getTrainNumeros();

   public String getGareOrigine();

   public String getGareDestination();

   public void addCirculation(Circulation circultation);

   // public ITrain getTrainAPartirDuCatalogue(ITrainCatalogue trainCatalogue);

   // public Map<Date, Circulation> getDateJourCirculMap(); // sont pareils
   public Map<Date, JourCirculation> getJoursCirculation(); // sont pareils

   boolean compare(ITrain train);

   public Train getTrainAPartirDuCatalogue(TrainCatalogue trainCatalogue); 
   public Train getTrainSSIMRestreint(Train trainCatalogue)  ;

   void adapt(Train train, Date date_deb_SSIM, Date date_fin_SSIM, IObservableJoursCirculation iObs);

}
