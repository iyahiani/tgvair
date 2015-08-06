package com.avancial.app.business.compagnieAerienne;

import java.util.List;

import com.avancial.app.business.train.circulation.JourCirculation;

public interface IObservableJoursCirculation {
 
   public void notifierTrainToCompagnie(List<TrainToCompagnie> ttc, JourCirculation jourCirculation)  ;
   public void addObservateur(TrainToCompagnie ttc)      ;
   public void deleteObservateur(TrainToCompagnie ttc)   ;
}
