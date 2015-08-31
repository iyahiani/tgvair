package com.avancial.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;

public class TestgetTRAIN extends AbstractDao {

   private EntityManager em = this.getEntityManager();

   public static void main(String[] args) {
      Configuration configuration = new Configuration();// "hibernate.cfg //"D:/Users/ismael.yahiani/git/TgvAir/WebContent/META-INF/context.xml"
      configuration.setProperty("connection.driver_class", "com.mysql.jdbc.Driver");
      configuration.setProperty("hibernate.connection.url", "jdbc:mysql://caliban/tgv_air");
      configuration.setProperty("hibernate.connection.username", "dbad_tgvair");
      configuration.setProperty("hibernate.connection.password", "!tgvair-12");
      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
      SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      Session session = sessionFactory.openSession();
     
      // Session session = ts.getEntityManager().unwrap(Session.class);
   }
   @Override
   public List<?> getAll() {
      TrainCatalogueDAO tcDAO = new TrainCatalogueDAO();
      List<TrainCatalogueDataBean> listTrainCat = new ArrayList<>();
      listTrainCat.addAll(tcDAO.getAll());
      System.out.println(listTrainCat.size());
      return null;
   }

   public EntityManager getEm() {
      return em;
   }

   public void setEm(EntityManager em) {
      this.em = em;
   }

}
