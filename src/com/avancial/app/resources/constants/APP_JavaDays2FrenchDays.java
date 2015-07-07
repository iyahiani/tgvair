package com.avancial.app.resources.constants;

public enum APP_JavaDays2FrenchDays {

   NAVIGATION_AJUSTEMENT("/pages/private/ajustement?faces-redirect=true"),
   NAVIGATION_TRAIN("/pages/private/train?faces-redirect=true"),
   NAVIGATION_CATALOGUE("/pages/private/catalogue?faces-redirect=true"),
   NAVIGATION_POINTARRET("/pages/private/pointArret?faces-redirect=true"),
   NAVIGATION_COMPAGNIEAERIENNE("/pages/private/compagnieAerienne?faces-redirect=true"),
   NAVIGATION_MDPOUBLIE("/pages/public/mdpoublie?faces-redirect=true");

   private String constante;

   private APP_JavaDays2FrenchDays(String constante) {

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
