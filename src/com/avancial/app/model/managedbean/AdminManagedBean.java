package com.avancial.app.model.managedbean;

import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.avancial.app.data.controller.dao.CompagnieAerienneDao;
import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
import com.avancial.app.jobs.JobAdaptation;
import com.avancial.app.jobs.JobExport;
import com.avancial.app.jobs.JobExportByCompagnie;
import com.avancial.app.jobs.JobImport;
import com.avancial.app.traitements.AdminTraitements;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

@Named("admin")
@ViewScoped
public class AdminManagedBean extends AManageBean {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private boolean finish;
   private SchedulerFactory sf;
   private Scheduler sched;
   private List<CompagnieAerienneDataBean> listCompagnies = new CompagnieAerienneDao().getAllCodeCompagnie();
   private String compagnie ;
   Logger logger = Logger.getLogger(AdminManagedBean.class) ;
   
   public AdminManagedBean() { 
   //   System.out.println("AdminManagedBean.AdminManagedBean()"); 
   }
   /**
    * 
    * @return lancement Manuel de l'import SSIM 
    */
   public String lancerImport() {
      
      this.sf = new StdSchedulerFactory()                                                               ;
      Calendar calendarEndAdapt = Calendar.getInstance()                                                ;
      calendarEndAdapt.add(Calendar.MINUTE, 10)                                                         ;
     
      this.logger.info("SUCCES Import SSIM");      
      AdminTraitements adminTraitements = new AdminTraitements() ;
     // adminTraitements.traitementImportSSIM();        
     adminTraitements.traitementAdaptation();
      
      return null;
   }

   public void valueChanged(ValueChangeEvent event) {
       
      this.compagnie =  (String) event.getNewValue(); 
   }
/**
 * lancement manuel de l'export SSIM 7 
 * 
 * @return
 */
   public String lancerExport() {
      AdminTraitements adminTraitements = new AdminTraitements() ;
      adminTraitements.traitementAdaptation(); 
      adminTraitements.traitementExport();
      return null;
   }
   
   public boolean isFinish() {
      return this.finish;
   }

   public void setFinish(boolean finish) {
      this.finish = finish;
   }

   

   public SchedulerFactory getSf() {
      return this.sf;
   }

   public void setSf(SchedulerFactory sf) {
      this.sf = sf;
   }

   public Scheduler getSched() {
      return this.sched;
   }

   public void setSched(Scheduler sched) {
      this.sched = sched;
   }

   public List<CompagnieAerienneDataBean> getListCompagnies() {
      return this.listCompagnies;
   }

   public void setListCompagnies(List<CompagnieAerienneDataBean> listCompagnies) {
      this.listCompagnies = listCompagnies;
   }

   public String getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(String compagnie) {
      this.compagnie = compagnie;
   }

  

}
