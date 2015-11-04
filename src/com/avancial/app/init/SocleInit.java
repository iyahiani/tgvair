/**
 * 
 */
package com.avancial.app.init;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.avancial.app.jobs.JobAdaptation;
import com.avancial.app.jobs.JobExport;
import com.avancial.app.jobs.JobImport;

/**
 *@author ismael.yahiani
 *  utilitaire de test des Job 
 */
@WebServlet(loadOnStartup = 1, urlPatterns = "/initAPP")
public class SocleInit extends HttpServlet {
//   Logger                    log              = Logger.getLogger(SocleInit.class);
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /*
    * (non-Javadoc)
    * 
    * @see javax.servlet.GenericServlet#init()
    */
   @Override
   public void init() throws ServletException {
      super.init();
      System.out.println("**********************************************");
      System.out.println("********  Job Tests Lunch  ********");
      System.out.println("**********************************************"); 
    //  this.log.info("Quartz APP initialisé");
      try {
         this.quartzInit();
      } catch (SchedulerException e) {
         e.printStackTrace();
        // this.log.error("erreur d'initialisation de Quartz APP");
      }
   }
   
   /**
    * @throws SchedulerException
    * 
    */ 
   
   private void quartzInit() throws SchedulerException {
      JobDetail job = JobBuilder.newJob(JobAdaptation.class)
            .withIdentity("JobAdaptation", "group1").build(); 
      Trigger trigger  = TriggerBuilder.newTrigger().withIdentity("JobAdaptation", "group1").withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatMinutelyForTotalCount(2)).build(); 
      Scheduler scheduler = new StdSchedulerFactory().getScheduler();
      if(!scheduler.checkExists(job.getKey())) {
      scheduler.start();
      scheduler.scheduleJob(job, trigger);
      }
      
      
      
      List<? extends Trigger> triggers = scheduler.getTriggersOfJob(job.getKey());
      for (Trigger trig : triggers) {
          TriggerState triggerState = scheduler.getTriggerState(trig.getKey());
            System.out.println(triggerState.name()); 
      }
      
   } 
}
