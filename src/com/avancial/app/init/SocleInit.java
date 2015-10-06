/**
 * 
 */
package com.avancial.app.init;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.avancial.app.jobs.GlobalJob;
import com.avancial.app.jobs.JobAdaptation;
import com.avancial.app.jobs.JobExport;
import com.avancial.app.jobs.JobImport;

/**
 * @author bruno.legloahec
 *
 */
@WebServlet(loadOnStartup = 1, urlPatterns = "/initAPP")
public class SocleInit extends HttpServlet {
   Logger log  =Logger.getLogger(SocleInit.class) ;
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
      System.out.println("********  Application initialization  ********");
      System.out.println("**********************************************");
      try {
         this.quartzInit(); 
         log.info("quartz initialisť");

      } catch (SchedulerException e) {
         e.printStackTrace(); 
         log.error("erreur d'initialisation de Quartz");
      }
   }
   
   /**
    * @throws SchedulerException
    * 
    */
   
   private void quartzInit() throws SchedulerException {
      SchedulerFactory sf = new StdSchedulerFactory();
      Scheduler sched = sf.getScheduler();
      // define the job and tie it to our HelloJob class
      JobDetail job = JobBuilder.newJob(JobAdaptation.class).withIdentity("JOB", "JOB ").build();// JobImport // JobAdaptation // JobExport
      // Trigger the job to run on the next round minute
      Trigger trigger = TriggerBuilder.newTrigger().withIdentity("JOB ", "JOB ").withSchedule(CronScheduleBuilder.cronSchedule("* */1 * * * ?")).build();
            
      sched.start()                    ;
      sched.scheduleJob(job , trigger)  ;
      sched.shutdown(true);
      
   }
}
