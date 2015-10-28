package com.avancial.app.model.managedbean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SelectableDataModel;

import com.avancial.app.data.controller.dao.CirculationDAO;
import com.avancial.app.data.controller.dao.PointArretDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.resources.connectionsUtils.SelectWithJDBC;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.app.resources.utils.StringToFormatedString;
import com.avancial.app.traitements.TraitementExportDAO;
import com.avancial.app.traitements.TraitementImportDAO;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

@Named("traincatalogue")
@ViewScoped
public class TrainCatalogueManagedBean extends AManageBean implements SelectableDataModel<TrainCatalogueDataBean> {

   private static final long serialVersionUID = 1L;
   private int idTrainCatalogue;
   private String numeroTrainCatalogue;
   private String numeroTrainCatalogue1;
   private String numeroTrainCatalogue2;
   private PointArretDataBean idPointArretOrigine;
   private PointArretDataBean idPointArretDestination;
   private String origine;
   private String destination;
   private Date heureDepartTrainCatalogue;
   private Date heureArriveeTrainCatalogue;
   private String regimeJoursTrainCatalogue;
   private Date dateDebutValidite;
   private Date dateFinValidite;
   private String operatingFlight;
   private List<TrainCatalogueDataBean> trainsCatalogue;
   private List<TrainCatalogueDataBean> filtredTrainsCatalogue;
   private TrainCatalogueDataBean selectedTrainsCatalogue;
   private List<String> listSelectedJoursCirculation;

   @Inject
   private SessionManagedBean session;

   public TrainCatalogueManagedBean() {
      this.trainsCatalogue = new ArrayList<>();
      this.idPointArretOrigine = new PointArretDataBean();
      this.idPointArretDestination = new PointArretDataBean();

      this.reload();
   }

   public void rowSelect(SelectEvent event) {

      this.selectedTrainsCatalogue = (TrainCatalogueDataBean) event.getObject();

   }

   public TimeZone getTimeZone() {

      return this.session.getTimeZone();
   }

   /**
    * recharger la liste des trains Catalogue
    */
   public void reload() {
      this.trainsCatalogue.clear();
      this.trainsCatalogue.addAll(new TrainCatalogueDAO().getAll());
   }

