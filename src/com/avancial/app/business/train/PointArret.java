package com.avancial.app.business.train;

import java.util.ArrayList;
import java.util.List;

public class PointArret {

   private String codeResarail ; 
   private String codeGDS ; 
   private String libelle ; 
   private List<Guichet> guichet ; 
   
   public PointArret() { 
      this.guichet = new ArrayList<Guichet>();
   }
   
   public void addGuichet(Guichet g) {
      this.guichet.add(g);
   }
   public String getCodeResarail() {
      return codeResarail;
   }
   public void setCodeResarail(String codeResarail) {
      this.codeResarail = codeResarail;
   }
   public String getCodeGDS() {
      return codeGDS;
   }
   public void setCodeGDS(String codeGDS) {
      this.codeGDS = codeGDS;
   }
   public String getLibelle() {
      return libelle;
   }
   public void setLibelle(String libelle) {
      this.libelle = libelle;
   }

   public List<Guichet> getGuichet() {
      return guichet;
   }
     
   
}
