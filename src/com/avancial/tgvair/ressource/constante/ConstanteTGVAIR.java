package com.avancial.tgvair.ressource.constante;

public enum ConstanteTGVAIR {
   
   NAVIGATION_LOGIN("/pages/public/login?faces-redirect=true"),
   NAVIGATION_AJUSTEMENT("/pages/tgvair/private/ajustement?faces-redirect=true"),
   NAVIGATION_TRAIN("/pages/tgvair/private/train?faces-redirect=true"), 
   NAVIGATION_CATALOGUE("/pages/tgvair/private/catalogue?faces-redirect=true"),
   NAVIGATION_POINTARRET("/pages/tgvair/private/pointArret?faces-redirect=true"),
   NAVIGATION_COMPAGNIEAERIENNE("/pages/tgvair/private/compagnieAerienne?faces-redirect=true"), 
   NAVIGATION_ACCEUIl("/pages/tgvair/public/accueil?faces-redirect=true"),;
   
   private String constante; 
   
   private ConstanteTGVAIR(String constante) {
    
   this.constante = constante ;
   }

   public String getConstante() {
      return constante;
   }

   public void setConstante(String constante) {
      this.constante = constante;
   } 
   
   @Override
   public String toString() {
      
      return this.constante;
   }
}
