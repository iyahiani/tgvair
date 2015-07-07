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
   private List<Circulation> circulations ;
   
   
   public Train(List<String> num , List<Circulation> circul ) {
      this.circulations = circul; 
      this.joursCirculation = new TreeMap<>() ;
      this.num_train = num ;
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
