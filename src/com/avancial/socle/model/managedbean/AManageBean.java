/**
 * 
 */
package com.avancial.socle.model.managedbean;

import java.io.Serializable;

import com.avancial.socle.exceptions.ASocleException;

/**
 * @author bruno.legloahec
 *
 */
public abstract class AManageBean implements Serializable, IManagedBean {

   protected Boolean         closeDialog      = false;
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.model.managedbean.IManagedBean#add()
    */
   @Override
   public String add() throws ASocleException {
      this.closeDialog = false;
      return null;

   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.model.managedbean.IManagedBean#update()
    */
   @Override
   public String update() throws ASocleException {
      this.closeDialog = false;
      return null;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.model.managedbean.IManagedBean#delete()
    */
   @Override
   public String delete() throws ASocleException {
      this.closeDialog = false;
      return null;
   }

   public Boolean getCloseDialog() {
      return this.closeDialog;
   }

   public void setCloseDialog(Boolean closeDialog) {
      this.closeDialog = closeDialog;
   }

}
