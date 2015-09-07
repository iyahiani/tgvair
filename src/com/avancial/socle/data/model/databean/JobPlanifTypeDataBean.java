package com.avancial.socle.data.model.databean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the socle_job_type database table.
 * 
 */
@Entity
@Table(name = "socle_job_Planif_type")
// @NamedQuery(name="JobTypePlanifDataBean.findAll", query="SELECT j FROM JobTypeDataBean j")
public class JobPlanifTypeDataBean implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private long              idJobPlanifType;

   @Column(nullable = false, length = 35)
   private String            libelleJobPlanifType;

   @Column(nullable = false, length = 35)
   private String            nomTechniqueJobPlanifType;

   public JobPlanifTypeDataBean() {
   }

   public long getIdJobPlanifType() {
      return this.idJobPlanifType;
   }

   public void setIdJobPlanifType(long idJobPlanifType) {
      this.idJobPlanifType = idJobPlanifType;
   }

   public String getLibelleJobPlanifType() {
      return this.libelleJobPlanifType;
   }

   public void setLibelleJobPlanifType(String libelleJobPlanifType) {
      this.libelleJobPlanifType = libelleJobPlanifType;
   }

   public String getNomTechniqueJobPlanifType() {
      return this.nomTechniqueJobPlanifType;
   }

   public void setNomTechniqueJobPlanifType(String nomTechniqueJobPlanifType) {
      this.nomTechniqueJobPlanifType = nomTechniqueJobPlanifType;
   }

}