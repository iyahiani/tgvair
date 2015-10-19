package com.avancial.app.model.managedbean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import com.avancial.app.data.controller.dao.CompagnieAerienneDao;
import com.avancial.app.data.controller.dao.ServiceDAO;
import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
import com.avancial.app.data.model.databean.ServiceDataBean;
import com.avancial.app.traitements.LancementTraitementsManuelle;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;

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
   private Date dateDebut ; 
   private Date dateFin ;
   private String compagnie ;
   Logger logger = Logger.getLogger(AdminManagedBean.class) ;
   
   public AdminManagedBean() { 
  
      
   }
   /**
    * 
    * @return lancement Manuel de l'import SSIM 
    */
   public String lancerImport() {
      
                                                                   ;
      
      LancementTraitementsManuelle lancementTraitementsManuelle = new LancementTraitementsManuelle() ;
     lancementTraitementsManuelle.getImportManuel().traitementImportSSIM();       
     lancementTraitementsManuelle.getAdaptationManuel().traitementAdaptation();
     this.logger.info("SUCCES Import SSIM");  
     
      return null;
   }

   public void valueChanged(ValueChangeEvent event) {
       
      String a =  (String) event.getNewValue(); 
   }
/**
 * lancement manuel de l'export SSIM 7 
 * 
 * @return
 */
   public String lancerExport() {
      
      LancementTraitementsManuelle lancementTraitementsManuelle = new LancementTraitementsManuelle() ;
             
      lancementTraitementsManuelle.getAdaptationManuel().traitementAdaptation();
      lancementTraitementsManuelle.getExportManuel().traitementExport();
      this.logger.info("SUCCES Export SSIM 7");  
      
       return null;
    
   }
   
   
   public String creerService() { 
      ServiceDataBean bean = new ServiceDataBean() ;
      ServiceDAO dao = new ServiceDAO() ;
      bean.setDateDebutService_tgvAir(this.dateDebut); 
      bean.setDatefinService_tgvAir(this.dateFin); 
      try {
         dao.save(bean);
      } catch (ASocleException e) {
         this.logger.error("erreur sauvegarde Service");
         e.printStackTrace();
      }      
      return null ;
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
   public Date getDateDebut() {
      return dateDebut;
   }
   public void setDateDebut(Date dateDebut) {
      this.dateDebut = dateDebut;
   }
   public Date getDateFin() {
      return dateFin;
   }
   public void setDateFin(Date dateFin) {
      this.dateFin = dateFin;
   }

  

}
