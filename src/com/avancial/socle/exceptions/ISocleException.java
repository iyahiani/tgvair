/**
 * 
 */
package com.avancial.socle.exceptions;

/**
 * Décrit le contrat minimum pour créer les exceptions du socle.
 * <ul>
 * Ce contrat permet de gérer 2 niveaux de communication :<br/>
 * <li>Pour le développeur avec l'exception originale</li>
 * <li>Pour le client avec un message fonctionnel clair et localisé</li>
 * </ul>
 * 
 * @author bruno.legloahec
 *
 */
public interface ISocleException {
	
   public Exception getOriginalException();
   public String getClientMessage();

}
