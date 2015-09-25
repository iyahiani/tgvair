/**
 * 
 */
package com.avancial.socle.init;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.avancial.socle.business.JobPlanifBean;
import com.avancial.socle.data.controller.dao.JobPlanifDao;
import com.avancial.socle.data.model.databean.JobPlanifDataBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * @author bruno.legloahec
 *
 */
@WebServlet(loadOnStartup = 1, urlPatterns = "/init")
public class SocleInit extends HttpServlet {
   public Scheduler          sched;

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
         FacesContext.getCurrentInstance().getExternalContext().redirect(SOCLE_constants.NAVIGATION_ACCUEIL.name());
      } catch (SchedulerException | IOException e) {
         e.printStackTrace();
      }
   }

   /**
    * @throws SchedulerException
    * 
    */
   private void quartzInit() throws SchedulerException {

      JobPlanifDao dao = new JobPlanifDao();
      List<JobPlanifDataBean> jobs = new ArrayList<>();
      jobs = dao.getAll();

      SchedulerFactory sf = new StdSchedulerFactory();
      this.sched = sf.getScheduler();

      for (JobPlanifDataBean jobPlanifDataBean : jobs) {
         Job newjob = null;
         JobPlanifBean bean = new JobPlanifBean(jobPlanifDataBean);
         try {

            newjob = (Job) Class.forName(jobPlanifDataBean.getJob().getClasseJob()).newInstance();
            Scheduler sched = sf.getScheduler();
            JobDetail job = JobBuilder.newJob(newjob.getClass()).withIdentity(bean.getLibelleJobPlanif(), "group1").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(bean.getLibelleJobPlanif(), "group1").withSchedule(CronScheduleBuilder.cronSchedule(bean.getCron())).build();
            sched.scheduleJob(job, trigger);

         } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
         }

      }

      // define the job and tie it to our HelloJob class

      // Trigger the job to run on the next round minute

      this.sched.start();
   }
}
