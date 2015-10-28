package com.avancial.app.traitements;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.avancial.app.business.compagnieAerienne.IObservableJoursCirculation;
import com.avancial.app.business.compagnieAerienne.ObservableJoursCirculation;
import com.avancial.app.business.export.ExportPDTByCompagnyToSSIM7;
import com.avancial.app.business.parser.APP_enumParserSSIM;
import com.avancial.app.business.parser.FilterEncodage;
import com.avancial.app.business.parser.FilterSSIMTypeEnr;
import com.avancial.app.business.parser.FiltreCatalogue;
import com.avancial.app.business.parser.FiltreSSIMCompagnieTrain;
import com.avancial.app.business.parser.FiltreTrancheOptionnel;
import com.avancial.app.business.reader.ReaderSSIM;
import com.avancial.app.business.train.Service;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.business.train.TrainFactory;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.business.train.circulation.CirculationFactory;
import com.avancial.app.data.controller.dao.CirculationDAO;
import com.avancial.app.data.controller.dao.CirculationSSIMDao;
import com.avancial.app.data.controller.dao.CompagnieAerienneDao;
import com.avancial.app.data.controller.dao.PointArretDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueToCompagnieDAO;
import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.CirculationSSIMDataBean;
import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueToCompagnieDataBean;
import com.avancial.app.resources.connectionsUtils.InsertWithJDBC;
import com.avancial.app.resources.constants.APP_TgvAir;
import com.avancial.app.resources.utils.GetPeriodeSSIM;
import com.avancial.app.resources.utils.GetTrainsNums;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;
import com.avancial.socle.resources.constants.SOCLE_constants;

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
