package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.avancial.app.data.model.databean.TrainCatalogueToCompagnieDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

public class TrainCatalogueToCompagnieDAO extends AbstractDao {

   @SuppressWarnings("unchecked")
   @Override
   public List<TrainCatalogueToCompagnieDataBean> getAll() {
      String sql = "From TrainCatalogueToCompagnieDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();

   }

   public void save(TrainCatalogueToCompagnieDataBean bean) throws ASocleException {
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

   public void delete(TrainCatalogueToCompagnieDataBean bean) throws ASocleException {
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

   public void update(TrainCatalogueToCompagnieDataBean bean) throws ASocleException {
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

   public List<TrainCatalogueToCompagnieDataBean> getTrainToCompagnieByID(int idTrainCatalogue) {
      
      String sql = " FROM TrainCatalogueToCompagnieDataBean as t WHERE t.trainCatalogueDataBean.idTrainCatalogue = ?";
      Query requete = this.getEntityManager().createQuery(sql);
      requete.setParameter(1,idTrainCatalogue) ;
      
      return requete.getResultList() ;
      
   }
   public Session getSession() {
      return this.getEntityManager().unwrap(Session.class) ;
   }
}
