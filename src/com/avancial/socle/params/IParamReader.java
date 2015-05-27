/**
 * 
 */
package com.avancial.socle.params;

import java.util.Collection;

import com.avancial.socle.params.beans.IParamBean;

/**
 * Interface de chargement et de récupération de paramètres
 * 
 * 
 * @author bruno
 *
 */
public interface IParamReader {

   /**
    * Permet de récupérer l'ensemble des paramètres
    * 
    * @return une collection contenant l'ensemble des paramètres
    */
   public Collection<IParamBean> getParams();

   /**
    * @return le nom des paramètres
    */
   public String getParamsName();

   /**
    * Permet de charger les paramètres en mémoire
    * 
    * @param paramsName
    * @throws Exception
    */
   public void loadParams(String paramsName) throws Exception;

}
