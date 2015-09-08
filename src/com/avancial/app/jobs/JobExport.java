package com.avancial.app.jobs;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.avancial.app.business.compagnieAerienne.TrainToCompagnie;
import com.avancial.app.business.export.ExportPDTByCompagnyToSSIM7;
import com.avancial.app.business.train.Service;
import com.avancial.app.data.controller.dao.ExportSSIMDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueToCompagnieDAO;
import com.avancial.app.data.model.databean.ExportSSIMDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueToCompagnieDataBean;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.app.traitements.TraitementExportDAO;
import com.avancial.app.traitements.TraitementExportDataBean;

/**
 * 
 * @author ismael.yahiani Job d'export du fichier SSIM7
 */
public class JobExport implements Job {

   public void execute(JobExecutionContext context) throws JobExecutionException {

      ExportSSIMDAO daoExport = new ExportSSIMDAO();
      Service s = new Service();
      List<ExportSSIMDataBean> listExport = new ArrayList<>(); 
      List<ExportSSIMDataBean> listParCompagnie = new ArrayList<>();
      Map<String, List<ExportSSIMDataBean>> listCompagnie = new TreeMap();
      
      listExport.clear();
      listExport.addAll(daoExport.getAll());

     String codeCompagnie ;//listExport.get(0).getIdTrainCatalogueToCompagnie().getCompagnieAerienneDataBean().getCodeCompagnieAerienne();
      TraitementExportDAO traitementExportDAO = new TraitementExportDAO();
      TraitementExportDataBean lastTraitement = traitementExportDAO.getLastID();
      int dernierTraitement = traitementExportDAO.getLastID().getIdTraitementExport();
      ExportPDTByCompagnyToSSIM7 exportToSSIM7 = new ExportPDTByCompagnyToSSIM7();
       
      for(int i = 0 ; i < listExport.size() ; i ++) {
         codeCompagnie = listExport.get(i).getIdTrainCatalogueToCompagnie().getCompagnieAerienneDataBean().getCodeCompagnieAerienne(); 
         if(listCompagnie.containsKey(codeCompagnie)) {
            listCompagnie.get(codeCompagnie).add(listExport.get(i)) ; 
         }
         else {
            listParCompagnie = new ArrayList<>();
            listParCompagnie.add(listExport.get(i)) ;
            listCompagnie.put(codeCompagnie, listParCompagnie); 
           }
       
        
      } 
      
      ///////////  rajouter le plan de transport des compagnies 
      
      
      
      exportToSSIM7 = new ExportPDTByCompagnyToSSIM7();
      try {
         exportToSSIM7.export(listCompagnie, lastTraitement, s);
      } catch (ParseException e) {

         e.printStackTrace();
      }
      

   }

}
