/**
 * 
 */
package com.avancial.socle.params;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.avancial.socle.params.beans.IParamBean;
import com.avancial.socle.params.beans.ParamBeanGeneric;

/**
 * Classe abstraite pour l'implémentation de la lecture de paramètres à partir
 * d'un fichier
 * 
 * @author bruno
 *
 */
public abstract class AParamReaderFile extends AParamReader {

   private String filePath;

   /**
    * @param filePath
    * @param paramsName
    * 
    */
   public AParamReaderFile(String filePath) {
      this.setFilePath(filePath);
      this.colIParamBeans = new ArrayList<>();
   }

   /**
    * get value for filePath
    * 
    * @return the filePath
    */
   public String getFilePath() {
      return this.filePath;
   }

   /**
    * sets value for filePath
    * 
    * @param filePath
    *           the filePath to set
    */
   public void setFilePath(String filePath) {
      this.filePath = filePath;
   }

   @Override
   public void loadParams(String paramsName) throws Exception {
      super.loadParams(paramsName);
      Properties properties = new Properties();

      try (FileInputStream input = new FileInputStream(this.getFilePath())) {
         properties.load(input);
      }

      for (Object key : properties.keySet()) {
         IParamBean paramBean = new ParamBeanGeneric();
         paramBean.setName(key.toString());
         paramBean.SetValue(properties.getProperty(key.toString()));
         this.colIParamBeans.add(paramBean);
      }

   }
}
