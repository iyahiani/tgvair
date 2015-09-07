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


@Entity 
@Table(name="tgvair_export_SSIM7")

public class ExportSSIMDataBean implements Serializable  {

   /**
    *  @author ismael.yahiani 
    *   data bean des Export SSIM7
    */
   private static final long serialVersionUID = 1L; 
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idExportSSIM", unique = true, nullable = false)
   private int idExportSSIM ; 
   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "idTrainCatalogue")
   private TrainCatalogueDataBean idTrainCatalogue ;
  
   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "idTrainCatalogueToCompagnie")
   private TrainCatalogueToCompagnieDataBean idTrainCatalogueToCompagnie ;
   
   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "idTraitementExport")
   private TraitementExportDataBean idTraitementExport ;
   
   private Date dateDebutCirculation ; 
   private Date dateFinCirculation ;

   private String HeureDepartCirculation ;
   private String HeureArriverCirculation ;
   private String regimeCirculation ;
   private String GMTDepart ;
   private String  GMTArriver ;
   
   
   public ExportSSIMDataBean() { 
      
      this.idTrainCatalogue = new TrainCatalogueDataBean() ;
      this.idTrainCatalogueToCompagnie = new TrainCatalogueToCompagnieDataBean() ;
      this.idTraitementExport = new TraitementExportDataBean() ;
   }
   
   public int getIdExportSSIM() {
      return idExportSSIM;
   }
   public void setIdExportSSIM(int idExportSSIM) {
      this.idExportSSIM = idExportSSIM;
   }
   public TrainCatalogueDataBean getIdTrainCatalogue() {
      return idTrainCatalogue;
   }
   public void setIdTrainCatalogue(TrainCatalogueDataBean idTrainCatalogue) {
      this.idTrainCatalogue = idTrainCatalogue;
   }
   public TrainCatalogueToCompagnieDataBean getIdTrainCatalogueToCompagnie() {
      return idTrainCatalogueToCompagnie;
   }
   public void setIdTrainCatalogueToCompagnie(TrainCatalogueToCompagnieDataBean idTrainCatalogueToCompagnie) {
      this.idTrainCatalogueToCompagnie = idTrainCatalogueToCompagnie;
   }
   public TraitementExportDataBean getIdTraitementExport() {
      return idTraitementExport;
   }
   public void setIdTraitementExport(TraitementExportDataBean idTraitementExport) {
      this.idTraitementExport = idTraitementExport;
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
   public String getHeureDepartCirculation() {
      return HeureDepartCirculation;
   }
   public void setHeureDepartCirculation(String heureDepartCirculation) {
      HeureDepartCirculation = heureDepartCirculation;
   }
   public String getHeureArriverCirculation() {
      return HeureArriverCirculation;
   }
   public void setHeureArriverCirculation(String heureArriverCirculation) {
      HeureArriverCirculation = heureArriverCirculation;
   }
   public String getRegimeCirculation() {
      return regimeCirculation;
   }
   public void setRegimeCirculation(String regimeCirculation) {
      this.regimeCirculation = regimeCirculation;
   }
   public String getGMTDepart() {
      return GMTDepart;
   }
   public void setGMTDepart(String gMTDepart) {
      GMTDepart = gMTDepart;
   }
   public String getGMTArriver() {
      return GMTArriver;
   }
   public void setGMTArriver(String gMTArriver) {
      GMTArriver = gMTArriver;
   }
      
}
