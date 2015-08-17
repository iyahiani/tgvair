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
 *  le bean de la table  tgvair_compagnie_aerienne
 */
@Entity 
@Table(name="tgvair_compagnie_aerienne")
public class CompagnieAerienneDataBean implements Serializable { 
   @Id 
  // @Column(name="idCompagnieAerienne", unique = true, nullable = false)  
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int idCompagnieAeriennne ; 
}
