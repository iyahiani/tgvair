package com.avancial.app.jobs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.avancial.app.business.compagnieAerienne.IObservableJoursCirculation;
import com.avancial.app.business.compagnieAerienne.IObserverJoursCirculation;
import com.avancial.app.business.compagnieAerienne.ObservableJoursCirculation;
import com.avancial.app.business.compagnieAerienne.ObserverJoursCirculation;
import com.avancial.app.business.compagnieAerienne.TrainToCompagnie;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.business.train.TrainFactory;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.data.controller.dao.CirculationDao;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueToCompagnieDAO;
import com.avancial.app.data.model.databean.CirculationDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueToCompagnieDataBean;
import com.avancial.app.traitements.TraitementImportDAO;
import com.avancial.app.traitements.TraitementsImportDataBean;

public class JobAdaptation implements Job {

   public void execute(JobExecutionContext context) throws JobExecutionException {

      List<TrainCatalogueDataBean> listTrainsCat = new TrainCatalogueDAO().getAll();
      List<TrainCatalogue> listTrains = new ArrayList<>();
      List<CirculationDataBean> listCirculation = new CirculationDao().getAll();
      List<TrainToCompagnie> listTrainToCompagnie = new ArrayList<>();
      List<TrainCatalogueToCompagnieDataBean> listCatalogueToCompagnieDataBeans = new ArrayList<>();
      // /////////////////////////////////////////////////////// recuperer les
      // train catalogue
      TrainFactory factory = new TrainFactory();
      for (TrainCatalogueDataBean bean : listTrainsCat) {
         TrainCatalogue train = factory.createTrainCatalgueFromBean(bean);
         listTrains.add(train);
      }
      // //////////////////////////// recuperer les trains ssim

      Train trainsSSIM = new Train();
      for (CirculationDataBean circulationBean : listCirculation) {
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
      Calendar c = Calendar.getInstance();
      c.set(2015, 0, 1);
      Date dateDebutService = c.getTime();
      c.set(2017, 0, 1);
      Date dateFinService = c.getTime();

      for (TrainCatalogue traincat : listTrains) {

         IObservableJoursCirculation iObs = new ObservableJoursCirculation();

         Train trainSSIMRestreint = trainsSSIM.getTrainSSIMRestreint(traincat);

         trainSSIMRestreint.remplirJoursCirculations();
         trainSSIMRestreint.calculeCirculationFromJoursCirculation();
         // System.out.println(trainSSIMRestreint);
         listCatalogueToCompagnieDataBeans.clear();
         listCatalogueToCompagnieDataBeans = new TrainCatalogueToCompagnieDAO().getTrainToCompagnieByID(traincat.getIdTrain());
         
         
         
         for (TrainCatalogueToCompagnieDataBean tc2c : listCatalogueToCompagnieDataBeans) {
            TrainToCompagnie trainToCompagnie = factory.createTrain2cFromBean(tc2c);
            IObserverJoursCirculation iObserver = new ObserverJoursCirculation(trainToCompagnie, traincat, dateDebutService, dateFinService, dateExtraction);
            iObs.addObservateur(iObserver);
         } 
         
         if (trainSSIMRestreint.getListeCirculations().size() > 0)
            if (!traincat.compare(trainSSIMRestreint)) {
               traincat.remplirJoursCirculations();
               traincat.adapt(trainSSIMRestreint, dateDebutSSIM, dateFinSSIM, iObs);
               traincat.calculeCirculationFromJoursCirculation();
               System.out.println(traincat);

            }
      }

   }
}
