package com.avancial.socle.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.socle.data.model.databean.JobPlanifTypeDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

/**
 * Classe DAO pour l'objet Role
 * 
 * @author bruno.legloahec
 * 
 */
public class JobPlanifTypeDao extends AbstractDao {

   @SuppressWarnings("unchecked")
   @Override
   public List<JobPlanifTypeDataBean> getAll() {

      String sql = "From JobPlanifTypeDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }

   public JobPlanifTypeDataBean getJobPlanifTypeById(Long id) {
      String sql = "From JobPlanifTypeDataBean where id=:id";

      return (JobPlanifTypeDataBean) this.getEntityManager().createQuery(sql).setParameter("id", id).getSingleResult();
   }

   public void save(JobPlanifTypeDataBean bean) throws ASocleException {
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

   public void delete(JobPlanifTypeDataBean bean) throws ASocleException {
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

   public void update(JobPlanifTypeDataBean bean) throws ASocleException {
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
