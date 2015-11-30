package com.avancial.test;

import java.util.Calendar;
import java.util.Date;

import com.avancial.app.resources.utils.StringToDate;

public class TestgetTRAIN {

   public static void main(String[] args) {
      
      Date d =new Date() ;
      Calendar c = Calendar.getInstance();
      d = c.getTime(); 
      System.out.println(d);
      System.out.println(Integer.valueOf(StringToDate.toFormatedString(d)));
   }

}
