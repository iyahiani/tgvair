package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.data.model.databean.RoleDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

public class CompagnieAerienneDao extends AbstractDao {

   @Override
   public List<CompagnieAerienneDataBean> getAll() {

      String sql = "select CodeCompagnieAerienne From CompagnieAerienneDataBean";//select codeCompagnieAerienne
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
         SocleExceptionManager manager=new SocleExceptionManager();
           throw SocleExceptionManager.getException(e);
        
      }
   }

   public void delete(CompagnieAerienneDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().remove(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw SocleExceptionManager.getException(e);
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
         throw SocleExceptionManager.getException(e);
      }
   }
}
