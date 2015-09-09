package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

public class CirculationAdapterDAO extends AbstractDao {

   @Override
   public List<CirculationAdapterDataBean> getAll() {
      
      String sql = "From CirculationAdapterDataBean";

      Query requete = this.getEntityManager().createQuery(sql);
      
      return requete.getResultList();
   } 
   
   
   public void save(CirculationAdapterDataBean bean) throws ASocleException {
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

    public void delete(CirculationAdapterDataBean bean) throws ASocleException {
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

   public void update(CirculationAdapterDataBean bean) throws ASocleException {
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
  public List<CirculationAdapterDataBean> getDistinctCirculation() {
     
     String sql = "From CirculationAdapterDataBean c group by c.trainCatalogueDataBean.idTrainCatalogue ";

     Query requete = this.getEntityManager().createQuery(sql);
     
     return requete.getResultList();
     
  }
}
