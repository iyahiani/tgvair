package com.avancial.app.traitements;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Query;

import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

public class TraitementExportDAO  extends AbstractDao{

   public void save(TraitementExportDataBean bean) throws ASocleException {
      try {

         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         @SuppressWarnings("unused")
         SocleExceptionManager manager = new SocleExceptionManager(e);
         throw SocleExceptionManager.getException();
      }
   }
   
   @Override
   public List<TraitementExportDataBean> getAll() {
      
      String sql = "From TraitementExportDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }
   public TraitementExportDataBean getLastID(){
      
      String sql = "from TraitementExportDataBean order by idTraitementExport DESC";
      Query requete = this.getEntityManager().createQuery(sql).setMaxResults(1)    ;
      return (TraitementExportDataBean)requete.getResultList().get(0)              ;
   }  
   
   public void saveExport(TraitementExportDataBean export ) {
      try { 
         
         Calendar calendar = Calendar.getInstance() ;
         export.setDateExtraction(calendar.getTime()); 
         export.setHeureCreation(String.valueOf( calendar.getTime().getHours() ).concat(String.valueOf(calendar.getTime().getMinutes())));
         this.save(export);
      } catch (ASocleException e) {
         e.printStackTrace();
      }
   }
}
