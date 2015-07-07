package com.avancial.app.business.train;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Train implements ITrain {

   private List<String> num_train;
   private  Map<Date, JourCirculation> joursCirculation ;
   private List<Circulation> circulations;

   public Train() {
      this.circulations = new ArrayList<>(); 
      this.joursCirculation = new TreeMap<>() ;
      this.num_train = new ArrayList<>() ;
   }

   @Override
   public void addCirculation(Circulation circultation) {
      this.circulations.add(circultation);
      this.remplirJoursCirculations();
   }

 
   @Override
   public String getGareOrigine() {
      return this.getCirculations().get(0).getJourCirculation().getOrigine();
   }

   @Override
   public String getGareDestination() {

      return this.getCirculations().get(getCirculations().size() - 1).getJourCirculation().getDestination();
   }


   /**
    * @author ismael.yahiani
    * récupére le train référencé dans le catalogie à partir de la SSIM 
    */
   @Override
   public Train getTrainAPartirDuCatalogue(TrainCatalogue trainCatalogue) {
      Train train = new Train();  
      for (String num :  trainCatalogue.getNumero_Train_Cat() ) train.setNumTrain(num);
      Circulation circulation = null;
      JourCirculation joursCirculation = null ; 
      
      for (Circulation circulSSIM : this.getCirculations()) {
         for (String num : trainCatalogue.getNumero_Train_Cat()) { 
            if (num.equalsIgnoreCase(circulSSIM.getNumeroTrain())) {
               if (circulSSIM.getJourCirculation().getOrigine().equalsIgnoreCase(trainCatalogue.getGareOrigine()) && circulation == null) {
                  
                  circulation = new Circulation()  ; 
                  circulation.setDateDebut(circulSSIM.getDateDebut())   ;
                  circulation.setDateFin(circulSSIM.getDateFin()) ;
                  
                  circulation.setJourCirculation(circulSSIM.getJourCirculation()) ;
                  //circulation.getJourCirculation().setHeureDepart(circulSSIM.getJourCirculation().getHeureDepart())  ;
                  
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
   
   /**
    * @author ismael.yahiani adapte les cicrculation dans les catalogues
    */

   @Override
   public boolean compare(ITrain train) {
      boolean comp = true;

      for (Entry<Date, JourCirculation> jourCirculation : this.getJoursCirculation().entrySet()) {

         if (train.getJoursCirculation().containsKey(jourCirculation.getKey())) {
            
            comp = jourCirculation.getValue()
                  .compare(train.getJoursCirculation().get(jourCirculation.getKey()));
            if (!comp)
               break;
         }
         else {
            comp = false;
            break;
         }
      }
         return comp;

   }

   public void remplirJoursCirculations() {
      
      for (Circulation circul : this.circulations) {
         this.joursCirculation.putAll(circul.getDateJourCirculMap()) ;
      }
   }
  
   public List<String> getNum_train() {
      return this.num_train ;
   }

   public void setNumTrain(String numTrain) {
      this.num_train.add(numTrain);
   }

   public List<Circulation> getCirculations() {
      return this.circulations;
   }

   @Override
   public void adapt(ITrain train) {
      
      Map<Date, JourCirculation> joursCirculation = new TreeMap<>(); 
      
      for (Entry<Date, JourCirculation> jourCirculation : this.getJoursCirculation().entrySet()) {

         if (train.getJoursCirculation().containsKey(jourCirculation.getKey())) {
            
           if (! jourCirculation.getValue()
                  .compare(train.getJoursCirculation().get(jourCirculation.getKey()))) 
             this.joursCirculation.put(jourCirculation.getKey(), train.getJoursCirculation().get(jourCirculation.getKey()) ) ;
         } 
                  
      }
         
   }
   
   @Override
   public Map<Date, JourCirculation> getJoursCirculation() {
      return this.joursCirculation;
   }
   
   public void setJoursCirculation(Map<Date, JourCirculation> joursCirculation) {
      this.joursCirculation = joursCirculation;
   }
   
   @Override
   public String toString() {
      
      StringBuilder sb = new  StringBuilder() ; 
      for (String num : this.getNum_train()) sb.append(num+"/") ;
      sb.append("\n");
      for (Map.Entry<Date, JourCirculation> entry : this.joursCirculation.entrySet()) {
         sb.append(entry.getValue()+"\n") ;
      }
      
      
      return sb.toString() ; 
   }
   

}
