package com.avancial.app.data.controller.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueToCompagnieDataBean;
import com.avancial.app.traitements.TraitementExportDAO;
import com.avancial.app.traitements.TraitementImportDAO;
import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;
import com.avancial.test.InsertWithJDBC;

public class TrainCatalogueDAO extends AbstractDao {

   Logger log = Logger.getLogger(TrainCatalogueDAO.class) ;
   @Override
  
   public List<TrainCatalogueDataBean> getAll() {
      String sql = "From TrainCatalogueDataBean"; 
      Query requete = this.getEntityManager().createQuery(sql); 
      return requete.getResultList();

   }

   public List<TrainCatalogueDataBean> getAllTrainAndValid() {
      String sql = " From TrainCatalogueDataBean"; // select
                                                   // concat(numeroTrainCatalogue,'-',dateDebutValidite)
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }

   public void save(TrainCatalogueDataBean bean) throws ASocleException {
      CirculationAdapterDataBean circBean=new CirculationAdapterDataBean();
      Calendar c = Calendar.getInstance() ;
      circBean.setDateDebutCirculation(bean.getDateDebutValidite());
      circBean.setDateFinCirculation(bean.getDateFinValidite());
      circBean.setTrainCatalogueDataBean(bean);
      circBean.setTraitementImport(new TraitementImportDAO().getLastID().get(0));
      circBean.setTraitementExport(new TraitementExportDAO().getLastID());
      circBean.setHeureDepart(bean.getHeureDepartTrainCatalogue().toString().substring(11, 13) + bean.getHeureDepartTrainCatalogue().toString().substring(14, 16));
      circBean.setHeureArriver(bean.getHeureArriveeTrainCatalogue().toString().substring(11, 13) + bean.getHeureArriveeTrainCatalogue().toString().substring(14, 16));
      circBean.setRegimeCirculation(bean.getRegimeJoursTrainCatalogue()); 
      circBean.setDateCreationLigneTrain(c.getTime());
      
      try {
          
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(bean);
         circBean.setTrainCatalogueDataBean(bean); 
         this.getEntityManager().persist(circBean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit(); 
        
      } catch (Exception e) {
         
         this.getEntityManager().getTransaction().rollback();
         this.getEntityManager().close();
         this.log.info("erreur de sauvegarde du trainCatalogue DB");
         @SuppressWarnings("unused")
         SocleExceptionManager manager = new SocleExceptionManager(e);
         throw SocleExceptionManager.getException();

      }
   }

   public List<TrainCatalogueDataBean> getTrainCatByID(int id) {
      String sql = " FROM TrainCatalogueDataBean as t WHERE t.idTrainCatalogue =" + id;
      Query requete = this.getEntityManager().createQuery(sql);

      return requete.getResultList();

   }

   public void delete(TrainCatalogueDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().remove(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
         
      } catch (Exception e) {
         
         this.getEntityManager().getTransaction().rollback();
         this.getEntityManager().close(); 
         this.log.info("Echec suppression TrainCatalogue"+bean.getIdTrainCatalogue()+" ");
         throw SocleExceptionManager.getException();
      }

   }

   public void update(TrainCatalogueDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      
      } catch (Exception e) {
      
         this.getEntityManager().getTransaction().rollback();
         this.getEntityManager().close(); 
         this.log.info("Echec Mise à Jours TrainCatalogue"+bean.getIdTrainCatalogue()+"");
         throw SocleExceptionManager.getException();
      }

   }

   public List<TrainCatalogueDataBean> getTrainToCompagnieByID(int idTrainCatalogue) {

      String sql = " FROM TrainCatalogueToCompagnieDataBean as t WHERE t.trainCatalogueDataBean.idTrainCatalogue =" + idTrainCatalogue;
      Query requete = this.getEntityManager().createQuery(sql);

      return requete.getResultList();

   }
   public TrainCatalogueDataBean getTrainCatalogueByID(int id) {
      Criteria criteria = this.getSession().createCriteria(TrainCatalogueDataBean.class) ;  
      
       List<TrainCatalogueDataBean> list = criteria.add(Restrictions.eqOrIsNull("idTrainCatalogue", id)).list() ;
      return list.get(0);
   }
   
