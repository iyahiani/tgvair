package com.avancial.app.business.train;

import java.util.List;

/**
 * 
 * @author ismael.yahiani la factory Train permet de creer des objets de type
 *         train
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
   public static Train createTrainComplet2(TrainCatalogue trainCatalogue) {

      Train train = new Train(trainCatalogue.getListeNumeros(), trainCatalogue.getCirculations(),trainCatalogue.listeJoursCirculation);

      return train;
   }
   public static TrainCatalogue createTrainCata(Train train) {

      TrainCatalogue trainC = new TrainCatalogue(train.getListeNumeros(), train.getCirculations());

      return trainC;
   }

}
