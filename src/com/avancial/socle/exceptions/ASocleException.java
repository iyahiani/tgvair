/**
 * 
 */
package com.avancial.socle.exceptions;

/**
 * @author bruno.legloahec
 *
 */
public abstract class ASocleException extends Throwable implements ISocleException {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private Exception         originalException;
   protected String          ClientMessage;
   protected String          messageToBeFound;

   /**
    * Constructeur
    */
   public ASocleException(Exception originalException) {
      this.setOriginalException(originalException);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.exceptions.ISocleException#getOriginalException()
    */
   @Override
   public Exception getOriginalException() {
      return this.originalException;
   }

   /**
    *	
    * 
    * @see com.avancial.socle.exceptions.ISocleException#getClientMessage()
    */
   @Override
   abstract public String getClientMessage();

   /**
    * @param originalException
    *           the originalException to set
    */
   public void setOriginalException(Exception originalException) {
      this.originalException = originalException;
   }

}
