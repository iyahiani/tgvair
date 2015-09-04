package com.avancial.app.data.model.databean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author ismael.yahiani
 *  permet de persister les Trains adpatés  
 */
@Entity 
@Table(name = "tgvair_train_catalogue_adapte")
public class TrainCatalogueAdapterDataBean implements Serializable { 
   
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue//(strategy = GenerationType.AUTO)
   private int idTrainCatalogueAdapter;
   private int idTrainCatalogue; 
   private String numeroTrainCatalogue ;
   private String numeroTrainCatalogue1 ;
   private String numeroTrainCatalogue2 ;
   
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "idPointArretDestination")
   private PointArretDataBean idPointArretDestination ;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "idPointArretOrigine")
   private PointArretDataBean idPointArretOrigine ;
   private String originePointArret ;
   private String destinationPointArret ;
   private Date heureDepartTrainCatalogue ;
   private Date heureArriveeTrainCatalogue ;
   private String regimeJoursTrainCatalogue ;
   private Date dateDebutValidite ; 
   private Date dateFinValidite ; 
   private String operatingFlight;
   public int getIdTrainCatalogueAdapter() {
      return idTrainCatalogueAdapter;
   }
   public void setIdTrainCatalogueAdapter(int idTrainCatalogueAdapter) {
      this.idTrainCatalogueAdapter = idTrainCatalogueAdapter;
   }
   public int getIdTrainCatalogue() {
      return idTrainCatalogue;
   }
   public void setIdTrainCatalogue(int idTrainCatalogue) {
      this.idTrainCatalogue = idTrainCatalogue;
   }
   public String getNumeroTrainCatalogue() {
      return numeroTrainCatalogue;
   }
   public void setNumeroTrainCatalogue(String numeroTrainCatalogue) {
      this.numeroTrainCatalogue = numeroTrainCatalogue;
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
   public PointArretDataBean getIdPointArretDestination() {
      return idPointArretDestination;
   }
   public void setIdPointArretDestination(PointArretDataBean idPointArretDestination) {
      this.idPointArretDestination = idPointArretDestination;
   }
   public PointArretDataBean getIdPointArretOrigine() {
      return idPointArretOrigine;
   }
   public void setIdPointArretOrigine(PointArretDataBean idPointArretOrigine) {
      this.idPointArretOrigine = idPointArretOrigine;
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
   public String getRegimeJoursTrainCatalogue() {
      return regimeJoursTrainCatalogue;
   }
   public void setRegimeJoursTrainCatalogue(String regimeJoursTrainCatalogue) {
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
   public String getOperatingFlight() {
      return operatingFlight;
   }
   public void setOperatingFlight(String operatingFlight) {
      this.operatingFlight = operatingFlight;
   } 
   
}
