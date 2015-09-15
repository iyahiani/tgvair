package com.avancial.app.business.train;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ListCellRenderer;

import com.avancial.app.business.compagnieAerienne.TrainToCompagnie;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.data.controller.dao.CirculationDAO;
import com.avancial.app.data.controller.dao.PointArretDAO;
import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueAdapterDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueToCompagnieDataBean;
import com.avancial.app.resources.utils.GetTrainsNums;
import com.avancial.app.resources.utils.StringToDate;

/**
 * 
 * @author ismael.yahiani la factory Train permet de creer des objets de type
 *         train
 */
public class TrainFactory implements ITrainFactory {

   @Override
   public ITrain createTrain() {

      ITrain train = new Train(null, null);
      return train;
   }

   @Override
   public ITrain createTrainByListCirculation(List<Circulation> listCircu) {

      return null;
   }

   public static Train createTrainComplet(TrainCatalogue trainCatalogue) {

      Train train = new Train(trainCatalogue.getListeNumeros(), trainCatalogue.getCirculations());

      return train;
   }
   public static Train createTrainComplet2(TrainCatalogue trainCatalogue) {

      Train train = new Train(trainCatalogue.getListeNumeros(), trainCatalogue.getCirculations(),trainCatalogue.listeJoursCirculation);

      return train;
   }
   public static TrainCatalogue createTrainCata(Train train) {

      TrainCatalogue trainC = new TrainCatalogue(train.getListeNumeros(), train.getCirculations());

      return trainC;
   }

   public TrainCatalogue createTrainCatalgueFromBean(CirculationAdapterDataBean bean) {
      
      TrainCatalogue train = new TrainCatalogue();
      //train.getListeNumeros().clear();
      train.setListeNumeros(GetTrainsNums.getTrainsNums(bean.getTrainCatalogueDataBean().getNumeroTrainCatalogue()));
      //train.getListeCirculations().clear();train.setListeCirculations(listCircul);
      train.setOoperatingFlight(bean.getTrainCatalogueDataBean().getOperatingFlight()); 
      train.setIdTrain(bean.getTrainCatalogueDataBean().getIdTrainCatalogue()); 
      train.setDateDebutValidite(bean.getTrainCatalogueDataBean().getDateDebutValidite());
      train.setDateFinValidite(bean.getTrainCatalogueDataBean().getDateFinValidite()); 
      train.setIdTrain(bean.getTrainCatalogueDataBean().getIdTrainCatalogue());
      return train ;
   }
   public TrainToCompagnie createTrain2cFromBean(TrainCatalogueToCompagnieDataBean bean) {
      
      TrainToCompagnie trainToCompagnie = new TrainToCompagnie() ;
      trainToCompagnie.setListeNumeros(GetTrainsNums.getTrainsNums(bean.getTrainCatalogueDataBean().getNumeroTrainCatalogue())); 
      trainToCompagnie.setCodeCompagnie(bean.getCodeCompagnieAerienne());
      trainToCompagnie.setDateDebutValidite(bean.getDateDebutValiditeTrainCatalogueToCompagnie());
      trainToCompagnie.setDateFinValidite(bean.getDateFinValiditeTrainCatalogueToCompagnie());  
      trainToCompagnie.setMarketingFlight(bean.getMarketingFlightTrainCatalogueToCompagnie());
      Circulation circulation = new Circulation();  
      circulation.setDateDebut(bean.getDateDebutValiditeTrainCatalogueToCompagnie());
      circulation.setDateFin(bean.getDateFinValiditeTrainCatalogueToCompagnie());
      circulation.setOrigine(bean.getTrainCatalogueDataBean().getOriginePointArret());
      circulation.setDestination(bean.getTrainCatalogueDataBean().getDestinationPointArret());
      circulation.setHeureDepart(Integer.valueOf(StringToDate.toFormatedString(bean.getTrainCatalogueDataBean().getHeureDepartTrainCatalogue())));
      circulation.setHeureArrivee(Integer.valueOf(StringToDate.toFormatedString(bean.getTrainCatalogueDataBean().getHeureArriveeTrainCatalogue()))); 
      circulation.setJoursCirculation(bean.getTrainCatalogueDataBean().getRegimeJoursTrainCatalogue()); 
      circulation.setGMTDepart("à ajouter"); 
      circulation.setGMTArrivee("à ajouter");
      trainToCompagnie.addCirculation (circulation); 
      trainToCompagnie.setDateDebutValidite(bean.getDateDebutValiditeTrainCatalogueToCompagnie());
      trainToCompagnie.setDateFinValidite(bean.getDateFinValiditeTrainCatalogueToCompagnie()); 
      trainToCompagnie.setIdTrainCatalogue(bean.getTrainCatalogueDataBean().getIdTrainCatalogue());
      trainToCompagnie.setIdTrain(bean.getIdTrainCatalogueToCompagnie());
      return trainToCompagnie ;
   }

  
   public List<TrainCatalogueAdapterDataBean> createBeanFromTrainCatalogueAdapter(TrainCatalogue tc) {
      return null;
      
   }
   

