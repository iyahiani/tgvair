package com.avancial.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.avancial.app.business.parser.APP_enumParserSSIM;
import com.avancial.app.business.parser.FilterEncodage;
import com.avancial.app.business.parser.FilterSSIMTypeEnr;
import com.avancial.app.business.parser.FiltreSSIMCompagnieTrain;
import com.avancial.app.business.train.Circulation;
import com.avancial.app.business.train.ITrain;
import com.avancial.app.business.train.ITrainFactory;
import com.avancial.app.business.train.JourCirculation;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.business.train.TrainFactory;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;

public class TestTrain {

   // @Test
   public void testTrainsIdentiques() {
      ITrain train1;
      ITrain train2;

      Circulation circul1 = new Circulation();
      Circulation circul2 = new Circulation();
      circul1.setDateDebut(new Date("01JAN15"));
      circul1.setDateFin(new Date("30JAN15"));
      circul1.setOrigine("CDG");
      circul1.setDestination("MAR");
      circul1.setHeureArrivee(1500);
      circul1.setHeureDepart(1515);
      circul1.setJoursCirculation("123");

      circul2.setDateDebut(new Date("01JAN15"));
      circul2.setDateFin(new Date("30JAN15"));
      circul2.setOrigine("CDG");
      circul2.setDestination("MAR");
      circul2.setHeureArrivee(1500);
      circul2.setHeureDepart(1515);
      circul2.setJoursCirculation("123");

      List<Circulation> listCircu = new ArrayList<Circulation>();
      listCircu.add(circul1);
      listCircu.add(circul2);

      ITrainFactory factory = new TrainFactory();
      train1 = factory.createTrainByListCirculation(listCircu);
      train2 = factory.createTrainByListCirculation(listCircu);
      Assert.assertTrue(train1.compare(train2));
   }

   // @Test
   @SuppressWarnings("deprecation")
   public void testTrainsDifferents() {
      ITrain train1;
      ITrain train2;

      Circulation circul1 = new Circulation();
      Circulation circul2 = new Circulation();
      circul1.setDateDebut(new Date("01JAN15"));
      circul1.setDateFin(new Date("30JAN15"));
      circul1.setOrigine("CDG");
      circul1.setDestination("MAR");
      circul1.setHeureArrivee(1500);
      circul1.setHeureDepart(1515);
      circul1.setJoursCirculation("123");

      circul2.setDateDebut(new Date("01JAN15"));
      circul2.setDateFin(new Date("30JAN15"));
      circul2.setOrigine("CDG");
      circul2.setDestination("MAR");
      circul2.setHeureArrivee(1500);
      circul2.setHeureDepart(1515);
      circul2.setJoursCirculation("123");

      List<Circulation> listCircu = new ArrayList<Circulation>();
      List<Circulation> listCircu2 = new ArrayList<Circulation>();
      listCircu.add(circul1);
      listCircu.add(circul2);

      circul1 = new Circulation();
      circul2 = new Circulation();
      circul1.setDateDebut(new Date("01JAN15"));
      circul1.setDateFin(new Date("30JAN15"));
      circul1.setOrigine("CDG");
      circul1.setDestination("MAR");
      circul1.setHeureArrivee(1500);
      circul1.setHeureDepart(1515);
      circul1.setJoursCirculation("123");

      circul2.setDateDebut(new Date("01JAN15"));
      circul2.setDateFin(new Date("30JAN15"));
      circul2.setOrigine("CDG");
      circul2.setDestination("MAR");
      circul2.setHeureArrivee(1500);
      circul2.setHeureDepart(1515);
      circul2.setJoursCirculation("1234");

      listCircu2.add(circul1);
      listCircu2.add(circul2);

      ITrainFactory factory = new TrainFactory();
      train1 = factory.createTrainByListCirculation(listCircu);
      train2 = factory.createTrainByListCirculation(listCircu2);

      // System.out.println(train1.getChaineCompare());
      // System.out.println(train2.getChaineCompare());
      Assert.assertFalse(train1.compare(train2));

   }

