package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;

public class PointArretDAO extends AbstractDao{

   @Override
   public List<PointArretDataBean> getAll() {
     
         return null;
      }
   
   public List<PointArretDataBean> getAllGDS() {
      
      String sql = "select libellePointArret From PointArretDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList(); 
   }
   }


