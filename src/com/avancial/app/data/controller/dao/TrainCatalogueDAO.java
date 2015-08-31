package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

public class TrainCatalogueDAO extends AbstractDao {

   @Override
   public List<TrainCatalogueDataBean> getAll() {
      String sql = "From TrainCatalogueDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
      
   }

   public List<TrainCatalogueDataBean> getAllTrainAndValid() {
      String sql = " From TrainCatalogueDataBean"; // select
                                                   // concat(numeroTrainCatalogue,'-',dateDebutValidite)
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }

   public void save(TrainCatalogueDataBean bean) throws ASocleException {
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

   public List<TrainCatalogueDataBean> getTrainCatByID(int id) {
     String sql = " FROM TrainCatalogueDataBean as t WHERE t.idTrainCatalogue ="+id ;
     Query requete = this.getEntityManager().createQuery(sql);
     
     return requete.getResultList() ;
   
   }

   public void delete(TrainCatalogueDataBean bean) throws ASocleException {
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

   public void update(TrainCatalogueDataBean bean) throws ASocleException {
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
   public Session getSession() {
      return this.getEntityManager().unwrap(Session.class) ;
   }
}
