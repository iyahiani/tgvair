package com.avancial.app.business.train;

import java.util.List;

/**
 * 
 * @author ismael.yahiani la factory Train permet de creer des objets de type train
 */
public class TrainFactory implements ITrainFactory {

   @Override
   public ITrain createTrain() {

      ITrain train = new Train();
      return train;
   }

   @Override
   public ITrain createTrainByListCirculation(List<Circulation> listCircu) {

      return null;
   }

   public ITrain createTrainComplet(ITrainCatalogue trainCatalogue) {

      Train train = new Train(trainCatalogue.getTrainNumeros(), trainCatalogue.getCirculations());

      return train;
   }

   /**
    * @author ismael.yahiani récupére le train référencé dans le catalogie à partir de la SSIM
    */
   public ITrain getTrainSSIMRestreint(TrainCatalogue trainCatalogue) {
      Train train = new Train();
      for (String num : trainCatalogue.getNumero_Train_Cat())
         train.setNumTrain(num);
      Circulation circulation = null;
      JourCirculation joursCirculation = null;

      for (Circulation circulSSIM : this.getCirculations()) {
         for (String num : trainCatalogue.getNumero_Train_Cat()) {
            if (num.equalsIgnoreCase(circulSSIM.getNumeroTrain())) {
               if (circulSSIM.getJourCirculation().getOrigine().equalsIgnoreCase(trainCatalogue.getGareOrigine()) && circulation == null) {

                  circulation = new Circulation();
                  circulation.setDateDebut(circulSSIM.getDateDebut());
                  circulation.setDateFin(circulSSIM.getDateFin());

                  circulation.setJourCirculation(circulSSIM.getJourCirculation());
                  // circulation.getJourCirculation().setHeureDepart(circulSSIM.getJourCirculation().getHeureDepart()) ;

                  circulation.setJoursCirculation(circulSSIM.getJoursCirculation());
                  circulation.setNumeroTrain(circulSSIM.getNumeroTrain());
               } else if (circulSSIM.getJourCirculation().getDestination().equalsIgnoreCase(trainCatalogue.getGareDestination()) && circulation != null) {
                  circulation.getJourCirculation().setDestination(circulSSIM.getJourCirculation().getDestination());
                  circulation.getJourCirculation().setHeureArrivee(circulSSIM.getJourCirculation().getHeureArrivee());
                  train.addCirculation(circulation);
                  circulation = null;
               }
            }
         }
      }

      return train;
   }
}
