package com.avancial.socle.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.socle.data.model.databean.RoleDataBean;
import com.avancial.socle.data.model.databean.User2RoleDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

/**
 * Classe DAO pour l'objet Role
 * 
 * @author bruno.legloahec
 * 
 */
public class RoleDao extends AbstractDao {

   @SuppressWarnings("unchecked")
   @Override
   public List<RoleDataBean> getAll() {

      String sql = "From RoleDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }
   public List<RoleDataBean> getUser2RoleByRole(String libelle) {
      return this.getEntityManager().createQuery("FROM RoleDataBean t WHERE t.labelRole = :libelle").setParameter("libelle", libelle).getResultList();
   }
   public void save(RoleDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
        @SuppressWarnings("unused")
		SocleExceptionManager manager=new SocleExceptionManager(e);
        throw SocleExceptionManager.getException();
      }
   }

   public void delete(RoleDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().remove(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         @SuppressWarnings("unused")
 		SocleExceptionManager manager=new SocleExceptionManager(e);
         throw SocleExceptionManager.getException();
      }

   }

   public void update(RoleDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         @SuppressWarnings("unused")
 		SocleExceptionManager manager=new SocleExceptionManager(e);
         throw SocleExceptionManager.getException();
      }

   }

}
