package com.avancial.app.data.model.databean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tgvair_train_Catalogue_To_Compagnie")
public class TrainCatalogueToCompagnieDataBean implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idTrainCatalogueToCompagnie", unique = true, nullable = false)
   private Long idTrainCatalogueToCompagnie;

   @OneToOne
   @JoinColumn(name = "idTrainCatalogue")
   private TrainCatalogueDataBean trainCatalogueDataBean;

   @OneToOne
   @JoinColumn(name = "idCompagnieAerienne")
   private CompagnieAerienneDataBean compagnieAerienneDataBean;

   private String joursCirculationTrainCatalogueToCompagnie;
   private int quotaPremiereTrainCatalogueToCompagnie;
   private int quotaDeuxiemeTrainCatalogueToCompagnie;
   private String marketingFlightTrainCatalogueToCompagnie;
   private String operatingFlightTrainCatalogueToCompagnie;
   private Date dateDebutValiditeTrainCatalogueToCompagnie;
   private Date dateFinValiditeTrainCatalogueToCompagnie;
   @OneToOne
   @JoinColumn(name = "idPointArret")
   private PointArretDataBean originePointArret;
   private PointArretDataBean destinationPointArret;

   public Long getIdTrainCatalogueToCompagnie() {
      return idTrainCatalogueToCompagnie;
   }

   public void setIdTrainCatalogueToCompagnie(Long idTrainCatalogueToCompagnie) {
      this.idTrainCatalogueToCompagnie = idTrainCatalogueToCompagnie;
   }

   public TrainCatalogueDataBean getTrainCatalogueDataBean() {
      return trainCatalogueDataBean;
   }

   public void setTrainCatalogueDataBean(TrainCatalogueDataBean trainCatalogueDataBean) {
      this.trainCatalogueDataBean = trainCatalogueDataBean;
   }

   public CompagnieAerienneDataBean getCompagnieAerienneDataBean() {
      return compagnieAerienneDataBean;
   }

   public void setCompagnieAerienneDataBean(CompagnieAerienneDataBean compagnieAerienneDataBean) {
      this.compagnieAerienneDataBean = compagnieAerienneDataBean;
   }

   public String getJoursCirculationTrainCatalogueToCompagnie() {
      return joursCirculationTrainCatalogueToCompagnie;
   }

   public void setJoursCirculationTrainCatalogueToCompagnie(String joursCirculationTrainCatalogueToCompagnie) {
      this.joursCirculationTrainCatalogueToCompagnie = joursCirculationTrainCatalogueToCompagnie;
   }

   public int getQuotaPremiereTrainCatalogueToCompagnie() {
      return quotaPremiereTrainCatalogueToCompagnie;
   }

   public void setQuotaPremiereTrainCatalogueToCompagnie(int quotaPremiereTrainCatalogueToCompagnie) {
      this.quotaPremiereTrainCatalogueToCompagnie = quotaPremiereTrainCatalogueToCompagnie;
   }

   public int getQuotaDeuxiemeTrainCatalogueToCompagnie() {
      return quotaDeuxiemeTrainCatalogueToCompagnie;
   }

   public void setQuotaDeuxiemeTrainCatalogueToCompagnie(int quotaDeuxiemeTrainCatalogueToCompagnie) {
      this.quotaDeuxiemeTrainCatalogueToCompagnie = quotaDeuxiemeTrainCatalogueToCompagnie;
   }

   public String getMarketingFlightTrainCatalogueToCompagnie() {
      return marketingFlightTrainCatalogueToCompagnie;
   }

   public void setMarketingFlightTrainCatalogueToCompagnie(String marketingFlightTrainCatalogueToCompagnie) {
      this.marketingFlightTrainCatalogueToCompagnie = marketingFlightTrainCatalogueToCompagnie;
   }

   public String getOperatingFlightTrainCatalogueToCompagnie() {
      return operatingFlightTrainCatalogueToCompagnie;
   }

   public void setOperatingFlightTrainCatalogueToCompagnie(String operatingFlightTrainCatalogueToCompagnie) {
      this.operatingFlightTrainCatalogueToCompagnie = operatingFlightTrainCatalogueToCompagnie;
   }

   public Date getDateDebutValiditeTrainCatalogueToCompagnie() {
      return dateDebutValiditeTrainCatalogueToCompagnie;
   }

   public void setDateDebutValiditeTrainCatalogueToCompagnie(Date dateDebutValiditeTrainCatalogueToCompagnie) {
      this.dateDebutValiditeTrainCatalogueToCompagnie = dateDebutValiditeTrainCatalogueToCompagnie;
   }

   public Date getDateFinValiditeTrainCatalogueToCompagnie() {
      return dateFinValiditeTrainCatalogueToCompagnie;
   }

   public void setDateFinValiditeTrainCatalogueToCompagnie(Date dateFinValiditeTrainCatalogueToCompagnie) {
      this.dateFinValiditeTrainCatalogueToCompagnie = dateFinValiditeTrainCatalogueToCompagnie;
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
}
