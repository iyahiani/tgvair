package com.avancial.app.model.managedbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.avancial.app.data.controller.dao.CompagnieAerienneDao;
import com.avancial.app.data.controller.dao.TrainCatalogueToCompagnieDAO;
import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueToCompagnieDataBean;
import com.avancial.socle.data.controller.dao.RoleDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.ConstantSocle;

@Named("traintocompagnie") 
@ViewScoped

public class TrainCatalToCompManagedBean extends AManageBean{ 
   private static final long  serialVersionUID = 1L;
   private List<TrainCatalogueDataBean> trainsCatalogues ;
   private List<TrainCatalogueToCompagnieDataBean> trainsCataloguesToCompagnies ; 
   private List<CompagnieAerienneDataBean> allCompagnie;
   private String codeCompagnie;
   private Date dateDebutValidite ;
   private Date dateFinValidite ;
   private String marketingFlight ; 
   private int quota1er ;
   private int quota2em ;
   
   public TrainCatalToCompManagedBean() { 
      this.trainsCatalogues = new ArrayList<>();
      this.trainsCataloguesToCompagnies = new ArrayList<>(); 
      this.allCompagnie =  new ArrayList<>() ;
      this.reload();
      
     // this.trainsCataloguesToCompagnies.addAll(new TrainCatalogueToCompagnieDAO().getAll()) ; 
   }
   
   public List<CompagnieAerienneDataBean> getAllCompagnie(){
      List<CompagnieAerienneDataBean> temp = new CompagnieAerienneDao().getAll() ;
     
      this.allCompagnie.clear();
      this.allCompagnie.addAll(temp);
      return this.allCompagnie ;
   }
   public String add() { 
      
      TrainCatalogueToCompagnieDataBean bean  =new TrainCatalogueToCompagnieDataBean() ;
      bean.setDateDebutValiditeTrainCatalogueToCompagnie(getDateDebutValidite());
      bean.setDateFinValiditeTrainCatalogueToCompagnie(getDateFinValidite());
      bean.setMarketingFlightTrainCatalogueToCompagnie(getMarketingFlight());
      bean.setQuotaPremiereTrainCatalogueToCompagnie(getQuota1er());
      bean.setQuotaDeuxiemeTrainCatalogueToCompagnie(getQuota2em());
      TrainCatalogueToCompagnieDAO dao = new TrainCatalogueToCompagnieDAO() ;
      try {
         dao.save(bean);
         FacesContext.getCurrentInstance().addMessage(ConstantSocle.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "La compagnie a été créé.")); 
         this.closeDialog = true ;
         RequestContext.getCurrentInstance().update(":tableCompAerienne");
        
      } catch (ASocleException e) {// ASocleException
         FacesContext.getCurrentInstance().addMessage(ConstantSocle.DIALOG_ADD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));//e.getClientMessage()
         e.printStackTrace();
      }
      return null ;
   }
   public void reload() {
      this.trainsCataloguesToCompagnies.clear();
      this.trainsCataloguesToCompagnies.addAll(new TrainCatalogueToCompagnieDAO().getAll());
   }
  
  
   public List<TrainCatalogueDataBean> getTrainsCatalogues() {
      return trainsCatalogues;
   }
   public void setTrainsCatalogues(List<TrainCatalogueDataBean> trainsCatalogues) {
      this.trainsCatalogues = trainsCatalogues;
   }
   public List<TrainCatalogueToCompagnieDataBean> getTrainsCataloguesToCompagnies() {
      return trainsCataloguesToCompagnies;
   }
   public void setTrainsCataloguesToCompagnies(List<TrainCatalogueToCompagnieDataBean> trainsCataloguesToCompagnies) {
      this.trainsCataloguesToCompagnies = trainsCataloguesToCompagnies;
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
   public String getMarketingFlight() {
      return marketingFlight;
   }
   public void setMarketingFlight(String marketingFlight) {
      this.marketingFlight = marketingFlight;
   }
   public int getQuota1er() {
      return quota1er;
   }
   public void setQuota1er(int quota1er) {
      this.quota1er = quota1er;
   }
   public int getQuota2em() {
      return quota2em;
   }
   public void setQuota2em(int quota2em) {
      this.quota2em = quota2em;
   }

   public String getCodeCompagnie() {
      return codeCompagnie;
   }

   public void setCodeCompagnie(String codeCompagnie) {
      this.codeCompagnie = codeCompagnie;
   } 
   
   
   }
