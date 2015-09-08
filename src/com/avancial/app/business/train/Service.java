package com.avancial.app.business.train;

import java.util.Calendar;
import java.util.Date;

public class Service {

   private Date dateDebutService ; 
   private Date dateFinService ;
   
   
   public Service() { 
      Calendar cal = Calendar.getInstance();
      cal.set(2015, 0, 1); 
      this.setDateDebutService(cal.getTime()); 
      Date dateDebutService = cal.getTime();
      cal.set(2017, 0, 1);
      this.dateFinService = cal.getTime() ;
   }
   
   public Date getDateDebutService() {
      return dateDebutService;
   }
   public void setDateDebutService(Date dateDebutService) {
      this.dateDebutService = dateDebutService;
   }
   public Date getDateFinService() {
      return dateFinService;
   }
   public void setDateFinService(Date dateFinService) {
      this.dateFinService = dateFinService;
   }
   
   
   
}
