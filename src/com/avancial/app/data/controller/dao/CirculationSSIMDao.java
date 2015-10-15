package com.avancial.app.data.controller.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;

import com.avancial.app.data.model.databean.CirculationSSIMDataBean;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;
import com.mysql.jdbc.PreparedStatement;

/**
 * 
 * @author ismael.yahiani 
 * DAO Import SSIM  
 *         
 */
public class CirculationSSIMDao extends AbstractDao {
   
   org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CirculationSSIMDao.class) ;
   
   private CirculationSSIMDataBean circulationSSIMDataBean;
    
   public void save(CirculationSSIMDataBean t) throws ASocleException {

      if (null != t)
         try {
            this.getEntityManager().getTransaction().begin();
            this.getEntityManager().persist(t);
            this.getEntityManager().flush();
            this.getEntityManager().getTransaction().commit();
         } catch (Exception e) {
            
            this.getEntityManager().getTransaction().rollback();
            this.getEntityManager().close();
            log.error("erreur Sauvegarde SSIM" + e.getMessage());
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
         this.getEntityManager().close();
         log.error("erreur Suppression SSIM" + e.getMessage());
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
         this.getEntityManager().close(); 
         log.error("erreur Mise à jour SSIM" + e.getMessage());
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
      this.getSession().createSQLQuery("DELETE FROM tgvair_import_SSIM where idCirculationSSIMtgvair <> :id").setParameter("id", id).executeUpdate();
   }

   public void saveSSIM(CirculationSSIMDataBean c) {
      try {
         this.save(c);
      } catch (ASocleException e) {

         e.printStackTrace();
      }
   }

   public void saveModeBatch(CirculationSSIMDataBean t) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(t);
         
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw SocleExceptionManager.getException();
      }
   }

   public void closeEntity() throws ASocleException {
      if (this.getEntityManager().isOpen()) {
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
         this.getEntityManager().close();
      }
   } 
    
   
   
   public void customSAave  (CirculationSSIMDataBean bean) throws SQLException {
      
      SessionFactory factory = (SessionFactory) this.getEntityManager().getEntityManagerFactory() ;
      
      String sql = "INSERT INTO CirculationSSIMDataBean (numeroTrain, originePointArret, destinationPointArret, GMTDepart,GMTArriver,dateDebutCirculation,dateFinCirculation,joursCirculation,rangTroncon,trancheFacultatif,restrictionTrafic,heureArriverCirculation,heureDepartCirculation) (?,?,?,?,?,?,?,?,?,?,?,?,?)"
          ; 
       Query query = this.getEntityManager().createQuery(sql) ;
       
       //this.getSession().beginTransaction(); 
       query.setParameter(1,bean.getNumeroTrain());
       query.setParameter(2,bean.getOriginePointArret());
       query.setParameter(3,bean.getDestinationPointArret());
       
       query.setParameter(4,bean.getGMTArriver());
       query.setParameter(5,bean.getGMTDepart());
       query.setParameter(6,bean.getDateDebutCirculation());
       query.setParameter(7,bean.getDateFinCirculation());
       query.setParameter(8,bean.getJoursCirculation());
       query.setParameter(9,bean.getRangTroncon());
       query.setParameter(10,bean.getTrancheFacultatif());
       query.setParameter(11,bean.getRestrictionTrafic());
       query.setParameter(12,bean.getHeureArriverCirculation());
       query.setParameter(13,bean.getHeureDepartCirculation());
       query.executeUpdate();
       //this.getSession().getTransaction().commit(); 
       // this.getSession().close() ;    
   }
}
