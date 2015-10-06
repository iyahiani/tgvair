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
   private static final Long serialVersionID = 1L ; 
   public CompagnieAerienneDataBean() {
      // TODO Auto-generated constructor stub
   }

   @Id 
   @Column(name="idCompagnieAerienne")  
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int idCompagnieAeriennne ; 
   
   @Column(name="codeCompagnieAerienne",nullable=true)
   private String CodeCompagnieAerienne ; 
   
   @Column(name="libelleCompagnieAerienne",nullable=true)
   private String libelleCompagnieAerienne ;
    
   @Column(name="imageCompagnieAerienne",nullable=true)
   private String imageCompagnieAerienne ;
   
   public int getIdCompagnieAeriennne() {
      return idCompagnieAeriennne;
   }

   public void setIdCompagnieAeriennne(int idCompagnieAeriennne) {
      this.idCompagnieAeriennne = idCompagnieAeriennne;
   }
   public String getCodeCompagnieAerienne() {
      return CodeCompagnieAerienne;
   }

   public void setCodeCompagnieAerienne(String codeCompagnieAerienne) {
      CodeCompagnieAerienne = codeCompagnieAerienne;
   }

   public String getLibelleCompagnieAerienne() {
      return libelleCompagnieAerienne;
   }

   public void setLibelleCompagnieAerienne(String libelleCompagnieAerienne) {
      this.libelleCompagnieAerienne = libelleCompagnieAerienne;
   } 
   @Override
   public String toString() {
      
      return this.CodeCompagnieAerienne+""+this.libelleCompagnieAerienne ;
   }

   public String getImageCompagnieAerienne() {
      return imageCompagnieAerienne;
   }

   public void setImageCompagnieAerienne(String imageCompagnieAerienne) {
      this.imageCompagnieAerienne = imageCompagnieAerienne;
   }
   
}
