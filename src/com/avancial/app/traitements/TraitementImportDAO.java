package com.avancial.app.traitements;

import java.util.List;

import javax.persistence.Query;

import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

public class TraitementImportDAO extends AbstractDao {

   public void save(TraitementsImportDataBean bean) throws ASocleException {
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

   @Override
   public List<TraitementsImportDataBean> getAll() {
      
      String sql = "From TraitementsImportDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }
   public List<TraitementsImportDataBean> getLastID(){
      
      String sql = "from TraitementsImportDataBean order by idTraitementImport DESC";
      Query requete = this.getEntityManager().createQuery(sql).setMaxResults(1);
      return requete.getResultList();
   }
}
