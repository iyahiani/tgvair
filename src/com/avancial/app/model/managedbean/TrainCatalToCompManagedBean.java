package com.avancial.app.model.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SelectableDataModel;

import com.avancial.app.business.compagnieAerienne.TrainToCompagnie;
import com.avancial.app.data.controller.dao.CompagnieAerienneDao;
import com.avancial.app.data.controller.dao.TrainCatalogueToCompagnieDAO;
import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueToCompagnieDataBean;
import com.avancial.socle.data.controller.dao.RoleDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

@Named("traintocompagnie")
@ViewScoped
public class TrainCatalToCompManagedBean extends AManageBean implements Serializable, SelectableDataModel<TrainCatalogueToCompagnieDataBean> {
   private static final long serialVersionUID = 1L;
   private List<TrainCatalogueDataBean> trainsCatalogues;
   private List<TrainCatalogueToCompagnieDataBean> trainsCataloguesToCompagnies;
   private List<CompagnieAerienneDataBean> allCompagnie; 
   private List<CompagnieAerienneDataBean> allCodeCompagnie ;
   private Integer idTrainCatalogueToCompagnie;
   private TrainCatalogueDataBean trainCatalogueBean;
   private CompagnieAerienneDataBean compagnieDataBean;
   private String codeCompagnie;
   private Date dateDebutValidite;
   private Date dateFinValidite;
   private String marketingFlight;
   private int quota1er;
   private int quota2em;
   private  String selectedOneMenu ; 
   private TrainCatalogueToCompagnieDataBean selectedTrain;

   public TrainCatalToCompManagedBean() {
      this.trainsCatalogues = new ArrayList<>();
      this.trainsCataloguesToCompagnies = new ArrayList<>();
      this.allCompagnie = new ArrayList<>();
      this.trainCatalogueBean = new TrainCatalogueDataBean();
      this.compagnieDataBean = new CompagnieAerienneDataBean(); 
      this.allCodeCompagnie = new ArrayList<>() ;
      //this.reload();
   }

