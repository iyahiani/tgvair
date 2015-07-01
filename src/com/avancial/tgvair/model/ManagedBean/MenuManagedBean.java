package com.avancial.tgvair.model.ManagedBean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.avancial.socle.resources.constants.ConstantSocle;
import com.avancial.tgvair.ressource.constante.ConstanteTGVAIR;

/**
 * Managed Bean de gestion de la page liste utilisateurs public
 * 
 * @author guillaume.bouziou
 * 
 */
@Named("menutgvair")
@SessionScoped
public class MenuManagedBean implements Serializable {
   private static final long serialVersionUID = 1L;

   public static String goAccueil() {
      return ConstantSocle.NAVIGATION_ACCUEIL.toString();
   }

   public static String goAjustement() {
      return ConstanteTGVAIR.NAVIGATION_AJUSTEMENT.toString();
   }

   public static String goCatalogue() {
      return ConstanteTGVAIR.NAVIGATION_CATALOGUE.toString();
   }

   public static String goCompagnieAerienne() {
      return ConstanteTGVAIR.NAVIGATION_COMPAGNIEAERIENNE.toString(); 
   }
   
   public static String goPointArret() {
      return ConstanteTGVAIR.NAVIGATION_POINTARRET.toString(); 
   }
   public static String goTrain() {
      return ConstanteTGVAIR.NAVIGATION_TRAIN.toString(); 
   }
}