   /**
    * supprime en cascade le train et ces circulations
    */
   @Override
   public String delete() throws ASocleException {
      super.delete();

      if (null != this.selectedTrainsCatalogue) {

         TrainCatalogueDAO dao = new TrainCatalogueDAO();
         try {
            new CirculationDAO().deleteCirculationByID(this.selectedTrainsCatalogue.getIdTrainCatalogue());
            dao.delete(this.selectedTrainsCatalogue);
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Enregistrement supprimé"));
            RequestContext.getCurrentInstance().update(":tableTrains:tableTrains");
            this.closeDialog = true;
         } catch (ASocleException e) {
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_DEL_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", "Enregistrement non supprimé"));
         }
      } else {
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "veuillez selectionner une ligne"));
         this.closeDialog = true;
      }
      return null;
   }

   @Override
   public TrainCatalogueDataBean getRowData(String arg0) {

      return null;
   }

   @Override
   public Object getRowKey(TrainCatalogueDataBean arg0) {

      return null;
   }

   public void saveState() {
      // ///////
   }

   /**
    * modifer le train et ca cerculation dans les tables trainsCatalogue /
    * Circulation
    */

   @Override
   public String update() throws ASocleException {

      TrainCatalogueDAO dao = new TrainCatalogueDAO();
      TrainCatalogueDataBean bean = new TrainCatalogueDataBean();
      PointArretDAO daoArretDAO = new PointArretDAO();

      if (null != this.selectedTrainsCatalogue) {
         bean.setNumeroTrainCatalogue(this.selectedTrainsCatalogue.getNumeroTrainCatalogue1()
               + (!this.selectedTrainsCatalogue.getNumeroTrainCatalogue2().isEmpty() ? "-" + this.selectedTrainsCatalogue.getNumeroTrainCatalogue2() : ""));
         super.update();

         bean.setIdTrainCatalogue(this.selectedTrainsCatalogue.getIdTrainCatalogue());
         bean.setIdPointArretOrigine(daoArretDAO.getPointArretbyName(this.origine).get(0));
         bean.setIdPointArretDestination(daoArretDAO.getPointArretbyName(this.destination).get(0));
         bean.setNumeroTrainCatalogue(this.selectedTrainsCatalogue.getNumeroTrainCatalogue());
         bean.setNumeroTrainCatalogue1(this.selectedTrainsCatalogue.getNumeroTrainCatalogue1());
         bean.setNumeroTrainCatalogue2(this.selectedTrainsCatalogue.getNumeroTrainCatalogue2());
         bean.setHeureDepartTrainCatalogue(this.heureDepartTrainCatalogue); // selectedTrainsCatalogue.getHeureDepartTrainCatalogue()
         bean.setHeureArriveeTrainCatalogue(this.heureArriveeTrainCatalogue);
         bean.setDateDebutValidite(this.dateDebutValidite);
         bean.setDateFinValidite(this.dateFinValidite);
         bean.setOperatingFlight(this.selectedTrainsCatalogue.getOperatingFlight());
         bean.setRegimeJoursTrainCatalogue(this.selectedTrainsCatalogue.getRegimeJoursTrainCatalogue());

         CirculationDAO circulationDAO = new CirculationDAO();
/*
         SelectWithJDBC jdbc = new SelectWithJDBC();
         TrainCatalogueDataBean tc = new TrainCatalogueDataBean();
         try {
            tc = jdbc.getCirculsFromCirculTable(this.selectedTrainsCatalogue.getIdTrainCatalogue());
         } catch (SQLException e1) {

            e1.printStackTrace();
         }
*/
         CirculationAdapterDataBean c = null;
         dao.getTrainCatByID(this.selectedTrainsCatalogue.getIdTrainCatalogue()).get(0);

         for (TrainCatalogueDataBean t : this.trainsCatalogue) {
            if (this.selectedTrainsCatalogue.getIdTrainCatalogue() == t.getIdTrainCatalogue()) {
               if (this.selectedTrainsCatalogue.getDateFinValidite().after(t.getDateFinValidite())) {
                  c = new CirculationAdapterDataBean();
                  c.setDateCreationLigneTrain(new Date());
                  c.setDateDebutCirculation(t.getDateFinValidite());
                  c.setDateFinCirculation(this.selectedTrainsCatalogue.getDateFinValidite());
                  c.setHeureDepart(StringToDate.toFormatedString(t.getHeureDepartTrainCatalogue()));
                  c.setHeureArriver(StringToDate.toFormatedString(t.getHeureArriveeTrainCatalogue()));
                  c.setRegimeCirculation(t.getRegimeJoursTrainCatalogue());
                  c.setTraitementImport(new TraitementImportDAO().getLastID().get(0));
                  c.setTraitementExport(new TraitementExportDAO().getLastID());
                  break;
               }
            }
         }

         if (c != null) {
            new CirculationDAO().save(c);
         }

         try {
            dao.update(bean);
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Train modifié"));
            this.reload();
            RequestContext.getCurrentInstance().update(":tableTrains");
            this.closeDialog = true;
         } catch (ASocleException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_UPD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
         }
      }

      return null;
   }

   @SuppressWarnings("static-method")
   public void recupererBeanOrigine(SelectEvent event) {
      event.getObject();
   }

   public Boolean getCloseDialog() {
      return this.closeDialog;
   }

   public void setCloseDialog(Boolean closeDialog) {
      this.closeDialog = closeDialog;
   }

   @SuppressWarnings("static-method")
   public List<TrainCatalogueDataBean> getListTrainsCatAndValid() {
      return new TrainCatalogueDAO().getAllTrainAndValid();
   }

   public String getMinDate() {

      if (this.selectedTrainsCatalogue != null) {
         return StringToDate.toFormatedStringddMMyyyy(this.selectedTrainsCatalogue.getDateDebutValidite());
      }
      return "";
   }

   public String getMaxDate() {

      if (this.selectedTrainsCatalogue != null) {
         return StringToDate.toFormatedStringddMMyyyy(this.selectedTrainsCatalogue.getDateFinValidite());
      }
      return "";
   }

   // ////////////// Getters And Setters
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

   public Date getHeureDepartTrainCatalogue() {
      if (this.selectedTrainsCatalogue != null)
         return this.selectedTrainsCatalogue.getHeureDepartTrainCatalogue();
      return this.heureDepartTrainCatalogue;
   }

   public void setHeureDepartTrainCatalogue(Date heureDepartTrainCatalogue) {
      this.heureDepartTrainCatalogue = heureDepartTrainCatalogue;
   }

   public Date getHeureArriveeTrainCatalogue() {

      if (this.selectedTrainsCatalogue != null)
         return this.selectedTrainsCatalogue.getHeureArriveeTrainCatalogue();
      return this.heureArriveeTrainCatalogue;
   }

   public void setHeureArriveeTrainCatalogue(Date date) {
      this.heureArriveeTrainCatalogue = date;
   }

   public String getRegimeJoursTrainCatalogue() {
      return this.regimeJoursTrainCatalogue;
   }

   public void setRegimeJoursTrainCatalogue(String regimeJoursTrainCatalogue) {
      this.regimeJoursTrainCatalogue = regimeJoursTrainCatalogue;
   }

   public Date getDateDebutValidite() {
      if (this.selectedTrainsCatalogue != null)
         return this.selectedTrainsCatalogue.getDateDebutValidite();
      return this.dateDebutValidite;
   }

   public void setDateDebutValidite(Date dateDebutValidite) {
      this.dateDebutValidite = dateDebutValidite;
   }

   public Date getDateFinValidite() {
      if (this.selectedTrainsCatalogue != null)
         return this.selectedTrainsCatalogue.getDateFinValidite();
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

   public List<TrainCatalogueDataBean> getTrainsCatalogue() {
      return this.trainsCatalogue;
   }

   public void setTrainsCatalogue(List<TrainCatalogueDataBean> trainsCatalogue) {
      this.trainsCatalogue = trainsCatalogue;
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

   public List<TrainCatalogueDataBean> getFiltredTrainsCatalogue() {
      return this.filtredTrainsCatalogue;
   }

   public void setFiltredTrainsCatalogue(List<TrainCatalogueDataBean> filtredTrainsCatalogue) {
      this.filtredTrainsCatalogue = filtredTrainsCatalogue;
   }

   public TrainCatalogueDataBean getSelectedTrainsCatalogue() {
      return this.selectedTrainsCatalogue;
   }

   public void setSelectedTrainsCatalogue(TrainCatalogueDataBean selectedTrainsCatalogue) {
      this.selectedTrainsCatalogue = selectedTrainsCatalogue;
   }

   public String getNumeroTrainCatalogue() {
      return this.numeroTrainCatalogue;
   }

   public void setNumeroTrainCatalogue(String numeroTrainCatalogue) {
      this.numeroTrainCatalogue = numeroTrainCatalogue;
   }

   public List<String> getListSelectedJoursCirculation() {

      if (null != this.selectedTrainsCatalogue)
         return StringToFormatedString.getRegimeCirculFromSelectedTrain(this.selectedTrainsCatalogue.getRegimeJoursTrainCatalogue());
      return this.listSelectedJoursCirculation;
   }

   public void setListSelectedJoursCirculation(List<String> listSelectedJoursCirculation) {
      this.listSelectedJoursCirculation = listSelectedJoursCirculation;
   }

   public int getIdTrainCatalogue() {
      return this.idTrainCatalogue;
   }

   public void setIdTrainCatalogue(int idTrainCatalogue) {
      this.idTrainCatalogue = idTrainCatalogue;
   }

   public SessionManagedBean getSession() {
      return this.session;
   }

   public void setSession(SessionManagedBean session) {
      this.session = session;
   }

   public String getOrigine() {
      if (this.selectedTrainsCatalogue != null)
         return this.selectedTrainsCatalogue.getIdPointArretOrigine().getLibellePointArret();
      return origine;
   }

   public void setOrigine(String origine) {
      this.origine = origine;
   }

   public String getDestination() {
      if (this.selectedTrainsCatalogue != null)
         return this.selectedTrainsCatalogue.getIdPointArretDestination().getLibellePointArret();
      return destination;
   }

   public void setDestination(String destination) {
      this.destination = destination;
   }

}
