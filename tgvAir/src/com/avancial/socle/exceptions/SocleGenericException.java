/**
 * 
 */
package com.avancial.socle.exceptions;

import com.avancial.socle.resources.MessageController;
import com.avancial.socle.resources.constants.ConstantSocle;

/**
 * @author bruno.legloahec
 *
 */
public class SocleGenericException extends ASocleException {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * Constructeur
    * 
    * @param originalException
    */
   public SocleGenericException(Exception originalException) {
      super(originalException);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.exceptions.ASocleException#getClientMessage()
    */
   @Override
   public String getClientMessage() {
      return MessageController.getTraduction(ConstantSocle.EXCEPTION_UNKNOWN.toString());
   }

}
