package com.avancial.app.business.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.avancial.app.business.compagnieAerienne.TrainToCompagnie;
import com.avancial.app.business.train.Service;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.data.model.databean.ExportSSIMDataBean;
import com.avancial.app.resources.utils.HeureFormattage;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.app.traitements.TraitementExportDataBean;
import com.avancial.writer.FormaterLeftSpaces;
import com.avancial.writer.FormaterLeftZero;
import com.avancial.writer.FormaterRightSpace;
import com.avancial.writer.FormaterStrategyFixedLength;
import com.avancial.writer.IFormaterFixedLength;
import com.avancial.writer.IFormaterStrategy;
import com.avancial.writer.IWriter;
import com.avancial.writer.WriteSSIM;
import com.avancial.writer.WriterTxt;
import com.avancial.writer.WriterTxtFixedLength;

public class ExportPDTByCompagnyToSSIM7 { // extends AExportFixedLength {

   private int cpt;
   private int varianceCirculation;
   private Date dateCourante;
   private DateFormat df;
   private IWriter writer;
   private int numFichier;

   /**
    * 
    @author Yahiani Ismail
    * @param Export
    *           des trains Compagnie Impactés par les modifications
    *           implementation de la class WriterSSIM pour ecrire dans un
    *           fichier
    */
   public ExportPDTByCompagnyToSSIM7() {
      this.cpt = 0;
      this.varianceCirculation = 0;
      this.dateCourante = new Date();
      this.df = new SimpleDateFormat("ddMMMyy");
      this.df.format(this.dateCourante);
      this.numFichier = 0;
   }

   /**
    * @author ismael.yahiani
    * @param tc2c
    * @throws ParseException
    * 
    */
   public void export(Map<String, List<ExportSSIMDataBean>> listCompagnie, TraitementExportDataBean bean, Service service) throws ParseException {

      for (Entry<String, List<ExportSSIMDataBean>> entry : listCompagnie.entrySet()) {
         List<ExportSSIMDataBean> list = entry.getValue() ;
         Logger log = Logger.getLogger(ExportPDTByCompagnyToSSIM7.class);
         File file = new File(String.valueOf(this.numFichier) + entry.getKey()+".txt");
         this.writer = new WriterTxt("D:/exportSSIM7/" + file);
         this.cpt = 0 ;
         try {
            int[] beginsType1 = { 0, 1, 35, 191, 194, 200 };
            int[] lengthsType1 = { 1, 34, 156, 3, 6, 1 };
            int[] beginsCompteurType1 = { 194 };
            int[] lengthsCompteurType1 = { 6 };

            int[] beginsType2 = { 0, 1, 2, 5, 10, 14, 28, 35, 71, 72, 190, 194, 200 };
            int[] lengthsType2 = { 1, 1, 3, 5, 4, 14, 7, 37, 1, 117, 4, 6, 1 };

            int[] beginsType3 = { 0, 1, 2, 5, 9, 11, 13, 14, 28, 35, 36, 39, 43, 47, 52, 54, 57, 61, 65, 70, 72, 75, 95, 172, 192, 194, 200 };
            int[] lengthsType3 = { 1, 1, 3, 4, 2, 2, 1, 14, 7, 1, 3, 4, 4, 5, 2, 3, 4, 4, 5, 2, 3, 20, 77, 20, 2, 6, 1 };

            int[] beginsType4 = { 0, 1, 2, 5, 9, 11, 13, 14, 28, 29, 30, 33, 36, 39, 194, 200 };
            int[] lengthsType4 = { 1, 1, 3, 4, 2, 2, 1, 14, 1, 1, 3, 3, 3, 155, 6, 1 };

            int[] beginsType5 = { 0, 1, 2, 5, 194, 200 };
            int[] lengthsType5 = { 1, 1, 3, 189, 6, 1 };

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

            IFormaterFixedLength[] formatersType5 = new IFormaterFixedLength[6];
            IFormaterStrategy formater5 = new FormaterStrategyFixedLength(beginsType5, lengthsType5, formatersType5, null, false, new FormaterLeftSpaces());
            // for (ExportSSIMDataBean tc2c : listTrainsToCompagnie)

            for (ExportSSIMDataBean tc2c : list) {
            if (tc2c.getDateFinCirculation().after(bean.getDateExtraction())) {// a remplacer par la date courante
               this.writer.setFormaterStrategy(formater3);
               this.writer.write(this.getEnrgType3(tc2c));
               this.writer.setFormaterStrategy(formater4);
               this.writer.write(this.getEnrgType4(tc2c));
            }
            }

            this.writer.setFormaterStrategy(formater5);
            this.writer.write(this.getEnrgType5());
            this.writer.close();

         } catch (IOException e) {
            log.error(e.getMessage());
         }
         this.numFichier++;
      }
   }

