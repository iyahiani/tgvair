package com.avancial.socle.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.socle.data.model.databean.Item2RoleDataBean;
import com.avancial.socle.data.model.databean.RoleDataBean;

/**
 * 
 * @author bruno.legloahec
 *
 */
public class Item2RoleDao extends AbstractDao {
   @SuppressWarnings("unchecked")
   @Override
   public List<Item2RoleDataBean> getAll() {
      this.getEntityManager().clear();
      Query query = this.getEntityManager().createQuery("FROM Item2RoleDataBean");
      return query.getResultList();
   }

   /**
    * @param roles
    * @return
    */
   public List<Item2RoleDataBean> getitemByListId(List<RoleDataBean> roles) throws Exception {
      this.getEntityManager().clear();
      Query query = this.getEntityManager().createQuery("FROM Item2RoleDataBean where idRole in (:roles) ");
      query.setParameter("roles", roles);
      return query.getResultList();
   }
}