   public TrainCatalogueToCompagnieDataBean createT2CBeanFromTrain2Compagnie(TrainToCompagnie t2c) {
      TrainCatalogueToCompagnieDataBean bean = new TrainCatalogueToCompagnieDataBean(); 
      
      return bean;
   }

   
   public static  List<TrainCatalogue> get2DerniersTC(int idTrainCatalogue, Date date) {
      List<TrainCatalogue> listeTC=new ArrayList<>();
      
      CirculationDAO dao=new CirculationDAO();
      
      //On récupère les circulations correspondant à l'id et à la date
      List<CirculationAdapterDataBean> liste= dao.getCirculationByIdTrainAndByDate(idTrainCatalogue, date);
      
      TrainCatalogue train=TrainFactory.createTrainCatalogueFromBeans(liste)  ;
      listeTC.add(train)                                                      ; 
      //On récupère la date J-1
      Date dateJM1=dao.getMaxDateCreationCirculationJourPrecedentByIdTrain(idTrainCatalogue, date);
      
      //      On récupère les circulations J-1
      liste.clear();
      if (dateJM1!= null ) { liste= dao.getCirculationByIdTrainAndByDate(idTrainCatalogue, dateJM1);
      train=TrainFactory.createTrainCatalogueFromBeans(liste);  listeTC.add(train);   
      }
       
      return listeTC;
   }

   public static TrainCatalogue createTrainCatalogueFromBeans(List<CirculationAdapterDataBean> liste) {
      TrainCatalogue train = new TrainCatalogue();
      if (liste.size()==0) return null;
      CirculationAdapterDataBean bean=liste.get(0);
      
      List<Circulation> listeCircul=new ArrayList<>();
      
      for (CirculationAdapterDataBean circulationAdapterDataBean : liste) {
         Circulation circul=new Circulation();
         circul.createCirculationFromBean(circulationAdapterDataBean);
         listeCircul.add(circul);
      }
      
      //train.getListeNumeros().clear();
      train.setListeNumeros(GetTrainsNums.getTrainsNums(bean.getTrainCatalogueDataBean().getNumeroTrainCatalogue()));
      train.setNumeroUMTrain(bean.getTrainCatalogueDataBean().getNumeroTrainCatalogue());
      train.getListeCirculations().clear();
      train.setListeCirculations(listeCircul);
      train.setOoperatingFlight(bean.getTrainCatalogueDataBean().getOperatingFlight()); 
      train.setIdTrain(bean.getTrainCatalogueDataBean().getIdTrainCatalogue()); 
      train.setDateDebutValidite(bean.getTrainCatalogueDataBean().getDateDebutValidite());
      train.setDateFinValidite(bean.getTrainCatalogueDataBean().getDateFinValidite()); 
      train.setIdTrainCatalogue(bean.getTrainCatalogueDataBean().getIdTrainCatalogue());
      
      
      return train ;
   }

   public static TrainCatalogue createTrainCatalogueFromOriginal(TrainCatalogueDataBean bean) {
      
      Circulation circulation = new Circulation();  
      circulation.setDateDebut(bean.getDateDebutValidite());
      circulation.setDateFin(bean.getDateFinValidite());
      circulation.setOrigine(bean.getIdPointArretOrigine().getCodeResarailPointArret());
      circulation.setDestination(bean.getIdPointArretDestination().getCodeResarailPointArret());
      circulation.setHeureDepart(Integer.valueOf(StringToDate.toFormatedString(bean.getHeureDepartTrainCatalogue())));
      circulation.setHeureArrivee(Integer.valueOf(StringToDate.toFormatedString(bean.getHeureArriveeTrainCatalogue()))); 
      circulation.setJoursCirculation(bean.getRegimeJoursTrainCatalogue()); 
      circulation.setGMTDepart("à ajouter"); 
      circulation.setGMTArrivee("à ajouter");
      
      TrainCatalogue train = new TrainCatalogue() ; 
      train.addCirculation(circulation);
      train.setListeNumeros(GetTrainsNums.getTrainsNums(bean.getNumeroTrainCatalogue()));
      train.setNumeroUMTrain(bean.getNumeroTrainCatalogue());
      train.getListeCirculations().clear();
   
      train.setOoperatingFlight(bean.getOperatingFlight()); 
      train.setIdTrain(bean.getIdTrainCatalogue()); 
      train.setDateDebutValidite(bean.getDateDebutValidite());
      train.setDateFinValidite(bean.getDateFinValidite()); 
      train.setIdTrainCatalogue(bean.getIdTrainCatalogue()); 
      
      return train ;
   } 
   
   

}
