package com.avancial.app.business.train;

import java.util.Calendar;
import java.util.Date;

import com.avancial.app.data.controller.dao.ServiceDAO;

public class Service {

   private Date dateDebutService ; 
   private Date dateFinService ;
   
   
   public Service() { 
      //Calendar cal = Calendar.getInstance();
      //cal.set(2015, 0, 1); 
      ServiceDAO dao = new ServiceDAO() ;
      this.setDateDebutService(dao.getLastService().getDateDebutService_tgvAir()); 
      //Date dateDebutService = cal.getTime();
      //cal.set(2017, 0, 1);
      this.setDateFinService(dao.getLastService().getDatefinService_tgvAir()) ;
   } 
   public Service(Date dateDebutService,  Date dateFinService) {
      this.dateDebutService = dateDebutService ; 
      this.dateFinService = dateFinService ;
   }
   
   
   public Date getDateDebutService() {
      return this.dateDebutService;
   }
   public void setDateDebutService(Date dateDebutService) {
      this.dateDebutService = dateDebutService;
   }
   public Date getDateFinService() {
      return this.dateFinService;
   }
   public void setDateFinService(Date dateFinService) {
      this.dateFinService = dateFinService;
   }
  
}
