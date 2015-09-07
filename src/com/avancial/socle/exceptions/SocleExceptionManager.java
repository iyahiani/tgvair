/**
 * 
 */
package com.avancial.socle.exceptions;

/**
 * C'est le point d'entrée pour le client. Il est chargé de contruire la chaine des Exception builder.
 * 
 * @author bruno.legloahec
 *
 */
public class SocleExceptionManager {
   protected static ASocleExceptionFinder finder = null;
   protected Exception                    e;

   public SocleExceptionManager(Exception e) {
      this.init(e);
   }

   @SuppressWarnings("static-method")
   protected void init(Exception e) {
      this.e = e;
      ASocleExceptionFinder finder = new SocleExceptionFinderSqlDuplicateId(null, e);

      // A laisser à la fin. Doit pointer sur le dernier finder
      SocleExceptionManager.finder = finder;
   }

   /**
    * 
    * @param finder
    */
   public static void add(ASocleExceptionFinder finder) {
      SocleExceptionManager.finder = finder;
   }

   public static ASocleException getException() {
      return SocleExceptionManager.finder.getSocleException();
   }

}
