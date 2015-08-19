package com.avancial.app.data.model.databean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author ismael.yahiani le bean de la table tgvair_train_catalogue
 */
@Entity
@Table(name = "tgvair_train_catalogue")
public class TrainCatalogueDataBean implements Serializable {

   private static final Long serialVersionID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idTrainCatalogue;
   private String numeroTrainCatalogue1 ;
   private String numeroTrainCatalogue2 ;
   
   @OneToOne
   @JoinColumn(name = "idPointArret")
   
   private PointArretDataBean originePointArret ;
   private PointArretDataBean destinationPointArret ;
   private Date heureDepartTrainCatalogue ;
   private Date heureArriveeTrainCatalogue ;
   private Date regimeJoursTrainCatalogue ;
   private Date dateDebutValidite ; 
   private Date dateFinValidite ; 
   private String operatingFlight;
   public Long getIdTrainCatalogue() {
      return idTrainCatalogue;
   }
   public void setIdTrainCatalogue(Long idTrainCatalogue) {
      this.idTrainCatalogue = idTrainCatalogue;
   }



   public PointArretDataBean getOriginePointArret() {
      return originePointArret;
   }

   public void setOriginePointArret(PointArretDataBean originePointArret) {
      this.originePointArret = originePointArret;
   }

   public PointArretDataBean getDestinationPointArret() {
      return destinationPointArret;
   }

   public void setDestinationPointArret(PointArretDataBean destinationPointArret) {
      this.destinationPointArret = destinationPointArret;
   }

   public Date getHeureDepartTrainCatalogue() {
      return heureDepartTrainCatalogue;
   }

   public void setHeureDepartTrainCatalogue(Date heureDepartTrainCatalogue) {
      this.heureDepartTrainCatalogue = heureDepartTrainCatalogue;
   }

   public Date getHeureArriveeTrainCatalogue() {
      return heureArriveeTrainCatalogue;
   }

   public void setHeureArriveeTrainCatalogue(Date heureArriveeTrainCatalogue) {
      this.heureArriveeTrainCatalogue = heureArriveeTrainCatalogue;
   }

   public Date getRegimeJoursTrainCatalogue() {
      return regimeJoursTrainCatalogue;
   }

   public void setRegimeJoursTrainCatalogue(Date regimeJoursTrainCatalogue) {
      this.regimeJoursTrainCatalogue = regimeJoursTrainCatalogue;
   }

   public Date getDateDebutValidite() {
      return dateDebutValidite;
   }

   public void setDateDebutValidite(Date dateDebutValidite) {
      this.dateDebutValidite = dateDebutValidite;
   }

   public Date getDateFinValidite() {
      return dateFinValidite;
   }

   public void setDateFinValidite(Date dateFinValidite) {
      this.dateFinValidite = dateFinValidite;
   }
   public TrainCatalogueDataBean() {
   }

   public String getNumeroTrainCatalogue1() {
      return numeroTrainCatalogue1;
   }

   public void setNumeroTrainCatalogue1(String numeroTrainCatalogue1) {
      this.numeroTrainCatalogue1 = numeroTrainCatalogue1;
   }

   public String getNumeroTrainCatalogue2() {
      return numeroTrainCatalogue2;
   }

   public void setNumeroTrainCatalogue2(String numeroTrainCatalogue2) {
      this.numeroTrainCatalogue2 = numeroTrainCatalogue2;
   }
   public String getOperatingFlight() {
      return operatingFlight;
   }
   public void setOperatingFlight(String operatingFlight) {
      this.operatingFlight = operatingFlight;
   }

   
}
