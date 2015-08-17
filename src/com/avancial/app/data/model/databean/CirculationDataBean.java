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
   @Column(name = "idCirculation",unique = true, nullable = false)
   private Long idCirculation;

   @OneToOne
   @JoinColumn(name = "idTrainCatalogue")
   @Column(name = "idTrainCatalogue")
   private TrainCatalogueDataBean trainCatalogueDataBean;

   @OneToOne
   @JoinColumn(name = "idPointArret")
   @Column(name = "originePointArret")
   private PointArretDataBean originePointArret;

   @OneToOne
   @JoinColumn(name = "idPointArret")
   @Column(name = "destinationPointArret")
   private PointArretDataBean destinationPointArret;

   private Date heureDepartCirculation;
   private Date heureArriverCirculation;
   private Date dateDebutCirculation;
   private Date dateFinCirculation;
   private String joursCirculation;
   private String indicateurFer;
   private String compagnieAerienne;
   private String rangTroncon;
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

   public Date getHeureDepartCirculation() {
      return heureDepartCirculation;
   }

   public void setHeureDepartCirculation(Date heureDepartCirculation) {
      this.heureDepartCirculation = heureDepartCirculation;
   }

   public Date getHeureArriverCirculation() {
      return heureArriverCirculation;
   }

   public void setHeureArriverCirculation(Date heureArriverCirculation) {
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

   public String getCompagnieAerienne() {
      return compagnieAerienne;
   }

   public void setCompagnieAerienne(String compagnieAerienne) {
      this.compagnieAerienne = compagnieAerienne;
   }

   public String getRangTroncon() {
      return rangTroncon;
   }

   public void setRangTroncon(String rangTroncon) {
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

   public TrainCatalogueDataBean getTrainCatalogueBean() {
      return trainCatalogueDataBean;
   }

   public void setTrainCatalogueBean(TrainCatalogueDataBean trainCatalogueDataBean) {
      this.trainCatalogueDataBean = trainCatalogueDataBean;
   }

   public void setOriginePointArret(PointArretDataBean originePointArret) {
      this.originePointArret = originePointArret;
   }

   public void setDestinationPointArret(PointArretDataBean destinationPointArret) {
      this.destinationPointArret = destinationPointArret;
   }

}
