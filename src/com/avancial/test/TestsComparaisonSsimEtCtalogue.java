package com.avancial.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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

public class TestsComparaisonSsimEtCtalogue {

   public static void main(String[] args) throws IOException, ParseException {

      IReader reader = new ReaderSSIM("D:/Users/ismael.yahiani/Documents/SN5209.txt");
      String chaine;
      String[] num = { "005209" };
      Circulation circulation = new Circulation();

      SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");
      IParser par = new ParserFixedLength(new FilterEncodage(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(new FiltreCatalogue(null, num)))), APP_enumParserSSIM.getBegins(),
            APP_enumParserSSIM.getEnds(), APP_enumParserSSIM.getNames());
      ITrain trainSSIM = new Train();
      while ((chaine = reader.readLine()) != null) {
         par.parse(chaine);
         if (!par.getParsedResult().isEmpty()) {
            circulation.setHeureDepart(Integer.valueOf(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_DEPART.name())));
            circulation.setHeureArrivee(Integer.valueOf(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_ARRIVER.name())));
            circulation.setJoursCirculation(par.getParsedResult().get(APP_enumParserSSIM.POSITION_JOURS_CIRCULATION.name()));
            circulation.setDateDebut(ConvertDateStringToDate.toDate(par.getParsedResult().get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT.name())));
            trainSSIM.addCirculation(circulation);
         }
      }
      Map<Date,String> joursCirculation = trainSSIM.creerMapJoursCircul();
      System.out.println(joursCirculation);
      ITrainCatalogue trainCatalogue = new TrainCatalogue();
      circulation = new Circulation() ;  
      circulation = TestTrain.createWithStringPeriode("01/01/2015#01/08/2015#1234567#FRLLE#FRMLW#0700#0730"); 
      trainCatalogue.addCirculation(circulation); 
      
   }

}
/*
 * circulation.setDateDebut(ConvertDateStringToDate.toDate(par.getParsedResult().
 * get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT.name())));
 * circulation.setJoursCirculation(par.getParsedResult().get(APP_enumParserSSIM.
 * POSITION_JOURS_CIRCULATION.name()));
 * circulation.setHeureDepart(Integer.valueOf
 * (APP_enumParserSSIM.POSITION_HEURE_DEPART.name()));
 * circulation.setHeureArrivee
 * (Integer.valueOf(APP_enumParserSSIM.POSITION_HEURE_ARRIVER.name()));
 * train.addCirculation(circulation);
 */