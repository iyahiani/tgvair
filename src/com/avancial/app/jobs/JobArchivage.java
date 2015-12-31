package com.avancial.app.jobs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.avancial.app.model.managedbean.ParamGetterManagedBean;
import com.avancial.app.resources.constants.APP_params;
import com.avancial.socle.params.exception.ParamCollectionNotLoadedException;
import com.avancial.socle.params.exception.ParamNotFoundException;
import com.avancial.socle.resources.constants.SOCLE_params;

public class JobArchivage implements Job {

   Logger                 log = Logger.getLogger(JobArchivage.class);

   ParamGetterManagedBean paramGetter;

   public JobArchivage() throws Exception {
      this.paramGetter = new ParamGetterManagedBean();
   }

   @Override
   public void execute(JobExecutionContext context) throws JobExecutionException {
      log.info("Job Archivage Lancé");
      String path;
      try {
         path = this.paramGetter.getParam(SOCLE_params.PARAM_DIRECTORIES.getValue(), APP_params.PARAMS_DIRECTORIES_SSIM_ARCHIVE.getValue()).getValue();
      } catch (ParamNotFoundException | ParamCollectionNotLoadedException e) {
         this.log.error(e.getMessage());
         throw new JobExecutionException(e.getMessage());

      }
      File repertoir = new File(path);

      File[] listFichier = repertoir.listFiles();
      List<Date> list = new ArrayList<>();
      List<File> listFichi = Arrays.asList(listFichier);
      if (listFichier.length > 0) {
         for (File file : listFichier) {
            if (file.isFile())
               list.add(new Date(file.lastModified()));
         }

         Collections.sort(listFichi);

         for (int i = 0; i < listFichi.size() - 4; i++) {
            listFichi.get(i).delete();
         }

      }
   }

}
