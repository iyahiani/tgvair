package com.avancial.app.model.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.avancial.app.data.controller.dao.CompagnieAerienneDao;
import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
import com.avancial.socle.data.controller.dao.RoleDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.ConstantSocle;

/**
 * 
 * @author ismael.yahiani
 *  managed bean relative a la gestion des compagnies Aerienne 
 */

@Named("compagnieAerienneMangedBean") 
@ViewScoped
public class CompagnieAerienneManagedBean extends AManageBean{
   private static final long  serialVersionUID = 1L;
   private List<CompagnieAerienneDataBean> compagniesAerienne; 
   private String codeCompagnieAerienne ; 
   private String libelleCompagnieAerienne ;
   
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
   public void addCompagnieAerienne() {
      CompagnieAerienneDataBean compagnieAerienneDataBean = new CompagnieAerienneDataBean() ;
      compagnieAerienneDataBean.setCodeCompagnieAerienne(getCodeCompagnieAerienne());
      compagnieAerienneDataBean.setLibelleCompagnieAerienne(getLibelleCompagnieAerienne());
      CompagnieAerienneDao dao = new CompagnieAerienneDao() ;
      try {
         dao.save(compagnieAerienneDataBean);
         FacesContext.getCurrentInstance().addMessage(ConstantSocle.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "La compagnie a été créé.")); 
         this.closeDialog = true ;
         //RequestContext.getCurrentInstance().update(":dataTable");
        
      } catch (ASocleException e) {// ASocleException
         FacesContext.getCurrentInstance().addMessage(ConstantSocle.DIALOG_ADD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));//e.getClientMessage()
         e.printStackTrace();
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
   
   
}
