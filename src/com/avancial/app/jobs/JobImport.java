package com.avancial.app.jobs;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
import com.avancial.app.data.controller.dao.CirculationDao;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.CirculationSSIMDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.resources.utils.GetPeriodeSSIM;
import com.avancial.app.resources.utils.GetTrainsNums;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.app.traitements.TraitementImportDAO;
import com.avancial.app.traitements.TraitementsImportDataBean;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;
import com.avancial.socle.exceptions.ASocleException;

/**
 *
 * @author ismael.yahiani class d'import des fichiers SSIM
 */
public class JobImport implements Job {

   Logger logger = Logger.getLogger(JobImport.class);

   @Override
   public void execute(JobExecutionContext context) throws JobExecutionException {

      TrainCatalogueDAO catalogueDAO = new TrainCatalogueDAO();
      List<TrainCatalogueDataBean> listTrainsCatalogue = catalogueDAO.getAll();
      List<String> listnums = new ArrayList<>();
      List<String> listnumsHashed = new ArrayList<>();

      IReader reader = null;
      try {
         reader = new ReaderSSIM("D:/5137.txt");
      } catch (IOException e1) {
         this.logger.error(e1.getMessage());
         e1.printStackTrace();
      }

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
      CirculationDao dao = new CirculationDao();
      dao.deleteAll();
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
               } catch (ParseException e) {
                  e.printStackTrace();
               }
               try {
                  circulation.setDateFinCirculation(StringToDate.toDate(par.getParsedResult().get("POSITION_PERIODE_CIRCULATION_FIN")));
               } catch (ParseException e) {
                  e.printStackTrace();
               }
               circulation.setTrancheFacultatif(chaine.substring(APP_enumParserSSIM.POSITION_TRANCHE_FACULTATIF.getPositionDebut(), APP_enumParserSSIM.POSITION_TRANCHE_FACULTATIF.getPositionFin()));
               circulation.setRestrictionTrafic(chaine.substring(APP_enumParserSSIM.POSITION_RESTRICTION_TRAFIC.getPositionDebut(), APP_enumParserSSIM.POSITION_RESTRICTION_TRAFIC.getPositionFin()));
               circulation.setRangTroncon(Integer.valueOf(chaine.substring(APP_enumParserSSIM.POSITION_RANG_TRANCON.getPositionDebut(), APP_enumParserSSIM.POSITION_RANG_TRANCON.getPositionFin())));

               circulation.setNumeroTrain(par.getParsedResult().get("POSITION_NUM_TRAIN"));

               try {

                  dao.save(circulation);
               } catch (ASocleException e) {
                  e.printStackTrace();
               }
            }
         }
      } catch (NumberFormatException e) {
         // logger.error(e.getMessage());
         e.printStackTrace();
      } catch (IOException e) {
         this.logger.error(e.getMessage());
         e.printStackTrace();
      }
      TraitementsImportDataBean bean = new TraitementsImportDataBean();
      try {
         bean.setDateDebutSSIM(GetPeriodeSSIM.getSSIMPeriode("D:/was_tmp/5137.txt").get("Date_Extraction"));
         bean.setDateFinSSIM(GetPeriodeSSIM.getSSIMPeriode("D:/was_tmp/5137.txt").get("Date_Fin"));
      } catch (Exception e1) {
         this.logger.error(e1.getMessage());
         e1.printStackTrace();

      }
      TraitementImportDAO daoImport = new TraitementImportDAO();

      try {
         daoImport.save(bean);
      } catch (ASocleException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
