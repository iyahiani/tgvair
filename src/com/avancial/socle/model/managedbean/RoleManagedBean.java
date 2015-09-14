/**
 * 
 */
package com.avancial.socle.model.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.avancial.socle.data.controller.dao.RoleDao;
import com.avancial.socle.data.model.databean.RoleDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.resources.MessageController;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * @author bruno.legloahec
 *
 */
@Named("roleManagedBean")
@ViewScoped
public class RoleManagedBean extends AManageBean {
   /**
    * 
    */
   private static final long  serialVersionUID = 1L;
   private List<RoleDataBean> selectedItems;
   private String             nomTechnique;
   private String             libelle;

   @Inject
   private RoleDataBean       selectedItem;

   /**
    * Constructeur
    */
   public RoleManagedBean() {
      this.selectedItems = new ArrayList<>();
      this.reload();

   }

   public void reload() {
      this.selectedItems.clear();
      this.selectedItems.addAll(new RoleDao().getAll());
   }

   public void initProperties() {
      this.libelle = "";
      this.nomTechnique = "";
   }

   /**
    * @return
    * @throws ASocleException
    */
   @Override
   public String add() throws ASocleException {
      super.add();
      RoleDataBean roleDataBean = new RoleDataBean();
      roleDataBean.setLabelRole(this.libelle);
      roleDataBean.setTechnicalNameRole(this.nomTechnique);
      RoleDao dao = new RoleDao();

      try {
         dao.save(roleDataBean);
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", MessageController.getTraduction("p_message_add_success_role")));
         // RequestContext.getCurrentInstance().update(":dataTable");
         this.closeDialog = true;
      } catch (ASocleException e) {
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_ADD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
         e.printStackTrace();
      }

      return null;
   }

   @Override
   public String update() throws ASocleException {
      super.update();
      if (null != this.selectedItem) {
         RoleDao dao = new RoleDao();
         try {
            dao.update(this.selectedItem);
            this.closeDialog = true;
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Enregistrement modifi�"));

         } catch (ASocleException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_UPD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
         }
      }
      return null;
   }

   @Override
   public String delete() throws ASocleException {
      super.delete();
      if (null != this.selectedItem) {
         RoleDao dao = new RoleDao();
         try {
            dao.delete(this.selectedItem);
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Enregistrement supprim�"));
            this.closeDialog = true;
         } catch (ASocleException e) {
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_DEL_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", "Enregistrement non effac�"));
         }
      }
      return null;
   }

   /**
    * @return the roleList
    */
   public List<RoleDataBean> getSelectedItems() {
      return this.selectedItems;
   }

   /**
    * @return the nomtechnique
    */
   public String getNomTechnique() {
      return this.nomTechnique;
   }

   /**
    * @param nomtechnique
    *           the nomtechnique to set
    */
   public void setNomTechnique(String nomtechnique) {
      this.nomTechnique = nomtechnique;
   }

   /**
    * @return the libelle
    */
   public String getLibelle() {
      return this.libelle;
   }

   /**
    * @param libelle
    *           the libelle to set
    */
   public void setLibelle(String libelle) {
      this.libelle = libelle;
   }

   /**
    * get value for roleSelected
    * 
    * @return the roleSelected
    */
   public RoleDataBean getSelectedItem() {

      return this.selectedItem;
   }

   /**
    * sets value for roleSelected
    * 
    * @param selectedItem
    *           the roleSelected to set
    */
   public void setSelectedItem(RoleDataBean selectedItem) {
      if (null != selectedItem) {
         this.selectedItem = selectedItem;
         this.libelle = selectedItem.getLabelRole();
         this.nomTechnique = selectedItem.getTechnicalNameRole();
      }
   }

   public Boolean getCloseDialog() {
      return this.closeDialog;
   }

   public void setCloseDialog(Boolean closeDialog) {
      this.closeDialog = closeDialog;
   }

}