   // @Test
   public void testTrainCatalogue() {

      List<String> listCircul = new ArrayList<String>();

      listCircul.add("3 SG00156601P19SEP1519SEP15     6  FRGNB07000700+0100  FRECE07050705+0100  TERB                                                           87  FSN885112");
      listCircul.add("3 SG00156602P19SEP1519SEP15     6  FRECE07060706+0100  FRHVS07120712+0100  TERB                                                           87  FSN885113");
      listCircul.add("3 SG00156603P19SEP1519SEP15     6  FRHVS07130713+0100  FRLEY07200720+0100  TERB                                                           87  FSN885113");
      listCircul.add("3 SG00156604P19SEP1519SEP15     6  FRLEY07210721+0100  FRHVV07250725+0100  TERB                                                           87  FSN885113");
      listCircul.add("3 SG00156605P19SEP1519SEP15     6  FRHVV07260726+0100  FRHVX07340734+0100  TERB                                                           87  FSN885112");

      String s = "3 SG00156605P19SEP1519SEP15     6  FRHVV07260726+0100  FRHVX07340734+0100  TERB                                                           87  FSN885112";
      String ss = "3 SG00156605P19SEP1519SEP15     6  FRHVV07260726+0100  FRHVX07340734+0100  TERB                                                           87  FSN885113";
      List<String> num_train = new ArrayList<String>();
      List<Date> exceptions = new ArrayList<Date>();
      exceptions.add(new Date(121214));
      exceptions.add(new Date(120115));
      exceptions.add(new Date(120215));
      exceptions.add(new Date(120315));
      TrainCatalogue trainCatalogue = new TrainCatalogue();
      trainCatalogue.setNumero_Train_Cat("885112");
      trainCatalogue.setNumero_Train_Cat("885113");
      // trainCatalogue.setDestination("1334");
      // trainCatalogue.setException(exceptions);
      trainCatalogue.getFlight_number();
      trainCatalogue.setNom_compagnie("AF");
      // trainCatalogue.setJours_Circulation_Compagnie("12346");

      IParser par = new ParserFixedLength(new FilterEncodage(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(null))), APP_enumParserSSIM.getNames(), APP_enumParserSSIM.getEnds(), APP_enumParserSSIM.getBegins());

      Assert.assertEquals(Integer.valueOf(trainCatalogue.getNumero_Train_Cat().get(0)), Integer.valueOf(s.substring(APP_enumParserSSIM.POSITION_NUM_TRAIN.getPositionDebut(), APP_enumParserSSIM.POSITION_NUM_TRAIN.getPositionFin())));

