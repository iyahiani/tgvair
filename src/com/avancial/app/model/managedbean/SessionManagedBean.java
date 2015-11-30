package com.avancial.app.model.managedbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.model.managedbean.AManageBean;

/**
 * permet de sauvegareder l'etat du Context d'une page
 * 
 * @author ismael.yahiani
 *
 */

@Named("savesession")
@javax.enterprise.context.SessionScoped

public class SessionManagedBean extends AManageBean {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private Map<String,String> num ; 
   private Map<String,Date> dates ; 
   private List<String> listJoursCirculs ; 
   private List<TrainCatalogueDataBean> tempListTrainDataBean  ;
  
   @PostConstruct
   public void init() {
      this.num =new TreeMap<>();
      this.dates =new TreeMap<>();
      this.listJoursCirculs = new ArrayList<>();
      this.tempListTrainDataBean = new TrainCatalogueDAO().getAll() ;  
   }
  
   public  TimeZone getTimeZone() {
      return TimeZone.getDefault();
   }

   public SessionManagedBean() { 
      //this.tempListTrainDataBean.addAll(new TrainCatalogueDAO().getAll()) ;
   }
  
   public Map<String, String> getNum() {
      return this.num;
   }
   public void setNum(Map<String, String> num) {
      this.num = num;
   }

   public Map<String, Date> getDates() {
      return dates;
   }

   public void setDates(Map<String, Date> dates) {
      this.dates = dates;
   }

   public List<String> getListJoursCirculs() {
      return listJoursCirculs;
   }

   public void setListJoursCirculs(List<String> listJoursCirculs) {
      this.listJoursCirculs = listJoursCirculs;
   }

   public List<TrainCatalogueDataBean> getTempListTrainDataBean() {
      return tempListTrainDataBean;
   }

   public void setTempListTrainDataBean(List<TrainCatalogueDataBean> tempListTrainDataBean) {
      this.tempListTrainDataBean = tempListTrainDataBean;
   }

}
