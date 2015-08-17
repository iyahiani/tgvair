package com.avancial.app.data.model.databean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author ismael.yahiani
 *  le bean de la table  tgvair_point_arret
 */
@Entity 
@Table (name="tgvair_point_arret")

public class PointArretDataBean implements Serializable {

	private static final Long serialVersionID =1L; 
	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
   
   private Long idPointArret ; 
	
	
	
}
