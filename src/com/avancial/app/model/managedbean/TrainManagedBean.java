package com.avancial.app.model.managedbean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean 
@SessionScoped 


public class TrainManagedBean {

   private Date date= new Date() ;

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }
   

}
