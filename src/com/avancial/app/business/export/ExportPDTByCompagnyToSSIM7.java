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

import com.avancial.app.business.compagnieAerienne.TrainToCompagnie;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.exportFile.AExportFixedLength;
import com.avancial.exportFile.AExportTxt;
import com.avancial.exportFile.IExportFile;
import com.avancial.test.CirculationTest;
import com.avancial.test.Lunch3;
import com.avancial.test.Luncher;
import com.avancial.test.TestTrain;

public class ExportPDTByCompagnyToSSIM7 { // extends AExportFixedLength {

   private int cpt;
   private int varianceCirculation;
   private Date dateCourante;
   private DateFormat df;

   // public ExportPDTByCompagnyToSSIM7(String fileName ,int[] begins, int[]
   // ends, String[] colNames, boolean bWriteHeaders) {
   // super(fileName, begins, ends, colNames, bWriteHeaders);
   public ExportPDTByCompagnyToSSIM7() {
      this.cpt = 0;
      this.varianceCirculation = 0;
      this.dateCourante = new Date();
      this.df = new SimpleDateFormat("ddMMMyy");
      df.format(dateCourante);

   }

   public void export(TrainToCompagnie tc2c) {
     /* TrainToCompagnie tc2c = new TrainToCompagnie()  ;
      Circulation c1 = new Circulation()  ;

      try {
         c1 = TestTrain.createWithStringPeriode("01/02/2015#30/06/2015#1234567#FRMLW#FRAET#0949#1127");
         c1.setGMTArrivee("+0100");
         c1.setGMTDepart("+0100");
         c1.setRangTranson(01);
      } catch (ParseException e1) {
         e1.printStackTrace();
      }
      tc2c.addCirculation(c1);
      tc2c.addNumeroTrain("5211");
      tc2c.setCodeCompagnie("AF");
      tc2c.setOperatingFlight("1217");
      tc2c.setMarketingFlight("AF4215");
      try {
         tc2c.setDateDebutValidite(StringToDate.toDate("01FEB15"));
         tc2c.setDateFinValidite(StringToDate.toDate("31JUL15"));
      } catch (ParseException e1) {
         e1.printStackTrace();
      }*/

      try {

         File file = new File("D:/exportSSIM7/SSIM7_Test.txt");

         // if file doesnt exists, then create it
         if (!file.exists()) {
            file.createNewFile();
         }

         FileWriter fw = new FileWriter(file.getAbsoluteFile());
         BufferedWriter bw = new BufferedWriter(fw);
         bw.write(this.getEnrgType1() + "\n");
         bw.write(this.getEnrgType2() + "\n");
         for (Circulation c : tc2c.getCirculations()) {
            bw.write(this.getEnrgType3(tc2c, c) + "\n");
            bw.write(this.getEnrgType4(tc2c, c) + "\n");
         } 
         bw.write(this.getEnrgType5());
         bw.close();
         System.out.println("Done");

      } catch (IOException e) {
      }
   }

   public String getEnrgType1() {

      StringBuilder sb = new StringBuilder();
      sb.append("1");
      sb.append("AIRLINE STANDARD SCHEDULE DATA SET");
      for (int i = 0; i < 155; i++) {
         sb.append(" ");
      }
      sb.append("0001");
      cpt++;
      if (String.valueOf(cpt).length() == 1)
         sb.append("00000" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 2)
         sb.append("0000" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 3)
         sb.append("000" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 4)
         sb.append("00" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 5)
         sb.append("0" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 6)
         sb.append(String.valueOf(cpt));

      return sb.toString();
   }

   public String getEnrgType2() {
      StringBuilder sb = new StringBuilder();
      sb.append("2L2C ");
      sb.append("0008 ");
      for (int i = 0; i < 4; i++)
         sb.append(" ");

      sb.append("05MAY15" + "31DEC15"); // date courante + date fin service
      sb.append("05MAY15"); // date creation

      for (int i = 0; i < 36; i++) {
         sb.append(" ");
      }
      sb.append("C");
      for (int i = 0; i < 118; i++) {
         sb.append(" ");
      }
      sb.append("1010");
      // sb.append(String.valueOf(cpt++)) ;
      cpt++;
      if (String.valueOf(cpt).length() == 1)
         sb.append("00000" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 2)
         sb.append("0000" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 3)
         sb.append("000" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 4)
         sb.append("00" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 5)
         sb.append("0" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 6)
         sb.append(String.valueOf(cpt));

      return sb.toString();
   }

