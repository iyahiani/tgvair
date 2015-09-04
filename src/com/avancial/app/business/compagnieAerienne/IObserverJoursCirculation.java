package com.avancial.app.business.compagnieAerienne;

import com.avancial.app.business.train.circulation.JourCirculation;

public interface IObserverJoursCirculation {

   public void refresh(JourCirculation jourCirculation) ; 
   public TrainToCompagnie getTc2c() ;
   
}
