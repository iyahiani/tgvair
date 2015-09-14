/**
 * 
 */
package com.avancial.socle.model.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.avancial.socle.business.JobPlanifBean;
import com.avancial.socle.data.controller.dao.JobDao;
import com.avancial.socle.data.controller.dao.JobPlanifDao;
import com.avancial.socle.data.controller.dao.JobPlanifTypeDao;
import com.avancial.socle.data.model.databean.JobDataBean;
import com.avancial.socle.data.model.databean.JobPlanifTypeDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;
import com.avancial.socle.jobs.JobTest;
import com.avancial.socle.resources.MessageController;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * @author bruno.legloahec
 *
 */
@Named("jobPlanifManagedBean")
@ViewScoped
public class JobPlanifManagedBean extends AManageBean {
   /**
    * 
    */
   private static final long   serialVersionUID = 1L;
   private List<JobPlanifBean> selectedItems;
   @Inject
   private JobPlanifBean       selectedItem;

   private String              libelle;
   private String              nomTechnique;
   private String              annee;
   private String              heures;
   private String              jourMois;
   private String              jourSemaine;
   private String              minutes;
   private String              mois;
   private String              secondes;

   private List<SelectItem>    listePlanifType;
   private String              jobTypeSelected;

   private List<SelectItem>    listeJob;
   private String              jobSelected;

   /**
    * Constructeur
    */
   public JobPlanifManagedBean() {
      this.selectedItems = new ArrayList<>();
      this.listePlanifType = new ArrayList<>();
      this.listeJob = new ArrayList<>();

      JobPlanifTypeDao dao = new JobPlanifTypeDao();

      for (JobPlanifTypeDataBean bean : dao.getAll()) {
         SelectItem item = new SelectItem(bean.getIdJobPlanifType(), bean.getLibelleJobPlanifType());
         this.listePlanifType.add(item);
      }

      JobDao jobDao = new JobDao();

      for (JobDataBean bean : jobDao.getAll()) {
         SelectItem item = new SelectItem(bean.getIdJob(), bean.getLibelleJob());
         this.listeJob.add(item);
      }

      this.reload();

   }

   public void reload() {
      this.selectedItems.clear();
      this.selectedItems.addAll(JobPlanifBean.getAll());
   }

   public void initProperties() {
      this.libelle = "";
   }

   /**
    * @return
    * @throws ASocleException
    * @throws SchedulerException
    */
   @Override
   public String add() throws ASocleException {
      super.add();

      JobPlanifBean bean = new JobPlanifBean();

      bean.setLibelleJobPlanif(this.libelle);
      bean.setAnneeJobPlanif(this.annee);
      bean.setHeuresJobPlanif(this.heures);
      bean.setJourMoisJobPlanif(this.jourMois);
      bean.setJourSemaineJobPlanif(this.jourSemaine);
      bean.setLibelleJobPlanif(this.libelle);
      bean.setMinutesJobPlanif(this.minutes);
      bean.setMoisJobPlanif(this.mois);
      bean.setNomTechniqueJobPlanif(this.nomTechnique);
      bean.setSecondesJobPlanif(this.secondes);

      try {
         bean.save(Long.valueOf(this.jobSelected), Long.valueOf(this.jobTypeSelected));
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "", MessageController.getTraduction("p_message_add_ok")));
         RequestContext.getCurrentInstance().update(":dataTable");
         this.closeDialog = true;

         SchedulerFactory sf = new StdSchedulerFactory();

