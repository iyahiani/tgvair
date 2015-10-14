package com.avancial.app.logs;

import java.util.List;

import javax.persistence.Query;

import com.avancial.socle.data.controller.dao.AbstractDao;

public class LogsDAO extends AbstractDao {

   @Override
   public List<LogsDataBean> getAll() {
      
      String sql = "From LogsDataBean t order by t.dateLog desc";

      Query requete = this.getEntityManager().createQuery(sql);
      
      return requete.getResultList();
   }

}
