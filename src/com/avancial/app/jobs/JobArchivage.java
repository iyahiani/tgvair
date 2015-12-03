package com.avancial.app.jobs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobArchivage implements Job {

   public void execute(JobExecutionContext context) throws JobExecutionException { 
      
      String path = "d:/testFiles" ; 
      File repertoir = new File(path); 
      File[] listFichier = repertoir.listFiles() ; 
      List<Date> list = new ArrayList<>() ;   
      List<File> listFichi= Arrays.asList(listFichier) ; 
      
      for (File file : listFichier) {
         if (file.isFile()) list.add(new Date(file.lastModified()));
      }
      
      Collections.sort(listFichi);
      
      for (int i = 0 ; i < listFichi.size()-4; i++) {
         listFichi.get(i).delete() ;
      }
   }

}
