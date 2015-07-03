package com.avancial.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
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

public class TestsComparaisonSsimEtCtalogue {

   public static void main(String[] args) throws IOException, ParseException {

      IReader reader = new ReaderSSIM("D:/Users/ismael.yahiani/Documents/SN5209.txt");
      String chaine;
      String[] num = { "005209" };
      Circulation circulation;

      SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");
      IParser par = new ParserFixedLength(new FilterEncodage(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(new FiltreCatalogue(null, num)))), APP_enumParserSSIM.getBegins(),
            APP_enumParserSSIM.getEnds(), APP_enumParserSSIM.getNames());
      ITrain trainSSIM = new Train();
      ITrain train = new Train();
      while ((chaine = reader.readLine()) != null) {

         par.parse(chaine);
         if (!par.getParsedResult().isEmpty()) {
            circulation = new Circulation();
            circulation.setHeureDepart(Integer.valueOf(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_DEPART.name())));
            circulation.setHeureArrivee(Integer.valueOf(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_ARRIVER.name())));
            circulation.setJoursCirculation(par.getParsedResult().get(APP_enumParserSSIM.POSITION_JOURS_CIRCULATION.name()));
            circulation.setDateDebut(ConvertDateStringToDate.toDate(par.getParsedResult().get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT.name())));
            circulation.setDateFin(ConvertDateStringToDate.toDate(par.getParsedResult().get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_FIN.name())));
            circulation.setOrigine(par.getParsedResult().get(APP_enumParserSSIM.POSITION_GARE_DEPART.name()));
            circulation.setDestination(par.getParsedResult().get(APP_enumParserSSIM.POSITION_GARE_ARRIVER.name()));
            trainSSIM.addCirculation(circulation);
         }
      }

      // ////////// NOTE : la methode creerMapJoursCircul dans Train ET la
      // methode getDateJourCirculMap dans Train Catalogue se ressemble

      TrainCatalogue trainCatalogue = new TrainCatalogue();
      Circulation circulation2, cc = new Circulation();
      circulation2 = TestTrain.createWithStringPeriode("01/01/2015#31/08/2015#12567#FRMLW#FRAET#1449#1627");
      cc = TestTrain.createWithStringPeriode("01/09/2015#31/12/2015#12567#FRMLW#FRAET#1449#1630");
      // System.out.println(trainSSIM.creerMapJoursCircul());
      trainCatalogue.addCirculation(circulation2);
      trainCatalogue.addCirculation(cc);
      Map<Date, Circulation> dateEtjoursCircuCatalog = new TreeMap<Date, Circulation>();
      Map<Date, Circulation> dateEtjoursCircuSSIM = new TreeMap<Date, Circulation>();

      // remplir la map relatife au vrais jours de service

      dateEtjoursCircuCatalog = trainCatalogue.getDateJourCirculMap();
      //dateEtjoursCircuSSIM = trainSSIM.getDateJourCirculMap();
      // System.out.println(dateEtjoursCircuSSIM );
      // System.out.println(dateEtjoursCircuCatalog);
      train = trainSSIM.getTrainAPartirDuCatalogue(trainCatalogue);
      dateEtjoursCircuSSIM = train.getDateJourCirculMap();
     
      for (Map.Entry<Date, Circulation> entryCatalog : dateEtjoursCircuCatalog.entrySet()) {
         for (Map.Entry<Date, Circulation> entrySSIM : dateEtjoursCircuSSIM.entrySet()) {

            if (entryCatalog.getKey().equals(entrySSIM.getKey()))
               if (!entryCatalog.getValue().compareCirculation(entrySSIM.getValue())) {
                  System.out.println("catalogue = " + entryCatalog.getKey() +"\t"
                        + "SSIM = " + entrySSIM.getKey() + "\t" + "CatalogueCircul"+"\t"+entryCatalog.getValue().getChaineCircu()
                        +"\t" + "SSIM Circul"+""+entrySSIM.getValue().getChaineCircu());
               }
         }
      }
   }

}
