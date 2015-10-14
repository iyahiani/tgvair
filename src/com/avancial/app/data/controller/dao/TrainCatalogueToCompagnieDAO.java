package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueToCompagnieDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

public class TrainCatalogueToCompagnieDAO extends AbstractDao {

   Logger log = Logger.getLogger(TrainCatalogueToCompagnieDAO.class) ;
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
         this.getEntityManager().close();
         log.info("sauvegarde TrainCatalogue To Compagnie Echouée");
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
         this.getEntityManager().close(); 
         log.error("erreur lors de la suppression train2Compagnie"+e.getMessage());
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
         this.getEntityManager().close(); 
         log.error("erreur lors de la mise à jour train2Compagnie"+e.getMessage());
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
   
   public TrainCatalogueToCompagnieDataBean getTrainCatalogue2CByID(int id) {
      Criteria criteria = this.getSession().createCriteria(TrainCatalogueToCompagnieDataBean.class) ;  
       List<TrainCatalogueToCompagnieDataBean> list = criteria.add(Restrictions.eqOrIsNull("idTrainCatalogueToCompagnie", id)).list() ;
      return list.get(0);
   } 
   
   /**
    * 
    * @param codeCompagnie
    * @param id
    * @return touts les trains de la compagnie "codeCompagnie" exepter le train "id" 
    */
   public List<TrainCatalogueToCompagnieDataBean> getListTrains2Compagnie(String codeCompagnie  ){
      String sql = " FROM TrainCatalogueToCompagnieDataBean as t WHERE t.compagnieAerienneDataBean.CodeCompagnieAerienne = ? ";
      Query requete = this.getEntityManager().createQuery(sql);
      requete.setParameter(1,codeCompagnie) ;
      return requete.getResultList() ;
   }

   public List<TrainCatalogueToCompagnieDataBean> getTrainCatalogueByIdCompagnie(int idCompagnieAeriennne) {
      String sql = " FROM TrainCatalogueToCompagnieDataBean as t WHERE t.compagnieAerienneDataBean.idCompagnieAeriennne = ? ";
      Query requete = this.getEntityManager().createQuery(sql);
      requete.setParameter(1,idCompagnieAeriennne) ; 
      return requete.getResultList() ;
      
   }
}
