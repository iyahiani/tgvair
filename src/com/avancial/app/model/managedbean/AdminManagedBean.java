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
   
   public String lancerImport() {
      
      this.sf = new StdSchedulerFactory()                                                               ;
      Calendar calendarEndAdapt = Calendar.getInstance()                                                ;
      calendarEndAdapt.add(Calendar.MINUTE, 10)                                                         ;
      JobDetail job = JobBuilder.newJob(JobImport.class).withIdentity("JOBManuel", "JOBManuel ").build();
      // Trigger the job to run on the next round minute
      SimpleTrigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule()).withIdentity("JOBManuel", "JOBManuel ").build();
        
      JobDetail jobAd = JobBuilder.newJob(JobAdaptation.class).withIdentity("JOBadapManuel", "JOBadapManuel ").build();
      // Trigger the job to run on the next round minute
      SimpleTrigger triggerAd =  TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule()).withIdentity("JOBadapManuel", "JOBadapManuel ").build();

      try {
         //this.sched.clear();   //this.sched.scheduleJob(jobAd, triggerAd);  
         this.sched = this.sf.getScheduler();
         this.sched.scheduleJob(job, trigger);
         this.sched.start()                  ;
         Thread.sleep(600L);
         this.sched.shutdown(true);
         this.sched = this.sf.getScheduler();
         this.sched.scheduleJob(jobAd, triggerAd)  ;
         this.sched.start()                        ;
         Thread.sleep(600L)                        ;
         this.sched.shutdown(true);
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "JOB", "SUCCES Import SSIM"));
         
      } catch (SchedulerException | InterruptedException e) {
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "JOB", "Echec Import SSIM"));
         e.printStackTrace();
      }

      return null;
   }

   public void valueChanged(ValueChangeEvent event) {
      //System.out.println(event.getNewValue());  
      this.compagnie =  (String) event.getNewValue(); 
   }

   public String lancerExport() {
      this.sf = new StdSchedulerFactory();
      JobDetail jobAdap = JobBuilder.newJob(JobAdaptation.class).withIdentity("JOBadapManuelExpo", "JOBadapManuelExpo").build();
      SimpleTrigger triggerAdap =  TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule()).withIdentity("JOBadapManuelExpo", "JOBadapManuelExpo").build(); 
      JobDetail jobexport = JobBuilder.newJob(JobExport.class).withIdentity("JobExportManuel", "JobExportManuel").build();
      SimpleTrigger triggerexport = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule()).withIdentity("JobExportManuel", "JobExportManuel ").build();
      JobDetail jobexportByCompagnie = JobBuilder.newJob(JobExportByCompagnie.class).withIdentity("JobExportManuelByCompagnie ", "JobExportManuelByCompagnie ").build();
      SimpleTrigger triggerexportByCompagnie  = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule()).withIdentity("JobExportManuelByCompagnie ", "JobExportManuelByCompagnie  ").build();
     
      try 
      {
         this.sched = this.sf.getScheduler();
         this.sched.scheduleJob(jobAdap, triggerAdap);
         this.sched.start();
         Thread.sleep(600L);
         this.sched.shutdown(true);
        // if(this.compagnie.equalsIgnoreCase("")) {    
         this.sched = this.sf.getScheduler();
         this.sched.scheduleJob(jobexport, triggerexport);
         this.sched.start();
         Thread.sleep(600L);
         this.sched.shutdown(true); 
       /*  }  else {
            this.sched = this.sf.getScheduler();
            this.sched.scheduleJob(jobexportByCompagnie, triggerexportByCompagnie);
            this.sched.start();
            Thread.sleep(600L);
            this.sched.shutdown(true);
      } */
      }
      catch (SchedulerException | InterruptedException e) {

         e.printStackTrace();
      }
    
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
