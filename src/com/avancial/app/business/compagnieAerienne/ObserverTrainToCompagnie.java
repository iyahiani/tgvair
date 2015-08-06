package com.avancial.app.business.compagnieAerienne;

import java.util.ArrayList;
import java.util.List;

import com.avancial.app.business.train.circulation.JourCirculation;

public class ObserverTrainToCompagnie implements IObserverTrainToCompagnie{

   public List<TrainToCompagnie> listTrainCompagnie = new ArrayList<TrainToCompagnie>( );
   public void refresh() {
   }
}