         try {
            Scheduler sched = sf.getScheduler();
            JobDetail job = JobBuilder.newJob(JobTest.class).withIdentity(bean.getLibelleJobPlanif(), "group1").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(bean.getLibelleJobPlanif(), "group1").withSchedule(CronScheduleBuilder.cronSchedule(bean.getCron())).build();
            sched.scheduleJob(job, trigger);

         } catch (SchedulerException e) {
            e.printStackTrace();
         }

      } catch (ASocleException e) {
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_ADD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getClientMessage()));
         e.printStackTrace();
      }

      return null;
   }

   @Override
   public String update() throws ASocleException {
      super.update();
      if (null != this.selectedItem) {
         JobPlanifDao dao = new JobPlanifDao();
         try {
            dao.update(this.selectedItem.getJobPlanif());

            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "", MessageController.getTraduction("p_message_update_ok")));
            this.closeDialog = true;
            // RequestContext.getCurrentInstance().update(":dataTable");
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sched = sf.getScheduler();
            Trigger oldTrigger = sched.getTrigger(TriggerKey.triggerKey(this.selectedItem.getLibelleJobPlanif(), "group1"));
            // obtain a builder that would produce the trigger
            TriggerBuilder tb = oldTrigger.getTriggerBuilder();
            // update the schedule associated with the builder, and build the
            // new trigger
            // (other builder methods could be called, to change the trigger in
            // any
            // desired way)
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(this.selectedItem.getLibelleJobPlanif(), "group1").withSchedule(CronScheduleBuilder.cronSchedule(this.selectedItem.getCron())).build();
            sched.rescheduleJob(oldTrigger.getKey(), trigger);

         } catch (ASocleException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_UPD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getClientMessage()));
         } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return null;
   }

   @Override
   public String delete() throws ASocleException {
      super.delete();
      if (null != this.selectedItem) {
         JobPlanifDao dao = new JobPlanifDao();
         try {
            dao.delete(this.selectedItem.getJobPlanif());
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sched = sf.getScheduler();
            sched.deleteJob(JobKey.jobKey(this.selectedItem.getLibelleJobPlanif(), "group1"));
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "", MessageController.getTraduction("p_message_delete_ok")));
            this.closeDialog = true;
         } catch (ASocleException e) {
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_DEL_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MessageController.getTraduction("p_message_delete_ko")));
         } catch (SchedulerException e) {
            e.printStackTrace();
            SocleExceptionManager manager = new SocleExceptionManager(e);
            throw manager.getException();

         }
      }
      return null;
   }

   public Boolean getCloseDialog() {
      return this.closeDialog;
   }

   public void setCloseDialog(Boolean closeDialog) {
      this.closeDialog = closeDialog;
   }

   public List<JobPlanifBean> getSelectedItems() {
      return this.selectedItems;
   }

   public void setSelectedItems(List<JobPlanifBean> selectedItems) {
      this.selectedItems = selectedItems;
   }

   public JobPlanifBean getSelectedItem() {
      return this.selectedItem;
   }

   public void setSelectedItem(JobPlanifBean selectedItem) {
      this.selectedItem = selectedItem;
   }

   public String getLibelle() {
      return this.libelle;
   }

   public void setLibelle(String libelle) {
      this.libelle = libelle;
   }

   public String getNomTechnique() {
      return this.nomTechnique;
   }

   public void setNomTechnique(String nomTechnique) {
      this.nomTechnique = nomTechnique;
   }

   public String getAnnee() {
      return this.annee;
   }

   public void setAnnee(String annee) {
      this.annee = annee;
   }

   public String getHeures() {
      return this.heures;
   }

   public void setHeures(String heures) {
      this.heures = heures;
   }

   public String getJourMois() {
      return this.jourMois;
   }

   public void setJourMois(String jourMois) {
      this.jourMois = jourMois;
   }

   public String getJourSemaine() {
      return this.jourSemaine;
   }

   public void setJourSemaine(String jourSemaine) {
      this.jourSemaine = jourSemaine;
   }

   public String getMinutes() {
      return this.minutes;
   }

   public void setMinutes(String minutes) {
      this.minutes = minutes;
   }

   public String getMois() {
      return this.mois;
   }

   public void setMois(String mois) {
      this.mois = mois;
   }

   public String getSecondes() {
      return this.secondes;
   }

   public void setSecondes(String secondes) {
      this.secondes = secondes;
   }

   public String getJobTypeSelected() {
      return this.jobTypeSelected;
   }

   public void setJobTypeSelected(String jobTypeSelected) {
      this.jobTypeSelected = jobTypeSelected;
   }

   public List<SelectItem> getListePlanifType() {
      return this.listePlanifType;
   }

   public void setListePlanifType(List<SelectItem> listePlanifType) {
      this.listePlanifType = listePlanifType;
   }

   public List<SelectItem> getListeJob() {
      return this.listeJob;
   }

   public void setListeJob(List<SelectItem> listeJob) {
      this.listeJob = listeJob;
   }

   public String getJobSelected() {
      return this.jobSelected;
   }

   public void setJobSelected(String jobSelected) {
      this.jobSelected = jobSelected;
   }

}
