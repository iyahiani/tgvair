package com.avancial.app.business.compagnieAerienne;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

//import javassist.bytecode.Mnemonic;

import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.circulation.JourCirculation;
import com.avancial.app.resources.utils.MaxMinDates;

public class ObserverJoursCirculation implements IObserverJoursCirculation {

   private TrainToCompagnie tc2c;
   private Train trainCatalogue;
   private Date dateExtraction;
   private Date dateDebutService;
   private Date dateFinService;

   private List<Date> listDatesdebut = new ArrayList<>();
   private List<Date> listDatesFin = new ArrayList<>();
   private Set<String> listCompagnieImpactees = new TreeSet<>();

   public List<TrainToCompagnie> listTrainCompagnie = new ArrayList<>();

   public ObserverJoursCirculation(TrainToCompagnie tc2c, Train train, Date dateDebutService, Date dateFinService, Date dateExtraction) {
      this.tc2c = tc2c;
      this.trainCatalogue = train;
      this.dateDebutService = dateDebutService;
      this.dateFinService = dateFinService;
      this.dateExtraction = dateExtraction;
      this.listDatesdebut.add(tc2c.getDateDebutValidite());
      this.listDatesdebut.add(train.getDateDebutValidite());
      this.listDatesdebut.add(dateExtraction);
      this.listDatesdebut.add(dateDebutService);
      this.listDatesFin.add(tc2c.getDateFinValidite());
      this.listDatesFin.add(train.getDateFinValidite());
      this.listDatesFin.add(dateFinService);
   }
   
   public void refresh(JourCirculation jourCirculation) {

      if (!jourCirculation.getDateCircul().before(MaxMinDates.getMaxDate(listDatesdebut)) 
            && !jourCirculation.getDateCircul().after(MaxMinDates.getMinDate(listDatesFin)))
         if (tc2c.getListeJoursCirculation().containsKey(jourCirculation.getDateCircul())) {
            tc2c.getListeJoursCirculation().put(jourCirculation.getDateCircul(),jourCirculation); 
            //this.listCompagnieImpactees.add(tc2c.getCodeCompagnie()) ;  
            
            
         }
       }

   ///////// Getters And Setters

   public TrainToCompagnie getTc2c() {
      return tc2c;
   }

   public void setTc2c(TrainToCompagnie tc2c) {
      this.tc2c = tc2c;
   }
  
   public List<TrainToCompagnie> getListTrainCompagnie() {
      return listTrainCompagnie;
   }

   public void setListTrainCompagnie(List<TrainToCompagnie> listTrainCompagnie) {
      this.listTrainCompagnie = listTrainCompagnie;
   }

   public Set<String> getListCompagnieImpactees() {
      return listCompagnieImpactees;
   }

}
