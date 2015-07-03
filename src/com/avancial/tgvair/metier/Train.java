package com.avancial.tgvair.metier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Train implements ITrain {

   private String numTrain;

   private List<Circulation> circulation;

   public String getNumTrain() {
      return numTrain;
   }

   public void setNumTrain(String numTrain) {
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

            if (ssimcircul.compareCirculation(catalCircul))
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

      return this.getCirculation().get(getCirculation().size() - 1).getDestination();
   }

   @Override
   public ITrain getTrainAPartirDuCatalogue(ITrainCatalogue trainCatalogue) {
      ITrain train = new Train();
      Circulation circulation = null;
      for (Circulation circulSSIM : this.getCirculations()) {

         if (circulSSIM.getOrigine().equalsIgnoreCase(trainCatalogue.getGareOrigine()) && circulation == null) {
            circulation = new Circulation();
            circulation.setOrigine(circulSSIM.getOrigine());
            circulation.setDateDebut(circulSSIM.getDateDebut());
            circulation.setDateFin(circulSSIM.getDateFin());
            circulation.setHeureDepart(circulSSIM.getHeureDepart());
            circulation.setJoursCirculation(circulSSIM.getJoursCirculation());
         } else if (circulSSIM.getDestination().equalsIgnoreCase(trainCatalogue.getGareDestination())) {
            circulation.setDestination(circulSSIM.getDestination());
            circulation.setHeureArrivee(circulSSIM.getHeureArrivee());
            train.addCirculation(circulation);
            circulation = null;
         }
      }
      return train;
   }

   /**
    * cette methode retourne une map des jours de circulation et de leurs dates
    */

   @Override
   public Map<Date, Circulation> getDateJourCirculMap() {
      Calendar dateDebut = Calendar.getInstance();
      Calendar dateFin = Calendar.getInstance();

      Map<Date, Circulation> mapCirucl = new TreeMap<Date, Circulation>();
      for (Circulation c : this.getCirculations()) {
         dateDebut.setTime(c.getDateDebut());
         dateFin.setTime(c.getDateFin());

         while (!dateDebut.getTime().after(dateFin.getTime())) {

            if (c.getJoursCirculation().contains(String.valueOf(dateDebut.get(Calendar.DAY_OF_WEEK) - 1)))
               mapCirucl.put(dateDebut.getTime(), c);
            if (dateDebut.get(Calendar.DAY_OF_WEEK) - 1 == 0 && c.getJoursCirculation().contains("7"))
               mapCirucl.put(dateDebut.getTime(), c);
            dateDebut.add(Calendar.DATE, 1);
         }

      }

      return mapCirucl;
   }
}
