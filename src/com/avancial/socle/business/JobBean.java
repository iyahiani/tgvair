/**
 * 
 */
package com.avancial.socle.business;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import com.avancial.socle.data.controller.dao.JobDao;
import com.avancial.socle.data.model.databean.JobDataBean;
import com.avancial.socle.exceptions.ASocleException;

/**
 * @author bruno.legloahec
 *
 */
public class JobBean {
   @Inject
   private JobDataBean jobDataBean;

   /**
    * Constructeur
    */
   public JobBean(JobDataBean jobDataBean) {
      this.jobDataBean = jobDataBean;
   }

   /**
    * Constructeur
    */
   public JobBean() {
      super();
      this.jobDataBean = new JobDataBean();
   }

   /**
    * @return
    */
   public static Collection<? extends JobBean> getAll() {
      ArrayList<JobBean> liste = new ArrayList<>();
      JobDao dao = new JobDao();
      for (JobDataBean bean : dao.getAll()) {
         JobBean job = new JobBean(bean);
         liste.add(job);
      }

      return liste;
   }

   /**
    * @throws ASocleException
    * 
    */
   public void save() throws ASocleException {

      JobDao dao = new JobDao();
      dao.save(this.jobDataBean);

   }

   /**
    * @throws ASocleException
    * 
    */
   public void update() throws ASocleException {
      JobDao dao = new JobDao();
      dao.update(this.jobDataBean);
   }

   /**
    * @throws ASocleException
    * 
    */
   public void delete() throws ASocleException {
      JobDao dao = new JobDao();
      dao.delete(this.jobDataBean);
   }

   public Boolean isExisteClasse(String classe) {

      try {
         Class.forName(classe);
         return true;
      } catch (ClassNotFoundException e) {
         return false;
      }

   }

   public String getLibelleJob() {
      return this.jobDataBean.getLibelleJob();
   }

   public void setLibelleJob(String libelleJob) {
      this.jobDataBean.setLibelleJob(libelleJob);
   }

   public String getNomTechniqueJob() {
      return this.jobDataBean.getNomTechniqueJob();
   }

   public void setNomTechniqueJob(String nomTechniqueJob) {
      this.jobDataBean.setNomTechniqueJob(nomTechniqueJob);
   }

   public String getClasseJob() {
      return this.jobDataBean.getClasseJob();
   }

   public void setClasseJob(String classeJob) {
      this.jobDataBean.setClasseJob(classeJob);
   }

   public Long getIdJob() {
      return this.jobDataBean.getIdJob();
   }

   public void setIdJob(Long id) {
      this.jobDataBean.setIdJob(id);

   }

}
