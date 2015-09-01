package com.avancial.app.data.model.databean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.avancial.app.business.train.Train;

@Entity
@Table(name = "tgvair_circulation")
public class CirculationDataBean implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idCirculation", unique = true, nullable = false)
   private Long idCirculation;
   private String numeroTrain;
   private String originePointArret;
   private String destinationPointArret;
   private int heureDepartCirculation;
   private String GMTDepart;
   private int heureArriverCirculation;
   private String GMTArriver;
   private Date dateDebutCirculation;
   private Date dateFinCirculation;
   private String joursCirculation;
   private String indicateurFer;
   private int rangTroncon;
   private String trancheFacultatif;
   private String restrictionTrafic;

   public CirculationDataBean() {
   }

   public Long getIdCirculation() {
      return idCirculation;
   }

   public void setIdCirculation(Long idCirculation) {
      this.idCirculation = idCirculation;
   }

   public int getHeureDepartCirculation() {
      return heureDepartCirculation;
   }

   public void setHeureDepartCirculation(int heureDepartCirculation) {
      this.heureDepartCirculation = heureDepartCirculation;
   }

   public int getHeureArriverCirculation() {
      return heureArriverCirculation;
   }

   public void setHeureArriverCirculation(int heureArriverCirculation) {
      this.heureArriverCirculation = heureArriverCirculation;
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

   public String getJoursCirculation() {
      return joursCirculation;
   }

   public void setJoursCirculation(String joursCirculation) {
      this.joursCirculation = joursCirculation;
   }

   public String getIndicateurFer() {
      return indicateurFer;
   }

   public void setIndicateurFer(String indicateurFer) {
      this.indicateurFer = indicateurFer;
   }

   public int getRangTroncon() {
      return rangTroncon;
   }

   public void setRangTroncon(int rangTroncon) {
      this.rangTroncon = rangTroncon;
   }

   public String getTrancheFacultatif() {
      return trancheFacultatif;
   }

   public void setTrancheFacultatif(String trancheFacultatif) {
      this.trancheFacultatif = trancheFacultatif;
   }

   public String getRestrictionTrafic() {
      return restrictionTrafic;
   }

   public void setRestrictionTrafic(String restrictionTrafic) {
      this.restrictionTrafic = restrictionTrafic;
   }

   public static long getSerialversionuid() {
      return serialVersionUID;
   }

   public String getNumeroTrain() {
      return numeroTrain;
   }

   public void setNumeroTrain(String numeroTrain) {
      this.numeroTrain = numeroTrain;
   }

   public String getOriginePointArret() {
      return originePointArret;
   }

   public void setOriginePointArret(String originePointArret) {
      this.originePointArret = originePointArret;
   }

   public String getDestinationPointArret() {
      return destinationPointArret;
   }

   public void setDestinationPointArret(String destinationPointArret) {
      this.destinationPointArret = destinationPointArret;
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

   @Override
   public String toString() {

      return this.destinationPointArret + "--" + this.originePointArret ;
   }
}
