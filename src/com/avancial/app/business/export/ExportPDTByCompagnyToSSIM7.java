package com.avancial.app.business.export;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.avancial.app.business.train.Service;
import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.model.managedbean.ParamGetterManagedBean;
import com.avancial.app.resources.constants.APP_params;
import com.avancial.app.resources.utils.FormatterInteger2String;
import com.avancial.app.resources.utils.StringToFormatedString;
import com.avancial.app.resources.utils.TimeZoneOffSet;
import com.avancial.app.traitements.TraitementExportDataBean;
import com.avancial.socle.params.exception.ParamCollectionNotLoadedException;
import com.avancial.socle.params.exception.ParamNotFoundException;
import com.avancial.socle.resources.constants.SOCLE_params;
import com.avancial.socle.utils.StringToDate;
import com.avancial.writer.FormaterLeftSpaces;
import com.avancial.writer.FormaterStrategyFixedLength;
import com.avancial.writer.IFormaterFixedLength;
import com.avancial.writer.IFormaterStrategy;
import com.avancial.writer.IWriter;
import com.avancial.writer.WriterTxt;

public class ExportPDTByCompagnyToSSIM7 { // extends AExportFixedLength {

   Logger                         log = Logger.getLogger(ExportPDTByCompagnyToSSIM7.class);
   private int                    cpt;
   private int                    varianceCirculation;
   private Date                   dateCourante;
   private DateFormat             df;
   private IWriter                writer;
   private int                    numFichier;
   private File                   file;
   private ParamGetterManagedBean paramGetter;

   /**
    * 
    * @author Yahiani Ismail
    * @param Export
    *           des trains Compagnie Impactés par les modifications implementation de la class WriterSSIM pour ecrire dans un fichier
    * @throws Exception
    */
   public ExportPDTByCompagnyToSSIM7() throws Exception {
      this.cpt = 0;
      this.varianceCirculation = 0;
      this.dateCourante = new Date();
      this.df = new SimpleDateFormat("ddMMMyy");
      this.df.format(this.dateCourante);
      this.numFichier = 0;
      this.paramGetter = new ParamGetterManagedBean();
   }

   /**
    * @author ismael.yahiani
    * @param tc2c
    * @throws ParseException
    *            exporter la liste des trains relatifs a la compagnie sous le format SSIM 7
    * 
    */

