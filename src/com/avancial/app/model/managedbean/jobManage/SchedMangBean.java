package com.avancial.app.model.managedbean.jobManage;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@ManagedBean(eager=true) 
@ApplicationScoped

public class SchedMangBean  { 

   public SchedMangBean() { 
      
      System.out.println("SchedMangBean.SchedMangBean()");       
   } 
   
   public static FacesContext getContext() {
      return FacesContext.getCurrentInstance() ;
   }
}
