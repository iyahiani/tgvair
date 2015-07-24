package com.avancial.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.poi.ss.formula.functions.Days360;
import org.junit.Test;

import com.avancial.app.business.train.Circulation;
import com.avancial.app.business.train.JourCirculation;
import com.avancial.app.business.train.Train;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class TestConstructionPeriode {

   int diff;

   @Test
   public void contruirePeriode() throws ParseException {
      SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");
      Train train1 = new Train();

      train1.addNumeroTrain("001111");
      train1.addNumeroTrain("001112");
      Train train2 = new Train();
      train2.addNumeroTrain("001113");
      Circulation c1 = new Circulation();
      Circulation c2 = new Circulation(), c1_2 = new Circulation(), c1_3 = new Circulation();
      c1 = TestTrain.createWithStringPeriode("01/06/2015#30/06/2015#134#FRMLW#FRACL#0800#1000");
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

      // ///////////////////////////////////////////////////////////////////////////////////////

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

      // //////////////List circulation Regrouper par heures

      for (Entry<Integer, Integer> dt : dates.entrySet()) {
         for (Entry<Date, JourCirculation> jc : jcTemp.entrySet()) {

            if (dt.getKey().equals(jc.getValue().getHeureDepart())) {
               jourCir.add(jc.getValue());

            }
         }
      }

      // ////////// Set regroupant l'ensembles des jours de circulation du train

      for (JourCirculation j : jourCir) {
         calendar.setTime(j.getDateCircul());
         if (calendar.get(Calendar.DAY_OF_WEEK) == 1)
            jours.add(7);
         else
            jours.add(calendar.get(Calendar.DAY_OF_WEEK) - 1);
      }

      // ///// list regroupante les circulation par heure de depart/Arriver et
      // par jour de circulation

      for (Entry<Integer, Integer> dt : dates.entrySet())
         for (int i : jours) {
            for (JourCirculation j : jourCir) {
               calendar.setTime(j.getDateCircul());
               if (j.getHeureDepart() == dt.getKey())
                  if ((calendar.get(Calendar.DAY_OF_WEEK) - 1) == i || (calendar.get(Calendar.DAY_OF_WEEK) == 7 && i == 1)) {
                     jourCir2.add(j);
                  }
            }
         }

      // System.out.println(jourCir2);

      // ///////////////////////////////// calcule des periodes

      int jourDeb, jourFin;
      Calendar dt_db = Calendar.getInstance(), compt = Calendar.getInstance(), dt_fin = Calendar.getInstance();
      Map<Integer, Map<Date,Date>> periodes = new TreeMap<>();
      Map<Date,Date> maPeriode = new LinkedHashMap<>();
      String periodeTest;
      int flag = 0;

      for (int j : jours) {

         for (Entry<Integer, Integer> dt : dates.entrySet()) {

            dt_db.setTime(jourCir2.get(flag).getDateCircul());

            for (int x = 1; x < jourCir2.size(); x++) {

               compt.setTime(jourCir2.get(x).getDateCircul());
               
               if (compt.get(Calendar.DAY_OF_WEEK) - 1 == j 
                     && dt_db.get(Calendar.DAY_OF_WEEK) - 1 == j 
                     && jourCir2.get(x).getHeureDepart() == dt.getKey()) {

                  diff = compt.get(Calendar.DAY_OF_YEAR) - dt_db.get(Calendar.DAY_OF_YEAR);
                  
                  if (diff == 7)
                     dt_fin = compt;
                  
                  //System.out.println(j + " " + dt_db.getTime() + " " + dt_fin.getTime() + " " + diff);  
                  maPeriode.put(dt_db.getTime(),dt_fin.getTime()) ;
                  
               
                  flag++;
               } else {
                  dt_db.setTime(jourCir2.get(x).getDateCircul());
               }
               
            }

         }

      } 
      System.out.println(maPeriode);

   }
}
