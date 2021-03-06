package com.avancial.app.traitements;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.avancial.app.business.parser.APP_enumParserSSIM;
import com.avancial.app.business.parser.FilterEncodage;
import com.avancial.app.business.parser.FilterSSIMTypeEnr;
import com.avancial.app.business.parser.FiltreCatalogue;
import com.avancial.app.business.parser.FiltreSSIMCompagnieTrain;
import com.avancial.app.business.parser.FiltreTrancheOptionnel;
import com.avancial.app.business.reader.ReaderSSIM;
import com.avancial.app.data.controller.dao.CirculationSSIMDao;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.CirculationSSIMDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.model.managedbean.ParamGetterManagedBean;
import com.avancial.app.resources.constants.APP_TgvAir;
import com.avancial.app.resources.constants.APP_params;
import com.avancial.app.resources.utils.GetPeriodeSSIM;
import com.avancial.app.resources.utils.GetTrainsNums;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.socle.resources.constants.SOCLE_constants;
import com.avancial.socle.resources.constants.SOCLE_params;
import com.avancial.socle.utils.StringToDate;

/**
 * 
 * @author ismael.yahiani implemente la methode de lancement de l'import manuellement
 * @param <ParamGetterBean>
 */

public class LancementImportManuel {
   // @Inject
   private ParamGetterManagedBean paramGetter;
   Logger                         logger = Logger.getLogger(LancementImportManuel.class);

   public LancementImportManuel() throws Exception {
      this.paramGetter = new ParamGetterManagedBean();
   }

   public void traitementImportSSIM() throws Exception {

      FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Import", "Traitement Import SSIM Lanc�"));
      TrainCatalogueDAO catalogueDAO = new TrainCatalogueDAO();
      List<TrainCatalogueDataBean> listTrainsCatalogue = catalogueDAO.getAll();
      List<String> listnums = new ArrayList<>();
      List<String> listnumsHashed = new ArrayList<>();
      this.logger.info("Import started");
      ReaderSSIM reader = null;
      // InsertWithJDBC insertWithJDBC = new InsertWithJDBC();
      try {

         reader = new ReaderSSIM(this.paramGetter.getParam(SOCLE_params.PARAM_DIRECTORIES.getValue(), APP_params.PARAMS_DIRECTORIES_SSIM.getValue()).getValue() + APP_TgvAir.SSIM_NOM_FICHIER);

      } catch (IOException e1) {
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "SSIM", "Erreur Lecture SSIM"));
         this.logger.info("Import:" + e1.getMessage());