   public ArrayList<String> getEnrgType1() {

      ArrayList<String> liste = new ArrayList<>();
      this.cpt++;

      liste.add("1");
      liste.add("AIRLINE STANDARD SCHEDULE DATA SET");
      liste.add("");
      liste.add("001");
      if (String.valueOf(this.cpt).length() == 1)
         liste.add("00000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 2)
         liste.add("0000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 3)
         liste.add("000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 4)
         liste.add("00" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 5)
         liste.add("0" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 6)
         liste.add(String.valueOf(this.cpt));
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
      liste.add(StringToDate.toString(service.getDateDebutService()).toUpperCase() + StringToDate.toString(service.getDateFinService()).toUpperCase()); // à
                                                                                                                                                        // mettre
                                                                                                                                                        // la
                                                                                                                                                        // date
                                                                                                                                                        // debut
                                                                                                                                                        // du
                                                                                                                                                        // service
                                                                                                                                                        // +
                                                                                                                                                        // //
                                                                                                                                                        // la
                                                                                                                                                        // date
                                                                                                                                                        // fin
                                                                                                                                                        // service
      liste.add(StringToDate.toString(bean.getDateExtraction()).toUpperCase()); // à
                                                                                // mettre
                                                                                // la
                                                                                // date
                                                                                // de
                                                                                // l'extraction
      liste.add("");
      liste.add("C");
      liste.add("");
      liste.add(String.valueOf(Integer.valueOf(bean.getHeureCreation()) < 1000 ? "0".concat(bean.getHeureCreation()) : bean.getHeureCreation())); // heure
                                                                                                                                                  // creation
                                                                                                                                                  // Fichier
                                                                                                                                                  // <
      this.cpt++;
      if (String.valueOf(this.cpt).length() == 1)
         liste.add("00000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 2)
         liste.add("0000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 3)
         liste.add("000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 4)
         liste.add("00" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 5)
         liste.add("0" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 6)
         liste.add(String.valueOf(this.cpt));
      liste.add("\n");
      return liste;
   }

   public ArrayList<String> getEnrgType3(ExportSSIMDataBean tc2c) {

      ArrayList<String> liste = new ArrayList<>();

      liste.add("3");
      liste.add("");
      liste.add("2C");
      liste.add(tc2c.getIdTrainCatalogue().getOperatingFlight().substring(2));
      this.varianceCirculation++;
      if (this.varianceCirculation < 10)
         liste.add("0" + String.valueOf(this.varianceCirculation));
      else
         liste.add(String.valueOf(this.varianceCirculation)); //
      liste.add("01");
      liste.add("J");
      liste.add(StringToDate.toString(tc2c.getDateDebutCirculation()) + StringToDate.toString(tc2c.getDateFinCirculation()));// tc2c.getDateDebutValidite()--tc2c.getDateFinValidite()
      liste.add(tc2c.getRegimeCirculation());
      liste.add("");
      liste.add(tc2c.getIdTrainCatalogue().getIdPointArretOrigine().getCodeGDSPointArret());
      liste.add(String.valueOf(tc2c.getHeureDepartCirculation()));
      liste.add(String.valueOf(tc2c.getHeureDepartCirculation()));
      liste.add("+0100");// tc2c.getGMTDepart()
      if (tc2c.getIdTrainCatalogue().getIdPointArretOrigine().getCodeGDSPointArret().endsWith("CDG"))
         liste.add("TN");
      else
         liste.add("");// ajouter un test si CDG alors TN sinon "  "
      liste.add("ZLN");
      liste.add(String.valueOf(tc2c.getHeureArriverCirculation()));
      liste.add(String.valueOf(tc2c.getHeureArriverCirculation()));
      liste.add("+0100"); //
      liste.add("");
      liste.add(tc2c.getIdTrainCatalogue().getIdPointArretDestination().getCodeGDSPointArret());
      liste.add("C" + String.valueOf(tc2c.getIdTrainCatalogueToCompagnie().getQuotaPremiereTrainCatalogueToCompagnie()) + "Y"
            + String.valueOf(tc2c.getIdTrainCatalogueToCompagnie().getQuotaDeuxiemeTrainCatalogueToCompagnie()));
      liste.add("");
      liste.add("C" + String.valueOf(tc2c.getIdTrainCatalogueToCompagnie().getQuotaPremiereTrainCatalogueToCompagnie()) + "Y"
            + String.valueOf(tc2c.getIdTrainCatalogueToCompagnie().getQuotaDeuxiemeTrainCatalogueToCompagnie()));
      liste.add("");
      this.cpt++;
      if (String.valueOf(this.cpt).length() == 1)
         liste.add("00000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 2)
         liste.add("0000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 3)
         liste.add("000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 4)
         liste.add("00" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 5)
         liste.add("0" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 6)
         liste.add(String.valueOf(this.cpt));
      liste.add("\n");
      return liste;
   }

   public ArrayList<String> getEnrgType4(ExportSSIMDataBean tc2c) {
      ArrayList<String> liste = new ArrayList<>();
      liste.add("4");
      liste.add("");
      liste.add("2C");
      liste.add(tc2c.getIdTrainCatalogue().getOperatingFlight().substring(2));
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
      liste.add(tc2c.getIdTrainCatalogue().getIdPointArretOrigine().getCodeGDSPointArret());
      liste.add(tc2c.getIdTrainCatalogue().getIdPointArretDestination().getCodeGDSPointArret()); // inserer
                                                                                                 // le
                                                                                                 // code
                                                                                                 // IATA
      liste.add(tc2c.getIdTrainCatalogueToCompagnie().getMarketingFlightTrainCatalogueToCompagnie());
      this.cpt++;
      if (String.valueOf(this.cpt).length() == 1)
         liste.add("00000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 2)
         liste.add("0000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 3)
         liste.add("000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 4)
         liste.add("00" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 5)
         liste.add("0" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 6)
         liste.add(String.valueOf(this.cpt));
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
      if (String.valueOf(this.cpt).length() == 1)
         liste.add("00000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 2)
         liste.add("0000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 3)
         liste.add("000" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 4)
         liste.add("00" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 5)
         liste.add("0" + String.valueOf(this.cpt));
      if (String.valueOf(this.cpt).length() == 6)
         liste.add(String.valueOf(this.cpt));
      liste.add("\n");
      return liste;
   }

   public int getNumFichier() {
      return numFichier;
   }

   public void setNumFichier(int numFichier) {
      this.numFichier = numFichier;
   }
}
