package com.avancial.app.traitements;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table(name="tgvair_traitement_export") 

public class TraitementExportDataBean implements Serializable{

   /**
    *  @author ismael.yahiani 
    *  date bean des traitements d'export SSIM7
    */
   private static final long serialVersionUID = 1L;   
   @Id
   @GeneratedValue
   private int idTraitementExport ;
   private Date dateExtraction ;  
   
   public TraitementExportDataBean() {
      
      Calendar calendar = Calendar.getInstance() ;
      this.dateExtraction = calendar.getTime();
   }

   public int getIdTraitementExport() {
      return idTraitementExport;
   }

   public void setIdTraitementExport(int idTraitementExport) {
      this.idTraitementExport = idTraitementExport;
   }

   public Date getDateExtraction() {
      return dateExtraction;
   }

   public void setDateExtraction(Date dateExtraction) {
      this.dateExtraction = dateExtraction;
   }

}
