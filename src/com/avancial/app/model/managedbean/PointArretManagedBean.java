package com.avancial.app.model.managedbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.context.RequestContext;

import com.avancial.app.data.controller.dao.PointArretDAO;
import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
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
   private Date lundiHeureOuverturePointsArret ;
   private Date lundiHeureFermeturePointsArret ;
   private Date mardiHeureOuverturePointsArret ;
   private Date mardiHeureFermeturePointsArret ;
   private Date mercrediHeureOuverturePointsArret ;
   private Date mercrediHeureFermeturePointsArret ;
   private Date jeudiHeureOuverturePointsArret ;
   private Date jeudiHeureFermeturePointsArret ; 
   private Date vendrediHeureOuverturePointsArret ;
   private Date vendrediHeureFermeturePointsArret ;
   private Date samediHeureOuverturePointsArret ; 
   private Date samediHeureFermeturePointsArret ; 
   private Date dimancheHeureOuverturePointsArret ;
   private Date dimancheHeureFermeturePointsArret ; 
   private boolean selectedHorraire =true ;
   private List<PointArretDataBean> listPointsArret ;
    
   public PointArretManagedBean() { 
      
      this.listPointsArret = new ArrayList<>();
   }
   @Override
   public String add() throws ASocleException {
      PointArretDAO dao = new PointArretDAO();  
      Session session = dao.getSession(); 
      Criteria criteria =  session.createCriteria(PointArretDataBean.class).add(Restrictions.eq("codeGDSPointArret", getCodeGDSPointArret())) ;   
      List<PointArretDataBean> c = new ArrayList<>() ; 
      c.clear();
      c.addAll(criteria.list()) ;  
      if (c.size()>0) {
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "cette Gare Existe Déja"));
      } else 
      {
      PointArretDataBean bean = new PointArretDataBean();
      bean.setCodeGDSPointArret(getCodeGDSPointArret());
      bean.setCodeResarailPointArret(getCodeResarailPointArret());
      bean.setLibellePointArret(getLibellePointArret()); 
      bean.setLundiHeureOuverturePointsArret(getLundiHeureOuverturePointsArret());
      bean.setLundiHeureFermeturePointsArret(getLundiHeureFermeturePointsArret());
      bean.setMardiHeureOuverturePointsArret(mardiHeureOuverturePointsArret)      ; 
      bean.setMardiHeureFermeturePointsArret(mardiHeureFermeturePointsArret)      ;
      bean.setMercrediHeureOuverturePointsArret(mercrediHeureOuverturePointsArret);
      bean.setMercrediHeureFermeturePointsArret(mercrediHeureFermeturePointsArret);
      bean.setJeudiHeureOuverturePointsArret(jeudiHeureOuverturePointsArret)      ;
      bean.setJeudiHeureFermeturePointsArret(jeudiHeureFermeturePointsArret)      ;
      bean.setVendrediHeureOuverturePointsArret(vendrediHeureOuverturePointsArret);
      bean.setVendrediHeureFermeturePointsArret(vendrediHeureFermeturePointsArret);
      bean.setSamediHeureOuverturePointsArret(samediHeureOuverturePointsArret)    ;
      bean.setSamediHeureFermeturePointsArret(samediHeureFermeturePointsArret)    ;
      bean.setDimancheHeureOuverturePointsArret(dimancheHeureOuverturePointsArret);
      bean.setDimancheHeureFermeturePointsArret(dimancheHeureFermeturePointsArret);
      try {
         dao.save(bean);
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Le point d'arret a été créé."));
         this.closeDialog = true;
      } catch (ASocleException e) {// ASocleException
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_ADD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));// e.getClientMessage()
         e.printStackTrace();
      }
      }
      return null ;
   }
 public String redirectTrain() {
      
      return "train.xhtml?faces-redirect=true"           ;
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
   public Date getLundiHeureOuverturePointsArret() {
      return lundiHeureOuverturePointsArret;
   }
   public void setLundiHeureOuverturePointsArret(Date lundiHeureOuverturePointsArret) {
      this.lundiHeureOuverturePointsArret = lundiHeureOuverturePointsArret;
   }
   public Date getLundiHeureFermeturePointsArret() {
      return lundiHeureFermeturePointsArret;
   }
   public void setLundiHeureFermeturePointsArret(Date lundiHeureFermeturePointsArret) {
      this.lundiHeureFermeturePointsArret = lundiHeureFermeturePointsArret;
   }
   public Date getMardiHeureOuverturePointsArret() {
      return mardiHeureOuverturePointsArret;
   }
   public void setMardiHeureOuverturePointsArret(Date mardiHeureOuverturePointsArret) {
      this.mardiHeureOuverturePointsArret = mardiHeureOuverturePointsArret;
   }
   public Date getMardiHeureFermeturePointsArret() {
      return mardiHeureFermeturePointsArret;
   }
   public void setMardiHeureFermeturePointsArret(Date mardiHeureFermeturePointsArret) {
      this.mardiHeureFermeturePointsArret = mardiHeureFermeturePointsArret;
   }
   public Date getMercrediHeureOuverturePointsArret() {
      return mercrediHeureOuverturePointsArret;
   }
   public void setMercrediHeureOuverturePointsArret(Date mercrediHeureOuverturePointsArret) {
      this.mercrediHeureOuverturePointsArret = mercrediHeureOuverturePointsArret;
   }
   public Date getMercrediHeureFermeturePointsArret() {
      return mercrediHeureFermeturePointsArret;
   }
   public void setMercrediHeureFermeturePointsArret(Date mercrediHeureFermeturePointsArret) {
      this.mercrediHeureFermeturePointsArret = mercrediHeureFermeturePointsArret;
   }
   public Date getJeudiHeureOuverturePointsArret() {
      return jeudiHeureOuverturePointsArret;
   }
   public void setJeudiHeureOuverturePointsArret(Date jeudiHeureOuverturePointsArret) {
      this.jeudiHeureOuverturePointsArret = jeudiHeureOuverturePointsArret;
   }
   public Date getJeudiHeureFermeturePointsArret() {
      return jeudiHeureFermeturePointsArret;
   }
   public void setJeudiHeureFermeturePointsArret(Date jeudiHeureFermeturePointsArret) {
      this.jeudiHeureFermeturePointsArret = jeudiHeureFermeturePointsArret;
   }
   public Date getVendrediHeureOuverturePointsArret() {
      return vendrediHeureOuverturePointsArret;
   }
   public void setVendrediHeureOuverturePointsArret(Date vendrediHeureOuverturePointsArret) {
      this.vendrediHeureOuverturePointsArret = vendrediHeureOuverturePointsArret;
   }
   public Date getVendrediHeureFermeturePointsArret() {
      return vendrediHeureFermeturePointsArret;
   }
   public void setVendrediHeureFermeturePointsArret(Date vendrediHeureFermeturePointsArret) {
      this.vendrediHeureFermeturePointsArret = vendrediHeureFermeturePointsArret;
   }
   public Date getSamediHeureOuverturePointsArret() {
      return samediHeureOuverturePointsArret;
   }
   public void setSamediHeureOuverturePointsArret(Date samediHeureOuverturePointsArret) {
      this.samediHeureOuverturePointsArret = samediHeureOuverturePointsArret;
   }
   public Date getSamediHeureFermeturePointsArret() {
      return samediHeureFermeturePointsArret;
   }
   public void setSamediHeureFermeturePointsArret(Date samediHeureFermeturePointsArret) {
      this.samediHeureFermeturePointsArret = samediHeureFermeturePointsArret;
   }
   public Date getDimancheHeureOuverturePointsArret() {
      return dimancheHeureOuverturePointsArret;
   }
   public void setDimancheHeureOuverturePointsArret(Date dimancheHeureOuverturePointsArret) {
      this.dimancheHeureOuverturePointsArret = dimancheHeureOuverturePointsArret;
   }
   public Date getDimancheHeureFermeturePointsArret() {
      return dimancheHeureFermeturePointsArret;
   }
   public void setDimancheHeureFermeturePointsArret(Date dimancheHeureFermeturePointsArret) {
      this.dimancheHeureFermeturePointsArret = dimancheHeureFermeturePointsArret;
   }

}
