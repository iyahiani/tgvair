/**
 * 
 */
package com.avancial.socle.data.controller.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Singleton de gestion du EntityManagerFactory et EntityManager
 * 
 * @author guillaume.bouziou
 * 
 */
public class AbstractEntityManager {

   private static AbstractEntityManager instance;

   private static final String          PERSISTENCE_UNIT_NAME = "persistence_socle";
   private EntityManagerFactory         emf;
   private EntityManager                em;

   /**
    * Constructor
    */
   private AbstractEntityManager() {

      this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
      this.em = this.emf.createEntityManager();

   }

   /**
    * Retourne l'instance courante
    * 
    * @return l'instance courante
    */
   public static synchronized AbstractEntityManager getInstance() {
      if (instance == null) {
         instance = new AbstractEntityManager();
      }

      return instance;
   }

   /**
    * Getter de l'entityManager
    * 
    * @return l'entityManager
    */
   public EntityManager getEntityManager() {
      return this.em;
   }

}
