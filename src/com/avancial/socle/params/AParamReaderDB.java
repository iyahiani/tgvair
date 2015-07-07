/**
 * 
 */
package com.avancial.socle.params;

import java.util.ArrayList;

import com.avancial.socle.data.controller.dao.AbstractDao;

/**
 * Classe abstraite servant de point de départ aux classes voulant charger des paramètres à partir des bases de données.
 * 
 * @author bruno.legloahec
 *
 */
public abstract class AParamReaderDB extends AParamReader {
   private AbstractDao dao;

   /**
    * Constructeur
    */
   public AParamReaderDB(AbstractDao dao) {
      this.setDao(dao);
      this.colIParamBeans = new ArrayList<>();
   }

   /**
    * @return the dao
    */
   public AbstractDao getDao() {
      return this.dao;
   }

   /**
    * @param dao
    *           the dao to set
    */
   public void setDao(AbstractDao dao) {
      this.dao = dao;
   }

}
