package com.avancial.app.data.model.databean;

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
@Table(name="tgvair_train_Catalogue_To_Compagnie") 

public class TrainCatalogueToCompagnieDataBean {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   //@Column (name="idTrainCatalogueToCompagnie",unique = true, nullable = false) 
   
   private Long idTrainCatalogueToCompagnie ; 
   
 }
