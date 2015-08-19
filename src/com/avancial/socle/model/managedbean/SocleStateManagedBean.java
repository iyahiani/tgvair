/**
 * 
 */
package com.avancial.socle.model.managedbean;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.Query;

import com.avancial.socle.data.controller.dao.AbstractEntityManager;
import com.avancial.socle.resources.MessageController;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * @author bruno
 *
 */
@Named("socleStateManagedBean")
@ApplicationScoped
public class SocleStateManagedBean implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID             = 1L;
   private boolean           isDataBaseConnected          = false;
   private String            dataBaseConnectionStateLabel = "";

   /**
    * 
    */
   public SocleStateManagedBean() {
      super();
      AbstractEntityManager em = AbstractEntityManager.getInstance();
      try {
         Query query = em.getEntityManager().createQuery("FROM UserDataBean u where u.idUser = 1");
         query.getSingleResult();
         this.setDataBaseConnected(true);
      } catch (Exception e) {
         this.setDataBaseConnected(false);
      }

   }

   /**
    * get value for isDataBaseConnected
    * 
    * @return the isDataBaseConnected
    */
   public boolean isDataBaseConnected() {
      return this.isDataBaseConnected;
   }

   /**
    * sets value for isDataBaseConnected
    * 
    * @param isDataBaseConnected
    *           the isDataBaseConnected to set
    */
   public void setDataBaseConnected(boolean isDataBaseConnected) {
      if (isDataBaseConnected)
         this.setDataBaseConnectionStateLabel(MessageController.getTraduction(SOCLE_constants.MESSAGE_CONNECTION_ACTIVE.toString()));
      else
         this.setDataBaseConnectionStateLabel(SOCLE_constants.MESSAGE_CONNECTION_INACTIVE.toString());

      this.isDataBaseConnected = isDataBaseConnected;

   }

   /**
    * get value for dataBaseConnectionStateLabel
    * 
    * @return the dataBaseConnectionStateLabel
    */
   public String getDataBaseConnectionStateLabel() {
      return this.dataBaseConnectionStateLabel;
   }

   /**
    * sets value for dataBaseConnectionStateLabel
    * 
    * @param dataBaseConnectionStateLabel
    *           the dataBaseConnectionStateLabel to set
    */
   public void setDataBaseConnectionStateLabel(String dataBaseConnectionStateLabel) {
      this.dataBaseConnectionStateLabel = dataBaseConnectionStateLabel;
   }

}
