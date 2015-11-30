package com.avancial.app.resources.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

@Named("calendar") 
@SessionScoped


public class CalendarManagedBean {
  
   private String[] availableEffects = {"blind", "bounce", "clip", "drop", "explode", "fade", "fold", "highlight", "puff", "pulsate", "scale", "shake", "size", "slide", "slideDown" };
   private Date simpleDate;
   private ArrayList<Date> listeDates; 
  private ArrayList<String> stringDates ; 
  private Date[] arrayDate ;
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
      if (simpleDate == null)
         return "no Date selected";
      String message = String.format("You choose '%s'.", simpleDate.toString());
      return message;

   } 
   public ArrayList<Date> getListeDates() throws Exception  {
      ArrayList<Date>  temp = new ArrayList<Date>();  //{,(StringToDate.toDate("19JUL2015")),(StringToDate.toDate("19SEP2015"))} ;
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
      temp.add(sdf.parse("19/08/15"));
      temp.add(sdf.parse("19/09/15"));
      temp.add(sdf.parse("19/10/15"));
     
      return temp ; 
   } 
   public ArrayList<String> getStringDates() throws Exception  {
      ArrayList<String>  temp = new ArrayList<String>();  //{,(StringToDate.toDate("19JUL2015")),(StringToDate.toDate("19SEP2015"))} ;
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
      temp.add("19/08/15");
      temp.add("19/09/15");
      temp.add("19/10/15");
     
      return temp ; 
   } 
   
}
