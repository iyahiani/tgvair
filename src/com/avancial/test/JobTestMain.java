package com.avancial.test;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.avancial.app.jobs.JobAdaptation;

public class JobTestMain {

   public static void main(String[] args) throws SchedulerException {

      JobDetail job = JobBuilder.newJob(JobAdaptation.class).withIdentity("JobAdaptation", "group1").build();
      Trigger trigger = TriggerBuilder.newTrigger().withIdentity("JobAdaptation", "group1")
            .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatMinutelyForTotalCount(1)).build();
      Scheduler scheduler = new StdSchedulerFactory().getScheduler()             ;
     
         scheduler.start();
         scheduler.scheduleJob(job, trigger); 
   
   }
  
}


