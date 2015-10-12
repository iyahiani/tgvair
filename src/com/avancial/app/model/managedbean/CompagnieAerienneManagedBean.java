package com.avancial.app.model.managedbean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;





import javax.persistence.criteria.Expression;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.avancial.app.data.controller.dao.CompagnieAerienneDao;
import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * 
 * @author ismael.yahiani managed bean relative a la gestion des compagnies Aerienne
 */

@Named("compagnieAerienneMangedBean")
@ViewScoped
public class CompagnieAerienneManagedBean extends AManageBean {
   private static final long               serialVersionUID = 1L;
   private List<CompagnieAerienneDataBean> compagniesAerienne;
   private String                          codeCompagnieAerienne;
   private String                          libelleCompagnieAerienne; 
   private InputStream                 imageCompagnieAerienne ;   
   private UploadedFile file ;
  
   
   public CompagnieAerienneManagedBean() {
      this.compagniesAerienne = new ArrayList<>();

   }

   public void reload() {
      this.compagniesAerienne.clear();
      this.compagniesAerienne.addAll(new CompagnieAerienneDao().getAll());
   }
  
   public void initProperties() {
      this.codeCompagnieAerienne = "";
      this.libelleCompagnieAerienne = "";
   }
   
   public void uploadesFile(FileUploadEvent event) {
      this.file = event.getFile() ; 
      try {
         if(null!= this.file)
         this.file.write(this.file.getFileName());
      } catch (Exception e) {
        
         e.printStackTrace();
      } 
      
      
   }
  public void upload() {
     if(this.file != null) {
        FacesMessage message = new FacesMessage("Succes", this.file.getFileName() + " est chargé.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
  }
   public void addCompagnieAerienne() { 
      //File file = new File(this.file.getFileName()); 
      
      CompagnieAerienneDao dao = new CompagnieAerienneDao();
      Session session = dao.getSession(); 
      Criteria criteria =  session.createCriteria(CompagnieAerienneDataBean.class).add(Restrictions.eq("CodeCompagnieAerienne", getCodeCompagnieAerienne())) ;   
      List<CompagnieAerienneDataBean> c = new ArrayList<CompagnieAerienneDataBean>() ; 
      c.addAll(criteria.list()) ; 
      c.clear();
      if (c.size()>0) {
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "cette Compagnie Existe Déja"));
      } else 
      {
      CompagnieAerienneDataBean compagnieAerienneDataBean = new CompagnieAerienneDataBean();
      compagnieAerienneDataBean.setCodeCompagnieAerienne(getCodeCompagnieAerienne());
    //  compagnieAerienneDataBean.setLibelleCompagnieAerienne(getLibelleCompagnieAerienne()); 
      try {
         compagnieAerienneDataBean.setImageCompagnieAerienne(this.file.getInputstream());
      } catch (IOException e1) {
        System.out.println("erreur logo compagnie aerienne ");
         e1.printStackTrace();
      }
    
      try {
         dao.save(compagnieAerienneDataBean);
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "La compagnie a été créé."));
         this.closeDialog = true;
         // RequestContext.getCurrentInstance().update(":dataTable");

      } catch (ASocleException e) {// ASocleException
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_ADD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));// e.getClientMessage()
         e.printStackTrace();
      }
   }
   }

   public List<CompagnieAerienneDataBean> getCompagniesAerienne() {
      return this.compagniesAerienne;
   }

   public void setCompagniesAerienne(List<CompagnieAerienneDataBean> compagniesAerienne) {
      this.compagniesAerienne = compagniesAerienne;
   }

   public String getCodeCompagnieAerienne() {
      return this.codeCompagnieAerienne;
   }

   public void setCodeCompagnieAerienne(String codeCompagnieAerienne) {
      this.codeCompagnieAerienne = codeCompagnieAerienne;
   }

   public String getLibelleCompagnieAerienne() {
      return this.libelleCompagnieAerienne;
   }

   public void setLibelleCompagnieAerienne(String libelleCompagnieAerienne) {
      this.libelleCompagnieAerienne = libelleCompagnieAerienne;
   }

   public UploadedFile getFile() {
      return this.file;
   }

   public void setFile(UploadedFile uploadFile) {
      this.file = uploadFile;
   }

   public InputStream getImageCompagnieAerienne() {
      return this.imageCompagnieAerienne;
   }

   public void setImageCompagnieAerienne(InputStream imageCompagnieAerienne) {
      this.imageCompagnieAerienne = imageCompagnieAerienne;
   }

}
