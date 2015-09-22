package com.avancial.app.resources.constants;

public enum APP_TgvAir {

   NAVIGATION_AJUSTEMENT("/pages/private/ajustement?faces-redirect=true"),
   NAVIGATION_TRAIN("/pages/private/train?faces-redirect=true"),
   NAVIGATION_CATALOGUE("/pages/private/catalogue?faces-redirect=true"),
   NAVIGATION_POINTARRET("/pages/private/pointArret?faces-redirect=true"),
   NAVIGATION_COMPAGNIEAERIENNE("/pages/private/compagnieAerienne?faces-redirect=true"),
   NAVIGATION_MDPOUBLIE("/pages/public/mdpoublie?faces-redirect=true"), 
   CHEMIN_SSIM("\\\\reha\\TGVAir_REC\\ssim\\7989.txt"), 
   CHEMIN_SSIM7("\\\\reha\\TGVAir_REC\\ssim7\\")
   ;

   private String constante;

   private APP_TgvAir(String constante) {

      this.constante = constante;
   }

   public String getConstante() {
      return this.constante;
   }
   

   public void setConstante(String constante) {
      this.constante = constante;
   }

   @Override
   public String toString() {

      return this.constante;
   }
}
