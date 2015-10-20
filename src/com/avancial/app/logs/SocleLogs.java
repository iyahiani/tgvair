package com.avancial.app.logs;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.avancial.socle.model.managedbean.LoginManagedBean;


@ApplicationScoped  

public class SocleLogs implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
    Logger log = Logger.getLogger(SocleLogs.class) ;
    
    public SocleLogs() { 
        
        System.out.println("SocleLogs.SocleLogs().construct"); 
   } 

    @PostConstruct 
   public void init() { 
     
     System.out.println("SocleLogs.SocleLogs().init");
  }
     
   @Inject 
   protected LoginManagedBean login ;
   
   public LoginManagedBean getLogin() {
      return login;
   }

      
   public void setLogin(LoginManagedBean login) {
      this.login = login;
   } 
   
   
         
}
