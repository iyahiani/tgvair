package com.avancial.app.business.train;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.avancial.app.business.compagnieAerienne.TrainToCompagnie;
import com.avancial.app.business.train.circulation.Circulation;
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
      Circulation circulation = new Circulation();
      circulation.setDateDebut(bean.getDateDebutCirculation());
      circulation.setDateFin(bean.getDateFinCirculation());
      circulation.setOrigine(bean.getTrainCatalogueDataBean().getIdPointArretOrigine().getCodeResarailPointArret());
      circulation.setDestination(bean.getTrainCatalogueDataBean().getIdPointArretDestination().getCodeResarailPointArret());
      circulation.setJoursCirculation(bean.getTrainCatalogueDataBean().getRegimeJoursTrainCatalogue());
    
      circulation.setHeureDepart(Integer.valueOf(bean.getHeureDepart()));
      circulation.setHeureArrivee(Integer.valueOf(bean.getHeureArriver()));
      train.setListeNumeros(GetTrainsNums.getTrainsNums(bean.getTrainCatalogueDataBean().getNumeroTrainCatalogue()));
      train.addCirculation(circulation);
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

}
