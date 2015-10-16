package com.avancial.app.traitements;

import java.io.IOException;
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
import com.avancial.app.resources.constants.APP_TgvAir;
import com.avancial.app.resources.utils.GetPeriodeSSIM;
import com.avancial.app.resources.utils.GetTrainsNums;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;
import com.avancial.socle.resources.constants.SOCLE_constants;
import com.avancial.test.InsertWithJDBC;

/**
 * Methodes pour le lancement les Imports et les Exports SSIM/SSIM7 depuis la fenetre
 * d'administration
 * 
 * @author ismael.yahiani
 * 
 */
public class LancementJobManuelle {

   Logger logger = Logger.getLogger(LancementJobManuelle.class);

   public LancementJobManuelle() {
   }

   // //////////////////////////////////////////////////////////////////////////////////////
   public void traitementImportSSIM() {
      FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Import", "Traitement Import SSIM Lancé"));
      TrainCatalogueDAO catalogueDAO = new TrainCatalogueDAO();
      List<TrainCatalogueDataBean> listTrainsCatalogue = catalogueDAO.getAll();
      List<String> listnums = new ArrayList<>();
      List<String> listnumsHashed = new ArrayList<>();
      this.logger.info("Import started");
      IReader reader = null;
      InsertWithJDBC insertWithJDBC = new InsertWithJDBC();
      try {
         
         reader = new ReaderSSIM(APP_TgvAir.CHEMIN_SSIM.toString());

      } catch (IOException e1) {
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "SSIM", "Erreur Lecture SSIM"));
         this.logger.info("Import:" + e1.getMessage());

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
      dao.getAll();
      if(dao.getAll().size()>0) dao.deleteAll(0);
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
                  this.logger.error("erreur ssim parse date deb/fin circulation ");
                  FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Erreur Import", "Erreur De lecture fichier SSIM"));
                  e.printStackTrace();
               }

               circulation.setTrancheFacultatif(chaine.substring(APP_enumParserSSIM.POSITION_TRANCHE_FACULTATIF.getPositionDebut(), APP_enumParserSSIM.POSITION_TRANCHE_FACULTATIF.getPositionFin()));
               circulation.setRestrictionTrafic(chaine.substring(APP_enumParserSSIM.POSITION_RESTRICTION_TRAFIC.getPositionDebut(), APP_enumParserSSIM.POSITION_RESTRICTION_TRAFIC.getPositionFin()));
               circulation.setRangTroncon(Integer.valueOf(chaine.substring(APP_enumParserSSIM.POSITION_RANG_TRANCON.getPositionDebut(), APP_enumParserSSIM.POSITION_RANG_TRANCON.getPositionFin())));
               circulation.setNumeroTrain(par.getParsedResult().get("POSITION_NUM_TRAIN"));
               insertWithJDBC.insertRecordIntoTable(circulation); 
              // dao.customSave(circulation);
                /*
                      try {

                         dao.save(circulation);
                      } catch (ASocleException e) {
                         this.logger.error("sauvegarde SSIM erreur" + e.getMessage());
                         e.printStackTrace();
                      }
                  
              */
            }
         } 
         
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Traitement", "SUCCES Import SSIM"));
      } catch (Exception e) {
         this.logger.error(e.getMessage());
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Traitement", "Echec Import SSIM"));
         e.printStackTrace();
      }

      TraitementsImportDataBean bean = new TraitementsImportDataBean();
      try {
         bean.setDateDebutSSIM(GetPeriodeSSIM.getSSIMPeriode(APP_TgvAir.CHEMIN_SSIM.toString()).get("Date_Extraction"));
         bean.setDateFinSSIM(GetPeriodeSSIM.getSSIMPeriode(APP_TgvAir.CHEMIN_SSIM.toString()).get("Date_Fin"));

      } catch (Exception e1) {
        
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Import", "Erreur Import SSIM"));
         this.logger.error(e1.getMessage());
         
      }

      TraitementImportDAO daoImport = new TraitementImportDAO();

      daoImport.saveTraitementSSIM(bean);
      FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Import", "Traitement Import SSIM Terminé"));
      this.logger.info("Import Terminé");
   }

   // //////////////////////////////////////////////////////////////////////////////////////////////////////

   public void traitementAdaptation() {

      // / update table circulation adapter avec la table des TrainsCatalogue
      Service services = new Service()   ;
      FacesContext.getCurrentInstance()
      .addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajustement", "Ajustement Des Trains Lancé"));
      
      List<TrainCatalogueDataBean> listTrainsCat = new TrainCatalogueDAO().getAll();
      for (TrainCatalogueDataBean tc : listTrainsCat) {
         new TrainCatalogueDAO().saveCirculation(tc);
      }

      // /////////////////////////////////////////////////////////////
      List<TrainCatalogue> listTrains = new ArrayList<>();
      List<TrainCatalogue> listTrainsAdapt = new ArrayList<>();
      List<CirculationSSIMDataBean> listCirculationSSIM = new CirculationSSIMDao().getAll();
      
      List<PointArretDataBean> listPointsArret = new PointArretDAO().getAll();

      // /////////////////////////////////////////////////////// recuperer les
      // train catalogue à partir des circulation adaptées

     // List<CirculationAdapterDataBean> listCirculAdapter = new CirculationDAO().getDistinctCirculation();
      List<CirculationAdapterDataBean> listCirculAdapter = new  ArrayList<>();
      listCirculAdapter.clear();
      listCirculAdapter.addAll(new CirculationDAO().getAll()) ;
      TrainFactory factory = new TrainFactory();
      // re-construire les circulations du trains à partir de la table des
      // circulation adaptés

      int idTrainCatalogue = listCirculAdapter.get(0).getTrainCatalogueDataBean().getIdTrainCatalogue();
      Circulation circulTemp = new Circulation();
      circulTemp.createCirculationFromBean(listCirculAdapter.get(0));
      TrainCatalogue train = factory.createTrainCatalgueFromBean(listCirculAdapter.get(0));
      
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
      List<Circulation> circulations = new ArrayList<>();
      CirculationFactory circulationFactory = new CirculationFactory() ;
      int i = 0;
      
      for (CirculationSSIMDataBean circulationBean : listCirculationSSIM) {
         Circulation circulation = circulationFactory.createCirculationFromSSIMBean(circulationBean);
         trainsSSIM.addNumeroTrain(circulationBean.getNumeroTrain());
        
         circulations.add(circulation);

      } 
      trainsSSIM.setListeCirculations(circulations);
      trainsSSIM.remplirJoursCirculations();
      // //////////////////////////////// recuperer la trains restreints et
      // lancer l'adaptation

      TraitementImportDAO dao = new TraitementImportDAO();
      List<TraitementsImportDataBean> listTraitements = dao.getLastID();
      Date dateDebutSSIM = listTraitements.get(0).getDateDebutSSIM();
      Date dateFinSSIM = listTraitements.get(0).getDateFinSSIM();
      listTraitements.get(0).getDateImport();
     
      services.getDateDebutService();
      services.getDateFinService();
       
      for (TrainCatalogue traincat : listTrains) {

         IObservableJoursCirculation iObs = new ObservableJoursCirculation();
         Train trainSSIMRestreint = trainsSSIM.getTrainSSIMRestreint(traincat);
         if (trainSSIMRestreint.getListeCirculations().size() > 0) {
         trainSSIMRestreint.remplirJoursCirculations();
         trainSSIMRestreint.calculeCirculationFromJoursCirculation();
            if (!traincat.compare(trainSSIMRestreint)) {
 
               traincat.remplirJoursCirculations();
               traincat.adapt(trainSSIMRestreint, dateDebutSSIM, dateFinSSIM, iObs);
              
               traincat.adaptGuichet(listPointsArret);
               traincat.calculeCirculationFromJoursCirculation();
               // //////////////////   Updater les circulations
               listTrainsAdapt.add(traincat) ;
         
            }
         }
      } 
      
      new TrainCatalogueDAO().updateCirculation(listTrainsAdapt);
      FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Traitement", "SUCCES Ajustement des Trains"));
   }

   public void traitementExport() {
      
      List<TrainCatalogueToCompagnieDataBean> listTC2C = new TrainCatalogueToCompagnieDAO().getAll();
      List<CompagnieAerienneDataBean> listeCompagnie = new CompagnieAerienneDao().getAll();
      FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Export", "Traitement Export SSIM 7 lancé"));
      Calendar lastCircule = Calendar.getInstance();
      lastCircule.setTime(new CirculationDAO().getLastDateCreation().getDateCreationLigneTrain());

      TrainCatalogueToCompagnieDAO catalogueToCompagnieDAO = new TrainCatalogueToCompagnieDAO();
      TrainCatalogueDAO catalogueDAO = new TrainCatalogueDAO();
      TrainCatalogueDataBean tc = null;

      for (CompagnieAerienneDataBean compagnie : listeCompagnie) {
         listTC2C = catalogueToCompagnieDAO.getTrainCatalogueByIdCompagnie(compagnie.getIdCompagnieAeriennne());
         boolean compare = false    ;

         for (TrainCatalogueToCompagnieDataBean tc2c : listTC2C) {
            tc = catalogueDAO.getTrainCatalogueByID(tc2c.getTrainCatalogueDataBean().getIdTrainCatalogue());

            List<TrainCatalogue> listeTrainCatalogue = TrainFactory.get2DerniersTC(tc2c.getTrainCatalogueDataBean().getIdTrainCatalogue(), lastCircule.getTime());// Calendar.getInstance().getTime()
            if (listeTrainCatalogue.size() > 1) {
               TrainCatalogue trainPortef1 = listeTrainCatalogue.get(0).getTrainFromPortefeuille(tc2c.getDateDebutValiditeTrainCatalogueToCompagnie(),
                     tc2c.getDateFinValiditeTrainCatalogueToCompagnie());
               TrainCatalogue trainPortef2 = listeTrainCatalogue.get(1).getTrainFromPortefeuille(tc2c.getDateDebutValiditeTrainCatalogueToCompagnie(),
                     tc2c.getDateFinValiditeTrainCatalogueToCompagnie());
               if (!trainPortef1.compare(trainPortef2)) {
                  compare = true;
                  break;
               }
            }
               
            
             if (listeTrainCatalogue.size()==1) {
                Calendar c = Calendar.getInstance() ; // Regarder si ce train date
           // d'aujourd'hui ou si c'est un ancien train :Si nouveau Export /
             // Sinon ne pas exporter 
                try {
                   if( StringToDate.toStringByFormat(new
             CirculationDAO().getCirculationByIdTrain(listeTrainCatalogue.get(0).getIdTrain()).get(0)
             .getDateCreationLigneTrain(),"dateBySlashSansHeure").equals
              (StringToDate.toStringByFormat(c.getTime(),"dateBySlashSansHeure"
          ))) { compare = true ;break ; } } catch (Exception e) {
           
              e.printStackTrace(); } }
           
         }
         if (compare) {
            List<TrainCatalogue> listCatalogue = new ArrayList<>();
            for (TrainCatalogueToCompagnieDataBean tc2c : listTC2C) {
               tc = catalogueDAO.getTrainCatalogueByID(tc2c.getTrainCatalogueDataBean().getIdTrainCatalogue());
               CirculationDAO dao = new CirculationDAO();
               // On récupère les circulations correspondant à l'id du train
               // catalogue on question
               List<CirculationAdapterDataBean> liste = dao.getCirculationByIdTrain(tc.getIdTrainCatalogue());
               if (liste.size()>0) {
               TrainCatalogue train = TrainFactory.createTrainCatalogueFromBeans(liste);
               System.out.println(tc.getIdTrainCatalogue());
            // System.out.println(train.getIdTrain());
            
               TrainCatalogue trainPortf = train.getTrainFromPortefeuille(tc2c.getDateDebutValiditeTrainCatalogueToCompagnie(), tc2c.getDateFinValiditeTrainCatalogueToCompagnie());
               trainPortf.setCodeCompagnie(tc2c.getCompagnieAerienneDataBean().getCodeCompagnieAerienne());
               trainPortf.setQuota1er(tc2c.getQuotaPremiereTrainCatalogueToCompagnie());
               trainPortf.setQuota2eme(tc2c.getQuotaDeuxiemeTrainCatalogueToCompagnie());
               trainPortf.setPointArretOrigine(tc2c.getTrainCatalogueDataBean().getIdPointArretOrigine());
               trainPortf.setPointArretDestination(tc2c.getTrainCatalogueDataBean().getIdPointArretDestination());
               trainPortf.setMarketingFlight(tc2c.getMarketingFlightTrainCatalogueToCompagnie());
               listCatalogue.add(trainPortf);
             } 
            }
            ExportPDTByCompagnyToSSIM7 export = new ExportPDTByCompagnyToSSIM7();
            try {
               export.export(listCatalogue, new TraitementExportDataBean(), new Service());
               FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Traitement", "SUCCES Export SSIM7"));
               this.logger.info("Export SSIM7 Terminé");

            } catch (ParseException e) {
               FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Traitement", "Echec Export SSIM7"));
               this.logger.error("Echec Export SSIm7");
               e.printStackTrace();
            }
         }

      }
   }

}
