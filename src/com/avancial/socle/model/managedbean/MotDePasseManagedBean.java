package com.avancial.socle.model.managedbean;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.avancial.app.resources.utils.Encrypte;
import com.avancial.app.resources.utils.HashGenerationException;
import com.avancial.socle.data.controller.dao.UserDao;
import com.avancial.socle.data.model.databean.UserDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.resources.constants.SOCLE_constants;

@Named("password")
@RequestScoped
public class MotDePasseManagedBean {

   private boolean correct;
   private boolean exist;
   private String login;
   private String password  ; 
   private String confirmPassWord ;
   
   public MotDePasseManagedBean() {
      this.correct = true; 
      this.exist =false ;
   }

   public String validerLogin() {

      UserDao userDao = new UserDao();
      UserDataBean userDataBean = new UserDataBean();
      userDataBean = userDao.getUserByLogin(this.login);
      if (userDataBean != null)
         this.exist = true;
      if(!this.exist)
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), 
               new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Votre Login est incorrecte"));
      
      if (this.password!=null && this.exist) {
         try {
            userDataBean.setPasswordUser(Encrypte.generateSHA1(this.password)); 
            userDao.update(userDataBean); 
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
         } catch (HashGenerationException | ASocleException | IOException e) {
            
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), 
                  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Erreur Lors de l'enregistrement du nouveau Mot de Passe"));
            e.printStackTrace();
         }
         
      }
         
      return null;
   }

   // ///////////// GETTERS & SETTERS

   public boolean isCorrect() {
      return correct;
   }

   public void setCorrect(boolean correct) {
      this.correct = correct;
   }

   public boolean isExist() {
      return exist;
   }

   public void setExist(boolean exist) {
      this.exist = exist;
   }

   public String getLogin() {
      return login;
   }

   public void setLogin(String login) {
      this.login = login;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getConfirmPassWord() {
      return confirmPassWord;
   }

   public void setConfirmPassWord(String confirmPassWord) {
      this.confirmPassWord = confirmPassWord;
   }

}
