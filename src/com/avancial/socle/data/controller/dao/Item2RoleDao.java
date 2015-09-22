package com.avancial.socle.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.socle.data.model.databean.Item2RoleDataBean;

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
   
   public List<Item2RoleDataBean> getItem2RoleByRoleId(Long idRole) {
   this.getEntityManager().clear();
   Query query = this.getEntityManager().createQuery("FROM Item2RoleDataBean where idRole=:role");
   query.setParameter("role", idRole);
   return query.getResultList();
}
   
   
}