package com.avancial.app.model.managedbean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.avancial.app.jobs.JobAdaptation;
import com.avancial.app.jobs.JobImport;
import com.avancial.socle.model.managedbean.AManageBean;

@Named("admin")
@ViewScoped
public class AdminManagedBean extends AManageBean {

   /**
    * 
    */
   private static final long serialVersionUID = 1L; 
   
   
   public String lancerJob() {
      
      SchedulerFactory sf = new StdSchedulerFactory();
      Scheduler sched;
      
      // define the job and tie it to our HelloJob class
      JobDetail job = JobBuilder.newJob(JobImport.class).withIdentity("JOBManuel", "JOBManuel ").build();// JobImport // JobAdaptation // JobExport
      // Trigger the job to run on the next round minute
      Trigger trigger = TriggerBuilder.newTrigger().withIdentity("JOBManuel", "JOBManuel ").startNow().build();
      
      JobDetail jobAd = JobBuilder.newJob(JobAdaptation.class).withIdentity("JOBadapManuel", "JOBadapManuel ").build();// JobImport // JobAdaptation // JobExport
      // Trigger the job to run on the next round minute
      Trigger triggerAd = TriggerBuilder.newTrigger().withIdentity("JOBadapManuel", "JOBadapManuel ").startNow().build();
            
      try {
         sched = sf.getScheduler();
         sched.start()                     ;
         sched.scheduleJob(job , trigger)  ;
         while(sched.isShutdown()) {
            sched.scheduleJob(jobAd , triggerAd)  ;   
         }
         
      } catch (SchedulerException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
    /* try {
      System.out.println(sched.getCurrentlyExecutingJobs().get(0).getJobDetail());
   } catch (SchedulerException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
   }*/
    return null ;
   }

}
