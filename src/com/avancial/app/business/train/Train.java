package com.avancial.app.business.train;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

   @Override
   public void setCirculation(List<Circulation> circulation) {
      this.circulation = circulation;
   }

   @Override
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
   public Map<Date, Circulation> adapt(TrainCatalogue train) {

      boolean comp = true;

      Map<Date, Circulation> dateEtjoursCircuCatalog = new TreeMap<Date, Circulation>();
      Map<Date, Circulation> dateEtjoursCircuSSIM = new TreeMap<Date, Circulation>();

      dateEtjoursCircuCatalog = this.getDateJourCirculMap();
      dateEtjoursCircuSSIM = train.getDateJourCirculMap();

      for (Map.Entry<Date, Circulation> entryCatalog : dateEtjoursCircuCatalog.entrySet()) {
         for (Map.Entry<Date, Circulation> entrySSIM : dateEtjoursCircuSSIM.entrySet()) {

            if (entryCatalog.getKey().equals(entrySSIM.getKey()))
               if (!entryCatalog.getValue().compareCirculation(entrySSIM.getValue())) {

                  entryCatalog.setValue(entrySSIM.getValue());
               }
         }
      }
      return dateEtjoursCircuCatalog;
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
   /**
    * @author ismael.yahiani
    * récupére le train référencé dans le catalogie à partir de la SSIM 
    */
   public ITrain getTrainAPartirDuCatalogue(TrainCatalogue trainCatalogue) {
      ITrain train = new Train();
      Circulation circulation = null;

      for (Circulation circulSSIM : this.getCirculations()) {
         for (String num : trainCatalogue.getNumero_Train_Cat()) {
            if (num.equalsIgnoreCase(circulSSIM.getNumeroTrain())) {
               if (circulSSIM.getOrigine().equalsIgnoreCase(trainCatalogue.getGareOrigine()) && circulation == null) {
                  circulation = new Circulation();
                  circulation.setOrigine(circulSSIM.getOrigine());
                  circulation.setDateDebut(circulSSIM.getDateDebut());
                  circulation.setDateFin(circulSSIM.getDateFin());
                  circulation.setHeureDepart(circulSSIM.getHeureDepart());
                  circulation.setJoursCirculation(circulSSIM.getJoursCirculation());
                  circulation.setNumeroTrain(circulSSIM.getNumeroTrain());
               } else if (circulSSIM.getDestination().equalsIgnoreCase(trainCatalogue.getGareDestination()) && circulation != null) {
                  circulation.setDestination(circulSSIM.getDestination());
                  circulation.setHeureArrivee(circulSSIM.getHeureArrivee());
                  train.addCirculation(circulation);
                  circulation = null;
               }
            }
         }
      }

      return train;
   }

   /**
    * @author ismael.yahiani cette methode retourne une map des jours de
    *         circulation et de leurs dates
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

   /**
    * retourne false si les circulation ne sont pas pareilles
    */
   @Override
   public boolean compare(Map<Date, Circulation> cat, Map<Date, Circulation> ssim) {
      boolean comp = true;

      for (Map.Entry<Date, Circulation> entryCatalog : cat.entrySet()) {
         for (Map.Entry<Date, Circulation> entrySSIM : ssim.entrySet()) {

            if (entryCatalog.getKey().equals(entrySSIM.getKey()))
               if (!entryCatalog.getValue().compareCirculation(entrySSIM.getValue())) {

                  //return false;
                  System.out.println("catalogue = " + entryCatalog.getKey() + "\t"
                  + "SSIM = " + entrySSIM.getKey() + "\t" + "CatalogueCircul" + "\t"
                        + entryCatalog.getValue().getChaineCircu() + "\t"
                        + "SSIM Circul" + " " + entrySSIM.getValue().getChaineCircu());

<<<<<<< HEAD
                  // return false;
=======
                  return false;
>>>>>>> f219dc85a30245e988cb2553770233fb0d45a137

               }
         }

      }
      return true;
   }

   /**
    * @author ismael.yahiani adapte les cicrculation dans les catalogues
    */

   @Override
   public void modifierCircul(TrainCatalogue trainCat) {
      
   }

   @Override
   public boolean compare(ITrain train) {
      boolean comp = true;
      Map<Date, Circulation> cat = new TreeMap<Date, Circulation>();
      Map<Date, Circulation> ssim = new TreeMap<Date, Circulation>();
      for (Map.Entry<Date, Circulation> entryCatalog : cat.entrySet()) {
         for (Map.Entry<Date, Circulation> entrySSIM : ssim.entrySet()) {

            if (entryCatalog.getKey().equals(entrySSIM.getKey()))
               if (!entryCatalog.getValue().compareCirculation(entrySSIM.getValue())) {

                  //return false;
                  System.out.println("catalogue = " + entryCatalog.getKey() + "\t"
                  + "SSIM = " + entrySSIM.getKey() + "\t" + "CatalogueCircul" + "\t"
                        + entryCatalog.getValue().getChaineCircu() + "\t"
                        + "SSIM Circul" + " " + entrySSIM.getValue().getChaineCircu());

                  // return false;

               }
         }

      }
      return true;
     
   }
}
