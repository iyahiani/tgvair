package com.avancial.app.business.train.circulation;

import com.avancial.app.data.model.databean.CirculationSSIMDataBean;
/**
 * factory des Objets circulations 
 * @author ismael.yahiani
 *
 */
public class CirculationFactory {

   public CirculationFactory() { 
   } 
   /**
    * @author ismael.yahiani
    * permet de récupérée la circulation à partir de la circulation SSIM de la table SSIM Import
    * @param circulationBean
    * @return
    */
   public Circulation createCirculationFromSSIMBean(CirculationSSIMDataBean circulationBean) { 
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
      return circulation ; 
      /*trainsSSIM.addNumeroTrain(circulationBean.getNumeroTrain());
      trainsSSIM.addCirculation(circulation); */
      
   }
}
