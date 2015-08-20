package com.avancial.app.model.managedbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.avancial.app.data.controller.dao.PointArretDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

@Named("traincatalogue")
@ViewScoped
public class TrainCatalogueManagedBean extends AManageBean{ 
   
   private static final long serialVersionUID = 1L; 
   private String numeroTrainCatalogue1 ;
   private String numeroTrainCatalogue2 ;
   private PointArretDataBean idPointArretOrigine ; 
   private PointArretDataBean idPointArretDestination ;
   private String originePointArret ;
   private String destinationPointArret ;
   private Date heureDepartTrainCatalogue ;
   private Date heureArriveeTrainCatalogue ;
   private Date regimeJoursTrainCatalogue ;
   private Date dateDebutValidite ; 
   private Date dateFinValidite ;  
   private String operatingFlight;
   private List<PointArretDataBean> listGDS ;
   private List<TrainCatalogueDataBean> trainsCatalogue ; 
  
   

   public TrainCatalogueManagedBean() {
      this.trainsCatalogue = new ArrayList<>();
      this.idPointArretOrigine = new PointArretDataBean() ;
      this.idPointArretDestination = new PointArretDataBean() ;
      this.reload();

   }

   public List<PointArretDataBean> getListGDS() {
      
      return new PointArretDAO().getAllGDS();
   }

   private void reload() {
      this.trainsCatalogue.clear();
      this.trainsCatalogue.addAll(new TrainCatalogueDAO().getAll());
   }

   public void clear() {
      
   }
   public String add(){
      TrainCatalogueDataBean bean = new TrainCatalogueDataBean() ; 
      
      bean.setNumeroTrainCatalogue1(getNumeroTrainCatalogue1()); 
      bean.setNumeroTrainCatalogue2(getNumeroTrainCatalogue2());
      bean.setNumeroTrainCatalogue(getNumeroTrainCatalogue1()+(!getNumeroTrainCatalogue2().isEmpty()? "-"+getNumeroTrainCatalogue2() : ""));
      bean.setOperatingFlight(getOperatingFlight());
      bean.setOriginePointArret(getOriginePointArret());
      bean.setDestinationPointArret(getDestinationPointArret());
      bean.setHeureDepartTrainCatalogue(getHeureDepartTrainCatalogue());
      bean.setHeureArriveeTrainCatalogue(getHeureArriveeTrainCatalogue());
      bean.setDateDebutValidite(getDateDebutValidite());
      bean.setDateFinValidite(getDateFinValidite());
      bean.setRegimeJoursTrainCatalogue(getRegimeJoursTrainCatalogue());
     
      TrainCatalogueDAO dao = new TrainCatalogueDAO();
      try {
         dao.save(bean);
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "La compagnie a été créé."));
         this.closeDialog = true;
         RequestContext.getCurrentInstance().update(":tableTrains");

      } catch (ASocleException e) {// ASocleException
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_ADD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));// e.getClientMessage()
         e.printStackTrace();
      }
      return null;
   }

   public String getNumeroTrainCatalogue1() {
      return numeroTrainCatalogue1;
   }

   public void setNumeroTrainCatalogue1(String numeroTrainCatalogue1) {
      this.numeroTrainCatalogue1 = numeroTrainCatalogue1;
   }

   public String getNumeroTrainCatalogue2() {
      return numeroTrainCatalogue2;
   }

   public void setNumeroTrainCatalogue2(String numeroTrainCatalogue2) {
      this.numeroTrainCatalogue2 = numeroTrainCatalogue2;
   }

   public String getOriginePointArret() {
      return originePointArret;
   }

   public void setOriginePointArret(String originePointArret) {
      this.originePointArret = originePointArret;
   }

   public String getDestinationPointArret() {
      return destinationPointArret;
   }

   public void setDestinationPointArret(String destinationPointArret) {
      this.destinationPointArret = destinationPointArret;
   }

   public Date getHeureDepartTrainCatalogue() {
      return heureDepartTrainCatalogue;
   }

   public void setHeureDepartTrainCatalogue(Date heureDepartTrainCatalogue) {
      this.heureDepartTrainCatalogue = heureDepartTrainCatalogue;
   }

   public Date getHeureArriveeTrainCatalogue() {
      return heureArriveeTrainCatalogue;
   }

   public void setHeureArriveeTrainCatalogue(Date heureArriveeTrainCatalogue) {
      this.heureArriveeTrainCatalogue = heureArriveeTrainCatalogue;
   }

   public Date getRegimeJoursTrainCatalogue() {
      return regimeJoursTrainCatalogue;
   }

   public void setRegimeJoursTrainCatalogue(Date regimeJoursTrainCatalogue) {
      this.regimeJoursTrainCatalogue = regimeJoursTrainCatalogue;
   }

   public Date getDateDebutValidite() {
      return dateDebutValidite;
   }

   public void setDateDebutValidite(Date dateDebutValidite) {
      this.dateDebutValidite = dateDebutValidite;
   }

   public Date getDateFinValidite() {
      return dateFinValidite;
   }

   public void setDateFinValidite(Date dateFinValidite) {
      this.dateFinValidite = dateFinValidite;
   }

   public String getOperatingFlight() {
      return operatingFlight;
   }

   public void setOperatingFlight(String operatingFlight) {
      this.operatingFlight = operatingFlight;
   }

   public List<TrainCatalogueDataBean> getTrainsCatalogue() {
      return trainsCatalogue;
   }

   public void setTrainsCatalogue(List<TrainCatalogueDataBean> trainsCatalogue) {
      this.trainsCatalogue = trainsCatalogue;
   }

   public PointArretDataBean getIdPointArretOrigine() {
      return idPointArretOrigine;
   }

   public void setIdPointArretOrigine(PointArretDataBean idPointArretOrigine) {
      this.idPointArretOrigine = idPointArretOrigine;
   }

   public PointArretDataBean getIdPointArretDestination() {
      return idPointArretDestination;
   }

   public void setIdPointArretDestination(PointArretDataBean idPointArretDestination) {
      this.idPointArretDestination = idPointArretDestination;
   }


   
}
