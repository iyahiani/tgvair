package com.avancial.app.traitements;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

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
import com.avancial.socle.resources.constants.SOCLE_constants;
import com.avancial.socle.utils.StringToDate;

/**
 * 
 * @author ismael.yahiani implemente la methode de lancement manuel de l'extraction de la SSIM 7 Train
 */
public class LancementExportManuel {

   Logger logger = Logger.getLogger(LancementExportManuel.class);

   public LancementExportManuel() {
   }

   public void traitementExport() {

      List<TrainCatalogueToCompagnieDataBean> listTC2C = new TrainCatalogueToCompagnieDAO().getAll();
      List<CompagnieAerienneDataBean> listeCompagnie = new CompagnieAerienneDao().getAll();
      FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Export", "Traitement Export SSIM 7 lancé"));
      Calendar lastCircule = Calendar.getInstance();
      lastCircule.setTime(new CirculationDAO().getLastDateCreation().getDateCreationLigneTrain());

      TrainCatalogueToCompagnieDAO catalogueToCompagnieDAO = new TrainCatalogueToCompagnieDAO();
      TrainCatalogueDAO catalogueDAO = new TrainCatalogueDAO();
      TrainCatalogueDataBean tc = null;

      for (CompagnieAerienneDataBean compagnie : listeCompagnie) {
         listTC2C = catalogueToCompagnieDAO.getTrainCatalogueByIdCompagnie(compagnie.getIdCompagnieAeriennne());
         boolean compare = false;

         for (TrainCatalogueToCompagnieDataBean tc2c : listTC2C) {
            tc = catalogueDAO.getTrainCatalogueByID(tc2c.getTrainCatalogueDataBean().getIdTrainCatalogue());

            List<TrainCatalogue> listeTrainCatalogue = TrainFactory.get2DerniersTC(tc2c.getTrainCatalogueDataBean().getIdTrainCatalogue(), lastCircule.getTime());// Calendar.getInstance().getTime()
            if (listeTrainCatalogue.size() > 1) {
               TrainCatalogue trainPortef1 = listeTrainCatalogue.get(0).getTrainFromPortefeuille(tc2c.getDateDebutValiditeTrainCatalogueToCompagnie(), tc2c.getDateFinValiditeTrainCatalogueToCompagnie());
               TrainCatalogue trainPortef2 = listeTrainCatalogue.get(1).getTrainFromPortefeuille(tc2c.getDateDebutValiditeTrainCatalogueToCompagnie(), tc2c.getDateFinValiditeTrainCatalogueToCompagnie());
               if (!trainPortef1.compare(trainPortef2)) {
                  compare = true;
                  break;
               }
            }

            if (listeTrainCatalogue.size() == 1) {
               Calendar c = Calendar.getInstance(); // Regarder si ce train date
               // d'aujourd'hui ou si c'est un ancien train :Si nouveau Export /
               // Sinon ne pas exporter
               try {
                  if (StringToDate.toStringByFormat(new CirculationDAO().getCirculationByIdTrain(listeTrainCatalogue.get(0).getIdTrain()).get(0).getDateCreationLigneTrain(), "dateBySlashSansHeure").equals(StringToDate.toStringByFormat(c.getTime(), "dateBySlashSansHeure"))) {
                     compare = true;
                     break;
                  }
               } catch (Exception e) {

                  e.printStackTrace();
               }
            }
         }

         if (compare) {
            List<TrainCatalogue> listCatalogue = new ArrayList<>();
            for (TrainCatalogueToCompagnieDataBean tc2c : listTC2C) {
               tc = catalogueDAO.getTrainCatalogueByID(tc2c.getTrainCatalogueDataBean().getIdTrainCatalogue());
               CirculationDAO dao = new CirculationDAO();
               // On récupère les circulations correspondant à l'id du train
               // catalogue on question
               List<CirculationAdapterDataBean> liste = dao.getCirculationByIdTrain(tc.getIdTrainCatalogue());
               if (liste.size() > 0) {
                  TrainCatalogue train = TrainFactory.createTrainCatalogueFromBeans(liste);
                  TrainCatalogue trainPortf = train.getTrainFromPortefeuille(tc2c.getDateDebutValiditeTrainCatalogueToCompagnie(), tc2c.getDateFinValiditeTrainCatalogueToCompagnie());
                  trainPortf.setCodeCompagnie(tc2c.getCompagnieAerienneDataBean().getCodeCompagnieAerienne());
                  trainPortf.setQuota1er(tc2c.getQuotaPremiereTrainCatalogueToCompagnie());
                  trainPortf.setQuota2eme(tc2c.getQuotaDeuxiemeTrainCatalogueToCompagnie());
                  trainPortf.setPointArretOrigine(tc2c.getTrainCatalogueDataBean().getIdPointArretOrigine());
                  trainPortf.setPointArretDestination(tc2c.getTrainCatalogueDataBean().getIdPointArretDestination());
                  trainPortf.setMarketingFlight(tc2c.getMarketingFlightTrainCatalogueToCompagnie());
                  listCatalogue.add(trainPortf);
               }
            }

            try {
               ExportPDTByCompagnyToSSIM7 export = new ExportPDTByCompagnyToSSIM7();
               export.export(listCatalogue, new TraitementExportDataBean(), new Service());
               FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Traitement", "SUCCES Export SSIM7"));
               this.logger.info("Export SSIM7 Terminé");
            } catch (Exception e) {
               FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Traitement", "Echec Export SSIM7"));
               this.logger.error("Echec Export SSIm7");
               e.printStackTrace();
            }
         }

      }
   }
}
