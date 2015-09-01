package com.avancial.app.model.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.SelectableDataModel;

import com.avancial.app.data.controller.dao.PointArretDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueToCompagnieDAO;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

@Named("traincatalogue")
@ViewScoped
public class TrainCatalogueManagedBean extends AManageBean implements Serializable, SelectableDataModel<TrainCatalogueDataBean> {

   private static final long serialVersionUID = 1L;
   private String numeroTrainCatalogue;
   private String numeroTrainCatalogue1;
   private String numeroTrainCatalogue2;
   private PointArretDataBean idPointArretOrigine;
   private PointArretDataBean idPointArretDestination;
   private String originePointArret;
   private String destinationPointArret;
   private Date heureDepartTrainCatalogue;
   private Date heureArriveeTrainCatalogue;
   private String regimeJoursTrainCatalogue;
   private Date dateDebutValidite;
   private Date dateFinValidite;
   private String operatingFlight;
   private List<PointArretDataBean> listGDS;
   private List<TrainCatalogueDataBean> trainsCatalogue;
   private List<TrainCatalogueDataBean> filtredTrainsCatalogue;
   private TrainCatalogueDataBean selectedTrainsCatalogue;
   
   private List<TrainCatalogueDataBean> listTrainsCatAndValid;
   private List<String> listSelectedJoursCirculation;

   public TrainCatalogueManagedBean() {
      this.trainsCatalogue = new ArrayList<>();
      this.idPointArretOrigine = new PointArretDataBean();
      this.idPointArretDestination = new PointArretDataBean();
      this.listTrainsCatAndValid = new ArrayList<>();
      this.reload();

   }

   public void rowSelect(SelectEvent event) {
      TrainCatalogueDataBean tcb = (TrainCatalogueDataBean) event.getObject();
   }

 
   public void onRowUnselect(UnselectEvent event) {
      this.selectedTrainsCatalogue = null;
   }

   public List<PointArretDataBean> getListGDS() {

      return new PointArretDAO().getAllGDS();
   }

   private void reload() {

      this.trainsCatalogue.clear();
      this.trainsCatalogue.addAll(new TrainCatalogueDAO().getAll());
   }

  

