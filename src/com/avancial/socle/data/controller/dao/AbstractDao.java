/**
 * 
 */
package com.avancial.socle.data.controller.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

/**
 * Classe abstraite servant de base à tous les objets DAO
 * 
 * @author bruno.legloahec
 *
 */
public abstract class AbstractDao {
   private EntityManager entityManager;

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
      return this.getEntityManager().unwrap(Session.class);
   }
}
