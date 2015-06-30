package com.avancial.tgvair.metier;

import java.util.ArrayList;
import java.util.List;

public class Train implements ITrain {

   private String[] numTrain;

   private List<Circulation> circulation;

   public String[] getNumTrain() {
      return numTrain;
   }

   public void setNumTrain(String[] numTrain) {
      this.numTrain = numTrain;
   }

   public List<Circulation> getCirculation() {
      return this.circulation;
   }

   public void setCirculation(List<Circulation> circulation) {
      this.circulation = circulation;
   }

   public String getChaineCompare() {

      StringBuilder sb = new StringBuilder();
      for (Circulation circulation : this.circulation) {
         sb.append(circulation.getChaineCircu());
         sb.append("\n");
      }

      return sb.toString();
   }

   public Train() {
      this.circulation = new ArrayList<>();
   }

   @Override
   public void addCirculation(Circulation circultation) {
      this.circulation.add(circultation);
   }

   @Override
   public List<Circulation> getCirculations() {
      return this.circulation;
   }

   @Override
   public boolean compare(ITrainCatalogue train) {
     
      boolean equal = false;

      for (Circulation ssimcircul : this.getCirculations()) {

         for (Circulation catalCircul : train.getCirculations()) {

            if (ssimcircul.compare(catalCircul))
                equal = true;
         }
      }
      return equal;
   }

   @Override
   public boolean compare(ITrain train) {
      return false;
   }

   @Override
   public ITrainCatalogue adapt(TrainCatalogue train) {
     return null; 
   }
   
   @Override
   public String getGareOrigine() {
      return this.getCirculation().get(0).getOrigine();
   }
   
   @Override
   public String getGareDestination() {

      return this.getCirculation().get(getCirculation().size()-1).getDestination();
   }

   @Override
   public ITrain getTrainAPartirDuCatalogue(ITrainCatalogue trainCatalogue) {
      ITrain train = new Train();

      Circulation circulation = null;
      for (Circulation circulSSIM : this.getCirculations()) {
         if (circulSSIM.getOrigine().equalsIgnoreCase(trainCatalogue.getGareOrigine()) && circulation == null) {   // manque un test sur la periode
            circulation = new Circulation();
            circulation.setOrigine(circulSSIM.getOrigine());
            circulation.setDateDebut(circulSSIM.getDateDebut());
            circulation.setDateFin(circulSSIM.getDateFin());
            circulation.setHeureDepart(circulSSIM.getHeureDepart());
            circulation.setJoursCirculation(circulSSIM.getJoursCirculation());
         }
         else if (circulSSIM.getDestination().equalsIgnoreCase(trainCatalogue.getGareDestination()))  {
            circulation.setDestination(circulSSIM.getDestination());
            circulation.setHeureArrivee(circulSSIM.getHeureArrivee());
            train.addCirculation(circulation);
            circulation = null;
         }
      }
      return train   ;
   }
}