         e1.printStackTrace();

      }

      if (reader != null) {
         for (TrainCatalogueDataBean tc : listTrainsCatalogue) {
            listnums.add(tc.getNumeroTrainCatalogue());
         }
         // /////////// RECUPERER LA LISTE DES NUMERO DE TRAINS

         for (String s : listnums) {
            listnumsHashed.addAll(GetTrainsNums.getTrainsNums(s));
         }

         // ////////////////////////////// RECHARGER LE TABLEAU DES NUMERO AVEC LA
         // LIST

         String[] num = new String[listnumsHashed.size()];
         for (int i = 0; i < num.length; i++) {
            num[i] = listnumsHashed.get(i);
         }

         IParser par = new ParserFixedLength(new FilterEncodage(new FiltreTrancheOptionnel(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(new FiltreCatalogue(null, num))))), APP_enumParserSSIM.getNames(), APP_enumParserSSIM.getBegins(), APP_enumParserSSIM.getEnds());
         String chaine = "";
         CirculationSSIMDao dao = new CirculationSSIMDao();

         if (dao.getAll().size() > 0)
            dao.deleteAll(0);
         List<CirculationSSIMDataBean> circulationSSIMDataBeans = new ArrayList<>();
         try {

            while ((chaine = reader.readLine()) != null) {
               par.parse(chaine);
               if (!par.getParsedResult().isEmpty()) {
                  CirculationSSIMDataBean circulation = new CirculationSSIMDataBean();
                  circulation.setOriginePointArret(par.getParsedResult().get(APP_enumParserSSIM.POSITION_GARE_DEPART.name()));
                  circulation.setDestinationPointArret(par.getParsedResult().get(APP_enumParserSSIM.POSITION_GARE_ARRIVER.name()));
                  circulation.setHeureArriverCirculation(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_ARRIVER.name()));
                  circulation.setHeureDepartCirculation(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_DEPART.name()));
                  circulation.setJoursCirculation(par.getParsedResult().get(APP_enumParserSSIM.POSITION_JOURS_CIRCULATION.name()));
                  circulation.setGMTDepart(par.getParsedResult().get(APP_enumParserSSIM.POSITION_DIFFERENCE_GMT_DEPART.name()));
                  circulation.setGMTArriver(par.getParsedResult().get(APP_enumParserSSIM.POSITION_DIFFERENCE_GMT_ARRIVER.name()));
                  try {
                     circulation.setDateDebutCirculation(StringToDate.toDate(par.getParsedResult().get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT.name())));
                     circulation.setDateFinCirculation(StringToDate.toDate(par.getParsedResult().get("POSITION_PERIODE_CIRCULATION_FIN")));
                  } catch (ParseException e) {
                     this.logger.error("erreur ssim parse date deb/fin circulation ");

                     e.printStackTrace();
                  }

                  circulation.setTrancheFacultatif(chaine.substring(APP_enumParserSSIM.POSITION_TRANCHE_FACULTATIF.getPositionDebut(), APP_enumParserSSIM.POSITION_TRANCHE_FACULTATIF.getPositionFin()));
                  circulation.setRestrictionTrafic(chaine.substring(APP_enumParserSSIM.POSITION_RESTRICTION_TRAFIC.getPositionDebut(), APP_enumParserSSIM.POSITION_RESTRICTION_TRAFIC.getPositionFin()));
                  circulation.setRangTroncon(Integer.valueOf(chaine.substring(APP_enumParserSSIM.POSITION_RANG_TRANCON.getPositionDebut(), APP_enumParserSSIM.POSITION_RANG_TRANCON.getPositionFin())));
                  circulation.setNumeroTrain(par.getParsedResult().get("POSITION_NUM_TRAIN"));
                  // insertWithJDBC.insertIntoImportSSIMTable(circulation);
                  circulationSSIMDataBeans.add(circulation);
               }
            }
            dao.customSave(circulationSSIMDataBeans);
            reader.closeReader();
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Traitement", "SUCCES Import SSIM"));
         } catch (Exception e) {
            this.logger.error(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Traitement", "Echec Import SSIM"));
            e.printStackTrace();
         }

         TraitementsImportDataBean bean = new TraitementsImportDataBean();
         try {
            bean.setDateDebutSSIM(GetPeriodeSSIM.getSSIMPeriode(this.paramGetter.getParam(SOCLE_params.PARAM_DIRECTORIES.getValue(), APP_params.PARAMS_DIRECTORIES_SSIM.getValue()).getValue() + APP_TgvAir.SSIM_NOM_FICHIER.getConstante()).get("Date_Extraction"));
            bean.setDateFinSSIM(GetPeriodeSSIM.getSSIMPeriode(this.paramGetter.getParam(SOCLE_params.PARAM_DIRECTORIES.getValue(), APP_params.PARAMS_DIRECTORIES_SSIM.getValue()).getValue() + APP_TgvAir.SSIM_NOM_FICHIER.getConstante()).get("Date_Fin"));
         } catch (Exception e1) {

            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Import", "Erreur Import SSIM"));
            this.logger.error(e1.getMessage());
         }

         TraitementImportDAO daoImport = new TraitementImportDAO();

         daoImport.saveTraitementSSIM(bean);

         this.logger.info("Import Termin�");
         new LancementTraitementsManuelle().getAdaptationManuel().traitementAdaptation();
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Import", "Traitement Import SSIM Termin�"));
      } else {
         this.logger.warn("fichier SSIM introuvable");
      }
   }

}
