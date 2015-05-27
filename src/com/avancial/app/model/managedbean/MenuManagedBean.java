package com.avancial.app.model.managedbean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.avancial.socle.resources.constants.ConstantSocle;

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
      return ConstantSocle.NAVIGATION_ACCUEIL.toString();
   }

   public static String goMdpOublie() {
      return ConstantSocle.NAVIGATION_MDPOUBLIE.toString();
   }

   public static String goRole() {
      return ConstantSocle.NAVIGATION_ROLE.toString();
   }

   public static String goUser() {
      return ConstantSocle.NAVIGATION_USER.toString();
   }
}
