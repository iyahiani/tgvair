/**
 * 
 */
package com.avancial.socle.resources;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Actions générales sur le context
 * 
 * @author guillaume.bouziou
 * 
 */
public final class ContextController {

	/**
	 * Constructor
	 */
	private ContextController() {
	}

	/**
	 * Ajoute un message d'erreur
	 * 
	 * @param message le code de traduction
	 */
	public static void addErrorMessage(String message) {
		addMessage(FacesMessage.SEVERITY_ERROR, message);
	}

	/**
	 * Ajout un message d'info
	 * 
	 * @param message le code de traduction
	 */
	public static void addInfoMessage(String message) {
		addMessage(FacesMessage.SEVERITY_INFO, message);
	}

	/**
	 * Ajout d'un message
	 * 
	 * @param severity
	 * @param message le code de traduction
	 */
	public static void addMessage(Severity severity, String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, MessageController.getTraduction(message), null));
	}
	
	
}
