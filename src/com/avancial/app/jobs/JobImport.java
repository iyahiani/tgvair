package com.avancial.app.jobs;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.avancial.app.business.parser.APP_enumParserSSIM;
import com.avancial.app.business.parser.FilterEncodage;
import com.avancial.app.business.parser.FilterSSIMTypeEnr;
import com.avancial.app.business.parser.FiltreCatalogue;
import com.avancial.app.business.parser.FiltreSSIMCompagnieTrain;
import com.avancial.app.business.parser.FiltreTrancheOptionnel;
import com.avancial.app.business.reader.ReaderSSIM;
import com.avancial.app.data.controller.dao.CirculationSSIMDao;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.CirculationSSIMDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.model.managedbean.ParamGetterManagedBean;
import com.avancial.app.resources.constants.APP_TgvAir;
import com.avancial.app.resources.constants.APP_params;
import com.avancial.app.resources.utils.DeplacerFicher;
import com.avancial.app.resources.utils.GetPeriodeSSIM;
import com.avancial.app.resources.utils.GetTrainsNums;
import com.avancial.app.traitements.LancementTraitementsManuelle;
import com.avancial.app.traitements.TraitementImportDAO;
import com.avancial.app.traitements.TraitementsImportDataBean;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.socle.params.exception.ParamCollectionNotLoadedException;
import com.avancial.socle.params.exception.ParamNotFoundException;
import com.avancial.socle.resources.constants.SOCLE_params;
import com.avancial.socle.utils.StringToDate;

/**
 *
 * @author ismael.yahiani class d'import des fichiers SSIM
 */
public class JobImport implements Job {

   // @Inject
   ParamGetterManagedBean paramGetter;
   Logger                 logger       = Logger.getLogger(JobImport.class);
   Date                   dateCourante = new Date();

   public JobImport() throws Exception {
      this.paramGetter = new ParamGetterManagedBean();
   }

