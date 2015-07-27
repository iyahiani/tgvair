package com.avancial.app.business.train;

import java.util.Date;

public class Guichet {

   private String  jour ; 
   private String heureOuverture ; 
   private String heureFermeture  ; 
   private Date heureOuvertureFerie ;
   private Date heureFermetureFerie  ;
  
   public Guichet() {
   }
   
   
   public String getJour() {
      return jour;
   }
   public void setJour(String jour) {
      this.jour = jour;
   }
   public String getHeureOuverture() {
      return heureOuverture;
   }
   public void setHeureOuverture(String heureOuverture) {
      this.heureOuverture = heureOuverture;
   }
   public String getHeureFermeture() {
      return heureFermeture;
   }
   public void setHeureFermeture(String heureFermeture) {
      this.heureFermeture = heureFermeture;
   }
   public Date getHeureOuvertureFerie() {
      return heureOuvertureFerie;
   }
   public void setHeureOuvertureFerie(Date heureOuvertureFerie) {
      this.heureOuvertureFerie = heureOuvertureFerie;
   }
   public Date getHeureFermetureFerie() {
      return heureFermetureFerie;
   }
   public void setHeureFermetureFerie(Date heureFermetureFerie) {
      this.heureFermetureFerie = heureFermetureFerie;
   } 
   
   
   
   }
