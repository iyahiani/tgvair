package com.avancial.socle.resources.constants;

/**
 * @author bruno
 *
 */
public enum SOCLE_constants {
   /**
 * 
 */
   BUNDLE_PATH("com.avancial.app.resources.internationalization.Message"),
   SOCLE_PROPERTIES_PATH("/classes/com/avancial/socle/resources/socle.properties"),
   MESSAGE_ERR_TRANSLATION_NOT_FOUND("erreur_traduction_non_trouve"),
   MESSAGE_CONNECTION_ACTIVE("message_connection_active"),
   MESSAGE_CONNECTION_INACTIVE("message_connection_inactive"),
   NAVIGATION_ACCUEIL("/pages/public/accueil?faces-redirect=true"),
   NAVIGATION_LOGIN("/pages/public/login?faces-redirect=true"),
   NAVIGATION_MDPOUBLIE("/pages/public/mdpoublie?faces-redirect=true"),
   NAVIGATION_ROLE("/pages/private/role?faces-redirect=true"),
   NAVIGATION_USER("/pages/private/user?faces-redirect=true"),
   NAVIGATION_JOB("/pages/private/job?faces-redirect=true"),
   NAVIGATION_JOB_PLANIF("/pages/private/jobPlanif?faces-redirect=true"), 
   NAVIGATION_JOURNAL_LOG("/pages/private/journalLogs?faces-redirect=true"),
   /**
    * ID du statut des messages généraux à l'IHM
    */
   PAGE_ID_MESSAGES("idPageMessages"),
   /**
    * ID du statut des messages pour l'ajout des données
    */
   DIALOG_ADD_MESSAGES("message_add"),
   /**
    * ID du statut des messages pour l'update des données
    */
   DIALOG_UPD_MESSAGES("message_upd"),
   /**
    * ID du statut des messages pour la suppression des données
    */
   DIALOG_DEL_MESSAGES("message_del"),
   

   EXCEPTION_DUPICATE_ID("exception_duplicate_id"),
   EXCEPTION_UNKNOWN("exception_unknown");

   private String constant = "";

   private SOCLE_constants(String pconstant) {
      this.constant = pconstant;
   }

   @Override
   public String toString() {
      return this.constant;
   }

}
