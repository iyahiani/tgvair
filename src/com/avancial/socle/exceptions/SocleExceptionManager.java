/**
 * 
 */
package com.avancial.socle.exceptions;

/**
 * C'est le point d'entr�e pour le client.
 * Il est charg� de contruire la chaine des Exception builder.
 * 
 * @author bruno.legloahec
 *
 */
public class SocleExceptionManager {

   public static ASocleException getException(Exception e) {

      ISocleExceptionBuilder builder = new SocleExceptionBuilderSqlDuplicateId(null, e);
      return builder.getSocleException();
   }
}
