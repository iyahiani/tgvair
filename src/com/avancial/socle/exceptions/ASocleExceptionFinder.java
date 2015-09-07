/**
 * 
 */
package com.avancial.socle.exceptions;

/**
 * @author bruno.legloahec
 *
 */
public abstract class ASocleExceptionFinder {
   protected ASocleException     socleException;
   private ASocleExceptionFinder next;
   private Exception             e;
   protected StringBuilder       buffer = new StringBuilder();
   protected String              messageToBeFound;

   /**
    * Constructeur
    */
   public ASocleExceptionFinder(ASocleExceptionFinder next, Exception e) {
      this.setE(e);
      this.next = next;
      // On tente de récupérer l'exception parente
      Throwable t = e.getCause();
      if (null != t.getCause())
         this.buffer.append(t.getCause().getMessage());
      else
         this.buffer.append(e.getMessage());
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.exceptions.ISocleExceptionFinder#getSocleException (com.avancial.socle.exceptions.ISocleExceptionFinder, java.lang.Exception)
    */
   public ASocleException getSocleException() {
      if (this.buffer.lastIndexOf(this.messageToBeFound) > -1)
         return this.socleException;
      else if (null != this.next)
         return this.next.getSocleException();
      else
         e.printStackTrace();
      return new SocleGenericException(e);
   }

   public ASocleExceptionFinder getNext() {
      return this.next;
   };

   /**
    * @param next
    *           the next to set
    */
   public void setNext(ASocleExceptionFinder next) {
      this.next = next;
   }

   /**
    * @return the e
    */
   public Exception getE() {
      return this.e;
   }

   /**
    * @param e
    *           the e to set
    */
   public void setE(Exception e) {
      this.e = e;
   }

}
