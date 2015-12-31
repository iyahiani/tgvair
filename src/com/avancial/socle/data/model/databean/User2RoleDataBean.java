package com.avancial.socle.data.model.databean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author bruno.legloahec
 *
 */
@Entity
@Table(name = "socle_user2role")
public class User2RoleDataBean implements Serializable{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   @Id
   protected Long         idUser2Role;
   protected Long         idUser;

   // @OneToOne
   // @JoinColumn(name = "idUser")
   // protected UserDataBean userDataBean;

   @OneToOne
   @JoinColumn(name = "idRole")
   protected RoleDataBean roleDataBean;

   public Long getIdUser2Role() {
      return this.idUser2Role;
   }

   public void setIdUser2Role(Long idUser2Role) {
      this.idUser2Role = idUser2Role;
   }

   // public UserDataBean getUserDataBean() {
   // return this.userDataBean;
   // }
   //
   // public void setUserDataBean(UserDataBean userDataBean) {
   // this.userDataBean = userDataBean;
   // }

   public RoleDataBean getRoleDataBean() {
      return this.roleDataBean;
   }

   public void setRoleDataBean(RoleDataBean roleDataBean) {
      this.roleDataBean = roleDataBean;
   }

   public Long getIdUser() {
      return this.idUser;
   }

   public void setIdUser(Long idUser) {
      this.idUser = idUser;
   }

}