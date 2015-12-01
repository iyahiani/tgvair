package com.avancial.socle.model.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.avancial.socle.data.controller.dao.UserDao;
import com.avancial.socle.data.model.databean.UserDataBean;
import com.avancial.socle.resources.ContextController;
import com.avancial.socle.resources.constants.SOCLE_constants;
import com.avancial.socle.utils.CalculeJoursFeriers;

/**
 * Managed bean de gestion du login
 * 
 * @author guillaume.bouziou
 * 
 */
@Named("login")
@RequestScoped
public class LoginManagedBean implements Serializable {

   //Logger log =Logger.getLogger(LoginManagedBean.class);
   private static final long serialVersionUID = 1L;
   private String login;
   private String password;
   @Inject
   private IhmManagedBean ihmManagedBean;
   @Inject
   private SecurityManagedBean securityManagedBean;

   // Dao de gestion des utilisateurs
   private UserDao utilisateurDao = new UserDao();

   /**
    * Initialisation de l'url courante
    */
   @PostConstruct
   public void init() {
      ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      if (this.ihmManagedBean.getOriginalURL() == null) {
         this.ihmManagedBean.setOriginalURL((String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI));
         if (this.ihmManagedBean.getOriginalURL() == null) {
            this.ihmManagedBean.setOriginalURL(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString());
         }
      }
   }  
   
   
   

   /**
    * Execute la connexion
    * 
    * @return
    * 
    * @throws IOException
    */
   public void login() throws IOException {
      ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
      String url = this.ihmManagedBean.getOriginalURL();
      String redirectURL =null ;
      try {
         request.login(this.login, this.password);
         UserDataBean user = this.utilisateurDao.getUserByLogin(this.login);
         if (null != user) {

            this.ihmManagedBean.setCurrentUser(user);
            this.securityManagedBean.init();

            this.ihmManagedBean.setOriginalURL(null);
          //  log.info("utilisateur connecté"+this.login);
         } 
          
         
         externalContext.redirect(url); 
         
      } catch (ServletException e) {
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
         HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
         request.logout();
        // log.info("utilisateur déconnecté"+this.login);
         this.ihmManagedBean.setCurrentUser(null);
         this.securityManagedBean.init();
         ContextController.addInfoMessage("login_deconnexion_ok");

      } catch (ServletException e) {
         e.printStackTrace();
      }
      return SOCLE_constants.NAVIGATION_ACCUEIL.toString();
   }
   
   public String motDePasseOublier() { 
      
      Calendar jour =Calendar.getInstance() ;
      if (CalculeJoursFeriers.listJoursFeriers(jour).contains(jour)) 
         return SOCLE_constants.NAVIGATION_MDPOUBLIE.toString(); 
      return SOCLE_constants.NAVIGATION_INFO_MOT_DE_PASSE.toString() ; 
   }
   
   /**
    * Gère le bouton annuler pour retourner à la page d'accueil
    * 
    * @return l'url de la page d'accueil 
    */ 
   
   public static String cancel() {
      return SOCLE_constants.NAVIGATION_ACCUEIL.toString();
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
