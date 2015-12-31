/**
 * 
 */
package com.avancial.socle.model.managedbean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.avancial.socle.data.controller.dao.Item2RoleDao;
import com.avancial.socle.data.model.databean.Item2RoleDataBean;

/**
 * Gère les droits des utilisateurs sur les objets de l'application.<br/>
 * Se base sur les rôles des utilisateurs et la table item2role.<br/>
 * Sur une page, placer une des propriétés de l'objet security dans le rendered ou autre propriété de l'objet.<br/>
 * ex : rendered=""{security.isRendered('pageJobPlanif')}" permet d'afficher l'élément si l'utilisateur a le droit rendered sur pageJobPlanif.
 * 
 * 
 * @author bruno
 *
 */
@Named("security")
@SessionScoped
public class SecurityManagedBean implements Serializable {
   /**
    * 
    */
   private static final long              serialVersionUID = 1L;
   private Map<String, Item2RoleDataBean> listeItem2Role;
   @Inject
   private IhmManagedBean                 ihmManagedBean;
   /**
    * Indique qu'un utilisateur n'a aucun rôle sur l'application²
    */
   private boolean                        noRole;

   /**
    * Constructeur
    */
   public SecurityManagedBean() {
      this.listeItem2Role = new HashMap<>();
   }

   /**
    * Remplit la liste des droits des utilisateur
    * 
    * @throws Exception
    * 
    */
   public void init() {
      try {
         this.remplirListeItem2Role();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * Indique si l'utilisateur a le droit d'afficher l'élément
    * 
    * @param itemName
    * @return
    */
   public boolean isRendered(String itemName) {
      Boolean result = false;
      Item2RoleDataBean bean = this.listeItem2Role.get(itemName);
      if (null != bean)
      {
         return bean.getIsRendered();
      }
      return result;
   }

   /**
    * Recharge les droits. A utiliser après un changement au niveau de la base de données.
    */
   public void reload() {
      this.listeItem2Role.clear();
      try {
         this.remplirListeItem2Role();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   /**
    * Remplit la liste des droits
    * 
    * @throws Exception
    * 
    */
   private void remplirListeItem2Role() throws Exception {
      this.listeItem2Role.clear();

      this.noRole = false;
      if (null != this.ihmManagedBean.getCurrentUser()) {
         if (this.ihmManagedBean.isLogged() && this.ihmManagedBean.getCurrentUser().getRoles().size() == 0) {
            this.noRole = true;
            return;
         }

      } else
         return;
      Item2RoleDao dao = new Item2RoleDao();
      for (Item2RoleDataBean bean : dao.getitemByListId(this.ihmManagedBean.getCurrentUser().getRoles())) {
         this.listeItem2Role.put(bean.getNameItem2Role(), bean);
      }

   }

   /**
    * @param b
    */
   public void setNoRole(boolean b) {
      this.noRole = b;

   }

   /**
    * get value for noRole
    * 
    * @return the noRole
    */
   public boolean isNoRole() {
      return this.noRole;
   }

}
