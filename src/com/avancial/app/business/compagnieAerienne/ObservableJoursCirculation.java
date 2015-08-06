package com.avancial.app.business.compagnieAerienne;

import java.util.ArrayList;
import java.util.List;

import com.avancial.app.business.train.circulation.JourCirculation;

public class ObservableJoursCirculation implements IObservableJoursCirculation {
   
   private List<TrainToCompagnie> listTrainToCompagnie = new ArrayList<TrainToCompagnie>();
   private List<JourCirculation> listJoursCirculation = new ArrayList<JourCirculation>();
   
   public void notifierTrainToCompagnie(List<TrainToCompagnie> ttc, JourCirculation jourCirculation) {
      
   }
   public void addObservateur(TrainToCompagnie ttc) {
    this.listTrainToCompagnie.add(ttc);
   }
   public void deleteObservateur(TrainToCompagnie ttc) {
      this.listTrainToCompagnie.remove(this.listTrainToCompagnie.indexOf(ttc));
   } 
  
}
