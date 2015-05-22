/**
 * 
 */
package com.avancial.socle.params.beans;

/**
 * @author bruno
 *
 */
public interface IParamBean {

   /**
    * Récupère le nom du paramètre
    * 
    * @return le nom du paramètre
    */
   public String getName();

   /**
    * @param name
    */
   public void setName(String name);

   /**
    * @return la valeur du paramètre
    */
   public String getValue();

   /**
    * @param value
    */
   public void SetValue(String value);

}
