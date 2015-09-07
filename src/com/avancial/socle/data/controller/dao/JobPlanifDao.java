package com.avancial.socle.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.socle.data.model.databean.JobPlanifDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

/**
 * Classe DAO pour l'objet JobPlanif
 * 
 * @author bruno.legloahec
 * 
 */
public class JobPlanifDao extends AbstractDao {
   @SuppressWarnings("unchecked")
   @Override
   public List<JobPlanifDataBean> getAll() {

      String sql = "From JobPlanifDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }

   public void save(JobPlanifDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         @SuppressWarnings("unused")
         SocleExceptionManager manager = new SocleExceptionManager(e);
         throw SocleExceptionManager.getException();
      }
   }

   public void delete(JobPlanifDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().remove(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         @SuppressWarnings("unused")
         SocleExceptionManager manager = new SocleExceptionManager(e);
         throw SocleExceptionManager.getException();
      }

   }

   public void update(JobPlanifDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         @SuppressWarnings("unused")
         SocleExceptionManager manager = new SocleExceptionManager(e);
         throw SocleExceptionManager.getException();
      }

   }

}
