package com.avancial.socle.model.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.avancial.socle.data.model.databean.User2RoleDataBean;
import com.avancial.socle.data.model.databean.UserDataBean;
import com.avancial.socle.resources.ContextController;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * Managed Bean de gestion du header
 * 
 * @author guillaume.bouziou
 * 
 */
@Named("ihmManagedBean")
@SessionScoped
public class IhmManagedBean implements Serializable {

   private static final long       serialVersionUID = 1L;
   private UserDataBean            currentUser;
   private List<User2RoleDataBean> roles;
   private String                  originalURL;
   private Locale                  locale;

   /**
    * Teste si il y a un utilisateur de connecté
    * 
    * @return résultat du test
    */
   public Boolean isLogged() {
      return this.currentUser != null;
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
         this.setCurrentUser(null);
         ContextController.addInfoMessage("login_deconnexion_ok");
      } catch (ServletException e) {
         e.printStackTrace();
      }
      return SOCLE_constants.NAVIGATION_ACCUEIL.toString();
   }

   /**
    * Redirection vers la page de login
    * 
    * @return L'url de la page de login
    */
   public String goLogin() {
      ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      this.setOriginalURL((String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI));
      // FIXME A Commenter
      if (this.getOriginalURL() == null) {
         this.setOriginalURL(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString());
      }

      return SOCLE_constants.NAVIGATION_LOGIN.toString();
   }

   /**
    * getter currentUser
    * 
    * @return the currentUser
    */
   public UserDataBean getCurrentUser() {
      return this.currentUser;
   }

   /**
    * setter the currentUser
    * 
    * @param currentUser
    */
   public void setCurrentUser(UserDataBean currentUser) {
      this.currentUser = currentUser;
   }

   /**
    * getter originalURL
    * 
    * @return the originalURL
    */
   public String getOriginalURL() {
      return this.originalURL;
   }

   /**
    * setter the originalURL
    * 
    * @param originalURL
    */
   public void setOriginalURL(String originalURL) {
      this.originalURL = originalURL;
   }

   /**
    * getter locale to string
    * 
    * @return locale to string
    */
   // Ne pas passer en static, ça plante dans les pages
   @SuppressWarnings("static-method")
   public String getLanguage() {
      return FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
   }

   /**
    * setter locale to string
    * 
    * @param language
    * 
    */
   public void setLanguage(String language) {
      this.locale = new Locale(language);
      FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
   }

   /**
    * setter the locale
    * 
    * @param locale
    */
   public void setLocale(Locale locale) {
      this.locale = locale;
   }

   /**
    * getter locale
    * 
    * @return the locale
    */
   public Locale getLocale() {
      if (null == this.locale)
         this.locale = new Locale(FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
      return this.locale;
   }

   /**
    * @return the roles
    */
   public List<User2RoleDataBean> getRoles() {
      return this.roles;
   }

   /**
    * @param roles
    *           the roles to set
    */
   public void setRoles(List<User2RoleDataBean> roles) {
      this.roles = roles;
   }
}
