package com.avancial.app.data.controller.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

public class CirculationDAO extends AbstractDao {

   @Override
   public List<CirculationAdapterDataBean> getAll() {
      
      String sql = "From CirculationAdapterDataBean";

      Query requete = this.getEntityManager().createQuery(sql);
      
      return requete.getResultList();
   } 
   
   
   public void save(CirculationAdapterDataBean bean) throws ASocleException {
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

    public void delete(CirculationAdapterDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().remove(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw SocleExceptionManager.getException();
      }

   }

   public void update(CirculationAdapterDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw SocleExceptionManager.getException();
      }

   } 
  public List<CirculationAdapterDataBean> getDistinctCirculation() {
     
     String sql = "From CirculationAdapterDataBean c group by c.trainCatalogueDataBean.idTrainCatalogue ";

     Query requete = this.getEntityManager().createQuery(sql);
     
     return requete.getResultList();
     
  }
 
  
  
  public Date getMaxDateCreationCirculationJourPrecedentByIdTrain(int idTrain, Date dateExtract){
     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
     String sql = "select max(t.dateCreationLigneTrain) from CirculationAdapterDataBean t where t.trainCatalogueDataBean.idTrainCatalogue = ? and t.dateCreationLigneTrain <Date(?) order by t.dateCreationLigneTrain DESC" ;
     Query requete = this.getEntityManager().createQuery(sql);
     requete.setParameter(1, idTrain) ; 
     requete.setParameter(2, sdf.format(dateExtract));
    return (Date) requete.getSingleResult(); 
     
  }
  
  public List<CirculationAdapterDataBean> getLastCircul(int idTrain){
     
     List<CirculationAdapterDataBean> lastCircul = new ArrayList<>(); 
     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
     String sql = "select max(t.dateCreationLigneTrain) from CirculationAdapterDataBean t where t.trainCatalogueDataBean.idTrainCatalogue = ? order by t.dateCreationLigneTrain DESC" ;
     Query requete = this.getEntityManager().createQuery(sql);
     requete.setParameter(1, idTrain) ; 
     
     Date date = (Date)requete.getSingleResult() ; 
     
     
     sql = "from CirculationAdapterDataBean t where t.trainCatalogueDataBean.idTrainCatalogue = ? and t.dateCreationLigneTrain =Date(?)";
     requete = this.getEntityManager().createQuery(sql);
     requete.setParameter(1, idTrain) ; 
     requete.setParameter(2, sdf.format(date)) ;
     lastCircul.addAll(requete.getResultList()) ;
     return lastCircul  ;
  }
  
  @SuppressWarnings("unchecked")
  public List<CirculationAdapterDataBean> getCirculationByIdTrainAndByDate(int idTrain, Date dateExtract) {
    
     String sql = "from CirculationAdapterDataBean t where t.trainCatalogueDataBean.idTrainCatalogue = ? and t.dateCreationLigneTrain =Date(?)" ;
     Query requete = this.getEntityManager().createQuery(sql);
     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
     requete.setParameter(1, idTrain) ; 
     requete.setParameter(2, sdf.format(dateExtract));
     ///requete.setMaxResults(2) ; 
     List<CirculationAdapterDataBean> listCirculAdap=new ArrayList<>();
     listCirculAdap.addAll(requete.getResultList()) ;
     
     
     
    return listCirculAdap ;
    
  }
  
  public List<CirculationAdapterDataBean> getCirculationByIdTrain(int idTrain) {
     
     String sql = "from CirculationAdapterDataBean t where t.trainCatalogueDataBean.idTrainCatalogue = ?" ;
     Query requete = this.getEntityManager().createQuery(sql);
    
     requete.setParameter(1, idTrain) ; 
   
     ///requete.setMaxResults(2) ; 
     List<CirculationAdapterDataBean> listCirculAdap=new ArrayList<>();
     listCirculAdap.addAll(requete.getResultList()) ;
     

     
    return listCirculAdap ;
    
  }
  
  
}