   public void saveCirculation(TrainCatalogueDataBean tc) { 
      Calendar c = Calendar.getInstance() ;
      List<CirculationAdapterDataBean> listCirculAdapter = new CirculationDAO().getDistinctCirculation();
      boolean trouve = false ;
      for (CirculationAdapterDataBean cAdapter : listCirculAdapter) {
         if (cAdapter.getTrainCatalogueDataBean().getIdTrainCatalogue() == tc.getIdTrainCatalogue() && cAdapter!=null) {
            trouve = true;
            break;
         }
      } 
      
      if (!trouve) {
         CirculationAdapterDataBean cirAdapterDataBean = new CirculationAdapterDataBean();
         cirAdapterDataBean.setDateDebutCirculation(tc.getDateDebutValidite());
         cirAdapterDataBean.setDateFinCirculation(tc.getDateFinValidite());
         cirAdapterDataBean.setTrainCatalogueDataBean(tc);
         cirAdapterDataBean.setTraitementImport(new TraitementImportDAO().getLastID().get(0));
         cirAdapterDataBean.setTraitementExport(new TraitementExportDAO().getLastID());
         cirAdapterDataBean.setHeureDepart(tc.getHeureDepartTrainCatalogue().toString().substring(11, 13) + tc.getHeureDepartTrainCatalogue().toString().substring(14, 16));
         cirAdapterDataBean.setHeureArriver(tc.getHeureArriveeTrainCatalogue().toString().substring(11, 13) + tc.getHeureArriveeTrainCatalogue().toString().substring(14, 16));
         cirAdapterDataBean.setRegimeCirculation(tc.getRegimeJoursTrainCatalogue()); 
         cirAdapterDataBean.setDateCreationLigneTrain(c.getTime());
         CirculationDAO dao = new CirculationDAO();
         try {
            dao.save(cirAdapterDataBean);
         } catch (ASocleException e) {
            
            e.printStackTrace();
         }
      }
   }
 
