package com.avancial.app.jobs;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
import com.avancial.app.data.model.databean.CirculationDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.resources.utils.GetTrainsNums;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;
import com.avancial.socle.exceptions.ASocleException;

public class JobImport implements Job {

   public void execute(JobExecutionContext context) throws JobExecutionException {
      
      TrainCatalogueDAO catalogueDAO = new TrainCatalogueDAO();
      List<TrainCatalogueDataBean> listTrainsCatalogue = catalogueDAO.getAll();
      List<String> listnums = new ArrayList<>();
      List<String> listnumsHashed = new ArrayList<>();

      IReader reader = null;
      try {
         reader = new ReaderSSIM("D:/was_tmp/5137.txt");
      } catch (IOException e1) {

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
           IParser par = new ParserFixedLength(new FilterEncodage(new FiltreTrancheOptionnel(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(new FiltreCatalogue(null, num))))),
            APP_enumParserSSIM.getNames(), APP_enumParserSSIM.getBegins(), APP_enumParserSSIM.getEnds());

      String chaine="";
      
      try {
         while ((chaine = reader.readLine()) != null) {
           System.out.println(par.parse(chaine));
            par.parse(chaine);
            if (!par.getParsedResult().isEmpty()) {

               CirculationDataBean circulation = new CirculationDataBean();
               circulation.setOriginePointArret(par. getParsedResult().get(APP_enumParserSSIM.POSITION_GARE_DEPART.name()));
               circulation.setDestinationPointArret(par.getParsedResult().get(APP_enumParserSSIM.POSITION_GARE_ARRIVER.name()));
               circulation.setHeureArriverCirculation(Integer.valueOf(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_ARRIVER.name())));
               circulation.setHeureDepartCirculation(Integer.valueOf(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_DEPART.name())));
               circulation.setJoursCirculation(par.getParsedResult().get(APP_enumParserSSIM.POSITION_JOURS_CIRCULATION.name()));
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
               CirculationDao dao = new CirculationDao();
               try {
                  System.out.println(circulation);
                  dao.save(circulation);
               } catch (ASocleException e) {

                  e.printStackTrace();
               }

            }
         }
      } catch (NumberFormatException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

}
