package com.avancial.app.business.compagnieAerienne;

import java.util.ArrayList;
import java.util.List;

import com.avancial.app.business.train.circulation.JourCirculation;

public class ObservableJoursCirculation implements IObservableJoursCirculation {
   
   
   private List<IObserverJoursCirculation> listObservers = new ArrayList<>();
   
   public void notifierTrainToCompagnie(JourCirculation jourCirculation) {
      for (IObserverJoursCirculation iObs : this.listObservers) {
         iObs.refresh(jourCirculation);
      }
   }
   public void addObservateur(IObserverJoursCirculation iObserver) {
    this.listObservers.add(iObserver);
   }
  
   public void deleteObservateur(IObserverJoursCirculation iObserver) {
      this.listObservers.remove(iObserver);
   }
   public List<IObserverJoursCirculation> getListObservers() {
      return this.listObservers;
   }
  
  
}
