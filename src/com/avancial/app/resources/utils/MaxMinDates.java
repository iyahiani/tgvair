package com.avancial.app.resources.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MaxMinDates {

   public static Date getMaxDate(List<Date> listDates) {
      Calendar c = Calendar.getInstance() ;
      Calendar c1 = Calendar.getInstance() ;
      
      Date temp = new Date();
      temp=listDates.get(0) ; 
      c.setTime(temp);
      
      for (Date d : listDates) { 
         c1.setTime(d);
         if(c.getTime().before(c1.getTime())) c.setTime(c1.getTime());  
      }
      return c.getTime();
      
   }
   
   public static Date getMinDate(List<Date> listDates) {
      Calendar c = Calendar.getInstance() ;
      Calendar c1 = Calendar.getInstance() ;
   
      Date temp = new Date(); temp=listDates.get(0) ; 
      c.setTime(temp);
      for (Date d : listDates) { 
         
         c1.setTime(d);
         
         if(c.getTime().after(c1.getTime())) c.setTime(c1.getTime());
         
      }
      return c.getTime();
   }
}
