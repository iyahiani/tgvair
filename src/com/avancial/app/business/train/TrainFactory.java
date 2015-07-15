package com.avancial.app.business.train;

import java.util.List;

import com.avancial.app.business.train.circulation.Circulation;

/**
 * 
 * @author ismael.yahiani la factory Train permet de creer des objets de type train
 */
public class TrainFactory implements ITrainFactory {

   @Override
   public ITrain createTrain() {

      ITrain train = new Train(null, null);
      return train;
   }

   @Override
   public ITrain createTrainByListCirculation(List<Circulation> listCircu) {

      return null;
   }

   public static Train createTrainComplet(TrainCatalogue trainCatalogue) {

      Train train = new Train(trainCatalogue.getListeNumeros(), trainCatalogue.getCirculations());

      return train;
   }

}