   public List<CompagnieAerienneDataBean> getAllCompagnie() {
      List<CompagnieAerienneDataBean> temp = new CompagnieAerienneDao().getAll();
      this.allCompagnie.clear();
      this.allCompagnie.addAll(temp);
      return this.allCompagnie;
   }
/**
 * @param event 
 * recupere la valeur choisie dans le selectOneMenu de l'association Train--Compagnie  
 */
   public void valueChanged(ValueChangeEvent event) {
      this.selectedOneMenu = (String) event.getNewValue() ; 
      this.marketingFlight = (String) event.getNewValue() ; 
      System.out.println(this.marketingFlight);
   }
   
   
   public List<CompagnieAerienneDataBean> getAllCodeCompagnie(){
      List<CompagnieAerienneDataBean> temp = new CompagnieAerienneDao().getAllCodeCompagnie();
      this.allCompagnie.clear();
      this.allCompagnie.addAll(temp);
      return this.allCompagnie;
   }
   @Override
   public String add() {

      CompagnieAerienneDao compagnieDao = new CompagnieAerienneDao();
      TrainCatalogueToCompagnieDAO dao = new TrainCatalogueToCompagnieDAO();
      TrainCatalogueToCompagnieDataBean bean = new TrainCatalogueToCompagnieDataBean();
      this.compagnieDataBean = compagnieDao.getCompagnieByCode(getCodeCompagnie()).get(0);
      bean.setTrainCatalogueDataBean(this.trainCatalogueBean);
      bean.setCompagnieAerienneDataBean(this.compagnieDataBean);
      bean.setDateFinValiditeTrainCatalogueToCompagnie(getDateFinValidite());
      bean.setDateDebutValiditeTrainCatalogueToCompagnie(getDateDebutValidite());
      bean.setMarketingFlightTrainCatalogueToCompagnie(getMarketingFlight());
      bean.setQuotaPremiereTrainCatalogueToCompagnie(getQuota1er());
      bean.setQuotaDeuxiemeTrainCatalogueToCompagnie(getQuota2em());
      bean.setCodeCompagnieAerienne(getCodeCompagnie());
      if(bean.getTrainCatalogueDataBean().getNumeroTrainCatalogue()==null)   {
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), 
               new FacesMessage(FacesMessage.SEVERITY_INFO, "Erreur", "Selectionnez Un train."));
         this.closeDialog = true;
      }
      else 
      try {
         dao.save(bean);
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), 
               new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Le train est associé à la compagnie."));
         this.closeDialog = true;
         RequestContext.getCurrentInstance().update(":tableCompAerienne");

      } catch (ASocleException e) {// ASocleException
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_ADD_MESSAGES.toString(), 
               new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
         e.printStackTrace();
      }

      return null;
   }
   @Override
   public String update() throws ASocleException {
      super.update();
           TrainCatalogueToCompagnieDAO dao = new TrainCatalogueToCompagnieDAO();
         try {
            dao.update(this.selectedTrain);
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Train modifié"));
            this.closeDialog = true;
            RequestContext.getCurrentInstance().update(":tableCompAerienne");
         } catch (ASocleException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_UPD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
         }
      
      return null;
   } 
   @Override
   public String delete() throws ASocleException {
      super.delete();
      if (null != this.selectedTrain) {
         TrainCatalogueToCompagnieDAO dao = new TrainCatalogueToCompagnieDAO();
         try {
            dao.delete(this.selectedTrain);
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Enregistrement supprimé"));
            this.closeDialog = true;
            RequestContext.getCurrentInstance().update(":tableCompAerienne");
         } catch (ASocleException e) {
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_DEL_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", "Enregistrement non effacé"));
         }
      }
      return null;
   }
    
   
   
   public void rowSelect(SelectEvent event) {

      this.trainCatalogueBean = (TrainCatalogueDataBean) event.getObject();
      this.trainsCataloguesToCompagnies.clear();
      trainsCataloguesToCompagnies.addAll(new TrainCatalogueToCompagnieDAO().getTrainToCompagnieByID(this.trainCatalogueBean.getIdTrainCatalogue()));
      RequestContext.getCurrentInstance().update(":tableCompAerienne");
      RequestContext.getCurrentInstance().update(":formAssocier");
   }
   
   public void rowSelectCompagnie(SelectEvent event) {

      this.selectedTrain = (TrainCatalogueToCompagnieDataBean) event.getObject() ;
   }

   

   public void reload() {
      this.trainsCataloguesToCompagnies.clear();
      this.trainsCataloguesToCompagnies.addAll(new TrainCatalogueToCompagnieDAO().getTrainToCompagnieByID(this.trainCatalogueBean.getIdTrainCatalogue()));
   }

  
   public void setSelectedTrain(TrainCatalogueToCompagnieDataBean selectedTrain) {
      this.selectedTrain = selectedTrain ;

   }

   public Boolean getCloseDialog() {
      return this.closeDialog;
   }

   public void setCloseDialog(Boolean closeDialog) {
      this.closeDialog = closeDialog;
   }
   public TrainCatalogueDataBean getTrainCatalogueBean() {
      return trainCatalogueBean;
   }

   public void setTrainCatalogueBean(TrainCatalogueDataBean trainCatalogueBean) {
      this.trainCatalogueBean = trainCatalogueBean;
   }

   public TrainCatalogueToCompagnieDataBean getRowData(String arg0) {

      return null;
   }

   public Object getRowKey(TrainCatalogueToCompagnieDataBean arg0) {

      return null;
   }

   public Integer getIdTrainCatalogueToCompagnie() {
      return idTrainCatalogueToCompagnie;
   }
   public List<TrainCatalogueDataBean> getTrainsCatalogues() {
      return this.trainsCatalogues;
   }

   public void setTrainsCatalogues(List<TrainCatalogueDataBean> trainsCatalogues) {
      this.trainsCatalogues = trainsCatalogues;
   }

   public List<TrainCatalogueToCompagnieDataBean> getTrainsCataloguesToCompagnies() {
      return this.trainsCataloguesToCompagnies;
   }

   public void setTrainsCataloguesToCompagnies(List<TrainCatalogueToCompagnieDataBean> trainsCataloguesToCompagnies) {
      this.trainsCataloguesToCompagnies = trainsCataloguesToCompagnies;
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

   public String getMarketingFlight() {
      return this.marketingFlight;
   }

   public void setMarketingFlight(String marketingFlight) {
      this.marketingFlight = marketingFlight;
   }

   public int getQuota1er() {
      return this.quota1er;
   }

   public void setQuota1er(int quota1er) {
      this.quota1er = quota1er;
   }

   public int getQuota2em() {
      return this.quota2em;
   }

   public void setQuota2em(int quota2em) {
      this.quota2em = quota2em;
   }

   public String getCodeCompagnie() {
      return this.codeCompagnie;
   }

   public void setCodeCompagnie(String codeCompagnie) {
      this.codeCompagnie = codeCompagnie;
   }

   public TrainCatalogueToCompagnieDataBean getSelectedTrain() {
      return this.selectedTrain;
   }

   public String getSelectedOneMenu() {
      return this.selectedOneMenu;
   }

   public void setSelectedOneMenu(String selectedOneMenu) {
      this.selectedOneMenu = selectedOneMenu;
   }

}