   public void export(List<TrainCatalogue> listCatalogue, TraitementExportDataBean bean, Service service) throws ParseException {

      Logger log = Logger.getLogger(ExportPDTByCompagnyToSSIM7.class);
      String compagnieName = listCatalogue.get(0).getCodeCompagnie();
      this.file = new File(compagnieName + StringToDate.toString(this.dateCourante) + bean.getHeureCreation() + ".txt");
      try {
         this.writer = new WriterTxt(this.paramGetter.getParam(SOCLE_params.PARAM_DIRECTORIES.getValue(), APP_params.PARAMS_DIRECTORIES_SSIM7.getValue()).getValue() + this.file);
      } catch (IOException | ParamNotFoundException | ParamCollectionNotLoadedException e1) {

         this.log.error("Erreur d'écriture Fichier SSIM" + e1.getMessage());
      }
      this.cpt = 0;
      try {
         int[] beginsType1 = { 0, 1, 35, 191, 194, 200 };
         int[] lengthsType1 = { 1, 34, 156, 3, 6, 1 };
         int[] beginsType2 = { 0, 1, 2, 5, 10, 14, 28, 35, 71, 72, 190, 194, 200 };
         int[] lengthsType2 = { 1, 1, 3, 5, 4, 14, 7, 36, 1, 118, 4, 6, 1 };

         int[] beginsType3 = { 0, 1, 2, 5, 9, 11, 13, 14, 28, 35, 36, 39, 43, 47, 52, 54, 57, 61, 65, 70, 72, 75, 95, 172, 192, 194, 200 };
         int[] lengthsType3 = { 1, 1, 3, 4, 2, 2, 1, 14, 7, 1, 3, 4, 4, 5, 2, 3, 4, 4, 5, 2, 3, 20, 77, 20, 2, 6, 1 };

         int[] beginsType4 = { 0, 1, 2, 5, 9, 11, 13, 14, 28, 29, 30, 33, 36, 39, 194, 200 };
         int[] lengthsType4 = { 1, 1, 3, 4, 2, 2, 1, 14, 1, 1, 3, 3, 3, 155, 6, 1 };

         int[] beginsType5 = { 0, 1, 2, 5, 187, 193, 194, 200 };
         int[] lengthsType5 = { 1, 1, 3, 182, 6, 1, 6, 1 };

         IFormaterFixedLength[] formatersType1 = new IFormaterFixedLength[6];
         IFormaterStrategy formater = new FormaterStrategyFixedLength(beginsType1, lengthsType1, formatersType1, null, false, new FormaterLeftSpaces());
         this.writer.setFormaterStrategy(formater);
         this.writer.write(this.getEnrgType1());

         IFormaterFixedLength[] formatersType2 = new IFormaterFixedLength[13];
         IFormaterStrategy formater2 = new FormaterStrategyFixedLength(beginsType2, lengthsType2, formatersType2, null, false, new FormaterLeftSpaces());
         this.writer.setFormaterStrategy(formater2);
         this.writer.write(this.getEnrgType2(bean, service));

         IFormaterFixedLength[] formatersType3 = new IFormaterFixedLength[27];
         IFormaterStrategy formater3 = new FormaterStrategyFixedLength(beginsType3, lengthsType3, formatersType3, null, false, new FormaterLeftSpaces());

         IFormaterFixedLength[] formatersType4 = new IFormaterFixedLength[16];
         IFormaterStrategy formater4 = new FormaterStrategyFixedLength(beginsType4, lengthsType4, formatersType4, null, false, new FormaterLeftSpaces());

         IFormaterFixedLength[] formatersType5 = new IFormaterFixedLength[8];
         IFormaterStrategy formater5 = new FormaterStrategyFixedLength(beginsType5, lengthsType5, formatersType5, null, false, new FormaterLeftSpaces());

         for (TrainCatalogue tc : listCatalogue) {
            for (Circulation c : tc.getListeCirculations()) {
               this.writer.setFormaterStrategy(formater3);
               this.writer.write(this.getEnrgType3(tc, c));
               this.writer.setFormaterStrategy(formater4);
               this.writer.write(this.getEnrgType4(tc, c));
            }
            this.varianceCirculation = 0;
         }

         this.writer.setFormaterStrategy(formater5);
         this.writer.write(this.getEnrgType5());
         this.writer.close();

      } catch (IOException e) {
         log.error(e.getMessage());
      }
      this.numFichier++;
   }

   public ArrayList<String> getEnrgType1() {

      ArrayList<String> liste = new ArrayList<>();
      this.cpt++;

      liste.add("1");
      liste.add("AIRLINE STANDARD SCHEDULE DATA SET");
      liste.add("");
      liste.add("001");
      liste.add(StringToFormatedString.formatterCompteurSSIM7(this.cpt));
      liste.add("\n");
      return liste;
   }

   public ArrayList<String> getEnrgType2(TraitementExportDataBean bean, Service service) {
      ArrayList<String> liste = new ArrayList<>();
      liste.add("2");
      liste.add("L");
      liste.add("2C");
      liste.add("0008");
      liste.add("");
      liste.add(StringToDate.toString(service.getDateDebutService()).toUpperCase() + StringToDate.toString(service.getDateFinService()).toUpperCase());
      liste.add(StringToDate.toString(bean.getDateExtraction()).toUpperCase());
      liste.add("");
      liste.add("C");
      liste.add("");
      liste.add(String.valueOf(Integer.valueOf(bean.getHeureCreation()) < 1000 ? "0".concat(bean.getHeureCreation()) : bean.getHeureCreation()));
      this.cpt++;
      liste.add(StringToFormatedString.formatterCompteurSSIM7(this.cpt));
      liste.add("\n");
      return liste;
   }

