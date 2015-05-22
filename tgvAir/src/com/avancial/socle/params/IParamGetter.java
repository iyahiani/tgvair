/**
 * 
 */
package com.avancial.socle.params;

import java.util.List;

import com.avancial.socle.params.beans.IParamBean;
import com.avancial.socle.params.exception.ParamCollectionNotLoadedException;
import com.avancial.socle.params.exception.ParamNotFoundException;

/**
 * Cette classe représente le point d'accès unique d'accès aux paramètres de
 * l'application.<br/>
 * Pour implémenter cette interface, il faut étendre la classe abstraite
 * AParamGetter sous la forme :
 * <ul>
 * 
 * 
 * <li>ParamGetterSocle pour le socle applicatif</li>
 * <li>ParamGetterApp pour l'application</li>
 * </ul>
 * 
 * @author bruno.legloahec
 *
 */

public interface IParamGetter {

   public void add(IParamReader iParamReader);

   public IParamBean getParam(String paramType, String paramName) throws ParamNotFoundException, ParamCollectionNotLoadedException;

   public List<IParamBean> getParamsFromCollection(String paramType) throws ParamCollectionNotLoadedException;

}
