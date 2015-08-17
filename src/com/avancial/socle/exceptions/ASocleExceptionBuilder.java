/**
 * 
 */
package com.avancial.socle.exceptions;

/**
 * @author bruno.legloahec
 *
 */
public abstract class ASocleExceptionBuilder implements ISocleExceptionBuilder {
   protected ASocleException      socleException;
   private ASocleExceptionBuilder next;
   private Exception              e;
   protected StringBuilder        buffer = new StringBuilder();
   protected String               messageToBeFound;

   /**
    * Constructeur
    */
   public ASocleExceptionBuilder(ASocleExceptionBuilder next, Exception e) {
      this.setE(e);
      this.next = next;
      // On tente de récupérer l'exception parente
      Throwable t = e.getCause();
      if (null!=t)
         if (null!=t.getCause())
      this.buffer.append(t.getCause().getMessage());
   }

   /*
    * (non-Javadoc)
    * 
    * @see
    * com.avancial.socle.exceptions.ISocleExceptionBuilder#getSocleException
    * (com.avancial.socle.exceptions.ISocleExceptionBuilder,
    * java.lang.Exception)
    */
   @Override
   public ASocleException getSocleException() {
      if (this.buffer.lastIndexOf(this.messageToBeFound) > -1)
         return this.socleException;
      else if (null != this.next)
         return this.next.getSocleException();
      else
         return new SocleGenericException(e);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.exceptions.ISocleExceptionBuilder#getNext()
    */
   @Override
   public ASocleExceptionBuilder getNext() {
      return this.next;
   };

   /**
    * @param next
    *           the next to set
    */
   public void setNext(ASocleExceptionBuilder next) {
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
