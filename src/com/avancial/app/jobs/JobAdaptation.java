package com.avancial.app.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.avancial.app.business.compagnieAerienne.IObservableJoursCirculation;
import com.avancial.app.business.compagnieAerienne.ObservableJoursCirculation;
import com.avancial.app.business.compagnieAerienne.TrainToCompagnie;
import com.avancial.app.business.train.Service;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.business.train.TrainFactory;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.data.controller.dao.CirculationDAO;
import com.avancial.app.data.controller.dao.CirculationSSIMDao;
import com.avancial.app.data.controller.dao.ExportSSIMDAO;
import com.avancial.app.data.controller.dao.PointArretDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueToCompagnieDAO;
import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.CirculationSSIMDataBean;
import com.avancial.app.data.model.databean.ExportSSIMDataBean;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.traitements.TraitementExportDAO;
import com.avancial.app.traitements.TraitementExportDataBean;
import com.avancial.app.traitements.TraitementImportDAO;
import com.avancial.app.traitements.TraitementsImportDataBean;
import com.avancial.socle.exceptions.ASocleException;

public class JobAdaptation implements Job {
    
   
   
   public void execute(JobExecutionContext context) throws JobExecutionException {
      Logger log = Logger.getLogger(JobAdaptation.class); 
      log.info("Job Adaptation Lancé");
      ////////////    Archiver la date de l'export 
      
     new TraitementExportDAO().saveExport(new TraitementExportDataBean());
     
      // / update table circulation adapter avec la table des TrainsCatalogue

      List<TrainCatalogueDataBean> listTrainsCat = new TrainCatalogueDAO().getAll();
      for (TrainCatalogueDataBean tc : listTrainsCat) {
        new TrainCatalogueDAO().saveCirculation(tc)                                 ;
      }
      
      ///////////////////////////////////////////////////////////////
      List<TrainCatalogue> listTrains = new ArrayList<>();
      List<TrainCatalogue> listCatalogue = new ArrayList<>();
      List<CirculationSSIMDataBean> listCirculationSSIM = new CirculationSSIMDao().getAll();
      List<TrainToCompagnie> listTrainToCompagnie = new ArrayList<>();
      List<PointArretDataBean> listPointsArret = new PointArretDAO().getAll() ;
      
      // /////////////////////////////////////////////////////// recuperer les
      // train catalogue des circulation adaptées
      List<CirculationAdapterDataBean> listCirculAdapter = new CirculationDAO().getDistinctCirculation();
      listCirculAdapter = new CirculationDAO().getAll();
      TrainFactory factory = new TrainFactory();
      // re-construire les circulations du traions à partir de la table des circulation   
                
      int idTrainCatalogue = listCirculAdapter.get(0).getTrainCatalogueDataBean().getIdTrainCatalogue() ; 
      Circulation circulTemp = new Circulation() ;
      circulTemp.createCirculationFromBean(listCirculAdapter.get(0)) ;
      List<Circulation> listCirculation = new ArrayList<>(); 
      TrainCatalogue train = factory.createTrainCatalgueFromBean(listCirculAdapter.get(0)); ;
      train.addCirculation(circulTemp);
     
      for (int i = 1 ; i < listCirculAdapter.size() ; i ++ ) {
         circulTemp = new Circulation() ;
         circulTemp.createCirculationFromBean(listCirculAdapter.get(i)) ;
         if (idTrainCatalogue == listCirculAdapter.get(i).getTrainCatalogueDataBean().getIdTrainCatalogue()) {
                        
            train.addCirculation(circulTemp);
         } else {
            listTrains.add(train);
            train = factory.createTrainCatalgueFromBean(listCirculAdapter.get(i));
            train.addCirculation(circulTemp);
            idTrainCatalogue = listCirculAdapter.get(i).getTrainCatalogueDataBean().getIdTrainCatalogue() ; 
         } 
         if(i==listCirculAdapter.size()-1) listTrains.add(train);
      }
      
      
      // //////////////////////////// recuperer les circulations de la ssim
      List<Circulation> circulations = new ArrayList<>();
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
         //trainsSSIM.addCirculation(circulation); 
         circulations.add(circulation) ;
      }  
      trainsSSIM.setListeCirculations(circulations);
      trainsSSIM.remplirJoursCirculations();
      // //////////////////////////////// recuperer la trains restreints et
      // lancer l'adaptation

      TraitementImportDAO dao = new TraitementImportDAO()   ;
      List<TraitementsImportDataBean> listTraitements = dao.getLastID();
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
               // traincat.calculeCirculationFromJoursCirculation();
               //traincat.adaptGuichet(pa);
               //traincat.calculeCirculationFromJoursCirculation();
               traincat.adaptGuichet(listPointsArret);
               traincat.calculeCirculationFromJoursCirculation();
               listCatalogue.add(traincat);
              
            }
      }
      // //////////////////Updater les circulations 
      new TrainCatalogueDAO().updateCirculation(listCatalogue); 
   }
}
