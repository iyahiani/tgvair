package com.avancial.app.traitements;

import java.util.Calendar;
import java.util.Date;

public class TraitementExport {

   private Date dateExport ; 
   
   public TraitementExport() { 
      
      Calendar calendar = Calendar.getInstance() ;
      this.dateExport = calendar.getTime();
   }

   public Date getDateExport() {
      return dateExport;
   }

   public void setDateExport(Date dateExport) {
      this.dateExport = dateExport;
   }
}