   public String getEnrgType3(TrainToCompagnie tc2c, Circulation c) {

      StringBuilder sb = new StringBuilder();
      sb.append("3");
      sb.append("  ");
      sb.append("2C ");
      sb.append(tc2c.getOperatingFlight());
      varianceCirculation++;
      if (varianceCirculation++ < 10)
         sb.append("0" + String.valueOf(varianceCirculation));
      else
         sb.append("0" + String.valueOf(varianceCirculation)); //
      sb.append("01");
      sb.append("J");
      sb.append(StringToDate.toString(tc2c.getDateDebutValidite()) + StringToDate.toString(tc2c.getDateFinValidite()));
      sb.append(c.getJoursCirculation());
      sb.append(" ");
      sb.append("CDG");
      sb.append(c.getHeureDepart());
      sb.append(c.getHeureDepart());
      sb.append(c.getGMTDepart());
      sb.append("TN");
      sb.append("ZLN");
      sb.append(c.getHeureArrivee());
      sb.append(c.getHeureArrivee());
      sb.append(c.getGMTArrivee());
      sb.append("TN");
      sb.append("ZLN");

      for (int i = 0; i < 19; i++) {// à ajouter quota 1 et 2 classe
         sb.append(" ");
      }
      for (int i = 0; i < 76; i++) {
         sb.append(" ");
      }
      for (int i = 0; i < 23; i++) {// à ajouter quota 1 et 2 classe
         sb.append(" ");
      }
      sb.append("  ");
      cpt++;
      if (String.valueOf(cpt).length() == 1)
         sb.append("00000" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 2)
         sb.append("0000" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 3)
         sb.append("000" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 4)
         sb.append("00" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 5)
         sb.append("0" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 6)
         sb.append(String.valueOf(cpt));
      
      return sb.toString();
   }

   public String getEnrgType4(TrainToCompagnie tc2c, Circulation c) {
      StringBuilder sb = new StringBuilder();
      sb.append("4");
      sb.append(" ");
      sb.append("2C ");
      sb.append(tc2c.getOperatingFlight());
      if (varianceCirculation < 10)
         sb.append("0" + String.valueOf(varianceCirculation));
      else
         sb.append("0" + String.valueOf(varianceCirculation));
      sb.append("01");
      sb.append("J");
      for (int i = 0; i < 13; i++) {
         sb.append(" ");
      }
      sb.append("A") ;
      sb.append("B") ;
      sb.append("010")  ;
      sb.append("CDG")  ;
      sb.append("XDB")  ;
      if (tc2c.getMarketingFlight().length() <= 7) {
         sb.append(tc2c.getMarketingFlight());
         for (int i = 0; i < 150; i++) {
            sb.append("0");
         }
      } else {
         for (int i = 0; i < 158; i++) {// à ajouter quota 1 et 2 classe
            sb.append(" ");
         }
      }
         cpt++;
         if (String.valueOf(cpt).length() == 1)
            sb.append("00000" + String.valueOf(cpt));
         if (String.valueOf(cpt).length() == 2)
            sb.append("0000" + String.valueOf(cpt));
         if (String.valueOf(cpt).length() == 3)
            sb.append("000" + String.valueOf(cpt));
         if (String.valueOf(cpt).length() == 4)
            sb.append("00" + String.valueOf(cpt));
         if (String.valueOf(cpt).length() == 5)
            sb.append("0" + String.valueOf(cpt));
         if (String.valueOf(cpt).length() == 6)
            sb.append(String.valueOf(cpt));
     

      return sb.toString();
   }

   public String getEnrgType5() {
      StringBuilder sb = new StringBuilder() ; 
      sb.append("5");
      sb.append("  ");
      sb.append("2C ");
      for (int i = 0; i < 188; i++) {
         sb.append(" ");
      }
      cpt++;
      if (String.valueOf(cpt).length() == 1)
         sb.append("00000" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 2)
         sb.append("0000" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 3)
         sb.append("000" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 4)
         sb.append("00" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 5)
         sb.append("0" + String.valueOf(cpt));
      if (String.valueOf(cpt).length() == 6)
         sb.append(String.valueOf(cpt));
      return sb.toString();
   }
}
