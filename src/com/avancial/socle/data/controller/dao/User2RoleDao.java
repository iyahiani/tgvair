package com.avancial.socle.data.controller.dao;

import java.util.List;

import com.avancial.socle.data.model.databean.User2RoleDataBean;

/**
 * 
 * @author bruno.legloahec
 *
 */
public class User2RoleDao extends AbstractDao {
   @SuppressWarnings("unchecked")
   @Override
   public List<User2RoleDataBean> getAll() {
      return this.getEntityManager().createQuery("FROM User2RoleDataBean").getResultList();
   }

   public List<User2RoleDataBean> getUser2RoleByIdUser(long idUser) {
      return this.getEntityManager().createQuery("FROM User2RoleDataBean WHERE idUser = :idUser").setParameter("idUser", idUser).getResultList();
   }

}