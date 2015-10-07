package com.avancial.app.model.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.context.RequestContext;

import com.avancial.app.data.controller.dao.PointArretDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.resources.constants.APP_TgvAir;
import com.avancial.app.resources.utils.StringToFormatedString;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.SOCLE_constants;


@Named("ajouttrain") 
@ViewScoped

/**
 * 
 * @author ismael.yahiani
 *  Managed Bean de la page Train   
 */

public class TrainManagedBean extends AManageBean{

   /**
    * 
    */
   private static final long serialVersionUID = 1L; 
   private String numeroTrainCatalogue;
   private String numeroTrainCatalogue1;
   private String numeroTrainCatalogue2;
   private PointArretDataBean idPointArretOrigine;
   private PointArretDataBean idPointArretDestination;
   private Date heureDepartTrainCatalogue;
   private Date heureArriveeTrainCatalogue;
   private String regimeJoursTrainCatalogue;
   private Date dateDebutValidite;
   private Date dateFinValidite;
   private String operatingFlight;
  
   private List<String> listSelectedJoursCirculation;
  
   @Inject
   SessionManagedBean sessionManagedBean;
   
   public TrainManagedBean() { 
      this.idPointArretOrigine = new PointArretDataBean();
      this.idPointArretDestination = new PointArretDataBean();
   }
   
