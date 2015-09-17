package com.avancial.app.model.managedbean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.component.schedule.Schedule;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.CronCalendar;
import org.quartz.impl.matchers.KeyMatcher;

import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.data.controller.dao.CompagnieAerienneDao;
import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
import com.avancial.app.jobs.JobAdaptation;
import com.avancial.app.jobs.JobExport;
import com.avancial.app.jobs.JobImport;
import com.avancial.socle.model.managedbean.AManageBean;

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
   private CompagnieAerienneDataBean compagnie;

   public String lancerJob() {

      this.sf = new StdSchedulerFactory();

      Calendar calendarEndAdapt = Calendar.getInstance();
      calendarEndAdapt.add(Calendar.MINUTE, 10);

      JobDetail job = JobBuilder.newJob(JobImport.class).withIdentity("JOBManuel", "JOBManuel ").build();
      // Trigger the job to run on the next round minute
      SimpleTrigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule()).withIdentity("JOBManuel", "JOBManuel ").build();
        
      JobDetail jobAd = JobBuilder.newJob(JobAdaptation.class).withIdentity("JOBadapManuel", "JOBadapManuel ").build();
      // Trigger the job to run on the next round minute
      SimpleTrigger triggerAd =  TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule()).withIdentity("JOBadapManuel", "JOBadapManuel ").build();

      try {
         //this.sched.clear();   
         this.sched = this.sf.getScheduler();
         this.sched.scheduleJob(job, trigger);
         //this.sched.scheduleJob(jobAd, triggerAd);
         this.sched.start();
         Thread.sleep(600L);
         this.sched.shutdown(true);
         this.sched = this.sf.getScheduler();
         this.sched.scheduleJob(jobAd, triggerAd);
         this.sched.start();
         Thread.sleep(300L);
         this.sched.shutdown(true);
         
      } catch (SchedulerException | InterruptedException e) {

         e.printStackTrace();
      }

      return null;
   }

   public void valueChanged(ValueChangeEvent event) {
      this.compagnie = (CompagnieAerienneDataBean) event.getNewValue();
   }

   public String lancerExport() {
      this.sf = new StdSchedulerFactory();
      JobDetail job = JobBuilder.newJob(JobExport.class).withIdentity("JobExportManuel", "JobExportManuel").build();
      SimpleTrigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule()).withIdentity("JobExportManuel", "JobExportManuel ").build();
      try 
      {
         this.sched = this.sf.getScheduler();
         this.sched.start();
          
         this.sched.scheduleJob(job, trigger);
         this.sched.shutdown();    
      } catch (SchedulerException e) {

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

   public CompagnieAerienneDataBean getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieAerienneDataBean compagnie) {
      this.compagnie = compagnie;
   }

}
