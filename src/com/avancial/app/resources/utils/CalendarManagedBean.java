package com.avancial.app.resources.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.poi.ss.usermodel.DateUtil;
import org.jboss.weld.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named("calendar") 
@SessionScoped 
public class CalendarManagedBean {
   private String[] availableEffects =
               { "blind", "bounce", "clip", "drop", "explode",
                "fade", "fold", "highlight", "puff", "pulsate",
                "scale", "shake", "size", "slide", "slideDown" };

   private Date simpleDate; 
   
   public String[] getAvailableEffects() {
      return availableEffects;
   }
  
   public Date getSimpleDate() {
      return simpleDate;
   }

   public void onDateSelect(SelectEvent event) {
      FacesContext facesContext = FacesContext.getCurrentInstance();
      SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
  }
   public String getSimpleDay() {
      if (simpleDate==null) return "no Date selected";
      String message=String.format("You choose '%s'.",simpleDate.toString() );
      return message ;
                       

   }
    
}