   public void updateCirculation(TrainCatalogue tc) {
      InsertWithJDBC insertWithJDBC = new InsertWithJDBC() ;  
      CirculationDAO daoDelete = new CirculationDAO();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ; 
      String today ;
      today = sdf.format(Calendar.getInstance().getTime()) ; 
      
      
      List<CirculationAdapterDataBean> listCircAdapt = new CirculationDAO().getLastCircul(tc.getIdTrain());
      String lastDate = sdf.format(listCircAdapt.get(0).getDateCreationLigneTrain()) ;
    
      for (CirculationAdapterDataBean c : listCircAdapt) {
         
         if (c.getTrainCatalogueDataBean().getIdTrainCatalogue() == tc.getIdTrain() 
               && lastDate.equalsIgnoreCase(today)
               ) 
            try {
               daoDelete.delete(c);
            } catch (ASocleException e) {
               e.printStackTrace()           ;
            }
      }  
      
      for (Circulation c : tc.getListeCirculations()) {
         CirculationAdapterDataBean cirAdapterDataBean = new CirculationAdapterDataBean();
         cirAdapterDataBean.setDateDebutCirculation(c.getDateDebut());
         cirAdapterDataBean.setDateFinCirculation(c.getDateFin());
         cirAdapterDataBean.setTrainCatalogueDataBean(new TrainCatalogueDAO().getTrainCatalogueByID(tc.getIdTrain()));
         cirAdapterDataBean.setTraitementImport(new TraitementImportDAO().getLastID().get(0));
         cirAdapterDataBean.setTraitementExport(new TraitementExportDAO().getLastID());
         cirAdapterDataBean.setHeureDepart(String.valueOf(c.getHeureDepart() < 1000 ? "0".concat(String.valueOf(c.getHeureDepart())) : String.valueOf(c.getHeureDepart())));
         cirAdapterDataBean.setHeureArriver(String.valueOf(c.getHeureArrivee() < 1000 ? "0".concat(String.valueOf(c.getHeureArrivee())) : String.valueOf(c.getHeureArrivee())));
         cirAdapterDataBean.setRegimeCirculation(c.getJoursCirculation())              ; 
         cirAdapterDataBean.setDateCreationLigneTrain(Calendar.getInstance().getTime());
         try {
           insertWithJDBC.insertRecordIntoTable(cirAdapterDataBean);
         } catch (Exception e) {
            this.log.error("erreur lors de sauvegarde des Adaptations"+e.getMessage());
            e.printStackTrace();
         }
         
        
      } 
   }
   public void updateCirculation(List<TrainCatalogue> listTC) {
      InsertWithJDBC insertWithJDBC = new InsertWithJDBC() ;  
      CirculationDAO daoDelete = new CirculationDAO();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ; 
      String today ;
      today = sdf.format(Calendar.getInstance().getTime()) ; 
      
      for(TrainCatalogue tc : listTC) {
      List<CirculationAdapterDataBean> listCircAdapt = new CirculationDAO().getLastCircul(tc.getIdTrain());
      String lastDate = sdf.format(listCircAdapt.get(0).getDateCreationLigneTrain()) ;
    
      for (CirculationAdapterDataBean c : listCircAdapt) {
         
         if (c.getTrainCatalogueDataBean().getIdTrainCatalogue() == tc.getIdTrain() 
               && lastDate.equalsIgnoreCase(today)
               ) 
            try {
               daoDelete.delete(c);
            } catch (ASocleException e) {
               e.printStackTrace()           ;
            }
      }  
     
       
      if (tc.getListeCirculations().size()==0) {
         this.log.info("Le Train"+new TrainCatalogueDAO().getTrainCatalogueByID(tc.getIdTrain()).getNumeroTrainCatalogue()+" à été supprimé");
      }
      
      for (Circulation c : tc.getListeCirculations()) {
         CirculationAdapterDataBean cirAdapterDataBean = new CirculationAdapterDataBean();
         cirAdapterDataBean.setDateDebutCirculation(c.getDateDebut());
         cirAdapterDataBean.setDateFinCirculation(c.getDateFin());
         cirAdapterDataBean.setTrainCatalogueDataBean(new TrainCatalogueDAO().getTrainCatalogueByID(tc.getIdTrain()));
         cirAdapterDataBean.setTraitementImport(new TraitementImportDAO().getLastID().get(0));
         cirAdapterDataBean.setTraitementExport(new TraitementExportDAO().getLastID());
         cirAdapterDataBean.setHeureDepart(String.valueOf(c.getHeureDepart() < 1000 ? "0".concat(String.valueOf(c.getHeureDepart())) : String.valueOf(c.getHeureDepart())));
         cirAdapterDataBean.setHeureArriver(String.valueOf(c.getHeureArrivee() < 1000 ? "0".concat(String.valueOf(c.getHeureArrivee())) : String.valueOf(c.getHeureArrivee())));
         cirAdapterDataBean.setRegimeCirculation(c.getJoursCirculation())              ; 
         cirAdapterDataBean.setDateCreationLigneTrain(Calendar.getInstance().getTime());
         try {
           insertWithJDBC.insertRecordIntoTable(cirAdapterDataBean);
         } catch (Exception e) {
            this.log.error("erreur lors de sauvegarde des Adaptations"+e.getMessage());
            e.printStackTrace();
         }
         
        
      } 
      
   }
   }
   
   
   
   
   
   public Session getSession() {
      return this.getEntityManager().unwrap(Session.class);
   }
}
