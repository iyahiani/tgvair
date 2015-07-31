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
import com.avancial.app.business.parser.FiltreTrancheOptionnel;
import com.avancial.app.business.reader.ReaderSSIM;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;

public class Lunch3 {

   public static Train getTrainSSIM(String path) throws IOException, ParseException {
      IReader reader = new ReaderSSIM(path);// "D:/Users/ismael.yahiani/Documents/ssim_6.txt"

      String chaine;
      String[] num = { "005211", "005214", "005215", "005225", "005226", "005227" }; // , "005225", "005226",,"001111","001112","001113","001115","002222","002223","001117",, "005214", "005215","005225", "005226","005227",
      // "005227","005211"
      //
      // ///////// Instantiation Des Trains SSIM

      Train trainsSSIM = new Train();
      Train trainCat = new Train();

      SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");

      IParser par = new ParserFixedLength(new FilterEncodage(new FiltreTrancheOptionnel(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(new FiltreCatalogue(null, num))))), APP_enumParserSSIM.getNames(), APP_enumParserSSIM.getBegins(), APP_enumParserSSIM.getEnds());
  
     
      int compt = 0 ;
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
            circulation.setTrancheFacultatif(chaine.substring(APP_enumParserSSIM.POSITION_TRANCHE_FACULTATIF.getPositionDebut(), APP_enumParserSSIM.POSITION_TRANCHE_FACULTATIF.getPositionFin()));
            circulation.setRestrictionTrafic(chaine.substring(APP_enumParserSSIM.POSITION_RESTRICTION_TRAFIC.getPositionDebut(), APP_enumParserSSIM.POSITION_RESTRICTION_TRAFIC.getPositionFin()));
            circulation.setRangTranson(Integer.valueOf(chaine.substring(APP_enumParserSSIM.POSITION_RANG_TRANCON.getPositionDebut(), APP_enumParserSSIM.POSITION_RANG_TRANCON.getPositionFin())));
            circulation.setNumeroTrain(par.getParsedResult().get("POSITION_NUM_TRAIN"));
            trainsSSIM.addNumeroTrain(circulation.getNumeroTrain());
            trainsSSIM.addCirculation(circulation); 
            
         }
      }
      return trainsSSIM;
   }

   public static Map<String, Date> getSSIMPeriode(String path) throws IOException, ParseException {

      Map<String, Date> datesSSIMExtraction = new TreeMap<String, Date>();
      String date_deb = "", date_fin = "";
      IReader reader = new ReaderSSIM(path);
      String chaine;

      while ((chaine = reader.readLine()) != null)
         if (chaine.startsWith("2")) {
            date_deb = chaine.substring(28, 35);
            date_fin = chaine.substring(21, 28);
            break;
         }
     

      datesSSIMExtraction.put("Date_Extraction", StringToDate.toDate(date_deb));
      datesSSIMExtraction.put("Date_Fin", StringToDate.toDate(date_fin));
      return datesSSIMExtraction;

   }

   public static void main(String[] args) throws IOException, ParseException {

      String pathProd = "D:/was_tmp/SIDHSSIM_150724.txt";
      String pathTest = "D:/Users/ismael.yahiani/Documents/new.txt";
      String pathTest2 = "D:/was_tmp/ssim_1.txt";

      Train trainSSIM7 = getTrainSSIM(pathProd);

      Date date_deb_ssim = getSSIMPeriode(pathProd).get("Date_Extraction"); // D:/was_tmp/RESARAIL_150713.txt
      Date date_fin_ssim = getSSIMPeriode(pathProd).get("Date_Fin");

      Circulation c1 = new Circulation(), c2 = new Circulation(), c3 = new Circulation();
      c1 = TestTrain.createWithStringPeriode("01/01/2015#31/12/2015#1234567#FRMLW#FRACL#0949#1208");
      c2 = TestTrain.createWithStringPeriode("01/01/2015#31/12/2015#1234567#FRMLW#FRACL#1249#1518");
      c3 = TestTrain.createWithStringPeriode("01/01/2015#31/12/2015#7#FRMLW#FRMPL#2130#0132");
      
      TrainCatalogue trainCata1 = new TrainCatalogue();
      trainCata1.addCirculation(c1);
      trainCata1.getListeNumeros().add("005211");
      trainCata1.getListeNumeros().add("005214");
      trainCata1.getListeNumeros().add("005215");

      TrainCatalogue trainCata2 = new TrainCatalogue();
      trainCata2.addCirculation(c2);
      trainCata2.getListeNumeros().add("005225");
      trainCata2.getListeNumeros().add("005226");
      trainCata2.getListeNumeros().add("005227");

      TrainCatalogue trainCata3 = new TrainCatalogue();
      trainCata3.addCirculation(c3);
      trainCata3.getListeNumeros().add("005137");
      List<TrainCatalogue> listTrainsCat = new ArrayList<TrainCatalogue>();
      // <<<<<<< HEAD

      // listTrainsCat.add(trainCata1);
      listTrainsCat.add(trainCata2);
      // listTrainsCat.add(trainCata3);
      // System.out.println(getSSIMPeriode(pathProd));
      // System.out.println(trainSSIM7);
      // System.out.println(trainSSIM7.getPeriodes());

 

      for (TrainCatalogue trainCat : listTrainsCat) {

         System.out.println("____________TRAIN DU CATALOGUE___________");
         Train train = trainCat.getTrain();
         train.remplirJoursCirculations();
         System.out.println("____________SSIM RESTREINT___________");
         Train trainSSIMRestreint = trainSSIM7.getTrainSSIMRestreint(trainCat);
         trainSSIMRestreint.remplirJoursCirculations();

         // System.out.println(trainSSIMRestreint);
        
         
        // System.out.println(trainSSIMRestreint.getPeriodes());
          
        
         System.out.println("____________TRAIN APRES ADAPT___________");


         if (!train.compare(trainSSIMRestreint)) {
            // 
            train.remplirJoursCirculations();
            train.adapt(trainSSIMRestreint, date_deb_ssim, date_fin_ssim);
            // System.out.println(train);
         //   System.out.println("PERIODES");

           //System.out.println(train.getPeriodes()); 
         //  train.adaptGuichet(Luncher.getListPointsArrets()); 
         //  System.out.println(train); 
         //  System.out.println(train.getPeriodes());

            // System.out.println(train.getPeriodes());
            train.adaptGuichet(Luncher.getListPointsArrets());
            System.out.println(train);
           System.out.println(train.getPeriodes());

            trainCat.setListeJoursCirculation(train.getListeJoursCirculation());
            trainCat.setListeCirculations(train.getListeCirculations());
           
           
            //
           // train.calculeCirculationFromJoursCirculation();
            // System.out.println(train);
            //
          
         }
      }
   }
}
