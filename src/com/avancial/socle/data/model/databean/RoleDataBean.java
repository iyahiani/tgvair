package com.avancial.socle.data.model.databean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the socle_user_role database table.
 * 
 */
@Entity
@Table(name = "socle_role")
public class RoleDataBean implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long              idRole;
   private String            labelRole;
   private String            technicalNameRole;

   public RoleDataBean() {
   }

   public Long getIdRole() {
      return this.idRole;
   }

   public void setIdRole(Long idRole) {
      this.idRole = idRole;
   }

   public String getLabelRole() {
      return this.labelRole;
   }

   public void setLabelRole(String labelRole) {
      this.labelRole = labelRole;
   }

   public String getTechnicalNameRole() {
      return this.technicalNameRole;
   }

   public void setTechnicalNameRole(String technicalNameRole) {
      this.technicalNameRole = technicalNameRole;
   }

}