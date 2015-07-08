package com.avancial.app.business.train;

import java.util.List;

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

   public static Train createTrainComplet(ITrainCatalogue trainCatalogue) {

      Train train = new Train(trainCatalogue.getTrainNumeros(), trainCatalogue.getCirculations());

      return train;
   }

}
