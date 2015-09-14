package com.avancial.socle.data.model.databean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the socle_job_Planif database table.
 * 
 */
@Entity
@Table(name = "socle_job_planif")
public class JobPlanifDataBean implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private long idJobPlanif;

   @Column(nullable = false, length = 50)
   private String libelleJobPlanif;

   @Column(nullable = false, length = 35)
   private String nomTechniqueJobPlanif;

   @Column(length = 10)
   private String anneeJobPlanif;

   @Column(length = 10)
   private String heuresJobPlanif;

   @Column(length = 10)
   private String jourMoisJobPlanif;

   @Column(length = 10)
   private String jourSemaineJobPlanif;

   @Column(length = 10)
   private String minutesJobPlanif;

   @Column(length = 10)
   private String moisJobPlanif;

   @Column(length = 10)
   private String secondesJobPlanif;

   @OneToOne
   @JoinColumn(name = "idJobPlanifType", nullable = false)
   private JobPlanifTypeDataBean jobPlanifTypeDataBean;

   @OneToOne
   @JoinColumn(name = "idJob", nullable = false)
   private JobDataBean job;

   public JobPlanifDataBean() {
   }

   public long getIdJobPlanif() {
      return this.idJobPlanif;
   }

   public void setIdJobPlanif(long idJobPlanif) {
      this.idJobPlanif = idJobPlanif;
   }

   public String getLibelleJobPlanif() {
      return this.libelleJobPlanif;
   }

   public void setLibelleJobPlanif(String libelleJobPlanif) {
      this.libelleJobPlanif = libelleJobPlanif;
   }

   public String getNomTechniqueJobPlanif() {
      return this.nomTechniqueJobPlanif;
   }

   public void setNomTechniqueJobPlanif(String nomTechniqueJobPlanif) {
      this.nomTechniqueJobPlanif = nomTechniqueJobPlanif;
   }

   public String getAnneeJobPlanif() {
      return this.anneeJobPlanif;
   }

   public void setAnneeJobPlanif(String anneeJobPlanif) {
      this.anneeJobPlanif = anneeJobPlanif;
   }

   public String getHeuresJobPlanif() {
      return this.heuresJobPlanif;
   }

   public void setHeuresJobPlanif(String heuresJobPlanif) {
      this.heuresJobPlanif = heuresJobPlanif;
   }

   public String getJourMoisJobPlanif() {
      return this.jourMoisJobPlanif;
   }

   public void setJourMoisJobPlanif(String jourMoisJobPlanif) {
      this.jourMoisJobPlanif = jourMoisJobPlanif;
   }

   public String getJourSemaineJobPlanif() {
      return this.jourSemaineJobPlanif;
   }

   public void setJourSemaineJobPlanif(String jourSemaineJobPlanif) {
      this.jourSemaineJobPlanif = jourSemaineJobPlanif;
   }

   public String getMinutesJobPlanif() {
      return this.minutesJobPlanif;
   }

   public void setMinutesJobPlanif(String minutesJobPlanif) {
      this.minutesJobPlanif = minutesJobPlanif;
   }

   public String getMoisJobPlanif() {
      return this.moisJobPlanif;
   }

   public void setMoisJobPlanif(String moisJobPlanif) {
      this.moisJobPlanif = moisJobPlanif;
   }

   public String getSecondesJobPlanif() {
      return this.secondesJobPlanif;
   }

   public void setSecondesJobPlanif(String secondesJobPlanif) {
      this.secondesJobPlanif = secondesJobPlanif;
   }

   public JobDataBean getJob() {
      return this.job;
   }

   public void setJob(JobDataBean job) {
      this.job = job;
   }

   public JobPlanifTypeDataBean getJobPlanifTypeDataBean() {
      return this.jobPlanifTypeDataBean;
   }

   public void setJobPlanifTypeDataBean(JobPlanifTypeDataBean jobPlanifTypeDataBean) {
      this.jobPlanifTypeDataBean = jobPlanifTypeDataBean;
   }

}