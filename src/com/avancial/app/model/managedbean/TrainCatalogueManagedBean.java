package com.avancial.app.model.managedbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SelectableDataModel;

import com.avancial.app.data.controller.dao.CirculationDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.resources.constants.APP_TgvAir;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.app.traitements.TraitementExportDAO;
import com.avancial.app.traitements.TraitementImportDAO;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

@Named("traincatalogue")
@ViewScoped
public class TrainCatalogueManagedBean extends AManageBean implements SelectableDataModel<TrainCatalogueDataBean> {

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
   private List<TrainCatalogueDataBean> trainsCatalogue;
   private List<TrainCatalogueDataBean> filtredTrainsCatalogue;
   private TrainCatalogueDataBean selectedTrainsCatalogue;
   private List<String> listSelectedJoursCirculation;
   private List<String> listAvailableJoursCirculation;

   @Inject
   SaveSession saveSession;

   private ExternalContext context;

   @PostConstruct
   public void init() {

      if (this.saveSession.getNum() != null) {
         
               if (this.saveSession.getNum().get("num1") != null)
                  this.numeroTrainCatalogue1 = this.saveSession.getNum().get("num1");
               if (this.saveSession.getNum().get("num2") != null)
                  this.numeroTrainCatalogue2 = this.saveSession.getNum().get("num2");
               if (this.saveSession.getNum().get("op") != null)
                  this.operatingFlight = this.saveSession.getNum().get("op");
               if (this.saveSession.getListJoursCirculs().size()>0) 
                  this.listSelectedJoursCirculation =this.saveSession.getListJoursCirculs();
               if (this.saveSession.getDates().get("debut") != null)
                  this.dateDebutValidite = this.saveSession.getDates().get("debut");
               if (this.saveSession.getDates().get("fin") != null)
                  this.dateFinValidite = this.saveSession.getDates().get("fin");
               if (this.saveSession.getNum().get("origine") != null)
                  this.originePointArret = this.saveSession.getNum().get("origine");
               if (this.saveSession.getNum().get("destination") != null)
                  this.destinationPointArret = this.saveSession.getNum().get("destination");
               if (this.saveSession.getDates().get("heureArr") != null)
                  this.heureArriveeTrainCatalogue = this.saveSession.getDates().get("heureArr");
               if (this.saveSession.getDates().get("heureDep") != null)
                  this.heureDepartTrainCatalogue = this.saveSession.getDates().get("heureDep");
               this.saveSession.getNum().clear();
               this.saveSession.getDates().clear(); 
               this.saveSession.getListJoursCirculs().clear();
      }
   }

   public TrainCatalogueManagedBean() {
      this.trainsCatalogue = new ArrayList<>();
      this.idPointArretOrigine = new PointArretDataBean();
      this.idPointArretDestination = new PointArretDataBean();
      this.reload();
   }

   public void rowSelect(SelectEvent event) {
      this.selectedTrainsCatalogue = (TrainCatalogueDataBean) event.getObject();

   }

   /*
    * public void onRowUnselect(UnselectEvent event) {
    * this.selectedTrainsCatalogue = null; }
    */
   public TimeZone getTimeZone() {
      return TimeZone.getDefault();
   }

  

   /**
   * recharger la liste des trains Catalogue 
   */
   public void reload() {
      this.trainsCatalogue.clear();
      this.trainsCatalogue.addAll(new TrainCatalogueDAO().getAll());
   }

  /* @SuppressWarnings("unchecked")
   @Override
   public String add() {

      TrainCatalogueDAO dao = new TrainCatalogueDAO();
      Session session = dao.getSession();
      Criteria criteria = session.createCriteria(TrainCatalogueDataBean.class).add(Restrictions.eq("numeroTrainCatalogue1", getNumeroTrainCatalogue1()));
      List<TrainCatalogueDataBean> c = new ArrayList<>();
      c.clear();
      c.addAll(criteria.list());
      if (c.size() > 0) {
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "ce Train Existe Déja"));
      } else {

         PointArretDAO pointArretDAO = new PointArretDAO();
         TrainCatalogueDataBean bean = new TrainCatalogueDataBean();
         bean.setIdPointArretDestination(pointArretDAO.getPointArretbyName(this.idPointArretDestination.getLibellePointArret()).get(0));
         bean.setIdPointArretOrigine(pointArretDAO.getPointArretbyName(this.idPointArretOrigine.getLibellePointArret()).get(0)); // pointArretDAO.getPointArretbyName(this.originePointArret).get(0)
         bean.setNumeroTrainCatalogue1(getNumeroTrainCatalogue1());
         bean.setNumeroTrainCatalogue2(getNumeroTrainCatalogue2());
         bean.setNumeroTrainCatalogue(getNumeroTrainCatalogue1() + (!getNumeroTrainCatalogue2().isEmpty() ? "-" + getNumeroTrainCatalogue2() : ""));
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
      }
      return null;
   }*/

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

