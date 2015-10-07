package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.avancial.app.data.model.databean.CirculationSSIMDataBean;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

/**
 * 
 * @author ismael.yahiani cette Classe offre des fonctionnalités pour l'objet Circulation
 */
public class CirculationSSIMDao extends AbstractDao {

   private CirculationSSIMDataBean circulationSSIMDataBean;

   public void save(CirculationSSIMDataBean t) throws ASocleException {

      if (null!= t) 
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

   public void delete(CirculationSSIMDataBean bean) throws ASocleException {
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

   public void update(CirculationSSIMDataBean bean) throws ASocleException {
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
   public List<CirculationSSIMDataBean> getAll() {

      String sql = "From CirculationSSIMDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
    
   }

   public void truncateTable(String tableName) {
    this.getSession().createSQLQuery("truncate table CirculationSSIMDataBean").executeUpdate();  
   }
   
   @Override
   public Session getSession() {
      
      return super.getSession();
   }
   public void deleteAll(int id) {
      this.getSession().createSQLQuery("DELETE FROM tgvair_import_SSIM where idCirculationSSIMtgvair <> :id").setParameter("id", id).executeUpdate() ;
   } 
   
   public void saveSSIM(CirculationSSIMDataBean c) {
      try {
         this.save(c);
      } catch (ASocleException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
}
