package com.avancial.app.traitements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.avancial.app.business.compagnieAerienne.IObservableJoursCirculation;
import com.avancial.app.business.compagnieAerienne.ObservableJoursCirculation;
import com.avancial.app.business.train.Service;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.business.train.TrainFactory;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.business.train.circulation.CirculationFactory;
import com.avancial.app.data.controller.dao.CirculationDAO;
import com.avancial.app.data.controller.dao.CirculationSSIMDao;
import com.avancial.app.data.controller.dao.PointArretDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.CirculationSSIMDataBean;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * 
 * @author ismael.yahiani
 *  implemente la methode de lancement manuel des adaptations Train    
 */
public class LancementAdaptationManuel {
   Logger logger = Logger.getLogger(LancementAdaptationManuel.class);
   
   public LancementAdaptationManuel() {
   }  
   
   public void traitementAdaptation() {

      // / update table circulation adapter avec la table des TrainsCatalogue
      
      Service services = new Service()   ;
      
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
         
      for (TrainCatalogueDataBean bean : listTrainsCat) {
         TrainCatalogue tc = factory.createTrainCatalogueFromDataBean(bean) ; 
         tc.remplirJoursCirculations(); 
         listTrains.add(tc) ;
      }
      
      
      /*
      int idTrainCatalogue = listCirculAdapter.get(0).getTrainCatalogueDataBean().getIdTrainCatalogue();
      Circulation circulTemp = new Circulation();
      circulTemp.createCirculationFromBean(listCirculAdapter.get(0));
      TrainCatalogue train = factory.createTrainCatalgueFromBean(listCirculAdapter.get(0));
      listTrains.clear();
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
      */
      
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

         if (traincat.getIdTrain()==40)             
         System.out.println(traincat.getIdTrain());
         
         IObservableJoursCirculation iObs = new ObservableJoursCirculation();
         Train trainSSIMRestreint = trainsSSIM.getTrainSSIMRestreint(traincat);
         if (trainSSIMRestreint.getListeCirculations().size() > 0) {
         trainSSIMRestreint.remplirJoursCirculations();
       //  trainSSIMRestreint.calculeCirculationFromJoursCirculation();
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
}
