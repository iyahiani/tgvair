package com.avancial.app.model.managedbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SelectableDataModel;

import com.avancial.app.data.controller.dao.PointArretDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueToCompagnieDAO;
import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

@Named("pointsArret")
@ViewScoped
public class PointArretManagedBean extends AManageBean implements SelectableDataModel<PointArretDataBean> {

   private static final long serialVersionUID = 1L;
   private int idPointArret ;
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
   private Date ferieHeureOuverturePointsArret ; 
   private Date ferieHeureFermeturePointsArret ;
   private boolean selectedHorraire =true ; 
   private PointArretDataBean selectedPointArret ;
   private List<PointArretDataBean> listPointsArret ;
   private List<PointArretDataBean> filtredPointArret; 
   
   
   
   
   public PointArretManagedBean() { 
      
      super() ;
   } 
   
   
   @PostConstruct 
   
   public void init() {
      this.listPointsArret = new PointArretDAO().getAll();
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
      bean.setMardiHeureOuverturePointsArret(this.mardiHeureOuverturePointsArret)      ; 
      bean.setMardiHeureFermeturePointsArret(this.mardiHeureFermeturePointsArret)      ;
      bean.setMercrediHeureOuverturePointsArret(this.mercrediHeureOuverturePointsArret);
      bean.setMercrediHeureFermeturePointsArret(this.mercrediHeureFermeturePointsArret);
      bean.setJeudiHeureOuverturePointsArret(this.jeudiHeureOuverturePointsArret)      ;
      bean.setJeudiHeureFermeturePointsArret(this.jeudiHeureFermeturePointsArret)      ;
      bean.setVendrediHeureOuverturePointsArret(this.vendrediHeureOuverturePointsArret);
      bean.setVendrediHeureFermeturePointsArret(this.vendrediHeureFermeturePointsArret);
      bean.setSamediHeureOuverturePointsArret(this.samediHeureOuverturePointsArret)    ;
      bean.setSamediHeureFermeturePointsArret(this.samediHeureFermeturePointsArret)    ;
      bean.setDimancheHeureOuverturePointsArret(this.dimancheHeureOuverturePointsArret);
      bean.setDimancheHeureFermeturePointsArret(this.dimancheHeureFermeturePointsArret);
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
   
   @Override
   public String update() throws ASocleException {
      super.update();
           PointArretDAO dao = new PointArretDAO();
         try {
            dao.update(this.selectedPointArret);
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Point d'Arrêt modifié"));
            this.closeDialog = true;
            RequestContext.getCurrentInstance().update(":tableCompAerienne");
         } catch (ASocleException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_UPD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
         }
      
      return null;
   } 
 public String redirectTrain() {
      
      return "train.xhtml?faces-redirect=true"           ;
   }

 public void rowSelect(SelectEvent event) {
    this.selectedPointArret = (PointArretDataBean) event.getObject(); 
   
         
 }
   
 
   public String getCodeResarailPointArret() {
      return this.codeResarailPointArret;
   }

   public void setCodeResarailPointArret(String codeResarailPointArret) {
      this.codeResarailPointArret = codeResarailPointArret;
   }

   public String getCodeGDSPointArret() {
      return this.codeGDSPointArret;
   }

   public void setCodeGDSPointArret(String codeGDSPointArret) {
      this.codeGDSPointArret = codeGDSPointArret;
   }

   public String getLibellePointArret() {
      return this.libellePointArret;
   }

   public void setLibellePointArret(String libellePointArret) {
      this.libellePointArret = libellePointArret;
   }
   public boolean isSelectedHorraire() {
      return this.selectedHorraire;
   }
   public void setSelectedHorraire(boolean selectedHorraire) {
      this.selectedHorraire = selectedHorraire;
   }
   public List<PointArretDataBean> getListPointsArret() {
      return this.listPointsArret;
   }
   public void setListPointsArret(List<PointArretDataBean> listPointsArret) {
      this.listPointsArret = listPointsArret;
   }
   public Date getLundiHeureOuverturePointsArret() {
      return this.lundiHeureOuverturePointsArret;
   }
   public void setLundiHeureOuverturePointsArret(Date lundiHeureOuverturePointsArret) {
      this.lundiHeureOuverturePointsArret = lundiHeureOuverturePointsArret;
   }
   public Date getLundiHeureFermeturePointsArret() {
      return this.lundiHeureFermeturePointsArret;
   }
   public void setLundiHeureFermeturePointsArret(Date lundiHeureFermeturePointsArret) {
      this.lundiHeureFermeturePointsArret = lundiHeureFermeturePointsArret;
   }
   public Date getMardiHeureOuverturePointsArret() {
      return this.mardiHeureOuverturePointsArret;
   }
   public void setMardiHeureOuverturePointsArret(Date mardiHeureOuverturePointsArret) {
      this.mardiHeureOuverturePointsArret = mardiHeureOuverturePointsArret;
   }
   public Date getMardiHeureFermeturePointsArret() {
      return this.mardiHeureFermeturePointsArret;
   }
   public void setMardiHeureFermeturePointsArret(Date mardiHeureFermeturePointsArret) {
      this.mardiHeureFermeturePointsArret = mardiHeureFermeturePointsArret;
   }
   public Date getMercrediHeureOuverturePointsArret() {
      return this.mercrediHeureOuverturePointsArret;
   }
   public void setMercrediHeureOuverturePointsArret(Date mercrediHeureOuverturePointsArret) {
      this.mercrediHeureOuverturePointsArret = mercrediHeureOuverturePointsArret;
   }
   public Date getMercrediHeureFermeturePointsArret() {
      return this.mercrediHeureFermeturePointsArret;
   }
   public void setMercrediHeureFermeturePointsArret(Date mercrediHeureFermeturePointsArret) {
      this.mercrediHeureFermeturePointsArret = mercrediHeureFermeturePointsArret;
   }
   public Date getJeudiHeureOuverturePointsArret() {
      return this.jeudiHeureOuverturePointsArret;
   }
   public void setJeudiHeureOuverturePointsArret(Date jeudiHeureOuverturePointsArret) {
      this.jeudiHeureOuverturePointsArret = jeudiHeureOuverturePointsArret;
   }
   public Date getJeudiHeureFermeturePointsArret() {
      return this.jeudiHeureFermeturePointsArret;
   }
   public void setJeudiHeureFermeturePointsArret(Date jeudiHeureFermeturePointsArret) {
      this.jeudiHeureFermeturePointsArret = jeudiHeureFermeturePointsArret;
   }
   public Date getVendrediHeureOuverturePointsArret() {
      return this.vendrediHeureOuverturePointsArret;
   }
   public void setVendrediHeureOuverturePointsArret(Date vendrediHeureOuverturePointsArret) {
      this.vendrediHeureOuverturePointsArret = vendrediHeureOuverturePointsArret;
   }
   public Date getVendrediHeureFermeturePointsArret() {
      return this.vendrediHeureFermeturePointsArret;
   }
   public void setVendrediHeureFermeturePointsArret(Date vendrediHeureFermeturePointsArret) {
      this.vendrediHeureFermeturePointsArret = vendrediHeureFermeturePointsArret;
   }
   public Date getSamediHeureOuverturePointsArret() {
      return this.samediHeureOuverturePointsArret;
   }
   public void setSamediHeureOuverturePointsArret(Date samediHeureOuverturePointsArret) {
      this.samediHeureOuverturePointsArret = samediHeureOuverturePointsArret;
   }
   public Date getSamediHeureFermeturePointsArret() {
      return this.samediHeureFermeturePointsArret;
   }
   public void setSamediHeureFermeturePointsArret(Date samediHeureFermeturePointsArret) {
      this.samediHeureFermeturePointsArret = samediHeureFermeturePointsArret;
   }
   public Date getDimancheHeureOuverturePointsArret() {
      return this.dimancheHeureOuverturePointsArret;
   }
   public void setDimancheHeureOuverturePointsArret(Date dimancheHeureOuverturePointsArret) {
      this.dimancheHeureOuverturePointsArret = dimancheHeureOuverturePointsArret;
   }
   public Date getDimancheHeureFermeturePointsArret() {
      return this.dimancheHeureFermeturePointsArret;
   }
   public void setDimancheHeureFermeturePointsArret(Date dimancheHeureFermeturePointsArret) {
      this.dimancheHeureFermeturePointsArret = dimancheHeureFermeturePointsArret;
   }
   public Date getFerieHeureOuverturePointsArret() {
      return this.ferieHeureOuverturePointsArret;
   }
   public void setFerieHeureOuverturePointsArret(Date ferieHeureOuverturePointsArret) {
      this.ferieHeureOuverturePointsArret = ferieHeureOuverturePointsArret;
   }
   public Date getFerieHeureFermeturePointsArret() {
      return this.ferieHeureFermeturePointsArret;
   }
   public void setFerieHeureFermeturePointsArret(Date ferieHeureFermeturePointsArret) {
      this.ferieHeureFermeturePointsArret = ferieHeureFermeturePointsArret;
   }
   public List<PointArretDataBean> getFiltredPointArret() {
      return filtredPointArret;
   }
   public void setFiltredPointArret(List<PointArretDataBean> filtredPointArret) {
      this.filtredPointArret = filtredPointArret;
   }
   public int getIdPointArret() {
      return idPointArret;
   }
   public void setIdPointArret(int idPointArret) {
      this.idPointArret = idPointArret;
   }

   
   public Object getRowKey(PointArretDataBean object) {
      
      return null;
   }
   public PointArretDataBean getRowData(String rowKey) {
      
      return null;
   }
   public PointArretDataBean getSelectedPointArret() {
      return selectedPointArret;
   }
   public void setSelectedPointArret(PointArretDataBean selectedPointArret) {
      this.selectedPointArret = selectedPointArret;
   }

}