   @Override
   public void execute(JobExecutionContext context) throws JobExecutionException {
      // InsertWithJDBC insertWithJDBC = new InsertWithJDBC() ;

      TrainCatalogueDAO catalogueDAO = new TrainCatalogueDAO();
      List<TrainCatalogueDataBean> listTrainsCatalogue = catalogueDAO.getAll();
      List<String> listnums = new ArrayList<>();
      List<String> listnumsHashed = new ArrayList<>();
      this.logger.info("Import started");
      ReaderSSIM reader = null;
      try {

         reader = new ReaderSSIM(this.paramGetter.getParam(SOCLE_params.PARAM_DIRECTORIES.getValue(), APP_params.PARAMS_DIRECTORIES_SSIM.getValue()).getValue() + APP_TgvAir.SSIM_NOM_FICHIER);

      } catch (IOException | ParamNotFoundException | ParamCollectionNotLoadedException e1) {

         this.logger.error("Import:" + e1.getMessage());

         e1.printStackTrace();

      }

      if (reader != null) {
         for (TrainCatalogueDataBean tc : listTrainsCatalogue) {
            listnums.add(tc.getNumeroTrainCatalogue());
         }
         // /////////// RECUPERER LA LISTE DES NUMERO DE TRAINS

         for (String s : listnums) {
            listnumsHashed.addAll(GetTrainsNums.getTrainsNums(s));
         }
         // ////////////////////////////// RECHARGER LE TABLEAU DES NUMERO AVEC LA
         // LIST

         String[] num = new String[listnumsHashed.size()];
         for (int i = 0; i < num.length; i++) {
            num[i] = listnumsHashed.get(i);

         }
         IParser par = new ParserFixedLength(new FilterEncodage(new FiltreTrancheOptionnel(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(new FiltreCatalogue(null, num))))), APP_enumParserSSIM.getNames(), APP_enumParserSSIM.getBegins(), APP_enumParserSSIM.getEnds());
         String chaine = "";
         CirculationSSIMDao dao = new CirculationSSIMDao();
         List<CirculationSSIMDataBean> list = dao.getAll();

         // supprimer le dernier Import

         if (dao.getAll().size() > 0 && reader != null)
            dao.deleteAll(0);

         List<CirculationSSIMDataBean> circulationSSIMDataBeans = new ArrayList<>();

         try {

            while ((chaine = reader.readLine()) != null) {
               par.parse(chaine);
               if (!par.getParsedResult().isEmpty()) {
                  CirculationSSIMDataBean circulation = new CirculationSSIMDataBean();
                  circulation.setOriginePointArret(par.getParsedResult().get(APP_enumParserSSIM.POSITION_GARE_DEPART.name()));
                  circulation.setDestinationPointArret(par.getParsedResult().get(APP_enumParserSSIM.POSITION_GARE_ARRIVER.name()));
                  circulation.setHeureArriverCirculation(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_ARRIVER.name()));
                  circulation.setHeureDepartCirculation(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_DEPART.name()));
                  circulation.setJoursCirculation(par.getParsedResult().get(APP_enumParserSSIM.POSITION_JOURS_CIRCULATION.name()));
                  circulation.setGMTDepart(par.getParsedResult().get(APP_enumParserSSIM.POSITION_DIFFERENCE_GMT_DEPART.name()));
                  circulation.setGMTArriver(par.getParsedResult().get(APP_enumParserSSIM.POSITION_DIFFERENCE_GMT_ARRIVER.name()));
                  try {
                     circulation.setDateDebutCirculation(StringToDate.toDate(par.getParsedResult().get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT.name())));
                     circulation.setDateFinCirculation(StringToDate.toDate(par.getParsedResult().get("POSITION_PERIODE_CIRCULATION_FIN")));
                  } catch (ParseException e) {
                     e.printStackTrace();
                     this.logger.error("Job Import" + e.getMessage());
                  }

                  circulation.setTrancheFacultatif(chaine.substring(APP_enumParserSSIM.POSITION_TRANCHE_FACULTATIF.getPositionDebut(), APP_enumParserSSIM.POSITION_TRANCHE_FACULTATIF.getPositionFin()));
                  circulation.setRestrictionTrafic(chaine.substring(APP_enumParserSSIM.POSITION_RESTRICTION_TRAFIC.getPositionDebut(), APP_enumParserSSIM.POSITION_RESTRICTION_TRAFIC.getPositionFin()));
                  circulation.setRangTroncon(Integer.valueOf(chaine.substring(APP_enumParserSSIM.POSITION_RANG_TRANCON.getPositionDebut(), APP_enumParserSSIM.POSITION_RANG_TRANCON.getPositionFin())));
                  circulation.setNumeroTrain(par.getParsedResult().get("POSITION_NUM_TRAIN"));
                  if (circulation != null) // dao.saveSSIM(circulation);

                     circulationSSIMDataBeans.add(circulation);
               }
            }
            dao.customSave(circulationSSIMDataBeans);
            reader.closeReader();
         } catch (Exception e) {
            this.logger.error("Job Import" + e.getMessage());
            e.printStackTrace();
         }

         TraitementsImportDataBean bean = new TraitementsImportDataBean();
         try {
            bean.setDateDebutSSIM(GetPeriodeSSIM.getSSIMPeriode(this.paramGetter.getParam(SOCLE_params.PARAM_DIRECTORIES.getValue(), APP_params.PARAMS_DIRECTORIES_SSIM.getValue()) + APP_TgvAir.SSIM_NOM_FICHIER.getConstante()).get("Date_Extraction"));
            bean.setDateFinSSIM(GetPeriodeSSIM.getSSIMPeriode(this.paramGetter.getParam(SOCLE_params.PARAM_DIRECTORIES.getValue(), APP_params.PARAMS_DIRECTORIES_SSIM.getValue()) + APP_TgvAir.SSIM_NOM_FICHIER.getConstante()).get("Date_Fin"));

         } catch (Exception e1) {

            e1.printStackTrace();
            this.logger.error("Job Import" + e1.getMessage());
         }

         TraitementImportDAO daoImport = new TraitementImportDAO();

         daoImport.saveTraitementSSIM(bean);

         this.logger.info("Import Terminé");

         try {
            archiveSSIM();
            this.logger.info("Archivage Terminé");
            new LancementTraitementsManuelle().getAdaptationManuel().traitementAdaptation();
         } catch (Exception e) {
            this.logger.error("erreur d'archivage du fichier SSIM");
            e.printStackTrace();
         }
      } else
         this.logger.warn("Fichier SSIM Introuvable");
   }

   /**
    * Archiver le Fichier SSIM dans \\reha\TGVAir_REC\Archives\
    * 
    * @throws Exception
    */

   private void archiveSSIM() {

      File dest;
      try {
         File source = new File(this.paramGetter.getParam(SOCLE_params.PARAM_DIRECTORIES.getValue(), APP_params.PARAMS_DIRECTORIES_SSIM.getValue()).getValue());
         dest = new File(this.paramGetter.getParam(SOCLE_params.PARAM_DIRECTORIES.getValue(), APP_params.PARAMS_DIRECTORIES_SSIM_ARCHIVE.getValue()) + "archiveSSIM" + StringToDate.toStringByFormat(new Date(), "dateSansSeparateurs") + ".txt");
         DeplacerFicher.copierFile(source, dest);
         source.delete();
      } catch (Exception e) {

         e.printStackTrace();
      }

   }

}
