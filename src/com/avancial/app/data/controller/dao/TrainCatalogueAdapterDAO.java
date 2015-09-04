package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.avancial.app.data.model.databean.TrainCatalogueAdapterDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

public class TrainCatalogueAdapterDAO extends AbstractDao{

   @Override
   public List<TrainCatalogueAdapterDataBean> getAll() {
      
      String sql = "From TrainCatalogueAdapterDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }
   public void save(TrainCatalogueAdapterDataBean bean) throws ASocleException {
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

   public List<TrainCatalogueAdapterDataBean> getTrainCatByID(int id) {
      String sql = " FROM TrainCatalogueAdapterDataBean as t WHERE t.idTrainCatalogueAdapter =" + id;
      Query requete = this.getEntityManager().createQuery(sql);

      return requete.getResultList();

   }

   public void delete(TrainCatalogueAdapterDataBean bean) throws ASocleException {
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

   public void update(TrainCatalogueAdapterDataBean bean) throws ASocleException {
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
   @Override
   public Session getSession() {
      
      return super.getSession();
   }
}
