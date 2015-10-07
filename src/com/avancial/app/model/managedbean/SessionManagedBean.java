package com.avancial.app.model.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.avancial.app.data.controller.dao.PointArretDAO;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

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
  
   @PostConstruct
   public void init() {
      this.num =new TreeMap<>();
      this.dates =new TreeMap<>();
      this.listJoursCirculs = new ArrayList<>();
      
   }
   
   public SessionManagedBean() { 
      
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

}
