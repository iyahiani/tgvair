package com.avancial.app.data.model.databean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="CirculationSSIMDataBean")
@Table(name = "tgvair_import_SSIM") 

public class CirculationSSIMDataBean implements Serializable {

   private static final long serialVersionUID = 1L;
 
   @Id 
   @Column(name = "idCirculationSSIMtgvair") 
   
   private  int idCirculationSSIMtgvair;
   private String numeroTrain;
   private String originePointArret;
   private String destinationPointArret;
   private String heureDepartCirculation;
   private String GMTDepart;
   private String heureArriverCirculation;
   private String GMTArriver;
   private Date dateDebutCirculation;
   private Date dateFinCirculation;
   private String joursCirculation;
   private int rangTroncon;
   private String trancheFacultatif;
   private String restrictionTrafic;
  
   ///   a enlever  
 
   
// @GeneratedValue(strategy = GenerationType.AUTO) 
// @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
   
   public CirculationSSIMDataBean() {
   }

     
   public int getIdCirculationSSIMtgvair() {
      return this.idCirculationSSIMtgvair;
   }

   public void setIdCirculationSSIMtgvair(int idCirculation) {
      this.idCirculationSSIMtgvair = idCirculation;
   }

   public String getHeureDepartCirculation() {
      return this.heureDepartCirculation;
   }

   public void setHeureDepartCirculation(String heureDepartCirculation) {
      this.heureDepartCirculation = heureDepartCirculation;
   }

   public String getHeureArriverCirculation() {
      return this.heureArriverCirculation;
   }

   public void setHeureArriverCirculation(String heureArriverCirculation) {
      this.heureArriverCirculation = heureArriverCirculation;
   }

   public Date getDateDebutCirculation() {
      return this.dateDebutCirculation;
   }

   public void setDateDebutCirculation(Date dateDebutCirculation) {
      this.dateDebutCirculation = dateDebutCirculation;
   }

   public Date getDateFinCirculation() {
      return this.dateFinCirculation;
   }

   public void setDateFinCirculation(Date dateFinCirculation) {
      this.dateFinCirculation = dateFinCirculation;
   }

   public String getJoursCirculation() {
      return this.joursCirculation;
   }

   public void setJoursCirculation(String joursCirculation) {
      this.joursCirculation = joursCirculation;
   }

   
   public int getRangTroncon() {
      return this.rangTroncon;
   }

   public void setRangTroncon(int rangTroncon) {
      this.rangTroncon = rangTroncon;
   }

   public String getTrancheFacultatif() {
      return this.trancheFacultatif;
   }

   public void setTrancheFacultatif(String trancheFacultatif) {
      this.trancheFacultatif = trancheFacultatif;
   }

   public String getRestrictionTrafic() {
      return this.restrictionTrafic;
   }

   public void setRestrictionTrafic(String restrictionTrafic) {
      this.restrictionTrafic = restrictionTrafic;
   }

   public static long getSerialversionuid() {
      return serialVersionUID;
   }
 
 
   public String getNumeroTrain() {
      return this.numeroTrain;
   }
   
  
   public void setNumeroTrain(String numeroTrain) {
      this.numeroTrain = numeroTrain;
   }

   public String getOriginePointArret() {
      return this.originePointArret;
   }

   public void setOriginePointArret(String originePointArret) {
      this.originePointArret = originePointArret;
   }

   public String getDestinationPointArret() {
      return this.destinationPointArret;
   }

   public void setDestinationPointArret(String destinationPointArret) {
      this.destinationPointArret = destinationPointArret;
   }

   public String getGMTDepart() {
      return this.GMTDepart;
   }

   public void setGMTDepart(String gMTDepart) {
      this.GMTDepart = gMTDepart;
   }

   public String getGMTArriver() {
      return this.GMTArriver;
   }

   public void setGMTArriver(String gMTArriver) {
      this.GMTArriver = gMTArriver;
   }

   @Override
   public String toString() {

      return this.destinationPointArret + "--" + this.originePointArret ;
   }


  }
