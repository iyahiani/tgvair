package com.avancial.app.jobs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.avancial.app.business.compagnieAerienne.IObservableJoursCirculation;
import com.avancial.app.business.compagnieAerienne.IObserverJoursCirculation;
import com.avancial.app.business.compagnieAerienne.ObservableJoursCirculation;
import com.avancial.app.business.compagnieAerienne.ObserverJoursCirculation;
import com.avancial.app.business.compagnieAerienne.TrainToCompagnie;
import com.avancial.app.business.train.Service;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.business.train.TrainFactory;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.data.controller.dao.CirculationAdapterDAO;
import com.avancial.app.data.controller.dao.CirculationDao;
import com.avancial.app.data.controller.dao.ExportSSIMDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueToCompagnieDAO;
import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.CirculationSSIMDataBean;
import com.avancial.app.data.model.databean.ExportSSIMDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueToCompagnieDataBean;
import com.avancial.app.traitements.TraitementExportDAO;
import com.avancial.app.traitements.TraitementExportDataBean;
import com.avancial.app.traitements.TraitementImportDAO;
import com.avancial.app.traitements.TraitementsImportDataBean;
import com.avancial.socle.exceptions.ASocleException;

public class JobAdaptation implements Job {

   public void execute(JobExecutionContext context) throws JobExecutionException {
      Logger log = Logger.getLogger(JobAdaptation.class);
      
      List<TrainCatalogueDataBean> listTrainsCat = new TrainCatalogueDAO().getAll();
      List<TrainCatalogue> listTrains = new ArrayList<>();
      List<CirculationSSIMDataBean> listCirculation = new CirculationDao().getAll();
      List<TrainToCompagnie> listTrainToCompagnie = new ArrayList<>();
      List<TrainCatalogueToCompagnieDataBean> listCatalogueToCompagnieDataBeans = new ArrayList<>();
      List<TrainCatalogueToCompagnieDataBean> listCompleteTrainToCompagnieDataBeans = new ArrayList<>();
      // Sava Extract Traitement  
      TraitementExportDataBean export = new TraitementExportDataBean();
      TraitementExportDAO traitementExportDAO = new TraitementExportDAO();
      try {
         traitementExportDAO.save(export);
      } catch (ASocleException e) {
         log.error(e.getMessage());
         e.printStackTrace();
      }
      
      // /////////////////////////////////////////////////////// recuperer les
      // train catalogue
      TrainFactory factory = new TrainFactory();
      for (TrainCatalogueDataBean bean : listTrainsCat) {
         TrainCatalogue train = factory.createTrainCatalgueFromBean(bean);
         listTrains.add(train);
      }
      // //////////////////////////// recuperer les circulations de la ssim

      Train trainsSSIM = new Train();
      for (CirculationSSIMDataBean circulationBean : listCirculation) {
         Circulation circulation = new Circulation();
         circulation.setOrigine(circulationBean.getOriginePointArret());
         circulation.setDestination(circulationBean.getDestinationPointArret());
         circulation.setHeureArrivee(Integer.valueOf(circulationBean.getHeureArriverCirculation()));
         circulation.setHeureDepart(Integer.valueOf(circulationBean.getHeureDepartCirculation()));
         circulation.setJoursCirculation(circulationBean.getJoursCirculation());
         circulation.setDateDebut(circulationBean.getDateDebutCirculation());
         circulation.setDateFin(circulationBean.getDateFinCirculation());
         circulation.setTrancheFacultatif(circulationBean.getTrancheFacultatif());
         circulation.setRestrictionTrafic(String.valueOf(circulationBean.getRangTroncon()));
         circulation.setRangTranson(circulationBean.getRangTroncon());
         circulation.setNumeroTrain(circulationBean.getNumeroTrain());
         // circulation.setNumeroTrain(circulationBean.getNumeroTrain());
         trainsSSIM.addNumeroTrain(circulationBean.getNumeroTrain());
         trainsSSIM.addCirculation(circulation);
      }
      // //////////////////////////////// recuperer la trains restreints et
      // lancer l'adaptation

      TraitementImportDAO dao = new TraitementImportDAO();
      List<TraitementsImportDataBean> listTraitements = dao.getLastID();
      Date dateDebutSSIM = listTraitements.get(0).getDateDebutSSIM();
      Date dateFinSSIM = listTraitements.get(0).getDateFinSSIM();
      Date dateExtraction = listTraitements.get(0).getDateImport();
      Service services = new Service() ; 
      Date dateDebutService = services.getDateDebutService();
      Date dateFinService = services.getDateFinService();  

      for (TrainCatalogue traincat : listTrains) {

         IObservableJoursCirculation iObs = new ObservableJoursCirculation();

         Train trainSSIMRestreint = trainsSSIM.getTrainSSIMRestreint(traincat);

         trainSSIMRestreint.remplirJoursCirculations();
         trainSSIMRestreint.calculeCirculationFromJoursCirculation();
         // System.out.println(trainSSIMRestreint);

         if (trainSSIMRestreint.getListeCirculations().size() > 0)
            if (!traincat.compare(trainSSIMRestreint)) {
               // ////////////////////////////////////////////// construire les
               // observateurs du train a adapté
               
               listCatalogueToCompagnieDataBeans.clear();
               listCatalogueToCompagnieDataBeans = new TrainCatalogueToCompagnieDAO().getTrainToCompagnieByID(traincat.getIdTrain());

               for (TrainCatalogueToCompagnieDataBean tc2c : listCatalogueToCompagnieDataBeans) {
                  TrainToCompagnie trainToCompagnie = factory.createTrain2cFromBean(tc2c);
                  IObserverJoursCirculation iObserver = new ObserverJoursCirculation(trainToCompagnie, traincat, dateDebutService, dateFinService, dateExtraction);
                  iObs.addObservateur(iObserver);
               }
               // ////////////////////////////////////////////// lancer
               // l'adaptation

               traincat.remplirJoursCirculations();
               traincat.adapt(trainSSIMRestreint, dateDebutSSIM, dateFinSSIM, iObs);
               traincat.calculeCirculationFromJoursCirculation();

               // /////////////////// persister les circulation du train adapté

               for (Circulation c : traincat.getListeCirculations()) {
                  CirculationAdapterDataBean cirAdapterDataBean = new CirculationAdapterDataBean();
                  cirAdapterDataBean.setDateDebutCirculation(c.getDateDebut());
                  cirAdapterDataBean.setDateFinCirculation(c.getDateFin());
                  cirAdapterDataBean.setTrainCatalogueDataBean(new TrainCatalogueDAO().getTrainCatalogueByID(traincat.getIdTrain()));
                  cirAdapterDataBean.setTraitementImport(new TraitementImportDAO().getLastID().get(0));
                  cirAdapterDataBean.setHeureDepart(String.valueOf(c.getHeureDepart() < 1000 ? "0".concat(String.valueOf(c.getHeureDepart())) : String.valueOf(c.getHeureDepart())));
                  cirAdapterDataBean.setHeureArriver(String.valueOf(c.getHeureArrivee() < 1000 ? "0".concat(String.valueOf(c.getHeureArrivee())) : String.valueOf(c.getHeureArrivee())));
                  cirAdapterDataBean.setRegimeCirculation(c.getJoursCirculation())  ;
                  CirculationAdapterDAO dao1 = new CirculationAdapterDAO()          ;
                  try {
                     dao1.save(cirAdapterDataBean); 
                    
                  } catch (ASocleException e) {
                     log.error(e.getMessage());
                     e.printStackTrace();
                  }
               }
               // ///////////////// recupere touts les trains de la compagnies 
               
               // /////////////////////////////////////////////////////////// archiver les compagnies a exporter dans la ssim7

               ExportSSIMDAO daoExportSSIM = new ExportSSIMDAO();
               for (int i = 0; i < iObs.getListObservers().size(); i++) {
                  TrainToCompagnie tc2c = iObs.getListObservers().get(i).getTc2c();
                  for (Circulation c : tc2c.getListeCirculations()) {
                     ExportSSIMDataBean exportBean = new ExportSSIMDataBean();
                    
                     exportBean.setIdTrainCatalogue(new TrainCatalogueDAO().getTrainCatalogueByID(tc2c.getIdTrainCatalogue()));
                     exportBean.setIdTrainCatalogueToCompagnie(new TrainCatalogueToCompagnieDAO().getTrainCatalogue2CByID(tc2c.getIdTrain()));
                     exportBean.setIdTraitementExport(traitementExportDAO.getLastID());
                     exportBean.setDateDebutCirculation(c.getDateDebut());
                     exportBean.setDateFinCirculation(c.getDateFin());
                     exportBean.setHeureDepartCirculation(String.valueOf(c.getHeureDepart() < 1000 ? "0".concat(String.valueOf(c.getHeureDepart())) : String.valueOf(c.getHeureDepart()))); 
                     exportBean.setHeureArriverCirculation(String.valueOf(c.getHeureArrivee() < 1000 ? "0".concat(String.valueOf(c.getHeureArrivee())) : String.valueOf(c.getHeureArrivee()))); 
                     exportBean.setGMTDepart(c.getGMTDepart())                ; 
                     exportBean.setGMTArriver(c.getGMTArrivee())              ;  
                     exportBean.setRegimeCirculation(c.getJoursCirculation()) ;  
                     try {
                        ////   reste le cas d'un update d'un tc2c
                        daoExportSSIM.save(exportBean);
                        listCompleteTrainToCompagnieDataBeans.clear(); 
                        
                        //listCompleteTrainToCompagnieDataBeans.addAll(new TrainCatalogueToCompagnieDAO().getListTrains2Compagnie(exportBean.getIdTrainCatalogueToCompagnie().getCodeCompagnieAerienne()));
                        System.out.println();
                     } catch (ASocleException e) {
                        log.error(e.getMessage());
                        e.printStackTrace();
                     }
                  }
               }
            }
      }

   }
}
