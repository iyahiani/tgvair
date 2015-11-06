/**
 * 
 */
package com.avancial.app.init;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.avancial.app.jobs.JobAdaptation;

/**
 * @author ismael.yahiani utilitaire de test des Jobs
 */
//@WebServlet(loadOnStartup = 1, urlPatterns = "/initAPP")
public class SocleInit extends HttpServlet {
   // Logger log = Logger.getLogger(SocleInit.class);
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
      System.out.println("********  Job Tests Lunch  *******************");
      System.out.println("**********************************************");
      // this.log.info("Quartz APP initialisé");
      try {
         this.quartzInit();
      } catch (SchedulerException e) {
         e.printStackTrace();
         // this.log.error("erreur d'initialisation de Quartz APP");
      }
   }
   
   /**
    *
    * @throws SchedulerException 
    */
   
   private void quartzInit() throws SchedulerException {
      
      JobDetail job = JobBuilder.newJob(JobAdaptation.class).withIdentity("JobAdaptation", "group1").build()   ;
      Trigger trigger = TriggerBuilder.newTrigger().withIdentity("JobAdaptation", "group1")
            .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatHourlyForTotalCount(1)).build() ; 
      
      Scheduler scheduler = new StdSchedulerFactory().getScheduler()             ;
         scheduler.start()                                                       ;
         scheduler.scheduleJob(job, trigger)                                     ;       
   }
}