   public ArrayList<String> getEnrgType3(TrainCatalogue tc2c, Circulation c) {

      ArrayList<String> liste = new ArrayList<>();

      liste.add("3");
      liste.add("");
      liste.add("2C");
      liste.add(tc2c.getOoperatingFlight().substring(2));
      this.varianceCirculation++;
      if (this.varianceCirculation < 10)
         liste.add("0" + String.valueOf(this.varianceCirculation));
      else
         liste.add(String.valueOf(this.varianceCirculation)); //
      liste.add("01");
      liste.add("U");
      liste.add(StringToDate.toString(c.getDateDebut()) + StringToDate.toString(c.getDateFin()));// tc2c.getDateDebutValidite()--tc2c.getDateFinValidite()
      liste.add(c.getJoursCirculation());
      liste.add("");
      liste.add(tc2c.getPointArretOrigine().getCodeGDSPointArret());
      liste.add(FormatterInteger2String.getFormatedIntToStr(c.getHeureDepart()));
      liste.add(FormatterInteger2String.getFormatedIntToStr(c.getHeureDepart()));
      liste.add(TimeZoneOffSet.getGMTDiff());
      // tc2c.getGMTDepart()
      // ajouter un test si CDG alors TN sinon " "
      if (tc2c.getPointArretOrigine().getCodeGDSPointArret().equalsIgnoreCase("CDG"))
         liste.add("TN");
      else
         liste.add("");// ajouter un test si CDG alors TN sinon " "

      liste.add(tc2c.getPointArretDestination().getCodeGDSPointArret());
      liste.add(FormatterInteger2String.getFormatedIntToStr(c.getHeureArrivee()));
      liste.add(FormatterInteger2String.getFormatedIntToStr(c.getHeureArrivee()));
      liste.add(TimeZoneOffSet.getGMTDiff()); //
      if (tc2c.getPointArretDestination().getCodeGDSPointArret().equalsIgnoreCase("CDG"))
         liste.add("TN");
      else
         liste.add("");
      liste.add("TRN");
      liste.add("C" + StringToFormatedString.formaterQuotas(String.valueOf(tc2c.getQuota1er())) + "Y" + StringToFormatedString.formaterQuotas(String.valueOf(tc2c.getQuota2eme())));
      liste.add("");
      liste.add("C" + StringToFormatedString.formaterQuotas(String.valueOf(tc2c.getQuota1er())) + "Y" + StringToFormatedString.formaterQuotas(String.valueOf(tc2c.getQuota2eme())));
      liste.add("");
      this.cpt++;
      liste.add(StringToFormatedString.formatterCompteurSSIM7(this.cpt));
      liste.add("\n");
      return liste;
   }

   public ArrayList<String> getEnrgType4(TrainCatalogue tc2c, Circulation c) {
      ArrayList<String> liste = new ArrayList<>();
      liste.add("4");
      liste.add("");
      liste.add("2C");
      liste.add(tc2c.getOoperatingFlight().substring(2));
      if (this.varianceCirculation < 10)
         liste.add("0" + String.valueOf(this.varianceCirculation));
      else
         liste.add(String.valueOf(this.varianceCirculation));
      liste.add("01");
      liste.add("J");
      liste.add("");

      liste.add("A");
      liste.add("B");
      liste.add("010");
      liste.add(tc2c.getPointArretOrigine().getCodeGDSPointArret());
      liste.add(tc2c.getPointArretDestination().getCodeGDSPointArret()); // inserer
                                                                         // le
                                                                         // code
                                                                         // IATA

      liste.add(StringToFormatedString.formaterMatketingFlight(tc2c.getMarketingFlight()));
      this.cpt++;
      liste.add(StringToFormatedString.formatterCompteurSSIM7(this.cpt));
      liste.add("\n");
      return liste;
   }

   public ArrayList<String> getEnrgType5() {
      ArrayList<String> liste = new ArrayList<>();
      liste.add("5");
      liste.add("");
      liste.add("2C");
      liste.add("");
      this.cpt++;
      liste.add(StringToFormatedString.formatterCompteurSSIM7(this.cpt));
      liste.add("E");
      this.cpt++;
      liste.add(StringToFormatedString.formatterCompteurSSIM7(this.cpt));
      liste.add("\n");
      return liste;
   }

   public int getNumFichier() {
      return this.numFichier;
   }

   public void setNumFichier(int numFichier) {
      this.numFichier = numFichier;
   }

   public File getFile() {
      return this.file;
   }

   public void setFile(File file) {
      this.file = file;
   }
}
