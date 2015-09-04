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
  
   private Date dateDebutCirculation ;
   private Date dateFinCirculation ;
   private Date heureDepartCirculation ; 
   private Date heureArriveeCirculation ;
   
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
   
   public void setDateDebutCirculation(Date dateDebutCirculation) {
      this.dateDebutCirculation = dateDebutCirculation;
   }
   public Date getDateFinCirculation() {
      return dateFinCirculation;
   }
   public void setDateFinCirculation(Date dateFinCirculation) {
      this.dateFinCirculation = dateFinCirculation;
   }
   public Date getHeureDepartCirculation() {
      return heureDepartCirculation;
   }
   public void setHeureDepartCirculation(Date heureDepartCirculation) {
      this.heureDepartCirculation = heureDepartCirculation;
   }
   public Date getHeureArriveeCirculation() {
      return heureArriveeCirculation;
   }
   public void setHeureArriveeCirculation(Date heureArriveeCirculation) {
      this.heureArriveeCirculation = heureArriveeCirculation;
   }
   public Date getDateDebutCirculation() {
      return dateDebutCirculation;
   } 
   
}
