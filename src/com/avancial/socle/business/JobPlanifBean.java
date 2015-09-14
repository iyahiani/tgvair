/**
 * 
 */
package com.avancial.socle.business;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import com.avancial.socle.data.controller.dao.JobDao;
import com.avancial.socle.data.controller.dao.JobPlanifDao;
import com.avancial.socle.data.controller.dao.JobPlanifTypeDao;
import com.avancial.socle.data.model.databean.JobDataBean;
import com.avancial.socle.data.model.databean.JobPlanifDataBean;
import com.avancial.socle.exceptions.ASocleException;

/**
 * @author bruno.legloahec
 *
 */
public class JobPlanifBean {
   @Inject
   private JobPlanifDataBean jobPlanifDataBean;
   @Inject
   private JobDataBean       job;

   /**
    * ² Constructeur
    */
   public JobPlanifBean(JobPlanifDataBean jobPlanifDataBean) {
      this.jobPlanifDataBean = jobPlanifDataBean;
   }

   /**
    * Constructeur
    */
   public JobPlanifBean() {
      super();
      this.jobPlanifDataBean = new JobPlanifDataBean();
   }

   /**
    * @return
    */
   public static Collection<? extends JobPlanifBean> getAll() {
      ArrayList<JobPlanifBean> liste = new ArrayList<>();
      JobPlanifDao dao = new JobPlanifDao();
      for (JobPlanifDataBean bean : dao.getAll()) {
         JobPlanifBean job = new JobPlanifBean(bean);
         liste.add(job);
      }

      return liste;
   }

   /**
    * @throws ASocleException
    * 
    */
   public void save(long jobId, long jobPlanifTypeId) throws ASocleException {
      JobPlanifTypeDao planifDao = new JobPlanifTypeDao();
      JobDao jobDao = new JobDao();

      this.getJobPlanif().setJobPlanifTypeDataBean(planifDao.getJobPlanifTypeById(jobPlanifTypeId));
      this.getJobPlanif().setJob(jobDao.getJobById(jobId));

      JobPlanifDao dao = new JobPlanifDao();

      dao.save(this.jobPlanifDataBean);

   }

   /**
    * @throws ASocleException
    * 
    */
   public void update() throws ASocleException {
      JobPlanifDao dao = new JobPlanifDao();
      dao.update(this.jobPlanifDataBean);
   }

   /**
    * @throws ASocleException
    * 
    */
   public void delete() throws ASocleException {
      JobPlanifDao dao = new JobPlanifDao();
      dao.delete(this.jobPlanifDataBean);
   }

   public Boolean isExisteClasse(String classe) {

      try {
         Class.forName(classe);
         return true;
      } catch (ClassNotFoundException e) {
         return false;
      }

   }

   public String getCron() {
      StringBuilder sb = new StringBuilder();
      sb.append(this.getSecondesJobPlanif() + " ");
      sb.append(this.getMinutesJobPlanif() + " ");
      sb.append(this.getHeuresJobPlanif() + " ");
      sb.append(this.getJourSemaineJobPlanif() + " ");
      sb.append(this.getJourMoisJobPlanif() + " ");
      sb.append(this.getMoisJobPlanif() + " ");
      sb.append(this.getAnneeJobPlanif() + " ");

      return sb.toString();
   }

   public long getIdJobPlanif() {
      return this.jobPlanifDataBean.getIdJobPlanif();
   }

   public void setIdJobPlanif(long idjobPlanif) {
      this.jobPlanifDataBean.setIdJobPlanif(idjobPlanif);
   }

   public String getAnneeJobPlanif() {
      return this.jobPlanifDataBean.getAnneeJobPlanif();
   }

   public void setAnneeJobPlanif(String anneeJobPlanif) {
      this.jobPlanifDataBean.setAnneeJobPlanif(anneeJobPlanif);
   }

   public String getHeuresJobPlanif() {
      return this.jobPlanifDataBean.getHeuresJobPlanif();
   }

   public void setHeuresJobPlanif(String heuresJobPlanif) {
      this.jobPlanifDataBean.setHeuresJobPlanif(heuresJobPlanif);
   }

   public String getJourMoisJobPlanif() {
      return this.jobPlanifDataBean.getJourMoisJobPlanif();
   }

   public void setJourMoisJobPlanif(String jourMoisJobPlanif) {
      this.jobPlanifDataBean.setJourMoisJobPlanif(jourMoisJobPlanif);
   }

   public String getJourSemaineJobPlanif() {
      return this.jobPlanifDataBean.getJourSemaineJobPlanif();
   }

   public void setJourSemaineJobPlanif(String jourSemaineJobPlanif) {
      this.jobPlanifDataBean.setJourSemaineJobPlanif(jourSemaineJobPlanif);
   }

   public String getLibelleJobPlanif() {
      return this.jobPlanifDataBean.getLibelleJobPlanif();
   }

   public void setLibelleJobPlanif(String libelleJobPlanif) {
      this.jobPlanifDataBean.setLibelleJobPlanif(libelleJobPlanif);
   }

   public String getNomTechniqueJobPlanif() {
      return this.jobPlanifDataBean.getNomTechniqueJobPlanif();
   }

   public void setNomTechniqueJobPlanif(String NomTechniqueJobPlanif) {
      this.jobPlanifDataBean.setNomTechniqueJobPlanif(NomTechniqueJobPlanif);
   }

   public String getMinutesJobPlanif() {
      return this.jobPlanifDataBean.getMinutesJobPlanif();
   }

   public void setMinutesJobPlanif(String minutesJobPlanif) {
      this.jobPlanifDataBean.setMinutesJobPlanif(minutesJobPlanif);
   }

   public String getMoisJobPlanif() {
      return this.jobPlanifDataBean.getMoisJobPlanif();
   }

   public void setMoisJobPlanif(String moisJobPlanif) {
      this.jobPlanifDataBean.setMoisJobPlanif(moisJobPlanif);
   }

   public String getSecondesJobPlanif() {
      return this.jobPlanifDataBean.getSecondesJobPlanif();
   }

   public void setSecondesJobPlanif(String secondesJobPlanif) {
      this.jobPlanifDataBean.setSecondesJobPlanif(secondesJobPlanif);
   }

   public JobPlanifDataBean getJobPlanif() {
      return this.jobPlanifDataBean;
   }

   public void setJobPlanif(JobPlanifDataBean jobPlanifDataBean) {
      this.jobPlanifDataBean = jobPlanifDataBean;
   }

   /**
    * @return the job
    */
   public JobDataBean getJob() {
      return this.job;
   }

   /**
    * @param job
    *           the job to set
    */
   public void setJob(JobDataBean job) {
      this.job = job;
   }

}
