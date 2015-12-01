package com.avancial.test;

import java.io.File;
import java.util.Date;

import org.junit.Test;

import com.avancial.app.resources.constants.APP_TgvAir;
import com.avancial.app.resources.utils.DeplacerFicher;
import com.avancial.app.resources.utils.StringToDate;

public class TestCopieFichier {


   @Test 
   
   public void copierFichier() throws Exception {
      
      File source= new File(APP_TgvAir.CHEMIN_SSIM.toString()) ;   
      File dest= new File(APP_TgvAir.CHEMIN_SSIMARCHIVE_REC.toString()+"archiveSSIM"+ StringToDate.toStringByFormat(new Date(),"dateSansSeparateurs")+".txt") ; 
      
      DeplacerFicher.copierFile(source, dest); 
     if ( source.delete() ) System.out.println("true"); 
     else System.out.println("false");
   }
}
