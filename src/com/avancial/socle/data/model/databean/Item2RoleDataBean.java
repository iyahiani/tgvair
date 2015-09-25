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
@Table(name = "socle_item2role")
public class Item2RoleDataBean implements Serializable{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   @Id
   protected Long         idItem2Role;
   @OneToOne
   @JoinColumn(name = "idRole")
   protected RoleDataBean roleDataBean;
   protected String       NameItem2Role;
   protected Boolean      isRendered;
   protected Boolean      isEditable;
   protected Boolean      isAddable;
   protected Boolean      isDeletable;

   public Long getIdItem2Role() {
      return this.idItem2Role;
   }

   public void setIdItem2Role(Long idItem2Role) {
      this.idItem2Role = idItem2Role;
   }

   public RoleDataBean getRoleDataBean() {
      return this.roleDataBean;
   }

   public void setRoleDataBean(RoleDataBean roleDataBean) {
      this.roleDataBean = roleDataBean;
   }

   public String getNameItem2Role() {
      return this.NameItem2Role;
   }

   public void setNameItem2Role(String nameItem2Role) {
      this.NameItem2Role = nameItem2Role;
   }

   public Boolean getIsRendered() {
      return this.isRendered;
   }

   public void setIsRendered(Boolean isRendered) {
      this.isRendered = isRendered;
   }

   public Boolean getIsEditable() {
      return this.isEditable;
   }

   public void setIsEditable(Boolean isEditable) {
      this.isEditable = isEditable;
   }

   public Boolean getIsAddable() {
      return this.isAddable;
   }

   public void setIsAddable(Boolean isAddable) {
      this.isAddable = isAddable;
   }

   public Boolean getIsDelitable() {
      return this.isDeletable;
   }

   public void setIsDelitable(Boolean isDelitable) {
      this.isDeletable = isDelitable;
   }

}