package com.avancial.tgvair.data.controller.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.avancial.socle.data.controller.dao.AbstractEntityManager;

public  class AEntityManager {

	  private static AEntityManager instance;

	   private static final String          PERSISTENCE_UNIT_NAME = "persistence_socle";
	   private EntityManagerFactory         emf;
	   private EntityManager                em;

	   /**
	    * Constructor
	    */
	   private AEntityManager() {
	      this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	      this.em = this.emf.createEntityManager();
	   }

	   /**
	    * 
	    * @return l'instance courante
	    */
	   public static synchronized AEntityManager getInstance() {
	      if (instance == null) {
	         instance = new AEntityManager();
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
