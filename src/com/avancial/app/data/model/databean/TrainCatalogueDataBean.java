package com.avancial.app.data.model.databean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * @author ismael.yahiani le bean de la table tgvair_train_catalogue
 */
@Entity
@Table(name = "tgvair_train_catalogue")

public class TrainCatalogueDataBean implements Serializable {

   private static final Long serialVersionID = 1L;
   
   @Id
   @GeneratedValue//(strategy = GenerationType.AUTO)
   private int idTrainCatalogue; 
   private String numeroTrainCatalogue ;
   private String numeroTrainCatalogue1 ;
   private String numeroTrainCatalogue2 ;
   
  @OneToOne (cascade=CascadeType.ALL)
  @JoinColumn(name = "idPointArretDestination" ) 
  
  private PointArretDataBean idPointArretDestination ;
 
  @OneToOne(cascade=CascadeType.ALL) 
  @JoinColumn(name = "idPointArretOrigine")
   private PointArretDataBean idPointArretOrigine ;
   
   private Date heureDepartTrainCatalogue ;
   private Date heureArriveeTrainCatalogue ;
   private String regimeJoursTrainCatalogue ;
   private Date dateDebutValidite ; 
   private Date dateFinValidite ; 
   private String operatingFlight; 
   

   
  
   
   
   public int getIdTrainCatalogue() {
      return idTrainCatalogue;
   }
   public void setIdTrainCatalogue(int idTrainCatalogue) {
      this.idTrainCatalogue = idTrainCatalogue;
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

   public void setHeureArriveeTrainCatalogue(Date date) {
      this.heureArriveeTrainCatalogue = date;
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
      DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy") ;
      try {
         this.dateDebutValidite = formatter.parse(formatter.format(dateDebutValidite));
      } catch (ParseException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
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

   public String getNumeroTrainCatalogue() {
      return numeroTrainCatalogue;
   }
   public void setNumeroTrainCatalogue(String numeroTrainCatalogue) {
      this.numeroTrainCatalogue = numeroTrainCatalogue;
   }
  
   @Override
   public String toString() {
      
      return "";
   }
   public PointArretDataBean getIdPointArretOrigine() {
      return idPointArretOrigine;
   }
   public void setIdPointArretOrigine(PointArretDataBean idPointArretOrigine) {
      this.idPointArretOrigine = idPointArretOrigine;
   }
   public PointArretDataBean getIdPointArretDestination() {
      return idPointArretDestination;
   }
   public void setIdPointArretDestination(PointArretDataBean idPointArretDestination) {
      this.idPointArretDestination = idPointArretDestination;
   }

   
}
