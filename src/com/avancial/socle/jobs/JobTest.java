/**
 * 
 */
package com.avancial.socle.jobs;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.mysql.fabric.xmlrpc.base.Array;

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
      
      TrainCatalogueDAO dao = new TrainCatalogueDAO(); 
      List<TrainCatalogueDataBean> list = new ArrayList<>() ; 
      list.addAll(dao.getAll()) ;
      System.out.println(list.size())  ; //"Job executed !!"

   }

}
