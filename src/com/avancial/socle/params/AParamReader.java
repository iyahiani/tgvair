/**
 * 
 */
package com.avancial.socle.params;

import java.util.Collection;

import com.avancial.socle.params.beans.IParamBean;

/**
 * Classe abstraite pour lire des paramètres
 * 
 * @author bruno
 *
 */
public abstract class AParamReader implements IParamReader {
   protected String                 paramsName;
   protected Collection<IParamBean> colIParamBeans;

   /**
    * get value for paramName
    * 
    * @return the paramName
    */
   @Override
   public String getParamsName() {
      return this.paramsName;
   }

   /**
    * sets value for paramName
    * 
    * @param paramsName
    *           the paramName to set
    */
   public void setParamsName(String paramsName) {
      this.paramsName = paramsName;
   }

   @Override
   public Collection<IParamBean> getParams() {
      return this.colIParamBeans;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.params.IParamReader#loadParams(java.lang.String)
    */
   @Override
   public void loadParams(String paramsName) throws Exception {
      this.paramsName = paramsName;
   }

}
