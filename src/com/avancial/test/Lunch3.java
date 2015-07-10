package com.avancial.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.avancial.app.business.parser.APP_enumParserSSIM;
import com.avancial.app.business.parser.FilterEncodage;
import com.avancial.app.business.parser.FilterSSIMTypeEnr;
import com.avancial.app.business.parser.FiltreCatalogue;
import com.avancial.app.business.parser.FiltreSSIMCompagnieTrain;
import com.avancial.app.business.reader.ReaderSSIM;
import com.avancial.app.business.train.Circulation;
import com.avancial.app.business.train.ITrain;
import com.avancial.app.business.train.JourCirculation;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;

public class Lunch3 {

   public static ITrain getTrainSSIM(String path) throws IOException, ParseException {
      IReader reader = new ReaderSSIM(path);// "D:/Users/ismael.yahiani/Documents/ssim_6.txt"
      String chaine;
      String[] num = { "001111", "001112", "001113", "001115", "001117", "002222", "002223" };
      //
      // ///////// Instantiation Des Trains SSIM

      ITrain trainsSSIM = new Train();
      ITrain trainCat = new Train();

      SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");
      IParser par = new ParserFixedLength(new FilterEncodage(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(new FiltreCatalogue(null, num)))), APP_enumParserSSIM.getNames(), APP_enumParserSSIM.getBegins(), APP_enumParserSSIM.getEnds());

