package com.avancial.app.resources.constants;

public enum APP_TgvAir {

   NAVIGATION_AJUSTEMENT("/pages/private/ajustement?faces-redirect=true"),
   NAVIGATION_TRAIN("/pages/private/train?faces-redirect=true"),
   NAVIGATION_CATALOGUE("/pages/private/catalogue?faces-redirect=true"),
   NAVIGATION_POINTARRET("/pages/private/pointArret?faces-redirect=true"),
   NAVIGATION_COMPAGNIEAERIENNE("/pages/private/compagnieAerienne?faces-redirect=true"),
   NAVIGATION_MDPOUBLIE("/pages/public/mdpoublie?faces-redirect=true"),

   NAVIGATION_ADMINISTRATION("/pages/private/Administration?faces-redirect=true"),
   
   CHEMIN_SSIM_REC("\\\\dione\\nas75_TgvAir_Recette\\SSIM.txt"),
   CHEMIN_SSIM("d:\\SSIM.txt "), 
   CHEMIN_SSIM_PROD("\\\\dione\\nas75_TgvAir_Prod\\SSIM.txt"), 
   
   
   CHEMIN_SSIM7_DEV("d:\\"),
   
   CHEMIN_SSIM7_REC("\\\\reha\\mft46-ssim7-af-rec\\"),
   CHEMIN_SSIM7_PROD("\\\\reha\\mft47-ssim7-af-prod\\"), 
   
   CHEMIN_SSIMARCHIVE_REC("\\\\dione\\nas75_TgvAir_Recette\\Archive\\"),
   CHEMIN_SSIMARCHIVE_PROD("\\\\dione\\nas75_TgvAir_Prod\\Archive\\"),
   
   CHEMIN_APP_PROPERTIES("/classes/com/avancial/app/resources/app.properties") 
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