   @Override
   public String add() {

      TrainCatalogueDAO dao = new TrainCatalogueDAO();
      Session session = dao.getSession();
      Criteria criteria = session.createCriteria(TrainCatalogueDataBean.class).add(Restrictions.eq("numeroTrainCatalogue1", getNumeroTrainCatalogue1()));
      List<TrainCatalogueDataBean> c = new ArrayList<>();
      c.clear();
      c.addAll(criteria.list());
      if (c.size() > 0) {
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), 
               new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "ce Train Existe Déja"));
      } else {

         PointArretDAO pointArretDAO = new PointArretDAO();
         TrainCatalogueDataBean bean = new TrainCatalogueDataBean();
         bean.setIdPointArretDestination(pointArretDAO.getPointArretbyName(this.destinationPointArret).get(0));
         bean.setIdPointArretOrigine(pointArretDAO.getPointArretbyName(this.originePointArret).get(0));
         bean.setNumeroTrainCatalogue1(getNumeroTrainCatalogue1());
         bean.setNumeroTrainCatalogue2(getNumeroTrainCatalogue2());
         bean.setNumeroTrainCatalogue(getNumeroTrainCatalogue1() + (!getNumeroTrainCatalogue2().isEmpty() ? "-" + getNumeroTrainCatalogue2() : ""));
         bean.setOperatingFlight(getOperatingFlight());
         bean.setOriginePointArret(getOriginePointArret());
         bean.setDestinationPointArret(getDestinationPointArret());
         bean.setHeureDepartTrainCatalogue(getHeureDepartTrainCatalogue());
         bean.setHeureArriveeTrainCatalogue(getHeureArriveeTrainCatalogue());
         bean.setDateDebutValidite(getDateDebutValidite());
         bean.setDateFinValidite(getDateFinValidite());
         // /
         bean.setRegimeJoursTrainCatalogue(formatterString(getListSelectedJoursCirculation().toString()));
         // /

         try {
            dao.save(bean);
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Le Train Catalogue a été créé."));
            this.closeDialog = true;
            RequestContext.getCurrentInstance().update(":tableTrains");

         } catch (ASocleException e) {
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_ADD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));// e.getClientMessage()
            e.printStackTrace();
         }
      }
      return null;
   }

   @Override
   public String delete() throws ASocleException {
      super.delete();
      if (null != this.selectedTrainsCatalogue) {
         TrainCatalogueDAO dao = new TrainCatalogueDAO();
         try {
            dao.delete(this.selectedTrainsCatalogue);
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Enregistrement supprimé"));
            this.closeDialog = true;
         } catch (ASocleException e) {
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_DEL_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", "Enregistrement non effacé"));
         }
      }
      return null;
   }
   public TrainCatalogueDataBean getRowData(String arg0) {
      System.out.println(arg0);
      return null;
   }

   public Object getRowKey(TrainCatalogueDataBean arg0) {

      return null;
   }

   public void goTrain() {
      
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      try {
         context.redirect("pointArret.xhtml?faces-redirect=true");
      } catch (IOException e) {

         e.printStackTrace();
      }

   }

   public String goPointArret() {
      return "/pages/private/pointArret.xhtml?faces-redirect=true";
   }

   @Override
   public String update() throws ASocleException {
      super.update();
      if (null != this.selectedTrainsCatalogue) {
         this.selectedTrainsCatalogue.setNumeroTrainCatalogue(this.selectedTrainsCatalogue.getNumeroTrainCatalogue1()
               + (!this.selectedTrainsCatalogue.getNumeroTrainCatalogue2().isEmpty() ? "-" + this.selectedTrainsCatalogue.getNumeroTrainCatalogue2() : ""));
         TrainCatalogueDAO dao = new TrainCatalogueDAO();
         try {
            dao.update(this.selectedTrainsCatalogue);
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Train modifié"));
            this.closeDialog = true;
            RequestContext.getCurrentInstance().update(":tableTrains");
         } catch (ASocleException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_UPD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
         }
      }
      return null;
   }

   public String formatterString(String chaine) {

      char[] temp = chaine.toCharArray();
      char[] temp2 = { ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
      String s = new String();
      for (int i = 0; i < temp.length; i++) {
         char v = chaine.charAt(i);

         if (v == '1')
            temp2[0] = '1';
         if (v == '2')
            temp2[1] = '2';
         if (v == '3')
            temp2[2] = '3';
         if (v == '4')
            temp2[3] = '4';
         if (v == '5')
            temp2[4] = '5';
         if (v == '6')
            temp2[5] = '6';
         if (v == '7')
            temp2[6] = '7';
      }
      return s.copyValueOf(temp2);
   }

   public String onEdit(CellEditEvent event) {

      /*
       * System.out.println(event.getSource().getClass());
       * RequestContext.getCurrentInstance().update(":tableTrains");
       */
      return null;
   }

   public Boolean getCloseDialog() {
      return this.closeDialog;
   }

   public void setCloseDialog(Boolean closeDialog) {
      this.closeDialog = closeDialog;
   }

   public List<TrainCatalogueDataBean> getListTrainsCatAndValid() {
      return new TrainCatalogueDAO().getAllTrainAndValid();
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

   public void setHeureArriveeTrainCatalogue(Date date) {
      this.heureArriveeTrainCatalogue = date;
   }

   public String getRegimeJoursTrainCatalogue() {
      return regimeJoursTrainCatalogue;
   }

   public void setRegimeJoursTrainCatalogue(String regimeJoursTrainCatalogue) {
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

   public List<TrainCatalogueDataBean> getFiltredTrainsCatalogue() {
      return filtredTrainsCatalogue;
   }

   public void setFiltredTrainsCatalogue(List<TrainCatalogueDataBean> filtredTrainsCatalogue) {
      this.filtredTrainsCatalogue = filtredTrainsCatalogue;
   }

   public TrainCatalogueDataBean getSelectedTrainsCatalogue() {
      return selectedTrainsCatalogue;
   }

   public void setSelectedTrainsCatalogue(TrainCatalogueDataBean selectedTrainsCatalogue) {
      this.selectedTrainsCatalogue = selectedTrainsCatalogue;
   }

   public void setListGDS(List<PointArretDataBean> listGDS) {
      this.listGDS = listGDS;
   }

   public void setListTrainsCatAndValid(List<TrainCatalogueDataBean> listTrainsCatAndValid) {
      this.listTrainsCatAndValid = listTrainsCatAndValid;
   }

   public String getNumeroTrainCatalogue() {
      return numeroTrainCatalogue;
   }

   public void setNumeroTrainCatalogue(String numeroTrainCatalogue) {
      this.numeroTrainCatalogue = numeroTrainCatalogue;
   }

   public List<String> getListSelectedJoursCirculation() {
      return listSelectedJoursCirculation;
   }

   public void setListSelectedJoursCirculation(List<String> listSelectedJoursCirculation) {
      this.listSelectedJoursCirculation = listSelectedJoursCirculation;
   }

}
