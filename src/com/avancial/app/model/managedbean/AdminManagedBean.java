package com.avancial.app.model.managedbean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;

import com.avancial.app.data.controller.dao.CompagnieAerienneDao;
import com.avancial.app.data.controller.dao.ServiceDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueToCompagnieDAO;
import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
import com.avancial.app.data.model.databean.ServiceDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.traitements.LancementTraitementsManuelle;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

@Named("admin")
@ViewScoped
public class AdminManagedBean extends AManageBean {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private boolean finish;
   private SchedulerFactory sf;
   private Scheduler sched;
   private List<CompagnieAerienneDataBean> listCompagnies = new CompagnieAerienneDao().getAllCodeCompagnie();
   /*private Date dateDebut ; 
   private Date dateFin ;*/
   private String compagnie ;  
   private List<ServiceDataBean> listServices ; 
   private ServiceDataBean selectedService ;
   private  LancementTraitementsManuelle lancementTraitementsManuelle ;
   Logger logger = Logger.getLogger(AdminManagedBean.class) ;
   
   public AdminManagedBean() { 
  
    this.lancementTraitementsManuelle  = new LancementTraitementsManuelle() ;
   }
  
 

   /*   public void valueChanged(ValueChangeEvent event) {
      String a =  (String) event.getNewValue(); 
   } */
   
   @PostConstruct 
   public void init() {
      this.listServices = new ServiceDAO().getAll() ;
   }
   
   public void rowSelect(SelectEvent event) {

      this.selectedService = (ServiceDataBean) event.getObject(); 
      
   } 
   
   public void reload() {
      this.listServices = new ServiceDAO().getAll() ;
   }
   
   public String update() throws ASocleException {
      super.update();
           ServiceDAO dao = new ServiceDAO();
         try {
            dao.update(this.selectedService);
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Service", "Service modifié"));
            this.closeDialog = true;
            RequestContext.getCurrentInstance().update(":form_admin"); 
            this.reload(); 
            this.logger.info("Service Modifier");
         } catch (ASocleException e) {
            e.printStackTrace();
            this.logger.error("Ereur Modification Service");
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_UPD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
         }
      
      return null;
   }
  
   
   /** 
    * lancement Manuel de l'import SSIM 
    * @return null
    */
   public String lancerImport() {
      
      this.lancementTraitementsManuelle.getImportManuel().traitementImportSSIM();       
      this.lancementTraitementsManuelle.getAdaptationManuel().traitementAdaptation();
      this.logger.info("SUCCES Import SSIM");  
      
       return null;
    }

/**
* 
* 
* lancement manuel de l'export SSIM 7 
* 
* @return null
*/
   public String lancerExport() {
      
     // this.lancementTraitementsManuelle.getAdaptationManuel().traitementAdaptation();
     this.lancementTraitementsManuelle.getExportManuel().traitementExport();
     this.logger.info("SUCCES Export SSIM 7");  
      return null;
   }
   
   
   public Boolean getCloseDialog() {
      return this.closeDialog;
   }

   public void setCloseDialog(Boolean closeDialog) {
      this.closeDialog = closeDialog;
   }
   public boolean isFinish() {
      return this.finish;
   }

   public void setFinish(boolean finish) {
      this.finish = finish;
   }

   
   public SchedulerFactory getSf() {
      return this.sf;
   }

   public void setSf(SchedulerFactory sf) {
      this.sf = sf;
   }

   public Scheduler getSched() {
      return this.sched;
   }

   public void setSched(Scheduler sched) {
      this.sched = sched;
   }

   public List<CompagnieAerienneDataBean> getListCompagnies() {
      return this.listCompagnies;
   }

   public void setListCompagnies(List<CompagnieAerienneDataBean> listCompagnies) {
      this.listCompagnies = listCompagnies;
   }

   public String getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(String compagnie) {
      this.compagnie = compagnie;
   }
  /* public Date getDateDebut() {
      return this.dateDebut;
   }
   public void setDateDebut(Date dateDebut) {
      this.dateDebut = dateDebut;
   }
   public Date getDateFin() {
      return this.dateFin;
   }
   public void setDateFin(Date dateFin) {
      this.dateFin = dateFin;
   }*/
   public LancementTraitementsManuelle getLancementTraitementsManuelle() {
      return this.lancementTraitementsManuelle;
   }
   public void setLancementTraitementsManuelle(LancementTraitementsManuelle lancementTraitementsManuelle) {
      this.lancementTraitementsManuelle = lancementTraitementsManuelle;
   }
   public Logger getLogger() {
      return this.logger;
   }
   public void setLogger(Logger logger) {
      this.logger = logger;
   }
   public List<ServiceDataBean> getListServices() {
      return this.listServices;
   }
   public void setListServices(List<ServiceDataBean> listServices) {
      this.listServices = listServices;
   }
   public ServiceDataBean getSelectedService() {
      return this.selectedService;
   }
   public void setSelectedService(ServiceDataBean selectedService) {
      this.selectedService = selectedService;
   }

  

}
