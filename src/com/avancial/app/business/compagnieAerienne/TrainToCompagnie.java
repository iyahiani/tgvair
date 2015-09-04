package com.avancial.app.business.compagnieAerienne;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.circulation.JourCirculation;

public class TrainToCompagnie extends Train{

   private int idTrainCatalogue ;
   private String codeCompagnie ; 
   private String marketingFlight ; 
   private String operatingFlight ; 
   private int quota_1er ;
   private int quota_2em ; 
   private Date dateDebutValidite ;
   private Date dateFinValidite ;
   
   public String getCodeCompagnie() {
      return this.codeCompagnie;
   }
   public void setCodeCompagnie(String codeCompagnie) {
      this.codeCompagnie = codeCompagnie;
   }
   public String getMarketingFlight() {
      return this.marketingFlight;
   }
   public void setMarketingFlight(String marketingFlight) {
      this.marketingFlight = marketingFlight;
   }
   public int getQuota_1er() {
      return this.quota_1er;
   }
   public void setQuota_1er(int quota_1er) {
      this.quota_1er = quota_1er;
   }
   public int getQuota_2em() {
      return this.quota_2em;
   }
   public void setQuota_2em(int quota_2em) {
      this.quota_2em = quota_2em;
   }
   @Override
   public Date getDateDebutValidite() {
      return this.dateDebutValidite;
   }
   @Override
   public void setDateDebutValidite(Date dateDebutValidite) {
      this.dateDebutValidite = dateDebutValidite;
   }
   @Override
   public Date getDateFinValidite() {
      return this.dateFinValidite;
   }
   @Override
   public void setDateFinValidite(Date dateFinValidite) {
      this.dateFinValidite = dateFinValidite;
   }
   public String getOperatingFlight() {
      return this.operatingFlight;
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
      for (Entry<Date, JourCirculation> entry : this.listeJoursCirculation.entrySet() ) {
         if((entry.getKey().after(debutService)||entry.getKey().equals(debutService)) && (entry.getKey().before(finService)||entry.getKey().equals(finService)))
            temp.put(entry.getKey(), entry.getValue());
      } 
      
      this.listeJoursCirculation.clear();
      //this.listeCirculations.clear();
      this.listeJoursCirculation.putAll(temp); 
      //this.calculeCirculationFromJoursCirculation();
   
   }
   public int getIdTrainCatalogue() {
      return idTrainCatalogue;
   }
   public void setIdTrainCatalogue(int idTrainCatalogue) {
      this.idTrainCatalogue = idTrainCatalogue;
   }
}
