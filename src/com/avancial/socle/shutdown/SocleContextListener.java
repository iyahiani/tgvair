/**
 * 
 */
package com.avancial.socle.shutdown;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author bruno.legloahec
 *
 */
@WebListener
public class SocleContextListener implements ServletContextListener {

   /*
    * (non-Javadoc)
    * 
    * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
    */
   @Override
   public void contextDestroyed(ServletContextEvent arg0) {
      this.shutdownQuartz();
   }

   /**
    * 
    */
   private void shutdownQuartz() {
      SchedulerFactory sf = new StdSchedulerFactory();
      try {
         System.out.println("Shutting down Quartz.....");
         Scheduler sched = sf.getScheduler();
         sched.shutdown(true);
      } catch (SchedulerException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

   /*
    * (non-Javadoc)
    * 
    * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
    */
   @Override
   public void contextInitialized(ServletContextEvent arg0) {
      
   }

}
