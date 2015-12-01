package com.avancial.test;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.avancial.socle.utils.CalculeJoursFeriers;

public class TestJoursFeries {

   @Test 
   public void testJoursFeries() {
      Calendar c = Calendar.getInstance() ;
      List<Calendar> lc =  CalculeJoursFeriers.listJoursFeriers(c);
      for (Calendar cal : lc) {
         System.out.println(cal.getTime());
      }
   }
}
