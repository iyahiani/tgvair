package com.avancial.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.avancial.metier.parser.APP_enumParserSSIM;
import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.FilterSSIMTypeEnr;
import com.avancial.metier.parser.FiltreCatalogue;
import com.avancial.metier.parser.FiltreSSIMCompagnieTrain;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;
import com.avancial.reader.ReaderSSIM;
import com.avancial.tgvair.metier.Circulation;
import com.avancial.tgvair.metier.ITrain;
import com.avancial.tgvair.metier.ITrainCatalogue;
import com.avancial.tgvair.metier.Train;
import com.avancial.tgvair.metier.TrainCatalogue;
import com.avancial.tgvair.util.ConvertDateStringToDate;

public class Lunch3 {

   public static ITrain getTrainSSIM(String path) throws IOException, ParseException {
      IReader reader = new ReaderSSIM(path);// "D:/Users/ismael.yahiani/Documents/ssim_6.txt"
      String chaine;
      String[] num = { "001111", "001112", "001113", "001115", "001117", "002222", "002223" };
      //
      // ///////// Instantiation Des Trains SSIM

      ITrain trainSSIM = new Train();

      SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");
      IParser par = new ParserFixedLength(new FilterEncodage(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(new FiltreCatalogue(null, num)))), APP_enumParserSSIM.getBegins(),
            APP_enumParserSSIM.getEnds(), APP_enumParserSSIM.getNames());

      while ((chaine = reader.readLine()) != null) {

         par.parse(chaine);

         if (!par.getParsedResult().isEmpty()) {
            Circulation circulation = new Circulation();
            circulation.setHeureDepart(Integer.valueOf(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_DEPART.name())));
            circulation.setHeureArrivee(Integer.valueOf(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_ARRIVER.name())));
            circulation.setJoursCirculation(par.getParsedResult().get(APP_enumParserSSIM.POSITION_JOURS_CIRCULATION.name()));
            circulation.setDateDebut(ConvertDateStringToDate.toDate(par.getParsedResult().get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT.name())));
            circulation.setDateFin(ConvertDateStringToDate.toDate(par.getParsedResult().get("POSITION_PERIODE_CIRCULATION_FIN")));
            circulation.setOrigine(par.getParsedResult().get("POSITION_GARE_DEPART"));
            circulation.setDestination(par.getParsedResult().get("POSITION_GARE_ARRIVER"));
            circulation.setNumeroTrain(par.getParsedResult().get("POSITION_NUM_TRAIN"));
            trainSSIM.addCirculation(circulation);

         }
      }
      return trainSSIM;

   }

   public static void main(String[] args) throws IOException, ParseException {

      ITrain trainSSIM = getTrainSSIM("D:/Users/ismael.yahiani/Documents/ssim_1.txt");
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
      trainCata1.setNumero_Train_Cat("001111");
      trainCata1.setNumero_Train_Cat("001112");

      TrainCatalogue trainCata2 = new TrainCatalogue();
      trainCata2.addCirculation(c2);
      trainCata2.setNumero_Train_Cat("001113");

      TrainCatalogue trainCata3 = new TrainCatalogue();
      trainCata3.addCirculation(c3);
      trainCata3.setNumero_Train_Cat("001115");
      trainCata3.setNumero_Train_Cat("002222");
      trainCata3.setNumero_Train_Cat("002223");

      TrainCatalogue trainCata4 = new TrainCatalogue();
      trainCata4.addCirculation(c4);
      trainCata4.setNumero_Train_Cat("001117");

      // List<StringBuilder> trains = new ArrayList<StringBuilder> () ;
      // trains.add(numTrain1);trains.add(numTrain2);trains.add(numTrain3);trains.add(numTrain4);

      List<TrainCatalogue> listTrainsCat = new ArrayList<TrainCatalogue>();
      listTrainsCat.add(trainCata1);
      listTrainsCat.add(trainCata2);
      listTrainsCat.add(trainCata3);
      listTrainsCat.add(trainCata4);
      ////////////////////////////////////////////////////  Construction Map des Train Catalogue 
      
      Map<Date, Circulation> listTrainsAdapte = new TreeMap<Date, Circulation>();
     
      for (TrainCatalogue t : listTrainsCat) {
         listTrainsAdapte.putAll(t.getDateJourCirculMap()); 
      }
      ////////////////AFFICHAGE Map Trains catalogue
      
      for (Map.Entry<Date, Circulation> entryCatalog : listTrainsAdapte.entrySet()) {

         System.out.println(entryCatalog.getKey()+"\t" 
               + entryCatalog.getValue().getChaineCircu());
      }

      System.out.println("---------------------------------TRAIN REFERENTIEL------------------------------------------");
      for (TrainCatalogue trainCat : listTrainsCat) {

         System.out.println(trainCat.getNumero_Train_Cat() 
                  + "\t" + trainCat.getCirculation().get(0).getChaineCircu());

      }

      System.out.println("-----------------------Train SSIM----------------------------------");

      for (TrainCatalogue trainCat : listTrainsCat) {

         ITrain train = new Train();
         train = trainSSIM.getTrainAPartirDuCatalogue(trainCat);
         for (Circulation c : train.getCirculations()) {
            System.out.println(c.getChaineCircu());
         }

      }

      System.out.println("-------------------------TRAIN ADAPTES----------------------------------------");

      for (TrainCatalogue trainCat : listTrainsCat) {

         ITrain train = new Train();
         train = trainSSIM.getTrainAPartirDuCatalogue(trainCat);
      }

   }
}
