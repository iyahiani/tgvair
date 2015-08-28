package com.avancial.app.model.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.avancial.app.data.controller.dao.PointArretDAO;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

@Named("pointsArret")
@ViewScoped
public class PointArretManagedBean extends AManageBean {

   private static final long serialVersionUID = 1L;
   private String codeResarailPointArret;
   private String codeGDSPointArret;
   private String libellePointArret; 
   
   private boolean selectedHorraire =true ;
   private List<PointArretDataBean> listPointsArret ;

   public PointArretManagedBean() { 
      
      this.listPointsArret = new ArrayList<PointArretDataBean>();
   }
   @Override
   public String add() throws ASocleException {

      PointArretDataBean bean = new PointArretDataBean();
      bean.setCodeGDSPointArret(getCodeGDSPointArret());
      bean.setCodeResarailPointArret(getCodeResarailPointArret());
      bean.setLibellePointArret(getLibellePointArret());
      PointArretDAO dao = new PointArretDAO();
      try {
         dao.save(bean);
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Le point d'arret a été créé."));
         this.closeDialog = true;
         

      } catch (ASocleException e) {// ASocleException
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_ADD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));// e.getClientMessage()
         e.printStackTrace();
      }
      return null ;
   }

   public String getCodeResarailPointArret() {
      return codeResarailPointArret;
   }

   public void setCodeResarailPointArret(String codeResarailPointArret) {
      this.codeResarailPointArret = codeResarailPointArret;
   }

   public String getCodeGDSPointArret() {
      return codeGDSPointArret;
   }

   public void setCodeGDSPointArret(String codeGDSPointArret) {
      this.codeGDSPointArret = codeGDSPointArret;
   }

   public String getLibellePointArret() {
      return libellePointArret;
   }

   public void setLibellePointArret(String libellePointArret) {
      this.libellePointArret = libellePointArret;
   }
   public boolean isSelectedHorraire() {
      return selectedHorraire;
   }
   public void setSelectedHorraire(boolean selectedHorraire) {
      this.selectedHorraire = selectedHorraire;
   }
   public List<PointArretDataBean> getListPointsArret() {
      return listPointsArret;
   }
   public void setListPointsArret(List<PointArretDataBean> listPointsArret) {
      this.listPointsArret = listPointsArret;
   }

}
