package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

public class PointArretDAO extends AbstractDao{

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
         throw SocleExceptionManager.getException();
      }

   }
   }


