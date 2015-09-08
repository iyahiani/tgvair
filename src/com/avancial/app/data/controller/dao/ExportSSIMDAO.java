package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.app.data.model.databean.ExportSSIMDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

public class ExportSSIMDAO extends AbstractDao {

   @Override
   public List<ExportSSIMDataBean> getAll() {
      String sql = "From ExportSSIMDataBean";

      Query requete = this.getEntityManager().createQuery(sql);
      
      return requete.getResultList();
   }

   public void save(ExportSSIMDataBean t) throws ASocleException {

      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(t);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         this.getEntityManager().close();
         throw SocleExceptionManager.getException();

      }
   } 
   
   

}
