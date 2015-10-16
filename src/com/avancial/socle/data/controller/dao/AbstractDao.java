/**
 * 
 */
package com.avancial.socle.data.controller.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.CirculationSSIMDataBean;

/**
 * Classe abstraite servant de base à tous les objets DAO
 * 
 * @author bruno.legloahec
 *
 */
public abstract class AbstractDao {
   private EntityManager entityManager;
   Logger log = Logger.getLogger(AbstractDao.class) ;
   /**
    * Constructeur
    */
   public AbstractDao() {
      this.setEntityManager(AbstractEntityManager.getInstance().getEntityManager());
   }

   /**
    * @return la liste contenant tous les enregistrements de l'entité concernée
    */
   public abstract List<?> getAll();

   protected EntityManager getEntityManager() {
      return this.entityManager;
   }

   protected void setEntityManager(EntityManager entityManager) {
      this.entityManager = entityManager;
   } 
   public Session getSession() { 
      if(this.getEntityManager().unwrap(Session.class)==null) log.info("erreur de création de session"); 
      return this.getEntityManager().unwrap(Session.class);
   } 
  
   
   public SessionFactory getSessionFactory() {
      Configuration configuration = new Configuration().configure();
      
       StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
       applySettings(configuration.getProperties());
       SessionFactory factory = configuration.buildSessionFactory(builder.build()); 
       return factory ;
   }
}