   @PostConstruct
   public void init() {

      if (this.sessionManagedBean.getNum() != null) {
         
               if (this.sessionManagedBean.getNum().get("num1") != null)
                  this.numeroTrainCatalogue1 = this.sessionManagedBean.getNum().get("num1");
               if (this.sessionManagedBean.getNum().get("num2") != null)
                  this.numeroTrainCatalogue2 = this.sessionManagedBean.getNum().get("num2");
               if (this.sessionManagedBean.getNum().get("op") != null)
                  this.operatingFlight = this.sessionManagedBean.getNum().get("op");
               if (this.sessionManagedBean.getListJoursCirculs().size()>0) 
                  this.listSelectedJoursCirculation =this.sessionManagedBean.getListJoursCirculs();
               if (this.sessionManagedBean.getDates().get("debut") != null)
                  this.dateDebutValidite = this.sessionManagedBean.getDates().get("debut");
               if (this.sessionManagedBean.getDates().get("fin") != null)
                  this.dateFinValidite = this.sessionManagedBean.getDates().get("fin");
               if (this.sessionManagedBean.getNum().get("origine") != null)
                  this.idPointArretOrigine.setLibellePointArret(this.sessionManagedBean.getNum().get("origine"));  
               if (this.sessionManagedBean.getNum().get("destination") != null)
                  this.idPointArretDestination.setLibellePointArret(this.sessionManagedBean.getNum().get("destination"));
               if (this.sessionManagedBean.getDates().get("heureArr") != null)
                  this.heureArriveeTrainCatalogue = this.sessionManagedBean.getDates().get("heureArr");
               if (this.sessionManagedBean.getDates().get("heureDep") != null)
                  this.heureDepartTrainCatalogue = this.sessionManagedBean.getDates().get("heureDep");
               this.sessionManagedBean.getNum().clear();
               this.sessionManagedBean.getDates().clear(); 
               this.sessionManagedBean.getListJoursCirculs().clear();
      }
   }
   @Override
   public String add() {

     /* TrainCatalogueDAO dao = new TrainCatalogueDAO();
      Session session = dao.getSession();
      Criteria criteria = session.createCriteria(TrainCatalogueDataBean.class).add(Restrictions.eq("numeroTrainCatalogue1", getNumeroTrainCatalogue1()));
      List<TrainCatalogueDataBean> c = new ArrayList<>();
      c.clear();
      c.addAll(criteria.list());
      if (c.size() > 0) {
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "ce Train Existe Déja"));
      } else {*/
         TrainCatalogueDAO dao = new TrainCatalogueDAO();
         PointArretDAO pointArretDAO = new PointArretDAO();
         TrainCatalogueDataBean bean = new TrainCatalogueDataBean();
         bean.setIdPointArretDestination(pointArretDAO.getPointArretbyName(this.idPointArretDestination.getLibellePointArret()).get(0));
         bean.setIdPointArretOrigine(pointArretDAO.getPointArretbyName(this.idPointArretOrigine.getLibellePointArret()).get(0)); // pointArretDAO.getPointArretbyName(this.originePointArret).get(0)
         bean.setNumeroTrainCatalogue1(getNumeroTrainCatalogue1());
         bean.setNumeroTrainCatalogue2(getNumeroTrainCatalogue2());
         bean.setNumeroTrainCatalogue(getNumeroTrainCatalogue1() + (!getNumeroTrainCatalogue2().isEmpty() ? " - " + getNumeroTrainCatalogue2() : ""));
         bean.setOperatingFlight(getOperatingFlight());
         // bean.setOriginePointArret(getOriginePointArret());
         // bean.setDestinationPointArret(getDestinationPointArret());
         bean.setHeureDepartTrainCatalogue(getHeureDepartTrainCatalogue());
         bean.setHeureArriveeTrainCatalogue(getHeureArriveeTrainCatalogue());
         bean.setDateDebutValidite(getDateDebutValidite());
         bean.setDateFinValidite(getDateFinValidite());
         // /
         bean.setRegimeJoursTrainCatalogue(StringToFormatedString.formatterString(getListSelectedJoursCirculation().toString()));
         // /
         try {
            dao.save(bean);
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Le Train Catalogue a été créé."));
            RequestContext.getCurrentInstance().update(":tableTrains");
            this.closeDialog = true;

         } catch (ASocleException e) {
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_ADD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));// e.getClientMessage()
            e.printStackTrace();
         }
      
      return null;
   }
   
   @SuppressWarnings("static-method")
   public List<PointArretDataBean> getListGDS() {
      return new PointArretDAO().getAllGDS();
   }
   public String goTrain() {

      System.out.println("TrainCatalogueManagedBean.goTrain()");
      this.sessionManagedBean.getNum().put("num1", this.numeroTrainCatalogue1);
      this.sessionManagedBean.getNum().put("num2", this.numeroTrainCatalogue2);
      this.sessionManagedBean.getNum().put("op", this.operatingFlight);
      this.sessionManagedBean.getListJoursCirculs().addAll(this.listSelectedJoursCirculation);
      this.sessionManagedBean.getDates().put("debut", this.dateDebutValidite);
      this.sessionManagedBean.getDates().put("fin", this.dateFinValidite);
      this.sessionManagedBean.getNum().put("origine", this.idPointArretOrigine.getLibellePointArret());
      this.sessionManagedBean.getNum().put("destination", this.idPointArretDestination.getLibellePointArret());
      this.sessionManagedBean.getDates().put("heureDep", this.heureDepartTrainCatalogue);
      this.sessionManagedBean.getDates().put("heureArr", this.heureArriveeTrainCatalogue);
      return APP_TgvAir.NAVIGATION_POINTARRET.toString();
   }
   
   public String getNumeroTrainCatalogue() {
      return this.numeroTrainCatalogue;
   } 
   public void setNumeroTrainCatalogue(String numeroTrainCatalogue) {
      this.numeroTrainCatalogue = numeroTrainCatalogue;
   }
   public String getNumeroTrainCatalogue1() {
      return this.numeroTrainCatalogue1;
   }
   public void setNumeroTrainCatalogue1(String numeroTrainCatalogue1) {
      this.numeroTrainCatalogue1 = numeroTrainCatalogue1;
   }
   public String getNumeroTrainCatalogue2() {
      return this.numeroTrainCatalogue2;
   }
   public void setNumeroTrainCatalogue2(String numeroTrainCatalogue2) {
      this.numeroTrainCatalogue2 = numeroTrainCatalogue2;
   }
   public PointArretDataBean getIdPointArretOrigine() {
      return this.idPointArretOrigine;
   }
   public void setIdPointArretOrigine(PointArretDataBean idPointArretOrigine) {
      this.idPointArretOrigine = idPointArretOrigine;
   }
   public PointArretDataBean getIdPointArretDestination() {
      return this.idPointArretDestination;
   }
   public void setIdPointArretDestination(PointArretDataBean idPointArretDestination) {
      this.idPointArretDestination = idPointArretDestination;
   }
 
   public Date getHeureDepartTrainCatalogue() {
      return this.heureDepartTrainCatalogue;
   }
   public void setHeureDepartTrainCatalogue(Date heureDepartTrainCatalogue) {
      this.heureDepartTrainCatalogue = heureDepartTrainCatalogue;
   }
   public Date getHeureArriveeTrainCatalogue() {
      return this.heureArriveeTrainCatalogue;
   }
   public void setHeureArriveeTrainCatalogue(Date heureArriveeTrainCatalogue) {
      this.heureArriveeTrainCatalogue = heureArriveeTrainCatalogue;
   }
   public String getRegimeJoursTrainCatalogue() {
      return this.regimeJoursTrainCatalogue;
   }
   public void setRegimeJoursTrainCatalogue(String regimeJoursTrainCatalogue) {
      this.regimeJoursTrainCatalogue = regimeJoursTrainCatalogue;
   }
   public Date getDateDebutValidite() {
      return this.dateDebutValidite;
   }
   public void setDateDebutValidite(Date dateDebutValidite) {
      this.dateDebutValidite = dateDebutValidite;
   }
   public Date getDateFinValidite() {
      return this.dateFinValidite;
   }
   public void setDateFinValidite(Date dateFinValidite) {
      this.dateFinValidite = dateFinValidite;
   }
   public String getOperatingFlight() {
      return this.operatingFlight;
   }
   public void setOperatingFlight(String operatingFlight) {
      this.operatingFlight = operatingFlight;
   }
   
   public List<String> getListSelectedJoursCirculation() {
      return this.listSelectedJoursCirculation;
   }
   public void setListSelectedJoursCirculation(List<String> listSelectedJoursCirculation) {
      this.listSelectedJoursCirculation = listSelectedJoursCirculation;
   }
   
   
   

}
