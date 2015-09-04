package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.app.data.model.databean.CirculationDataBean;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

/**
 * 
 * @author ismael.yahiani cette Classe offre des fonctionnalités pour l'objet Circulation
 */
public class CirculationDao extends AbstractDao {

   private CirculationDataBean circulationDataBean;

   public void save(CirculationDataBean t) throws ASocleException {

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

   public void delete(CirculationDataBean bean) throws ASocleException {
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

   public void update(CirculationDataBean bean) throws ASocleException {
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
   public List<CirculationDataBean> getAll() {

      String sql = "From CirculationDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
    
   }

}
