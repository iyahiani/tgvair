package com.avancial.app.business.compagnieAerienne;

import java.util.List;

import com.avancial.app.business.train.circulation.JourCirculation;

public interface IObservableJoursCirculation {
 
   public void notifierTrainToCompagnie( JourCirculation jourCirculation)  ;
   public void addObservateur(IObserverJoursCirculation iObserver)      ;
   public void deleteObservateur(IObserverJoursCirculation iObserver)   ;
}
