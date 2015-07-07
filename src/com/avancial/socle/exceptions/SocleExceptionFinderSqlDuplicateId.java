/**
 * 
 */
package com.avancial.socle.exceptions;

/**
 * @author bruno.legloahec
 *
 */
public class SocleExceptionFinderSqlDuplicateId extends ASocleExceptionFinder {

   /**
    * Constructeur
    * 
    * @param next
    * @param e
    */
   public SocleExceptionFinderSqlDuplicateId(ASocleExceptionFinder next, Exception e) {
      super(next, e);
      this.socleException = new SocleDuplicateIdException(e);
      this.messageToBeFound = "Duplicate entry";

   }

}
