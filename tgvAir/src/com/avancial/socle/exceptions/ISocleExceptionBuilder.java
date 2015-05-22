/**
 * 
 */
package com.avancial.socle.exceptions;

/**
 * Cette famille d'objet aura la responsabilité de créer des exceptions
 * personnalisées à partir des exceptions classiques de Java.
 * 
 * @author bruno.legloahec
 *
 */
public interface ISocleExceptionBuilder {
   public ASocleException getSocleException();

   public ASocleExceptionBuilder getNext();
}
