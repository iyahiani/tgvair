/**
 * 
 */
package com.avancial.app.model.managedbean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.avancial.app.resources.constants.APP_TgvAir;
import com.avancial.socle.params.AParamGetter;
import com.avancial.socle.params.IParamReader;
import com.avancial.socle.params.ParamReaderFileGeneric;
import com.avancial.socle.params.beans.IParamBean;

/**
 * Classe implémentant un point unique de gestion des paramètres. Inspiré du design patttern Facade. <br>
 * Les paramètres du socle sont chargés dans la classe abstraite. Pour ajouter ceux de l'application, il suffit de compléter le constructeur.<br/>
 * Voir {@link IParamBean} , {@link IParamReader}
 * 
 * @author bruno
 *
 */
@Named("paramGetterBean")
@ApplicationScoped
public class ParamGetterManagedBean extends AParamGetter {

   /**
    * Constructeur
    * 
    * @throws Exception
    */
   public ParamGetterManagedBean() throws Exception {
      super();
      // On instancie les Paramètres de l'appli
      ParamReaderFileGeneric app = new ParamReaderFileGeneric(this.getPathToWebInf() + APP_TgvAir.CHEMIN_APP_PROPERTIES.toString());
      app.loadParams("app");
      this.add(app);
   }

}
