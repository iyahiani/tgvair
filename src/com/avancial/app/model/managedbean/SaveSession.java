package com.avancial.app.model.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
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
@ViewScoped
public class SaveSession extends AManageBean implements Serializable {

   /**
    * 
    */

   private static final long serialVersionUID = 1L;
   private StringBuilder num ; 
   protected FacesContext fc ;
   
   @Inject
   private PointArretManagedBean pointArretManagedBean;
   @Inject
   protected TrainCatalogueManagedBean tc ; 
   
  
   public String redirectPointArret()  {
      this.num = new StringBuilder() ;
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
      this.num.append( context.getRequestParameterMap().get("ajoutTain:num") ) ; 
      return "pointArret.xhtml?faces-redirect=true"      ;
      }

   public String redirectTrain() {
      
      return "train.xhtml?faces-redirect=true"           ;
   }

   public PointArretManagedBean getPointArretManagedBean() {
      return this.pointArretManagedBean                  ;
   }

   public void setPointArretManagedBean(PointArretManagedBean pointArretManagedBean) {
      this.pointArretManagedBean = pointArretManagedBean ;
   }

   public TrainCatalogueManagedBean getTc() {
      return this.tc                                     ;
   }

   public void setTc(TrainCatalogueManagedBean tc) {
      this.tc = tc                                       ;
   }

   public FacesContext getFc() {
      return this.fc                                     ;
   }

   public void setFc(FacesContext fc) {
      this.fc = fc                                       ;
   }

   public StringBuilder getNum() {
      return this.num                                    ;
   }
   
   public void setNum(StringBuilder num) {
      this.num = num                                     ;
   } 
   
}
