package com.avancial.app.data.model.databean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tgvair_train_catalogue_to_compagnie")
public class TrainCatalogueToCompagnieDataBean implements Serializable {
  
   private static final Long serialVersionID = 1L ; 
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
  
   private Integer idTrainCatalogueToCompagnie;

   @OneToOne(cascade = CascadeType.REFRESH)
   @JoinColumn(name = "idTrainCatalogue")
   private TrainCatalogueDataBean trainCatalogueDataBean;

   @OneToOne(cascade = CascadeType.REFRESH)
   @JoinColumn(name = "idCompagnieAerienne")
   private CompagnieAerienneDataBean compagnieAerienneDataBean; 
   
   private String codeCompagnieAerienne ;
   
   private int quotaPremiereTrainCatalogueToCompagnie;
   private int quotaDeuxiemeTrainCatalogueToCompagnie;
   private String marketingFlightTrainCatalogueToCompagnie;
   
   private Date dateDebutValiditeTrainCatalogueToCompagnie;
   private Date dateFinValiditeTrainCatalogueToCompagnie;

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
      this.dateFinValiditeTrainCatalogueToCompagnie = dateFinValiditeTrainCatalogueToCompagnie ;
   }
 
   public String getCodeCompagnieAerienne() {
      return codeCompagnieAerienne;
   }

   public void setCodeCompagnieAerienne(String codeCompagnieAerienne) {
      this.codeCompagnieAerienne = codeCompagnieAerienne;
   }

}
