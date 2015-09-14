package com.avancial.app.jobs;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.avancial.app.business.compagnieAerienne.IObservableJoursCirculation;
import com.avancial.app.business.compagnieAerienne.ObservableJoursCirculation;
import com.avancial.app.business.compagnieAerienne.TrainToCompagnie;
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
import com.avancial.app.data.controller.dao.CirculationDAO;
import com.avancial.app.data.controller.dao.CirculationSSIMDao;
import com.avancial.app.data.controller.dao.CompagnieAerienneDao;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueToCompagnieDAO;
import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.CirculationSSIMDataBean;
import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueToCompagnieDataBean;
import com.avancial.app.resources.utils.GetPeriodeSSIM;
import com.avancial.app.resources.utils.GetTrainsNums;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.app.traitements.TraitementExportDAO;
import com.avancial.app.traitements.TraitementExportDataBean;
import com.avancial.app.traitements.TraitementImportDAO;
import com.avancial.app.traitements.TraitementsImportDataBean;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;

public class GlobalJob implements Job {

   public void execute(JobExecutionContext context) throws JobExecutionException {

      TrainCatalogueDAO catalogueDAO = new TrainCatalogueDAO();
      List<TrainCatalogueDataBean> listTrainsCatalogue = catalogueDAO.getAll();
      List<String> listnums = new ArrayList<>();
      List<String> listnumsHashed = new ArrayList<>();

      IReader reader = null;
      try {
         reader = new ReaderSSIM("D:/was_tmp/7989.txt");
      } catch (IOException e1) {
         // this.logger.error(e1.getMessage());
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

      String chaine = "";
      CirculationSSIMDao dao = new CirculationSSIMDao();
      List<CirculationSSIMDataBean> list = dao.getAll();
      dao.deleteAll(0);
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
               dao.saveSSIM(circulation);
            }
         }
      } catch (NumberFormatException e) {
         // logger.error(e.getMessage());
         e.printStackTrace();
      } catch (IOException e) {
         // this.logger.error(e.getMessage());
         e.printStackTrace();
      }
      TraitementsImportDataBean bean = new TraitementsImportDataBean();
      try {
         bean.setDateDebutSSIM(GetPeriodeSSIM.getSSIMPeriode("D:/was_tmp/5137.txt").get("Date_Extraction"));
         bean.setDateFinSSIM(GetPeriodeSSIM.getSSIMPeriode("D:/was_tmp/5137.txt").get("Date_Fin"));
      } catch (Exception e1) {
         // this.logger.error(e1.getMessage());
         e1.printStackTrace();

      }
      TraitementImportDAO daoImport = new TraitementImportDAO();
      daoImport.saveTraitementSSIM(bean);

      // ///////////////////////////////////////////////
      new TraitementExportDAO().saveExport(new TraitementExportDataBean());
      
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
      // / update table circulation adapter avec la table des TrainsCatalogue

      List<TrainCatalogueDataBean> listTrainsCat = new TrainCatalogueDAO().getAll();
      for (TrainCatalogueDataBean tc : listTrainsCat) {
         new TrainCatalogueDAO().saveCirculation(tc);
      }

      // /////////////////////////////////////////////////////////////
      List<TrainCatalogue> listTrains = new ArrayList<>();
      List<CirculationSSIMDataBean> listCirculationSSIM = new CirculationSSIMDao().getAll();
      List<TrainToCompagnie> listTrainToCompagnie = new ArrayList<>();

      // /////////////////////////////////////////////////////// recuperer les
      // train catalogue des circulation adaptées
      List<CirculationAdapterDataBean> listCirculAdapter = new CirculationDAO().getDistinctCirculation();
      listCirculAdapter = new CirculationDAO().getAll();
      TrainFactory factory = new TrainFactory();

      // re-construire les circulations du traions à partir de la table des
      // circulation

      int idTrainCatalogue = listCirculAdapter.get(0).getTrainCatalogueDataBean().getIdTrainCatalogue();

      Circulation circulTemp = new Circulation();
      circulTemp.createCirculationFromBean(listCirculAdapter.get(0));
      List<Circulation> listCirculation = new ArrayList<Circulation>();
      TrainCatalogue train = factory.createTrainCatalgueFromBean(listCirculAdapter.get(0));
      ;
      train.addCirculation(circulTemp);

      for (int i = 1; i < listCirculAdapter.size(); i++) {
         circulTemp = new Circulation();
         circulTemp.createCirculationFromBean(listCirculAdapter.get(i));
         if (idTrainCatalogue == listCirculAdapter.get(i).getTrainCatalogueDataBean().getIdTrainCatalogue()) {

            train.addCirculation(circulTemp);
         } else {
            listTrains.add(train);
            train = factory.createTrainCatalgueFromBean(listCirculAdapter.get(i));
            train.addCirculation(circulTemp);
            idTrainCatalogue = listCirculAdapter.get(i).getTrainCatalogueDataBean().getIdTrainCatalogue();
         }
         if (i == listCirculAdapter.size() - 1)
            listTrains.add(train);
      }

      // //////////////////////////// recuperer les circulations de la ssim

      Train trainsSSIM = new Train();
      for (CirculationSSIMDataBean circulationBean : listCirculationSSIM) {
         Circulation circulation = new Circulation();
         circulation.setOrigine(circulationBean.getOriginePointArret());
         circulation.setDestination(circulationBean.getDestinationPointArret());
         circulation.setHeureArrivee(Integer.valueOf(circulationBean.getHeureArriverCirculation()));
         circulation.setHeureDepart(Integer.valueOf(circulationBean.getHeureDepartCirculation()));
         circulation.setJoursCirculation(circulationBean.getJoursCirculation());
         circulation.setDateDebut(circulationBean.getDateDebutCirculation());
         circulation.setDateFin(circulationBean.getDateFinCirculation());
         circulation.setGMTDepart(circulationBean.getGMTDepart());
         circulation.setGMTArrivee(circulationBean.getGMTArriver());
         circulation.setTrancheFacultatif(circulationBean.getTrancheFacultatif());
         circulation.setRestrictionTrafic(String.valueOf(circulationBean.getRangTroncon()));
         circulation.setRangTranson(circulationBean.getRangTroncon());
         circulation.setNumeroTrain(circulationBean.getNumeroTrain());
         trainsSSIM.addNumeroTrain(circulationBean.getNumeroTrain());
         trainsSSIM.addCirculation(circulation);
      }
      // //////////////////////////////// recuperer la trains restreints et
      // lancer l'adaptation

      TraitementImportDAO dao1 = new TraitementImportDAO();
      List<TraitementsImportDataBean> listTraitements = dao1.getLastID();
      Date dateDebutSSIM = listTraitements.get(0).getDateDebutSSIM();
      Date dateFinSSIM = listTraitements.get(0).getDateFinSSIM();
      Date dateExtraction = listTraitements.get(0).getDateImport();
      Service services = new Service();
      Date dateDebutService = services.getDateDebutService();
      Date dateFinService = services.getDateFinService();

      for (TrainCatalogue traincat : listTrains) {

         IObservableJoursCirculation iObs = new ObservableJoursCirculation();

         Train trainSSIMRestreint = trainsSSIM.getTrainSSIMRestreint(traincat);

         trainSSIMRestreint.remplirJoursCirculations();
         trainSSIMRestreint.calculeCirculationFromJoursCirculation();

         if (trainSSIMRestreint.getListeCirculations().size() > 0)
            if (!traincat.compare(trainSSIMRestreint)) {

               traincat.remplirJoursCirculations();
               traincat.adapt(trainSSIMRestreint, dateDebutSSIM, dateFinSSIM, iObs);
               traincat.calculeCirculationFromJoursCirculation();

               // //////////////////Updater les circulations
               new TrainCatalogueDAO().updateCirculation(traincat);
            }

      }
      
      
      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      List<TrainCatalogueToCompagnieDataBean> listTC2C = new TrainCatalogueToCompagnieDAO().getAll();
      List<CompagnieAerienneDataBean> listeCompagnie = new CompagnieAerienneDao().getAll();

      TrainCatalogueToCompagnieDAO catalogueToCompagnieDAO = new TrainCatalogueToCompagnieDAO();
      TrainCatalogueDAO catalogueDAO1 = new TrainCatalogueDAO();
      TrainCatalogueDataBean tc = null;

      for (CompagnieAerienneDataBean compagnie : listeCompagnie) {
         listTC2C = catalogueToCompagnieDAO.getTrainCatalogueByIdCompagnie(compagnie.getIdCompagnieAeriennne()); 
         boolean compare = false ;
         
         for (TrainCatalogueToCompagnieDataBean tc2c : listTC2C) {
            tc = catalogueDAO1.getTrainCatalogueByID(tc2c.getTrainCatalogueDataBean().getIdTrainCatalogue());
            List<TrainCatalogue> listeTrainCatalogue = TrainFactory.get2DerniersTC(tc2c.getTrainCatalogueDataBean().getIdTrainCatalogue(), Calendar.getInstance().getTime());

               if (listeTrainCatalogue.size()>1) { 
                  TrainCatalogue trainPortef1=listeTrainCatalogue.get(0).getTrainFromPortefeuille(tc2c.getDateDebutValiditeTrainCatalogueToCompagnie(), tc2c.getDateFinValiditeTrainCatalogueToCompagnie());
                  TrainCatalogue trainPortef2= listeTrainCatalogue.get(1).getTrainFromPortefeuille(tc2c.getDateDebutValiditeTrainCatalogueToCompagnie(), tc2c.getDateFinValiditeTrainCatalogueToCompagnie());
                        
                  if (!trainPortef1.compare(trainPortef2)) {compare = true ;break ;}
         }
               
               if (listeTrainCatalogue.size()==1) {
                  compare = true  ;break ;
               }
      } 
         if (compare) {

            List<TrainCatalogue> listCatalogue = new ArrayList<TrainCatalogue>();
            for (TrainCatalogueToCompagnieDataBean tc2c : listTC2C) {
               tc = catalogueDAO1.getTrainCatalogueByID(tc2c.getTrainCatalogueDataBean().getIdTrainCatalogue()); 
               CirculationDAO dao11=new CirculationDAO();
               //On récupère les circulations correspondant à l'id du train catalogue on question 
               
               List<CirculationAdapterDataBean> liste= dao11.getCirculationByIdTrain(tc.getIdTrainCatalogue());
               TrainCatalogue train1=TrainFactory.createTrainCatalogueFromBeans(liste);
                TrainCatalogue trainPortf = train1.getTrainFromPortefeuille(tc2c.getDateDebutValiditeTrainCatalogueToCompagnie(),tc2c.getDateFinValiditeTrainCatalogueToCompagnie());
                trainPortf.setCodeCompagnie(tc2c.getCodeCompagnieAerienne()); 
                trainPortf.setQuota1er(tc2c.getQuotaPremiereTrainCatalogueToCompagnie()); 
                trainPortf.setQuota2eme(tc2c.getQuotaDeuxiemeTrainCatalogueToCompagnie()); 
                trainPortf.setPointArretOrigine(tc2c.getTrainCatalogueDataBean().getIdPointArretOrigine());
                trainPortf.setPointArretDestination(tc2c.getTrainCatalogueDataBean().getIdPointArretDestination());
                trainPortf.setMarketingFlight(tc2c.getMarketingFlightTrainCatalogueToCompagnie());
                listCatalogue.add(trainPortf)   ;  
            }
            
            ExportPDTByCompagnyToSSIM7 export = new ExportPDTByCompagnyToSSIM7() ;
            try {
               export.export(listCatalogue , new TraitementExportDataBean(), new Service());
            } catch (ParseException e) {
                e.printStackTrace();
            }
         }
   }
   }

}
