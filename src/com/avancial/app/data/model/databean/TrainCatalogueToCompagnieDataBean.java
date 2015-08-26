package com.avancial.app.data.model.databean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tgvair_train_catalogue_to_compagnie")
public class TrainCatalogueToCompagnieDataBean implements Serializable {
  
   private static final Long serialVersionID = 1L ; 
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
  
   private Integer idTrainCatalogueToCompagnie;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "idTrainCatalogue")
   private TrainCatalogueDataBean trainCatalogueDataBean;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "idCompagnieAerienne")
   private CompagnieAerienneDataBean compagnieAerienneDataBean; 
   
   private String codeCompagnieAerienne ;
   private String joursCirculationTrainCatalogueToCompagnie;
   private int quotaPremiereTrainCatalogueToCompagnie;
   private int quotaDeuxiemeTrainCatalogueToCompagnie;
   private String marketingFlightTrainCatalogueToCompagnie;
   private String operatingFlightTrainCatalogueToCompagnie;
   private Date dateDebutValiditeTrainCatalogueToCompagnie;
   private Date dateFinValiditeTrainCatalogueToCompagnie;
  /* @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "idOriginePointArret")
   private PointArretDataBean idOriginePointArret;
   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "idDestinationPointArret")
   private PointArretDataBean idDestinationPointArret;*/

   public Integer getIdTrainCatalogueToCompagnie() {
      return idTrainCatalogueToCompagnie;
   }

   public void setIdTrainCatalogueToCompagnie(Integer idTrainCatalogueToCompagnie) {
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
      DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;
      
      
      try {
         this.dateDebutValiditeTrainCatalogueToCompagnie = sdf.parse(sdf.format( dateDebutValiditeTrainCatalogueToCompagnie));
      } catch (ParseException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public Date getDateFinValiditeTrainCatalogueToCompagnie() {
      DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;
      
      return dateFinValiditeTrainCatalogueToCompagnie;
   }

   public void setDateFinValiditeTrainCatalogueToCompagnie(Date dateFinValiditeTrainCatalogueToCompagnie) {
      DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;
      try {
         this.dateFinValiditeTrainCatalogueToCompagnie = sdf.parse(sdf.format(dateFinValiditeTrainCatalogueToCompagnie));
      } catch (ParseException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

  

   
   public String getCodeCompagnieAerienne() {
      return codeCompagnieAerienne;
   }

   public void setCodeCompagnieAerienne(String codeCompagnieAerienne) {
      this.codeCompagnieAerienne = codeCompagnieAerienne;
   }

   /*public PointArretDataBean getIdOriginePointArret() {
      return idOriginePointArret;
   }

   public void setIdOriginePointArret(PointArretDataBean idOriginePointArret) {
      this.idOriginePointArret = idOriginePointArret;
   }

   public PointArretDataBean getIdDestinationPointArret() {
      return idDestinationPointArret;
   }

   public void setIdDestinationPointArret(PointArretDataBean idDestinationPointArret) {
      this.idDestinationPointArret = idDestinationPointArret;
   }*/

  
}
