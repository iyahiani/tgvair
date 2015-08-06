package com.avancial.app.business.compagnieAerienne;

import java.util.Date;
import java.util.List;

import com.avancial.app.business.train.Train;

public class TrainToCompagnie extends Train{

   
   private String codeCompagnie ; 
   private String marketingFlight ; 
   private int quota_1er ;
   private int quota_2em ; 
   
   
    
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
}
