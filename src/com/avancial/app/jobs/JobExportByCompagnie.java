package com.avancial.app.jobs;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.avancial.app.business.export.ExportPDTByCompagnyToSSIM7;
import com.avancial.app.business.train.Service;
import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.business.train.TrainFactory;
import com.avancial.app.data.controller.dao.CirculationDAO;
import com.avancial.app.data.controller.dao.CompagnieAerienneDao;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueToCompagnieDAO;
import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.CompagnieAerienneDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueToCompagnieDataBean;
import com.avancial.app.traitements.TraitementExportDataBean;

public class JobExportByCompagnie implements Job{
   
   String admin ;   
   public void execute(JobExecutionContext context) throws JobExecutionException {
     CompagnieAerienneDataBean  compagnie = new CompagnieAerienneDao().getCompagnieByCode(this.admin).get(0) ;
     List<TrainCatalogueToCompagnieDataBean> listTC2C = new TrainCatalogueToCompagnieDAO().getAll();
     TrainCatalogueToCompagnieDAO catalogueToCompagnieDAO = new TrainCatalogueToCompagnieDAO();
     TrainCatalogueDAO catalogueDAO = new TrainCatalogueDAO();
     listTC2C = catalogueToCompagnieDAO.getTrainCatalogueByIdCompagnie(compagnie.getIdCompagnieAeriennne()); 
     TrainCatalogueDataBean tc = null;
         boolean compare = false ;
         for (TrainCatalogueToCompagnieDataBean tc2c : listTC2C) {
            tc = catalogueDAO.getTrainCatalogueByID(tc2c.getTrainCatalogueDataBean().getIdTrainCatalogue());
            List<TrainCatalogue> listeTrainCatalogue = TrainFactory.get2DerniersTC(tc2c.getTrainCatalogueDataBean().getIdTrainCatalogue(), Calendar.getInstance().getTime());
               if (listeTrainCatalogue.size()>1) { 
                  TrainCatalogue trainPortef1=listeTrainCatalogue.get(0).getTrainFromPortefeuille(tc2c.getDateDebutValiditeTrainCatalogueToCompagnie(), tc2c.getDateFinValiditeTrainCatalogueToCompagnie());
                  TrainCatalogue trainPortef2= listeTrainCatalogue.get(1).getTrainFromPortefeuille(tc2c.getDateDebutValiditeTrainCatalogueToCompagnie(), tc2c.getDateFinValiditeTrainCatalogueToCompagnie());
                  if (!trainPortef1.compare(trainPortef2)) {
                     compare = true ;break ;
                     }
         }  
               if (listeTrainCatalogue.size()==1) { 
                  Calendar c = Calendar.getInstance() ; 
                  // Regarder si ce train date d'aujourd'hui ou si c'est un ancien train :Si nouveau Export / Sinon ne pas exporter  
                  if( new CirculationDAO().getCirculationByIdTrain(listeTrainCatalogue.get(0).getIdTrain()).get(0).getDateCreationLigneTrain().equals(c.getTime())) {
                  compare = true  ;break ;
                  }
               }
      } 
         if (compare) {
            List<TrainCatalogue> listCatalogue = new ArrayList<>();
            for (TrainCatalogueToCompagnieDataBean tc2c : listTC2C) {
               tc = catalogueDAO.getTrainCatalogueByID(tc2c.getTrainCatalogueDataBean().getIdTrainCatalogue()); 
               CirculationDAO dao=new CirculationDAO();
               //On récupère les circulations correspondant à l'id du train catalogue on question 
               List<CirculationAdapterDataBean> liste= dao.getCirculationByIdTrain(tc.getIdTrainCatalogue());
               TrainCatalogue train=TrainFactory.createTrainCatalogueFromBeans(liste);
                TrainCatalogue trainPortf = train.getTrainFromPortefeuille(tc2c.getDateDebutValiditeTrainCatalogueToCompagnie(),tc2c.getDateFinValiditeTrainCatalogueToCompagnie());
                trainPortf.setCodeCompagnie(tc2c.getCodeCompagnieAerienne()); 
                trainPortf.setQuota1er(tc2c.getQuotaPremiereTrainCatalogueToCompagnie()); 
                trainPortf.setQuota2eme(tc2c.getQuotaDeuxiemeTrainCatalogueToCompagnie()); 
                trainPortf.setPointArretOrigine(tc2c.getTrainCatalogueDataBean().getIdPointArretOrigine());
                trainPortf.setPointArretDestination(tc2c.getTrainCatalogueDataBean().getIdPointArretDestination());
                trainPortf.setMarketingFlight(tc2c.getMarketingFlightTrainCatalogueToCompagnie());
                listCatalogue.add(trainPortf)   ;  
            }
            
            ExportPDTByCompagnyToSSIM7 export = new ExportPDTByCompagnyToSSIM7() ;
            try {
               export.export(listCatalogue , new TraitementExportDataBean(), new Service());
            } catch (ParseException e) {
                e.printStackTrace();
            }
         }
   } 
   
}
