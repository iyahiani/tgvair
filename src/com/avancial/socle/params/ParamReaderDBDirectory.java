/**
 * 
 */
package com.avancial.socle.params;

import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.data.model.databean.RefDirectoryDataBean;
import com.avancial.socle.params.beans.IParamBean;
import com.avancial.socle.params.beans.ParamBeanGeneric;

/**
 * @author bruno
 *
 */
public class ParamReaderDBDirectory extends AParamReaderDB {

   /**
    * Classe servant à lire les paramètres "Répertoire" stockés en base de données
    * 
    * @param dao
    */
   public ParamReaderDBDirectory(AbstractDao dao) {
      super(dao);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.params.IParamReader#loadParams(java.lang.String)
    */
   @Override
   public void loadParams(String paramsName) throws Exception {
      try {
         super.loadParams(paramsName);
         for (Object bean : this.getDao().getAll()) {

            IParamBean iParamBean = new ParamBeanGeneric();
            iParamBean.setName(((RefDirectoryDataBean) bean).getTechnicalNameRefDirectory());
            iParamBean.SetValue(((RefDirectoryDataBean) bean).getPathRefDirectory());
            this.colIParamBeans.add(iParamBean);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}
