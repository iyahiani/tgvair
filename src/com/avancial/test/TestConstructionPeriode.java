package com.avancial.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

import com.avancial.app.business.train.Circulation;
import com.avancial.app.business.train.JourCirculation;
import com.avancial.app.business.train.Train;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class TestConstructionPeriode {

   @Test
   public void contruirePeriode() throws ParseException {
      SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");
      Train train1 = new Train();

      train1.addNumeroTrain("001111");
      train1.addNumeroTrain("001112");
      Train train2 = new Train();
      train2.addNumeroTrain("001113");
      Circulation c1 = new Circulation();
      Circulation c2 = new Circulation(), c1_2 = new Circulation(),c1_3 = new Circulation();
      c1 = TestTrain.createWithStringPeriode("01/07/2015#31/07/2015#13#FRMLW#FRACL#0800#1000");
      c1_2 = TestTrain.createWithStringPeriode("01/07/2015#31/08/2015#245#FRMLW#FRACL#0810#1010");
      c1_3 = TestTrain.createWithStringPeriode("01/04/2015#30/04/2015#245#FRMLW#FRACL#0800#1000");
      // --------------------------------------------- DEC VARIABLES
      // -----------------------------------------------

      Map<Date, JourCirculation> jcTemp = new TreeMap<Date, JourCirculation>();
      Map<Integer, Integer> dates = new TreeMap<Integer, Integer>();
      Map<Entry<Integer, Integer>, List<JourCirculation>> heureGrouper = new HashMap<>();
      List<JourCirculation> jourCir = new ArrayList<>();
      List<JourCirculation> jourCir2 = new ArrayList<>();
      Calendar calendar = Calendar.getInstance();
      Calendar calendar2 = Calendar.getInstance();
      Set<Integer> jours = new HashSet<Integer>();
      Map<Map.Entry<Integer, Integer>, Map<Integer, List<JourCirculation>>> heureJoursGrouper = new TreeMap<>();
      Map<Integer, List<JourCirculation>> joursGrouper = new TreeMap<>();

      train1.addCirculation(c1);
      train1.addCirculation(c1_2);
      train1.addCirculation(c1_3);
      train1.remplirJoursCirculations();

      for (Entry<Date, JourCirculation> jc : train1.getListeJoursCirculation().entrySet()) {
         if (jc.getValue().isFlagCirculation()) {
            jcTemp.put(jc.getKey(), jc.getValue());
         }
      }
      
      
      for (Entry<Date, JourCirculation> jc : jcTemp.entrySet()) {
         dates.put(jc.getValue().getHeureDepart(), jc.getValue().getHeureArrivee());
      }
      
            
      for (Entry<Integer, Integer> dt : dates.entrySet()) {
         for (Entry<Date, JourCirculation> jc : jcTemp.entrySet()) {
            
            if (dt.getKey().equals(jc.getValue().getHeureDepart())) {
               jourCir.add(jc.getValue()); 
               
            } 
         } 
      }
       
     //System.out.println(jourCir); 
      
      
      //for (Entry<Map.Entry<Integer, Integer>, List<JourCirculation>> entry : heureGrouper.entrySet()) {
         for (JourCirculation j : jourCir) {
            calendar.setTime(j.getDateCircul());
            if (calendar.get(Calendar.DAY_OF_WEEK) == 1)
               jours.add(7);
            else
               jours.add(calendar.get(Calendar.DAY_OF_WEEK) - 1);
          }  
         
      for (int i : jours) {
         for (JourCirculation j : jourCir) {
             calendar.setTime(j.getDateCircul()) ; 
       
            if ((calendar.get(Calendar.DAY_OF_WEEK)-1) == i || (calendar.get(Calendar.DAY_OF_WEEK)==7 && i==1)) {
               jourCir2.add(j) ; 
            }  ;
         }
      }
      System.out.println(jourCir2); 
      JourCirculation current = new JourCirculation() ; 
      current = jourCir2.get(0) ; 
      
      for (int i = 1 ; i < jourCir2.size(); i++ ) {
         calendar.setTime(current.getDateCircul()); 
         calendar2.setTime(jourCir2.get(i).getDateCircul()); 
         if (calendar.get(Calendar.DAY_OF_WEEK) == calendar2.get(Calendar.DAY_OF_WEEK)) { 
            
         }
      }
   } 
}
