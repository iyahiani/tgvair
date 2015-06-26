package com.avancial.socle.model.managedbean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.avancial.socle.data.controller.dao.UtilisateurDao;
import com.avancial.socle.resources.ContextController;
import com.avancial.socle.resources.constants.ConstantSocle;

/**
 * Managed bean de gestion du login
 * 
 * @author guillaume.bouziou
 * 
 */
@Named("login")
@RequestScoped
public class LoginManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LoginManagedBean.class);
	private String login;
	private String password;

	@Inject
	private IhmManagedBean ihmManagedBean;

	// Dao de gestion des utilisateurs

	private UtilisateurDao utilisateurDao = new UtilisateurDao();

	/**
	 * Initialisation de l'url courante
	 */
	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		if (this.ihmManagedBean.getOriginalURL() == null) {
			this.ihmManagedBean
					.setOriginalURL((String) externalContext.getRequestMap()
							.get(RequestDispatcher.FORWARD_REQUEST_URI));
			if (this.ihmManagedBean.getOriginalURL() == null) {
				this.ihmManagedBean
						.setOriginalURL(((HttpServletRequest) FacesContext
								.getCurrentInstance().getExternalContext()
								.getRequest()).getRequestURL().toString()); 
				
			}
		}
	}

	/**
	 * Execute la connexion
	 * 
	 * @throws IOException
	 */
	public void login() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext
				.getRequest();

		try {
			request.login(this.login, this.password);
			this.ihmManagedBean.setCurrentUser(this.utilisateurDao
					.getUserByLogin(this.login));
			String url = this.ihmManagedBean.getOriginalURL();
			this.ihmManagedBean.setOriginalURL(null);
			externalContext.redirect(url); 
			log.log(Level.INFO, "user connecter");
		} catch (ServletException e) {
			log.log(Level.ERROR, e.getMessage());
			ContextController.addErrorMessage("login_connexion_erreur");
		}
	}

	/**
	 * Execute la déconnexion et renvoie l'utilisateur sur la page d'accueil
	 * 
	 * @return l'url de la page d'accueil
	 */
	public String logout() {
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			request.logout();
			this.ihmManagedBean.setCurrentUser(null); 
			log.fatal("login_deconnexion_ok"+getLogin());
			ContextController.addInfoMessage("login_deconnexion_ok");
		} catch (ServletException e) {
			e.printStackTrace();
		}
		return ConstantSocle.NAVIGATION_ACCUEIL.toString();
	}

	/**
	 * Gère le bouton annuler pour retourner à la page d'accueil
	 * 
	 * @return l'url de la page d'accueil
	 */
	public static String cancel() {
		return ConstantSocle.NAVIGATION_ACCUEIL.toString();
	}

	/**
	 * getter password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * setter the password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * getter login
	 * 
	 * @return the login
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * setter the login
	 * 
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

}
