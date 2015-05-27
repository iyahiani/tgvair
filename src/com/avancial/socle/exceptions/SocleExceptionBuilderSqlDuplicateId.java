/**
 * 
 */
package com.avancial.socle.exceptions;

/**
 * @author bruno.legloahec
 *
 */
public class SocleExceptionBuilderSqlDuplicateId extends ASocleExceptionBuilder {

   /**
    * Constructeur
    * 
    * @param next
    * @param e
    */
   public SocleExceptionBuilderSqlDuplicateId(ASocleExceptionBuilder next, Exception e) {
      super(next, e);
      this.socleException = new SocleDuplicateIdException(e);
      this.messageToBeFound = "Duplicate entry";

   }

}
