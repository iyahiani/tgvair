package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

public class CompagnieAerienneDao extends AbstractDao {

   @Override
   public List<CompagnieAerienneDataBean> getAll() {

      String sql = "From CompagnieAerienneDataBean";// select
                                                                                 // codeCompagnieAerienne
      Query requete = this.getEntityManager().createQuery(sql);
      System.out.println(requete.getResultList());
      return requete.getResultList();
   }

   public void save(CompagnieAerienneDataBean bean) throws ASocleException {
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

   public List<CompagnieAerienneDataBean> getAllCodeCompagnie() {

      String sql = "select CodeCompagnieAerienne From CompagnieAerienneDataBean";// select
                                                                                 // codeCompagnieAerienne
      Query requete = this.getEntityManager().createQuery(sql);
      System.out.println(requete.getResultList());
      return requete.getResultList();
   }
   public void delete(CompagnieAerienneDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().remove(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw SocleExceptionManager.getException();
      }

   }

   public void update(CompagnieAerienneDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw SocleExceptionManager.getException();
      }
   }

   /**
    * 
    * @param code
    *           retourne le bean relative au code passer en paramétre
    * @return
    */
   public List<CompagnieAerienneDataBean> getCompagnieByCode(String code) {

      String sql = "From CompagnieAerienneDataBean as p WHERE p.CodeCompagnieAerienne =?";
      Query requete = this.getEntityManager().createQuery(sql);
      requete.setParameter(1, code);

      return requete.getResultList();

   } 
   
   public Session getSession() {
      return this.getEntityManager().unwrap(Session.class) ;
   }
}
