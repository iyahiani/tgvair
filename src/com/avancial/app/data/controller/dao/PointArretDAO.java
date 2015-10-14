package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

public class PointArretDAO extends AbstractDao{
 
   Logger log = Logger.getLogger(PointArretDAO.class) ; 
   @Override
   public List<PointArretDataBean> getAll() {
     
      String sql = "From PointArretDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
      }
   
   public List<PointArretDataBean> getAllGDS() {
      
      String sql = "select libellePointArret From PointArretDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList(); 
   } 
   
   public List<PointArretDataBean> getPointArretbyName(String name) {
      String sql = "From PointArretDataBean as p WHERE p.libellePointArret =?"; 
      
      Query requete = this.getEntityManager().createQuery(sql); 
      requete.setParameter(1, name) ;
     
      return requete.getResultList(); 
   } 
   

   public void save(PointArretDataBean bean) throws ASocleException {
      try {

         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         this.getEntityManager().close(); 
         log.error("erreur de sauvegarde du point d'arret"+e.getMessage());
         @SuppressWarnings("unused")
         SocleExceptionManager manager = new SocleExceptionManager(e);
         throw SocleExceptionManager.getException();

      }
   }

    public void delete(PointArretDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().remove(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         this.getEntityManager().close();
         log.error("erreur de suppression du point d'arret"+e.getMessage());
         throw SocleExceptionManager.getException();
      }

   }

   public void update(PointArretDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         this.getEntityManager().close();
         log.error("erreur de mise à jour du point d'arret"+e.getMessage());
         throw SocleExceptionManager.getException();
      }

   } 
   
   public Session getSession() {
      return this.getEntityManager().unwrap(Session.class) ;
   }
   }


