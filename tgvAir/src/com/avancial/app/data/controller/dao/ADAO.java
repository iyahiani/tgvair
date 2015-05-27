package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.avancial.socle.data.controller.dao.AbstractEntityManager;
/**
 * 
 * @author ismael.yahiani
 *      
 */
public abstract class ADAO<T> { 
	
	private EntityManager entityManager ;
	public ADAO() {
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
	   
	   public abstract T find(int id ) ;
}
