package com.avancial.app.traitements;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tgvair_traitement_Import")
public class TraitementsImportDataBean implements Serializable{

   
   /**
    * 
    */ 
   
   
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue
   private Long idTraitementImport ;
   private Date dateImport ;
   private Date dateDebutSSIM ;
   private Date dateFinSSIM ;
    
   public TraitementsImportDataBean() { 
      Calendar calendar = Calendar.getInstance() ;
      this.dateImport = calendar.getTime();
   }

   public Date getDateImport() {
      return dateImport;
   }

   public void setDateImport(Date dateImport) {
      this.dateImport = dateImport;
   }

   public Long getIdTraitementImport() {
      return idTraitementImport;
   }

   public void setIdTraitementImport(Long idTraitementImport) {
      this.idTraitementImport = idTraitementImport;
   }

   public Date getDateDebutSSIM() {
      return dateDebutSSIM;
   }

   public void setDateDebutSSIM(Date dateDebutSSIM) {
      this.dateDebutSSIM = dateDebutSSIM;
   }

   public Date getDateFinSSIM() {
      return dateFinSSIM;
   }

   public void setDateFinSSIM(Date dateFinSSIM) {
      this.dateFinSSIM = dateFinSSIM;
   } 
   
   
}
