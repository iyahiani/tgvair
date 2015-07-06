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
   /**
    * @author ismael.yahiani
    * r�cup�re le train r�f�renc� dans le catalogie � partir de la SSIM 
    */
   public ITrain getTrainAPartirDuCatalogue(TrainCatalogue trainCatalogue) {
      ITrain train = new Train();
      Circulation circulation = null;

      for (Circulation circulSSIM : this.getCirculations()) {
         for (String num : trainCatalogue.getNumero_Train_Cat())
            if (circulSSIM.getNumeroTrain().equalsIgnoreCase(num)) {
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
   public boolean compare(ITrain train) {

      boolean comp = true;

      Map<Date, Circulation> dateEtjoursCircuCatalog = new TreeMap<Date, Circulation>();
      Map<Date, Circulation> dateEtjoursCircuSSIM = new TreeMap<Date, Circulation>();

      dateEtjoursCircuCatalog = this.getDateJourCirculMap();
      dateEtjoursCircuSSIM = train.getDateJourCirculMap();

      for (Map.Entry<Date, Circulation> entryCatalog : dateEtjoursCircuCatalog.entrySet()) {
         for (Map.Entry<Date, Circulation> entrySSIM : dateEtjoursCircuSSIM.entrySet()) {

            if (entryCatalog.getKey().equals(entrySSIM.getKey()))
               if (!entryCatalog.getValue().compareCirculation(entrySSIM.getValue())) {

                  System.out.println("catalogue = " + entryCatalog.getKey() + "\t" + "SSIM = " 
                  + entrySSIM.getKey() + "\t"
                        + "CatalogueCircul" 
                  + "\t" + entryCatalog.getValue().getChaineCircu() 
                  + "\t" + "SSIM Circul"+ " " 
                  + entrySSIM.getValue().getChaineCircu());

                  // return false;
               }
         }
      }
      return comp;
   }

   /**
    * @author ismael.yahiani adapte les cicrculation dans les catalogues
    */
   @Override
   public void adapte(Date d, Circulation c) {
      boolean comp = true;

      Map<Date, Circulation> dateEtjoursCircuCatalog = new TreeMap<Date, Circulation>();

      dateEtjoursCircuCatalog = this.getDateJourCirculMap();

      for (Map.Entry<Date, Circulation> entryCatalog : dateEtjoursCircuCatalog.entrySet()) {

         if (entryCatalog.getKey().equals(d))
            entryCatalog.setValue(c);
      }

   }
}
