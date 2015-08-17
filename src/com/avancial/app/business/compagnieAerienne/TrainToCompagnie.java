package com.avancial.app.business.compagnieAerienne;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.circulation.JourCirculation;

public class TrainToCompagnie extends Train{

   private String codeCompagnie ; 
   private String marketingFlight ; 
   private String operatingFlight ; 
   private int quota_1er ;
   private int quota_2em ; 
   private Date dateDebutValidite ;
   private Date dateFinValidite ;
   
   public String getCodeCompagnie() {
      return codeCompagnie;
   }
   public void setCodeCompagnie(String codeCompagnie) {
      this.codeCompagnie = codeCompagnie;
   }
   public String getMarketingFlight() {
      return marketingFlight;
   }
   public void setMarketingFlight(String marketingFlight) {
      this.marketingFlight = marketingFlight;
   }
   public int getQuota_1er() {
      return quota_1er;
   }
   public void setQuota_1er(int quota_1er) {
      this.quota_1er = quota_1er;
   }
   public int getQuota_2em() {
      return quota_2em;
   }
   public void setQuota_2em(int quota_2em) {
      this.quota_2em = quota_2em;
   }
   public Date getDateDebutValidite() {
      return dateDebutValidite;
   }
   public void setDateDebutValidite(Date dateDebutValidite) {
      this.dateDebutValidite = dateDebutValidite;
   }
   public Date getDateFinValidite() {
      return dateFinValidite;
   }
   public void setDateFinValidite(Date dateFinValidite) {
      this.dateFinValidite = dateFinValidite;
   }
   public String getOperatingFlight() {
      return operatingFlight;
   }
   public void setOperatingFlight(String operatingFlight) {
      this.operatingFlight = operatingFlight;
   }
   /**
    * 
    * @param debutService
    * @param finService 
    * permet d'extraire les periodes de circulation des trains de compagnies relative à la periode de service
    */
   public void adaptService(Date debutService, Date finService) {
      Map<Date, JourCirculation> temp = new TreeMap<>();
      TrainToCompagnie tc2cTemp = new TrainToCompagnie();  
      
      for (Entry<Date, JourCirculation> entry : this.listeJoursCirculation.entrySet() ) {
         if((entry.getKey().after(debutService)||entry.getKey().equals(debutService)) && (entry.getKey().before(finService)||entry.getKey().equals(finService)))
            temp.put(entry.getKey(), entry.getValue());
      } 
      
      this.listeJoursCirculation.clear();
      //this.listeCirculations.clear();
      this.listeJoursCirculation.putAll(temp); 
      //this.calculeCirculationFromJoursCirculation();
   
   }
}
