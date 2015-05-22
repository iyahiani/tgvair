/**
 * 
 */
package com.avancial.socle.exceptions;

/**
 * Cette famille d'objet aura la responsabilit� de cr�er des exceptions
 * personnalis�es � partir des exceptions classiques de Java.
 * 
 * @author bruno.legloahec
 *
 */
public interface ISocleExceptionBuilder {
   public ASocleException getSocleException();

   public ASocleExceptionBuilder getNext();
}