      Assert.assertEquals(Integer.valueOf(trainCatalogue.getNumero_Train_Cat().get(0)), Integer.valueOf(s.substring(APP_enumParserSSIM.POSITION_NUM_TRAIN.getPositionDebut(), APP_enumParserSSIM.POSITION_NUM_TRAIN.getPositionFin())));

   }

   // @Test
   public void instancierTrainSSIm() {

      IParser par = new ParserFixedLength(new FilterEncodage(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(null))), APP_enumParserSSIM.getNames(), APP_enumParserSSIM.getEnds(), APP_enumParserSSIM.getBegins());

      ArrayList<String> listLegs = new ArrayList();

      listLegs.add("3 BR00000101W26APR1510MAY15      7 CHZWE07130713+0100  CHWIL07180718+0100  TICB                                                           70  FSN000224                                                      00000003");
      listLegs.add("4 BR00000101W              01921CHZWECHWIL1 GL  GL                                                                                                                                                           00000004");
      listLegs.add("3 BR00000102W26APR1510MAY15      7 CHWIL07190719+0100  CHZTG07240724+0100  TICB                                                           70  FSN000224                                                      00000005");
      listLegs.add("3 BR00000101W14MAY1517MAY15   4  7 CHZWE07130713+0100  CHWIL07180718+0100  TICB                                                           70  FSN000224                                                      00000006");

      String numTrain = "";
      String numTrainSSIM = " ";
      Train train = new Train();

      for (String legs : listLegs) {

         if (!par.parse(legs).equals("")) {
            numTrainSSIM = par.getParsedResult().get(APP_enumParserSSIM.POSITION_NUM_TRAIN.name());
            if (!numTrain.equals(numTrainSSIM)) {

               // Nouveau train
               numTrain = numTrainSSIM;
               train = new Train();
               Circulation circul = new Circulation();
               train.addCirculation(circul);
            } else {
               // / nouvelle circulation
               Circulation circul = new Circulation();
               train.addCirculation(circul);
            }
         }
      }

      Assert.assertTrue("Test", train.getCirculations().size() == 3);
   }

   // @Test
   public void comparePeriodes() {

      String chaine1 = "3 BR00000101W30APR1510MAY15      7 CHZWE07130713+0100  CHWIL07180718+0100  TICB                                                           70  FSN000224                                                      00000003";
      String chaine2 = "3 BR00000101W26APR1530APR15     6  CHZWE07130713+0100  CHWIL07180718+0100  TICB                                                           70  FSN000224                                                      00000003";

      Circulation circul = new Circulation();
      Circulation circul2 = new Circulation();

      IParser par = new ParserFixedLength(new FilterEncodage(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(null))), APP_enumParserSSIM.getNames(), APP_enumParserSSIM.getEnds(), APP_enumParserSSIM.getBegins());

      if (!par.parse(chaine1).equals("")) {
         circul.setPeriode(par.getParsedResult().get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT.name()) + par.getParsedResult().get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_FIN.name()));
      }

      if (!par.parse(chaine2).equals("")) {
         circul2.setPeriode(par.getParsedResult().get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT.name()) + par.getParsedResult().get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_FIN.name()));
      }
      Assert.assertTrue(circul.compareCirculation(circul2));
   }

   // @Test
   public void getCatalogCirculFromSSIM() throws ParseException {

      ITrain trainSSIM = new Train();
      TrainCatalogue trainCatalogue = new TrainCatalogue();
      ITrain train = new Train();

      Circulation circul = new Circulation();
      Circulation circul2 = new Circulation();
      Circulation circul3 = new Circulation();
      Circulation circul4 = new Circulation();

      train = trainSSIM.getTrainAPartirDuCatalogue(trainCatalogue);
      circul = createWithStringPeriode("01/01/2015#15/01/2015#1234567#FRLLE#FRMLW#0700#0730");
      circul2 = createWithStringPeriode("01/01/2015#15/01/2015#1234567#FRMLW#FRXYZ#0730#0800");
      circul4 = createWithStringPeriode("01/01/2015#15/01/2015#1234567#FRXYZ#FRCDG#0830#0910");
      circul3 = createWithStringPeriode("01/01/2015#15/01/2015#1234567#FRMLW#FRCDG#0730#0910");

      trainSSIM.addCirculation(circul);
      trainSSIM.addCirculation(circul2);
      trainSSIM.addCirculation(circul4);
      trainCatalogue.addCirculation(circul3);
      train = trainSSIM.getTrainAPartirDuCatalogue(trainCatalogue);
      Assert.assertTrue(train.compare(trainCatalogue));
   }

   public static Circulation createWithStringPeriode(String periode) throws ParseException {

      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      Circulation circulation = new Circulation();
      int heureArrivee = Integer.valueOf(periode.split("#")[6]);
      int heureDepart = Integer.valueOf(periode.split("#")[5]);
      String origine = periode.split("#")[3];
      String destination = periode.split("#")[4];
      Date dateCircul = sdf.parse(periode.split("#")[0]);
      JourCirculation joursCircul = new JourCirculation(dateCircul, heureDepart, heureArrivee, origine, destination, true);
      // circulation.setJourCirculation(joursCircul);

      circulation.setDateDebut(sdf.parse(periode.split("#")[0]));
      circulation.setDateFin(sdf.parse(periode.split("#")[1]));
      circulation.setJoursCirculation(periode.split("#")[2]);

      return circulation;
   }

   /**
    * Workflow Complet du catalogue jusqu'aux adaptations
    * 
    * @throws ParseException
    */
   @Test
   public void workflow() throws ParseException {

      // On part d'un train catalogue que l'on va initialiser avec ses attributs
      // minimums
      TrainCatalogue trainCatalogue = TrainCatalogueFactoryTest.create("01/01/2015#15/01/2015#1234567#FRAAA#FRMCCC#0700#0845");
      // Ce train catalogue doit pouvoir restituer une liste de circulation
      Assert.assertTrue(trainCatalogue.getCirculations().size() == 1);

      // On récupère le train issu du catalogue avec sa circulation complète
      // C'est ce train que nous allons adapter
      // On sauvegardera ce train à l'issue de sa création dans le catalogue.
      Train trainFromCatalogue = trainCatalogue.getTrain();
      trainFromCatalogue.remplirJoursCirculations();
      System.out.println(trainFromCatalogue);
      trainFromCatalogue.calculeCirculationFromJoursCirculation();
      System.out.println(trainFromCatalogue);

      // Train issu de la SSIM
      // C'est un train qui circule entre les gares FRAAA, FRBBB, FRCCC et FFDDD
      // On a donc des circulation (ou des legs) qui ne nous intéressent pas
      //

      Train trainSSIM = TrainFactoryTest.createTrainSSIM();

      // De ce train SSIM, nous devons chercher notre train Catalogue avec une
      // période et une OD qui correspond

      // Train trainCatalogueFromSSIM =
      // train.getTrainFromCatalogue(TrainFromCatalogue);

      // Train train = new Train();
      //
      // Circulation circul = new Circulation();
      // Circulation circul2 = new Circulation();
      // circul =
      // createWithStringPeriode("01/01/2015#15/01/2015#1234567#FRLLE#FRMLW#0700#0730");
      // circul2 =
      // createWithStringPeriode("01/01/2015#15/01/2015#123457#FRLLE#FRMLW#0730#0800");
      // train.addCirculation(circul);
      // trainSSIM.addCirculation(circul2);
      //
      // train.adapt(trainSSIM);
      // System.out.println(train.getJoursCirculation());
      // Assert.assertTrue(train.compare(trainSSIM));

   }

}
