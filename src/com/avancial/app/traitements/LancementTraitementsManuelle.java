package com.avancial.app.traitements;

import java.io.Serializable;

import org.apache.log4j.Logger;

/**
 * Methodes pour le lancement les Imports et les Exports SSIM/SSIM7 depuis la fenetre
 * d'administration
 * 
 * @author ismael.yahiani
 * 
 */
public class LancementTraitementsManuelle implements Serializable {

   Logger logger = Logger.getLogger(LancementTraitementsManuelle.class);
   private LancementImportManuel importManuel ; 
   private LancementAdaptationManuel adaptationManuel ; 
   private LancementExportManuel exportManuel ;
   
   
   public LancementTraitementsManuelle() {
       
      this.importManuel = new LancementImportManuel() ; 
      this.adaptationManuel = new LancementAdaptationManuel() ; 
      this.exportManuel = new LancementExportManuel() ;
   }
    
   
   public Logger getLogger() {
      return logger;
   }

   public void setLogger(Logger logger) {
      this.logger = logger;
   }

   public LancementImportManuel getImportManuel() {
      return importManuel;
   }

   public void setImportManuel(LancementImportManuel importManuel) {
      this.importManuel = importManuel;
   }

   public LancementAdaptationManuel getAdaptationManuel() {
      return adaptationManuel;
   }

   public void setAdaptationManuel(LancementAdaptationManuel adaptationManuel) {
      this.adaptationManuel = adaptationManuel;
   }

   public LancementExportManuel getExportManuel() {
      return exportManuel;
   }

   public void setExportManuel(LancementExportManuel exportManuel) {
      this.exportManuel = exportManuel;
   }

}
