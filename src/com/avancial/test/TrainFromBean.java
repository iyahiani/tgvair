package com.avancial.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Before;
import org.junit.Test;

import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;

public class TrainFromBean  {

   @Before
   public void before() {
      Configuration configuration = new Configuration();
      configuration.setProperty("connection.driver_class", "com.mysql.jdbc.Driver");
      configuration.setProperty("hibernate.connection.url", "jdbc:mysql://caliban/tgv_air");
      configuration.setProperty("hibernate.connection.username", "dbad_tgvair");
      configuration.setProperty("hibernate.connection.password", "!tgvair-12");
      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
      SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      Session session = sessionFactory.openSession();
      
   }

  // @Test
   public void getTrainFromBean() {
      getTrains();
      List<String> nums = new ArrayList<>();
      nums.add("1111/4-1221/2");
      nums.add("2222/3");
      nums.add("5555");
      for (String s : nums) {
         System.out.println(trainsNums(s).toString());
      }
   }

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




   
   
}