      while ((chaine = reader.readLine()) != null) {

         par.parse(chaine);

         if (!par.getParsedResult().isEmpty()) {

            Circulation circulation = new Circulation();
            circulation.setOrigine(par.getParsedResult().get(APP_enumParserSSIM.POSITION_GARE_DEPART.name()));
            circulation.setDestination(par.getParsedResult().get(APP_enumParserSSIM.POSITION_GARE_ARRIVER.name()));
            circulation.setHeureArrivee(Integer.valueOf(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_ARRIVER.name())));
            circulation.setHeureDepart(Integer.valueOf(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_DEPART.name())));
            circulation.setJoursCirculation(par.getParsedResult().get(APP_enumParserSSIM.POSITION_JOURS_CIRCULATION.name()));
            circulation.setDateDebut(StringToDate.toDate(par.getParsedResult().get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT.name())));
            circulation.setDateFin(StringToDate.toDate(par.getParsedResult().get("POSITION_PERIODE_CIRCULATION_FIN")));
            circulation.setNumeroTrain(par.getParsedResult().get("POSITION_NUM_TRAIN"));
            trainsSSIM.addCirculation(circulation);

         }
      }
      return trainsSSIM;

   }

   public static void main(String[] args) throws IOException, ParseException {

      ITrain trainSSIM = getTrainSSIM("D:/was_tmp/ssim_1.txt");

      // System.out.println(trainSSIM.getJoursCirculation());
      ITrain trainSSIM2 = getTrainSSIM("D:/was_tmp/ssim_2.txt");
      ITrain trainSSIM3 = getTrainSSIM("D:/was_tmp/ssim_3.txt");
      ITrain trainSSIM4 = getTrainSSIM("D:/was_tmp/ssim_4.txt");
      ITrain trainSSIM5 = getTrainSSIM("D:/was_tmp/ssim_5.txt");
      ITrain trainSSIM6 = getTrainSSIM("D:/was_tmp/ssim_6.txt");
      // ///////////// creation des num de trains catalogue

      // //////////////////// Creation des circulations

      Circulation c1 = new Circulation(), c2 = new Circulation(), c3 = new Circulation(), c4 = new Circulation();
      c1 = TestTrain.createWithStringPeriode("01/07/2015#31/08/2015#12345#FRAAA#FRBBB#0800#1000");
      c2 = TestTrain.createWithStringPeriode("01/01/2015#31/12/2015#67#FRAAA#FRBBB#1000#1154");
      c3 = TestTrain.createWithStringPeriode("01/07/2015#31/07/2015#123#FRAAA#FRCCC#1554#1932");
      c4 = TestTrain.createWithStringPeriode("01/09/2015#31/12/2015#123#FRCCC#FRBBB#0623#0854");

      // //////////// Creation Des Trains Catalogues et i,initialisation des
      // Valeurs

      TrainCatalogue trainCata1 = new TrainCatalogue();
      trainCata1.addCirculation(c1);
      trainCata1.getListeNumeros().add("001111");
      trainCata1.getListeNumeros().add("001112");

      TrainCatalogue trainCata2 = new TrainCatalogue();
      trainCata2.addCirculation(c2);
      trainCata2.getListeNumeros().add("001113");

      TrainCatalogue trainCata3 = new TrainCatalogue();
      trainCata3.addCirculation(c3);
      trainCata3.getListeNumeros().add("001115");
      trainCata3.getListeNumeros().add("002222");
      trainCata3.getListeNumeros().add("002223");

      TrainCatalogue trainCata4 = new TrainCatalogue();
      trainCata4.addCirculation(c4);
      trainCata4.getListeNumeros().add("001117");

      // List<StringBuilder> trains = new ArrayList<StringBuilder> () ;
      // trains.add(numTrain1);trains.add(numTrain2);trains.add(numTrain3);trains.add(numTrain4);

      List<TrainCatalogue> listTrainsCat = new ArrayList<TrainCatalogue>();
      listTrainsCat.add(trainCata1);
      listTrainsCat.add(trainCata2);
      listTrainsCat.add(trainCata3);
      listTrainsCat.add(trainCata4);

      // ////////////////////////////////////////////////// Construction Map des
      // Train Catalogue

      // ////////////////////////////////////////////////// Construction Map des
      // Train Catalogue

      Map<Date, JourCirculation> listTrainsAdapte = new TreeMap<>();

      for (TrainCatalogue t : listTrainsCat) {
         listTrainsAdapte.putAll(t.getJoursCirculation());
      }
      // //////////////AFFICHAGE Map Trains catalogue

      for (Map.Entry<Date, JourCirculation> entryCatalog : listTrainsAdapte.entrySet()) {

         // System.out.println( entryCatalog.getValue());
      }

      System.out.println("---------------------------------TRAIN REFERENTIEL------------------------------------------");
      for (TrainCatalogue trainCat : listTrainsCat) {

         // System.out.println(trainCat.getListeNumeros() + "\t" + trainCat.getCirculations().get(0).getChaineCircu());

      }

     // System.out.println("-----------------------Train SSIM----------------------------------");

      /*
       * for (TrainCatalogue trainCat : listTrainsCat) {
       * 
       * Train train = new Train(); train = trainSSIM.getTrainSSIMRestreint(trainCat); System.out.println(train); }
       */
//
     // System.out.println("-------------------------TRAIN ADAPTES----------------------------------------");

      /*for (TrainCatalogue trainCat : listTrainsCat) {

         //System.out.println("____________TRAIN DU CATALOGUE___________");
         Train train = trainCat.getTrain();
         train.remplirJoursCirculations();
        // System.out.println(train);

         train.remplirJoursCirculations();

        // System.out.println("____________SSIM RESTREINT___________");
         Train trainSSIMRestreint = trainSSIM.getTrainSSIMRestreint(trainCat);
        // System.out.println(trainSSIMRestreint);
         trainSSIMRestreint.remplirJoursCirculations();

         System.out.println("____________TRAIN APRES ADAPT___________");

         if (!train.compare(trainSSIMRestreint)) {
            train.adapt(trainSSIMRestreint);
            train.calculeCirculationFromJoursCirculation();
           // System.out.println(train);

         } 
         
      } */
      
      
      ////////////////////////  Test  2 -----------------------------------------------------------------------------------------------------------------------
      
      c1 = new Circulation(); c2 = new Circulation(); c3 = new Circulation(); c4 = new Circulation(); Circulation c1_1 = new Circulation() ; Circulation c1_2 = new Circulation() ;
      c1 = TestTrain.createWithStringPeriode("01/07/2015#31/07/2015#13#FRAAA#FRBBB#0800#1000");
      c1_1 = TestTrain.createWithStringPeriode("01/07/2015#31/07/2015#245#FRAAA#FRBBB#0810#1010"); 
      c1_2 = TestTrain.createWithStringPeriode("01/08/2015#31/08/2015#12345#FRAAA#FRBBB#0800#1000");
      c2 = TestTrain.createWithStringPeriode("01/01/2015#31/12/2015#67#FRAAA#FRBBB#1000#1154");
      c3 = TestTrain.createWithStringPeriode("01/07/2015#31/07/2015#123#FRAAA#FRCCC#1554#1932");
      c4 = TestTrain.createWithStringPeriode("01/09/2015#31/12/2015#1234567#FRCCC#FRBBB#0623#0854");

      // //////////// Creation Des Trains Catalogues et i,initialisation des
      // Valeurs

      trainCata1.getListeCirculations().clear(); trainCata1.getListeNumeros().clear();
      trainCata1.addCirculation(c1);
      trainCata1.addCirculation(c1_1); trainCata1.addCirculation(c1_2);
      trainCata1.getListeNumeros().add("001111");
      trainCata1.getListeNumeros().add("001112");

      trainCata2.getListeCirculations().clear();trainCata2.getListeNumeros().clear();
      trainCata2.addCirculation(c2);
      trainCata2.getListeNumeros().add("001113");

      trainCata3.getListeCirculations().clear();trainCata3.getListeNumeros().clear();
      trainCata3.addCirculation(c3);
      trainCata3.getListeNumeros().add("001115");
      trainCata3.getListeNumeros().add("002222");
      trainCata3.getListeNumeros().add("002223");

      trainCata4.getListeCirculations().clear(); trainCata4.getListeNumeros().clear();
      trainCata4.addCirculation(c4);
      trainCata4.getListeNumeros().add("001117");

      // List<StringBuilder> trains = new ArrayList<StringBuilder> () ;
      // trains.add(numTrain1);trains.add(numTrain2);trains.add(numTrain3);trains.add(numTrain4);

      listTrainsCat.clear();
      listTrainsCat.add(trainCata1);
      listTrainsCat.add(trainCata2);
      listTrainsCat.add(trainCata3);
      listTrainsCat.add(trainCata4);  
      
      System.out.println("-------------------------------------------------     TESTS2 -------------------------------------------- ");
      for (TrainCatalogue trainCat : listTrainsCat) {

         System.out.println("____________TRAIN DU CATALOGUE___________");
         Train train = trainCat.getTrain();
         train.remplirJoursCirculations();
         System.out.println(train);

         train.remplirJoursCirculations();

         System.out.println("____________SSIM RESTREINT___________");
         Train trainSSIMRestreint = trainSSIM6.getTrainSSIMRestreint(trainCat);
         System.out.println(trainSSIMRestreint);
         trainSSIMRestreint.remplirJoursCirculations();

         System.out.println("____________TRAIN APRES ADAPT___________");

         if (!train.compare(trainSSIMRestreint)) {
            train.adapt(trainSSIMRestreint);
            train.calculeCirculationFromJoursCirculation();
            System.out.println(train);
            
         } 
         
      } 
   }
}
