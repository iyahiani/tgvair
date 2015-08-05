package com.avancial.app.business.compagnieAerienne;

import java.util.Date;
import java.util.List;

import com.avancial.app.business.train.Train;

public class TrainToCompagnie {

   private Train trainsToCompagnie ; 
   private String codeCompagnie ; 
   private Date debutValidite ;
   private Date finValidite ; 
   private String marketingFlight ; 
   private int quota_1er ;
   private int quota_2em ; 
   
   
   public Train getTrainsToCompagnie() {
      return trainsToCompagnie;
   }
   public void setTrainsToCompagnie(Train trainsToCompagnie) {
      this.trainsToCompagnie = trainsToCompagnie;
   }
   public String getCodeCompagnie() {
      return codeCompagnie;
   }
   public void setCodeCompagnie(String codeCompagnie) {
      this.codeCompagnie = codeCompagnie;
   }
   public Date getDebutValidite() {
      return debutValidite;
   }
   public void setDebutValidite(Date debutValidite) {
      this.debutValidite = debutValidite;
   }
   public Date getFinValidite() {
      return finValidite;
   }
   public void setFinValidite(Date finValidite) {
      this.finValidite = finValidite;
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
}
