package com.avancial.app.model.managedbean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.avancial.app.resources.constants.APP_TgvAir;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * Managed Bean de gestion de la page liste utilisateurs public
 * 
 * @author guillaume.bouziou
 * 
 */
@Named("menu")
@SessionScoped
public class MenuManagedBean implements Serializable {
   private static final long serialVersionUID = 1L;

   public static String goAccueil() {
      return SOCLE_constants.NAVIGATION_ACCUEIL.toString();
   }
   public static String goAdministration() {
      return APP_TgvAir.NAVIGATION_ADMINISTRATION.toString();
   }

   public static String goMdpOublie() {
      return SOCLE_constants.NAVIGATION_MDPOUBLIE.toString();
   }

   public static String goRole() {
      return SOCLE_constants.NAVIGATION_ROLE.toString();
   }

   public static String goUser() {
      return SOCLE_constants.NAVIGATION_USER.toString();
   }

   public static String goAjustement() {
      return APP_TgvAir.NAVIGATION_AJUSTEMENT.toString();
   }
   public static String goJob() {
      return SOCLE_constants.NAVIGATION_JOB.toString();
   }

   public static String goCatalogue() {
      return APP_TgvAir.NAVIGATION_CATALOGUE.toString();
   }

   public static String goCompagnieAerienne() {
      return APP_TgvAir.NAVIGATION_COMPAGNIEAERIENNE.toString();
   }

   public static String goPointArret() {
      return APP_TgvAir.NAVIGATION_POINTARRET.toString();
   }

   public static String goTrain() {
      return APP_TgvAir.NAVIGATION_TRAIN.toString();
   }
   public static String goJobPlanif() {
      return SOCLE_constants.NAVIGATION_JOB_PLANIF.toString();
   }
}
