package com.avancial.app.data.model.databean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.avancial.app.traitements.TraitementExportDataBean;
import com.avancial.app.traitements.TraitementsImportDataBean;

@Entity
@Table(name="tgvair_circulation")
public class CirculationAdapterDataBean implements Serializable  {

   /**
    * @author ismael.yahiani
    * archive des circulations adaptées  
    */
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idCirculation", unique = true, nullable = false)
   private int idCirculation ;
   
   @OneToOne(cascade = CascadeType.REFRESH)
   @JoinColumn(name = "idTrainCatalogue")
   private TrainCatalogueDataBean trainCatalogueDataBean;
   
   @OneToOne(cascade = CascadeType.REFRESH)
   @JoinColumn(name = "idTraitementImport") 
   
   private TraitementsImportDataBean traitementImport ; 
   
   @OneToOne(cascade = CascadeType.REFRESH)
   @JoinColumn(name = "idTraitementExport") 
   
   private TraitementExportDataBean traitementExport ; 
   
   private Date dateDebutCirculation;
   private Date dateFinCirculation ;
   private String heureDepart ; 
   private String heureArriver ; 
   private String regimeCirculation ;
   private Date  dateCreationLigneTrain ;
   public int getIdCirculation() {
      return idCirculation;
   }

   public void setIdCirculation(int idCirculation) {
      this.idCirculation = idCirculation;
   }

   public TrainCatalogueDataBean getTrainCatalogueDataBean() {
      return trainCatalogueDataBean;
   }

   public void setTrainCatalogueDataBean(TrainCatalogueDataBean trainCatalogueDataBean) {
      this.trainCatalogueDataBean = trainCatalogueDataBean;
   }

   public TraitementsImportDataBean getTraitementImport() {
      return traitementImport;
   }

   public void setTraitementImport(TraitementsImportDataBean traitementImport) {
      this.traitementImport = traitementImport;
   }

   public Date getDateDebutCirculation() {
      return dateDebutCirculation;
   }

   public void setDateDebutCirculation(Date dateDebutCirculation) {
      this.dateDebutCirculation = dateDebutCirculation;
   }

   public Date getDateFinCirculation() {
      return dateFinCirculation;
   }

   public void setDateFinCirculation(Date dateFinCirculation) {
      this.dateFinCirculation = dateFinCirculation;
   }

   public String getRegimeCirculation() {
      return regimeCirculation;
   }

   public void setRegimeCirculation(String regimeCirculation) {
      this.regimeCirculation = regimeCirculation;
   }

   public String getHeureDepart() {
      return heureDepart;
   }

   public void setHeureDepart(String heureDepart) {
      this.heureDepart = heureDepart;
   }

   public String getHeureArriver() {
      return heureArriver;
   }

   public void setHeureArriver(String heureArriver) {
      this.heureArriver = heureArriver;
   }

   public TraitementExportDataBean getTraitementExport() {
      return traitementExport;
   }

   public void setTraitementExport(TraitementExportDataBean traitementExport) {
      this.traitementExport = traitementExport;
   }

   public Date getDateCreationLigneTrain() {
      return dateCreationLigneTrain;
   }

   public void setDateCreationLigneTrain(Date dateCreationLigneTrain) {
      this.dateCreationLigneTrain = dateCreationLigneTrain;
   }
   
}
