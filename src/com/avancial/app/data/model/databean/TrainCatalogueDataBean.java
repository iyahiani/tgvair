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
 * @author ismael.yahiani
 *  le bean de la table  tgvair_train_catalogue
 */
@Entity 
@Table (name="tgvair_train_catalogue")

public class TrainCatalogueDataBean implements Serializable  {

private static final Long serialVersionID = 1L ;
	

@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
 
private Long idTrainCatalogue ; 
	public TrainCatalogueDataBean() {	
	}
	
	

		
}

