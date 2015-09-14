/**
 * 
 */
package com.avancial.socle.jobs;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author bruno.legloahec
 *
 */
public class JobTest implements Job {

   /*
    * (non-Javadoc)
    * 
    * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
    */
   @Override
   public void execute(JobExecutionContext arg0) throws JobExecutionException {
      System.out.println("Job executed !!" + new Date());

   }

}