   public String goTrain() {

      System.out.println("TrainCatalogueManagedBean.goTrain()");
      this.saveSession.getNum().put("num1", this.numeroTrainCatalogue1);
      this.saveSession.getNum().put("num2", this.numeroTrainCatalogue2);
      this.saveSession.getNum().put("op", this.operatingFlight);
      this.saveSession.getListJoursCirculs().addAll(this.listSelectedJoursCirculation);
      this.saveSession.getDates().put("debut", this.dateDebutValidite);
      this.saveSession.getDates().put("fin", this.dateFinValidite);
      this.saveSession.getNum().put("origine", this.originePointArret);
      this.saveSession.getNum().put("destination", this.destinationPointArret);
      this.saveSession.getDates().put("heureDep", this.heureDepartTrainCatalogue);
      this.saveSession.getDates().put("heureArr", this.heureArriveeTrainCatalogue);
      return APP_TgvAir.NAVIGATION_POINTARRET.toString();
   }

   public void saveState() {

   }

   @SuppressWarnings("static-method")
   public String goPointArret() {
      return APP_TgvAir.NAVIGATION_POINTARRET.toString();
   }

   /**
    * modifer le train et ca cerculation dans les tables trainsCatalogue /
    * Circulation
    */
   @Override
   public String update() throws ASocleException {
      super.update();

      if (null != this.selectedTrainsCatalogue) {

         this.selectedTrainsCatalogue.setNumeroTrainCatalogue(this.selectedTrainsCatalogue.getNumeroTrainCatalogue1()
               + (!this.selectedTrainsCatalogue.getNumeroTrainCatalogue2().isEmpty() ? "-" + this.selectedTrainsCatalogue.getNumeroTrainCatalogue2() : ""));

         /*
          * Todo : Modification de la date de fin de la validité
          */
         // Comparaison old date fin de validité avec la new date fin de validé
         // si c'est > => On crée une new circulation avec la date old jusqu'a
         // la date new avec les criteres du train originial

         CirculationAdapterDataBean c = null;
         new TrainCatalogueDAO().getTrainCatByID(this.selectedTrainsCatalogue.getIdTrainCatalogue()).get(0);

         for (TrainCatalogueDataBean t : this.trainsCatalogue) {

            System.out.println(this.selectedTrainsCatalogue.getHeureDepartTrainCatalogue().toString());
            System.out.println(this.selectedTrainsCatalogue.getHeureDepartTrainCatalogue().toString());
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

         TrainCatalogueDAO dao = new TrainCatalogueDAO();

         try {
            if (c != null) {
               new CirculationDAO().save(c);
            }
            dao.update(this.selectedTrainsCatalogue);
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Train modifié"));

            this.closeDialog = true;
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

   
   
   ////////////////  Getters And Setters 
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

   public String getOriginePointArret() {
      return this.originePointArret;
   }

   public void setOriginePointArret(String originePointArret) {
      this.originePointArret = originePointArret;
   }

   public String getDestinationPointArret() {
      return this.destinationPointArret;
   }

   public void setDestinationPointArret(String destinationPointArret) {
      this.destinationPointArret = destinationPointArret;
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
      return this.listSelectedJoursCirculation;
   }

   public void setListSelectedJoursCirculation(List<String> listSelectedJoursCirculation) {
      this.listSelectedJoursCirculation = listSelectedJoursCirculation;
   }

   public List<String> getListAvailableJoursCirculation() {
      return listAvailableJoursCirculation;
   }

   public void setListAvailableJoursCirculation(List<String> listAvailableJoursCirculation) {
      this.listAvailableJoursCirculation = listAvailableJoursCirculation;
   }

   public ExternalContext getContext() {
      return context;
   }

   public void setContext(ExternalContext context) {
      this.context = context;
   }

}
