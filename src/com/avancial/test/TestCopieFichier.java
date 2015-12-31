package com.avancial.test;

import java.io.File;
import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;

import com.avancial.app.model.managedbean.ParamGetterManagedBean;
import com.avancial.app.resources.constants.APP_TgvAir;
import com.avancial.app.resources.constants.APP_params;
import com.avancial.app.resources.utils.DeplacerFicher;
import com.avancial.socle.resources.constants.SOCLE_params;
import com.avancial.socle.utils.StringToDate;

public class TestCopieFichier {

   @Inject
   ParamGetterManagedBean paramGetter;

   @Test
   public void copierFichier() throws Exception {

      File source = new File(this.paramGetter.getParam(SOCLE_params.PARAM_DIRECTORIES.getValue(), APP_params.PARAMS_DIRECTORIES_SSIM.getValue()) + APP_TgvAir.SSIM_NOM_FICHIER.getConstante());
      File dest = new File(this.paramGetter.getParam(SOCLE_params.PARAM_DIRECTORIES.getValue(), APP_params.PARAMS_DIRECTORIES_SSIM_ARCHIVE.getValue()) + "archiveSSIM" + StringToDate.toStringByFormat(new Date(), "dateSansSeparateurs") + ".txt");

      DeplacerFicher.copierFile(source, dest);
      if (source.delete())
         System.out.println("true");
      else
         System.out.println("false");
   }
}
