package com.avancial.app.business.compagnieAerienne;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.avancial.app.business.train.Train;
//import javassist.bytecode.Mnemonic;
import com.avancial.app.business.train.circulation.JourCirculation;
import com.avancial.app.resources.utils.MaxMinDates;

public class ObserverJoursCirculation implements IObserverJoursCirculation {

   private TrainToCompagnie      tc2c;
   private List<Date>            listDatesdebut     = new ArrayList<>();
   private List<Date>            listDatesFin       = new ArrayList<>();

   public List<TrainToCompagnie> listTrainCompagnie = new ArrayList<>();

   public ObserverJoursCirculation(TrainToCompagnie tc2c, Train train, Date dateDebutService, Date dateFinService, Date dateExtraction) {
      this.tc2c = tc2c;
      this.listDatesdebut.add(tc2c.getDateDebutValidite());
      this.listDatesdebut.add(train.getDateDebutValidite());
      this.listDatesdebut.add(dateExtraction);
      this.listDatesdebut.add(dateDebutService);
      this.listDatesFin.add(tc2c.getDateFinValidite());
      this.listDatesFin.add(train.getDateFinValidite());
      this.listDatesFin.add(dateFinService);
   }

   @Override
   public void refresh(JourCirculation jourCirculation) {

      if (!jourCirculation.getDateCircul().before(MaxMinDates.getMaxDate(this.listDatesdebut)) && !jourCirculation.getDateCircul().after(MaxMinDates.getMinDate(this.listDatesFin)))
         if (this.tc2c.getListeJoursCirculation().containsKey(jourCirculation.getDateCircul())) {
            this.tc2c.getListeJoursCirculation().put(jourCirculation.getDateCircul(), jourCirculation);
            this.tc2c.calculeCirculationFromJoursCirculation();
           

         }
   }

   // /////// Getters And Setters

   @Override
   public TrainToCompagnie getTc2c() {
      return this.tc2c;
   }

   public void setTc2c(TrainToCompagnie tc2c) {
      this.tc2c = tc2c;
   }

   public List<TrainToCompagnie> getListTrainCompagnie() {
      return this.listTrainCompagnie;
   }

   public void setListTrainCompagnie(List<TrainToCompagnie> listTrainCompagnie) {
      this.listTrainCompagnie = listTrainCompagnie;
   }

}
