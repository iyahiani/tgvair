/**
 * 
 */
package com.avancial.socle.params.exception;

/**
 * @author bruno.legloahec
 *
 */
public class ParamNotFoundException extends Exception {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * Constructeur
    */
   public ParamNotFoundException(String type, String name) {
      super(String.format(AParamException.MSG_COLLECTION_ITEM_NOT_FOUND, name, type));
   }
}
