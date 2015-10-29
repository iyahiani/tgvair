package com.avancial.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.Test;

import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.resources.utils.CalculeJoursFeriers;
import com.avancial.app.resources.utils.TimeZoneOffSet;

public class TrainFromBean  {

   

   public List<String> trainsNums(String a) {
      String[] split = a.split("-");
      String temp = "", temp1 = "";
      List<String> buff = new ArrayList<>();
      for (String s : split) {
         System.out.println("-------- AVANT SPLIT-------------");
         System.out.println(s);
         System.out.println("-------- APRES SPLIT-------------");
         if (s.length() > 4) {

            temp = s.substring(0, 4);
            buff.add("00".concat(temp));
            temp1 = temp.substring(0, 3).concat(s.substring(5, 6));
            buff.add("00".concat(temp1));

         } else {
            buff.add("00".concat(s));
         }
      }

      return buff;
   }

  // @Test
   public void getTrains() {
      TrainCatalogue tc = new TrainCatalogue();

      TrainCatalogueDAO tcDAO = new TrainCatalogueDAO();
      Session tcSession = tcDAO.getSession();
      Criteria criteria = tcSession.createCriteria(TrainCatalogueDataBean.class);
      System.out.println(criteria.toString());
      List<TrainCatalogueDataBean> listTrainCat = new ArrayList<>();
      listTrainCat.clear();
      for (TrainCatalogueDataBean trainCatalogueDataBean : listTrainCat) {
         System.out.println(trainCatalogueDataBean);

      }
   }



// @Test 

public void testCalendar() {
   
   List<Calendar> cal = CalculeJoursFeriers.listJoursFeriers(Calendar.getInstance()) ;
   for (Calendar calendar : cal) {
      System.out.println(calendar.getTime());
      
   }
}
   @Test
   public void testOffset() {
     System.out.println(TimeZoneOffSet.getGMTDiff()); 
   }
   
}
