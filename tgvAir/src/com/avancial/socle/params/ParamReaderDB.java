/**
 * 
 */
package com.avancial.socle.params;

import java.util.ArrayList;

import com.avancial.socle.data.controller.dao.AbstractDao;

/**
 * @author bruno.legloahec
 *
 */
public abstract class ParamReaderDB extends AParamReader {
   private AbstractDao dao;

   /**
    * Constructeur
    */
   public ParamReaderDB(AbstractDao dao) {
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
