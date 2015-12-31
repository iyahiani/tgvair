/**
 * 
 */
package com.avancial.socle.model.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.avancial.socle.business.JobPlanifBean;
import com.avancial.socle.data.controller.dao.JobDao;
import com.avancial.socle.data.controller.dao.JobPlanifDao;
import com.avancial.socle.data.controller.dao.JobPlanifTypeDao;
import com.avancial.socle.data.model.databean.JobDataBean;
import com.avancial.socle.data.model.databean.JobPlanifDataBean;
import com.avancial.socle.data.model.databean.JobPlanifTypeDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;
import com.avancial.socle.resources.MessageController;
import com.avancial.socle.resources.constants.SOCLE_constants;
import com.avancial.socle.utils.FormatCroneExpression;

/**
 * @author bruno.legloahec
 *
 */
@Named("jobPlanifManagedBean")
@ViewScoped
public class JobPlanifManagedBean extends AManageBean implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private List<JobPlanifBean> selectedItems;
 
 //  @Inject
   private JobPlanifBean selectedItem;

   private String libelle; 
   private String typeJob ;
   private String nomTechnique;
   private String annee;
   private String heures;
   private String jourMois;
   private String jourSemaine;
   private String minutes;
   private String mois;
   private String secondes;

   private List<SelectItem> listePlanifType;
   private String jobTypeSelected;

   private List<SelectItem> listeJob;
   private String jobSelected ;
   private boolean isCrone    ;
   private boolean isDaily    ;
   private boolean isNow      ; 
   
   private List<String> listSelectedJours;

   /**
    * Constructeur
    */

   public void valueChanged() { // ValueChangeEvent event

      if (!this.jobTypeSelected.equalsIgnoreCase("")) {
         if (Integer.valueOf(this.jobTypeSelected) == 1) {
            this.isCrone = true;
            this.isDaily = false;
            this.isNow = false;
         } else if (Integer.valueOf(this.jobTypeSelected) == 2) {
            this.isCrone = false;
            this.isDaily = true;
            this.isNow = false;
         } else if (Integer.valueOf(this.jobTypeSelected) == 3) {
            this.isCrone = false;
            this.isDaily = false;
            this.isNow = true;
         }
      } else {
         this.isCrone = true;
         this.isDaily = true;
         this.isNow = true;
      }
    
   }

   @PostConstruct
   public void init() {
      this.isCrone = false;
      this.isDaily = false;
      this.isNow = false;
   }

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
      JobPlanifBean bean = this.creerJobPlanifBean();
    
      //// Si le type du Job est de type NOW : on ne l'enregistre pas, on l'execute une fois  
      
      if(!bean.getJobPlanif().getJobPlanifTypeDataBean().getLibelleJobPlanifType().equalsIgnoreCase("now")) {
      try {
         bean.save(bean.getJob().getIdJob(), bean.getJobPlanif().getJobPlanifTypeDataBean().getIdJobPlanifType()
               );
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(),
               new FacesMessage(FacesMessage.SEVERITY_INFO, "", MessageController.getTraduction("p_message_add_ok")));
         RequestContext.getCurrentInstance().update(":dataTable");
         this.closeDialog = true; 
      }  catch (ASocleException e) {
         RequestContext.getCurrentInstance().
         showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
         e.getClientMessage();
         RequestContext.getCurrentInstance().addCallbackParam("notValid", true); 
            e.printStackTrace();
         }
      } 
      else {
         SchedulerFactory sf = new StdSchedulerFactory();
         try {
            Job newjob = (Job) Class.forName(bean.getJobPlanif().getJob().getClasseJob()).newInstance();
            Scheduler sched = sf.getScheduler();
            JobDetail job = JobBuilder.newJob(newjob.getClass()).withIdentity(bean.getLibelleJobPlanif(), "group1").build();
            
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(bean.getLibelleJobPlanif(), "group1").withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatHourlyForTotalCount(1)).build();
            sched.scheduleJob(job, trigger); 
            sched.start(); 
            Thread.sleep(600L); 
            sched.shutdown(true); 
          } catch (SchedulerException | InstantiationException | IllegalAccessException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
         }
      } 
      

      return null;
   }

    
   private JobPlanifBean creerJobPlanifBean() {
      JobPlanifBean bean = new JobPlanifBean() ;
      bean.setLibelleJobPlanif(this.libelle);
      bean.setMinutesJobPlanif(this.minutes != null ? this.minutes :"*" )             ;
      bean.setMoisJobPlanif(this.mois != null ? this.mois : "*")                   ;
      bean.setNomTechniqueJobPlanif(this.nomTechnique)   ;
      bean.setSecondesJobPlanif(this.secondes !=null ? this.secondes : "*")           ;
      bean.setHeuresJobPlanif(this.heures != null ? this.heures :"*")               ;
      bean.setJourMoisJobPlanif(this.jourMois != null ? this.jourMois : "*"); 
      bean.setJourSemaineJobPlanif(this.listSelectedJours.size()>0  
            ?FormatCroneExpression.setJoursSemaine(this.listSelectedJours): "?");
      bean.setAnneeJobPlanif(this.annee != null ? this.annee : "*"); 
      
      JobPlanifTypeDao planifDao = new JobPlanifTypeDao();
      JobDao jobDao = new JobDao()                       ;
      bean.getJobPlanif().setJobPlanifTypeDataBean(planifDao.getJobPlanifTypeById(Long.valueOf(this.jobTypeSelected)));
      bean.getJobPlanif().setJob(jobDao.getJobById(Long.valueOf(this.jobSelected)));
      return bean;
   }  
   
   private JobPlanifBean modifierJobPlanifBean() {
      JobPlanifBean bean = new JobPlanifBean() ;
      
      bean.setLibelleJobPlanif(this.selectedItem.getLibelleJobPlanif());
      bean.setMinutesJobPlanif(this.selectedItem.getMinutesJobPlanif())             ;
      bean.setMoisJobPlanif(this.selectedItem.getJourMoisJobPlanif())                   ;
      bean.setNomTechniqueJobPlanif(this.selectedItem.getNomTechniqueJobPlanif())   ;
      bean.setSecondesJobPlanif(this.selectedItem.getSecondesJobPlanif())           ;
      bean.setHeuresJobPlanif(this.selectedItem.getHeuresJobPlanif())               ;
      JobPlanifTypeDao planifDao = new JobPlanifTypeDao()                           ;
      JobDao jobDao = new JobDao()                                                  ;
      
      bean.getJobPlanif().setJobPlanifTypeDataBean(planifDao.getJobPlanifTypeById(Long.valueOf(this.jobTypeSelected)));
      bean.getJobPlanif().setJob(jobDao.getJobById(Long.valueOf(this.jobSelected)));
      return bean;
   }
   
   @Override
   public String update() throws ASocleException {
      super.update(); 
      if (null != this.jobTypeSelected) { 
      JobPlanifBean bean = this.modifierJobPlanifBean() ;
         JobPlanifDao dao = new JobPlanifDao(); 
         try {
            dao.update(bean.getJobPlanif());
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(),
                  new FacesMessage(FacesMessage.SEVERITY_INFO, "", MessageController.getTraduction("p_message_update_ok")));
            this.closeDialog = true                         ;
            SchedulerFactory sf = new StdSchedulerFactory() ;
            Scheduler sched = sf.getScheduler()             ;
            Trigger oldTrigger = sched.getTrigger(TriggerKey.triggerKey(this.selectedItem.getLibelleJobPlanif(), "group1"));
            TriggerBuilder tb = oldTrigger.getTriggerBuilder();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(this.selectedItem.getLibelleJobPlanif(), "group1").withSchedule(CronScheduleBuilder.cronSchedule(this.selectedItem.getCron()))
                  .build();
            sched.rescheduleJob(oldTrigger.getKey(), trigger);
         } catch (ASocleException | SchedulerException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_UPD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur Modification Job", e.getMessage()));
         } 
      }
      return null;
   } 
   
   public void rowSelect(SelectEvent event) {

      this.selectedItem = (JobPlanifBean) event.getObject();

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
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(),
                  new FacesMessage(FacesMessage.SEVERITY_INFO, "", MessageController.getTraduction("p_message_delete_ok")));
            this.closeDialog = true;
         } catch (ASocleException e) {
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_DEL_MESSAGES.toString(),
                  new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MessageController.getTraduction("p_message_delete_ko")));
         } catch (SchedulerException e) {
            e.printStackTrace();
            SocleExceptionManager manager = new SocleExceptionManager(e);
            throw manager.getException();

         }
      }
      return null;
   }

   ///////////////////////////////////////////   GETTERS AND SETTERS 
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

   public boolean isCrone() {
      return this.isCrone;
   }

   public void setCrone(boolean isCrone) {
      this.isCrone = isCrone;
   }

   public boolean isDaily() {
      return this.isDaily;
   }

   public void setDaily(boolean isDaily) {
      this.isDaily = isDaily;
   }

   public boolean isNow() {
      return this.isNow;
   }

   public void setNow(boolean isNow) {
      this.isNow = isNow;
   }

   public List<String> getListSelectedJours() {
      return this.listSelectedJours;
   }

   public void setListSelectedJours(List<String> listSelectedJours) {
      this.listSelectedJours = listSelectedJours;
   }

   public String getTypeJob() {
      return typeJob;
   }

   public void setTypeJob(String typeJob) {
      this.typeJob = typeJob;
   }

}
