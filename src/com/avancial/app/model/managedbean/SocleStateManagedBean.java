/**
 * 
 */
package com.avancial.app.model.managedbean;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

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
   private static final long serialVersionUID = 1L;
   private boolean isDataBaseConnected = false;
   private String dataBaseConnectionStateLabel = "";

   /**
    * 
    */
   public SocleStateManagedBean() {
      super();
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
         this.setDataBaseConnectionStateLabel("Connexion active.");
      else
         this.setDataBaseConnectionStateLabel("Connexion inactive.");

      this.isDataBaseConnected = isDataBaseConnected;

   }

   /**
    * get value for dataBaseConnectionStateLabel
    * 
    * @return the dataBaseConnectionStateLabel
    */
   public String getDataBaseConnectionStateLabel() {
      return dataBaseConnectionStateLabel;
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
