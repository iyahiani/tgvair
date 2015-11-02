package com.avancial.test;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.avancial.app.jobs.JobExport;

public class JobTestMain {

   public static void main(String[] args) throws SchedulerException {

      SchedulerFactory sf = new StdSchedulerFactory();
      Scheduler sched = sf.getScheduler();
      // define the job and tie it to our HelloJob class
      JobDetail job = JobBuilder.newJob(JobExport.class).withIdentity("JOB", "JOB ").build();// JobImport // JobAdaptation // JobExport
      // Trigger the job to run on the next round minute
      Trigger trigger = TriggerBuilder.newTrigger().withIdentity("JOB ", "JOB ").withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * * * ?")).build();
            
      sched.start()                    ;
      sched.scheduleJob(job , trigger)  ; 
      try {
         Thread.sleep(600L);
      } catch (InterruptedException e) {
        
         e.printStackTrace();
      }
      // sched.shutdown(true);
   
   }
  
}


