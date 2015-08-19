package com.avancial.app.data.controller.dao;

import java.util.List;

import com.avancial.app.data.model.databean.CirculationDataBean;
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

   @Override
   public List<?> getAll() {

      return null;
   }

}
