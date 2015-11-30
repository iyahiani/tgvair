package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.avancial.app.data.model.databean.ServiceDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

public class ServiceDAO extends AbstractDao{

   Logger log  = Logger.getLogger(ServiceDAO.class) ;
   @Override
   
   
   public List<ServiceDataBean> getAll() { 
      
     
      String sql = "From ServiceDataBean"; 
      Query requete = this.getEntityManager().createQuery(sql); 
      return requete.getResultList();
   }

   public void save(ServiceDataBean bean) throws ASocleException {
      try {

         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit(); 
         
      } catch (Exception e) {
         log.info("Echec sauvegarde circulations adaptées");
         this.getEntityManager().getTransaction().rollback();
         @SuppressWarnings("unused")
         SocleExceptionManager manager = new SocleExceptionManager(e);
         throw SocleExceptionManager.getException();

      }
   }
   
   public void update(ServiceDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      
      } catch (Exception e) {
      
         this.getEntityManager().getTransaction().rollback();
         this.getEntityManager().close(); 
         this.log.info("Echec Mise à Jours Service"+e.getMessage());
         throw SocleExceptionManager.getException();
      }

   }
   
   
   
   public ServiceDataBean getLastService() {
      
      String sql = "from ServiceDataBean t order by t.datefinService_tgvAir DESC" ;
      Query requete = this.getEntityManager().createQuery(sql);
      return (ServiceDataBean) requete.getResultList().get(0); 
   } 
   
   
   
}
