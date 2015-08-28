/**
 * 
 */
package com.avancial.socle.data.controller.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

/**
 * Classe abstraite servant de base � tous les objets DAO
 * 
 * @author bruno.legloahec
 *
 */
public abstract class AbstractDao  implements Serializable{
   private EntityManager entityManager;

   /**
    * Constructeur
    */
   public AbstractDao() {
      this.setEntityManager(AbstractEntityManager.getInstance().getEntityManager());
   }

   /**
    * @return la liste contenant tous les enregistrements de l'entit� concern�e
    */
   public abstract List<?> getAll();

   protected EntityManager getEntityManager() {
      return this.entityManager;
   }

   protected void setEntityManager(EntityManager entityManager) {
      this.entityManager = entityManager;
   }
}
