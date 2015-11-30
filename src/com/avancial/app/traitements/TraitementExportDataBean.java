package com.avancial.app.traitements;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avancial.app.resources.utils.HeureFormattage;


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
   private String heureCreation ;  
   
   public TraitementExportDataBean() {
      this.dateExtraction = new Date() ; 
      this.heureCreation = HeureFormattage.heureToString(this.dateExtraction.getHours()).concat(HeureFormattage.heureToString(this.dateExtraction.getMinutes()));
       }

   public int getIdTraitementExport() {
      return this.idTraitementExport;
   }

   public void setIdTraitementExport(int idTraitementExport) {
      this.idTraitementExport = idTraitementExport;
   }

   public Date getDateExtraction() {
      return this.dateExtraction;
   }

   public void setDateExtraction(Date dateExtraction) {
      this.dateExtraction = dateExtraction;
   }

   public String getHeureCreation() {
      return this.heureCreation;
   }

   public void setHeureCreation(String heureCreation) {
      this.heureCreation = heureCreation;
   }

  

}
