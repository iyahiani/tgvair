package com.avancial.app.data.model.databean;

import java.io.Serializable;
import java.util.Date;

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
	public PointArretDataBean() {
   } 
   
   @Id
   @GeneratedValue//(strategy = GenerationType.AUTO)
   @Column(unique=true, nullable=false) 
   private Integer idPointArret ; 
   
   @Column(nullable=false, length=100 ) 
   private String codeResarailPointArret ;
   
   @Column(nullable=false, length=100) 
   private String codeGDSPointArret ;
   
   @Column(nullable=false, length=100 ) 
   private String libellePointArret ;
   
   private Date lundiHeureOuverturePointsArret ;
   private Date lundiHeureFermeturePointsArret ;
   private Date mardiHeureOuverturePointsArret ;
   private Date mardiHeureFermeturePointsArret ;
   private Date mercrediHeureOuverturePointsArret ;
   private Date mercrediHeureFermeturePointsArret ;
   private Date jeudiHeureOuverturePointsArret ;
   private Date jeudiHeureFermeturePointsArret ; 
   private Date vendrediHeureOuverturePointsArret ;
   private Date vendrediHeureFermeturePointsArret ;
   private Date samediHeureOuverturePointsArret ; 
   private Date samediHeureFermeturePointsArret ; 
   private Date dimancheHeureOuverturePointsArret ;
   private Date dimancheHeureFermeturePointsArret ;  
   private Date ferieHeureOuverturePointsArret ; 
   private Date ferieHeureFermeturePointsArret ;
   
   public int getIdPointArret() {
      return idPointArret;
   }

   public void setIdPointArret(int idPointArret) {
      this.idPointArret = idPointArret;
   }

   public String getCodeResarailPointArret() {
      return codeResarailPointArret;
   }

   public void setCodeResarailPointArret(String codeResarailPointArret) {
      this.codeResarailPointArret = codeResarailPointArret;
   }

   public String getCodeGDSPointArret() {
      return codeGDSPointArret;
   }

   public void setCodeGDSPointArret(String codeGDSPointArret) {
      this.codeGDSPointArret = codeGDSPointArret;
   }

   public String getLibellePointArret() {
      return libellePointArret;
   }

   public void setLibellePointArret(String libellePointArret) {
      this.libellePointArret = libellePointArret;
   }

   public Date getLundiHeureOuverturePointsArret() {
      return lundiHeureOuverturePointsArret;
   }

   public void setLundiHeureOuverturePointsArret(Date lundiHeureOuverturePointsArret) {
      this.lundiHeureOuverturePointsArret = lundiHeureOuverturePointsArret;
   }

   public Date getLundiHeureFermeturePointsArret() {
      return lundiHeureFermeturePointsArret;
   }

   public void setLundiHeureFermeturePointsArret(Date lundiHeureFermeturePointsArret) {
      this.lundiHeureFermeturePointsArret = lundiHeureFermeturePointsArret;
   }

   public Date getMardiHeureOuverturePointsArret() {
      return mardiHeureOuverturePointsArret;
   }

   public void setMardiHeureOuverturePointsArret(Date mardiHeureOuverturePointsArret) {
      this.mardiHeureOuverturePointsArret = mardiHeureOuverturePointsArret;
   }

   public Date getMardiHeureFermeturePointsArret() {
      return mardiHeureFermeturePointsArret;
   }

   public void setMardiHeureFermeturePointsArret(Date mardiHeureFermeturePointsArret) {
      this.mardiHeureFermeturePointsArret = mardiHeureFermeturePointsArret;
   }

   public Date getMercrediHeureOuverturePointsArret() {
      return mercrediHeureOuverturePointsArret;
   }

   public void setMercrediHeureOuverturePointsArret(Date mercrediHeureOuverturePointsArret) {
      this.mercrediHeureOuverturePointsArret = mercrediHeureOuverturePointsArret;
   }

   public Date getMercrediHeureFermeturePointsArret() {
      return mercrediHeureFermeturePointsArret;
   }

   public void setMercrediHeureFermeturePointsArret(Date mercrediHeureFermeturePointsArret) {
      this.mercrediHeureFermeturePointsArret = mercrediHeureFermeturePointsArret;
   }

   public Date getJeudiHeureOuverturePointsArret() {
      return jeudiHeureOuverturePointsArret;
   }

   public void setJeudiHeureOuverturePointsArret(Date jeudiHeureOuverturePointsArret) {
      this.jeudiHeureOuverturePointsArret = jeudiHeureOuverturePointsArret;
   }

   public Date getJeudiHeureFermeturePointsArret() {
      return jeudiHeureFermeturePointsArret;
   }

   public void setJeudiHeureFermeturePointsArret(Date jeudiHeureFermeturePointsArret) {
      this.jeudiHeureFermeturePointsArret = jeudiHeureFermeturePointsArret;
   }

   public Date getVendrediHeureOuverturePointsArret() {
      return vendrediHeureOuverturePointsArret;
   }

   public void setVendrediHeureOuverturePointsArret(Date vendrediHeureOuverturePointsArret) {
      this.vendrediHeureOuverturePointsArret = vendrediHeureOuverturePointsArret;
   }

   public Date getVendrediHeureFermeturePointsArret() {
      return vendrediHeureFermeturePointsArret;
   }

   public void setVendrediHeureFermeturePointsArret(Date vendrediHeureFermeturePointsArret) {
      this.vendrediHeureFermeturePointsArret = vendrediHeureFermeturePointsArret;
   }

   public Date getSamediHeureOuverturePointsArret() {
      return samediHeureOuverturePointsArret;
   }

   public void setSamediHeureOuverturePointsArret(Date samediHeureOuverturePointsArret) {
      this.samediHeureOuverturePointsArret = samediHeureOuverturePointsArret;
   }

   public Date getSamediHeureFermeturePointsArret() {
      return samediHeureFermeturePointsArret;
   }

   public void setSamediHeureFermeturePointsArret(Date samediHeureFermeturePointsArret) {
      this.samediHeureFermeturePointsArret = samediHeureFermeturePointsArret;
   }

   public Date getDimancheHeureOuverturePointsArret() {
      return dimancheHeureOuverturePointsArret;
   }

   public void setDimancheHeureOuverturePointsArret(Date dimancheHeureOuverturePointsArret) {
      this.dimancheHeureOuverturePointsArret = dimancheHeureOuverturePointsArret;
   }

   public Date getDimancheHeureFermeturePointsArret() {
      return dimancheHeureFermeturePointsArret;
   }

   public void setDimancheHeureFermeturePointsArret(Date dimancheHeureFermeturePointsArret) {
      this.dimancheHeureFermeturePointsArret = dimancheHeureFermeturePointsArret;
   }

   public void setIdPointArret(Integer idPointArret) {
      this.idPointArret = idPointArret;
   }

   public Date getFerieHeureOuverturePointsArret() {
      return ferieHeureOuverturePointsArret;
   }

   public void setFerieHeureOuverturePointsArret(Date ferieHeureOuverturePointsArret) {
      this.ferieHeureOuverturePointsArret = ferieHeureOuverturePointsArret;
   }

   public Date getFerieHeureFermeturePointsArret() {
      return ferieHeureFermeturePointsArret;
   }

   public void setFerieHeureFermeturePointsArret(Date ferieHeureFermeturePointsArret) {
      this.ferieHeureFermeturePointsArret = ferieHeureFermeturePointsArret;
   } 
	
	
	
}
