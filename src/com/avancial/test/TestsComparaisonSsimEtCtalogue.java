package com.avancial.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.avancial.app.business.parser.APP_enumParserSSIM;
import com.avancial.app.business.parser.FilterEncodage;
import com.avancial.app.business.parser.FilterSSIMTypeEnr;
import com.avancial.app.business.parser.FiltreCatalogue;
import com.avancial.app.business.parser.FiltreSSIMCompagnieTrain;
import com.avancial.app.business.reader.ReaderSSIM;
import com.avancial.app.business.train.ITrain;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;

public class TestsComparaisonSsimEtCtalogue {

   public static void main(String[] args) throws IOException, ParseException {

      IReader reader = new ReaderSSIM("D:/Users/ismael.yahiani/Documents/SN5232.txt");
      String chaine;
      String[] num = { "005232", "005233" };

      Circulation circulation;

      SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");
      IParser par = new ParserFixedLength(new FilterEncodage(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(new FiltreCatalogue(null, num)))), APP_enumParserSSIM.getNames(), APP_enumParserSSIM.getEnds(), APP_enumParserSSIM.getBegins());
      ITrain trainSSIM = new Train();
      ITrain train = new Train();

      while ((chaine = reader.readLine()) != null) {

         par.parse(chaine);

         if (!par.getParsedResult().isEmpty()) {
            circulation = new Circulation();
            circulation.setHeureDepart(Integer.valueOf(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_DEPART.name())));
            circulation.setHeureArrivee(Integer.valueOf(par.getParsedResult().get(APP_enumParserSSIM.POSITION_HEURE_ARRIVER.name())));
            circulation.setJoursCirculation(par.getParsedResult().get(APP_enumParserSSIM.POSITION_JOURS_CIRCULATION.name()));
            circulation.setDateDebut(StringToDate.toDate(par.getParsedResult().get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT.name())));
            circulation.setDateFin(StringToDate.toDate(par.getParsedResult().get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_FIN.name())));
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
      trainCatalogue.addCirculation(circulation2);

      train = trainSSIM.getTrainAPartirDuCatalogue(trainCatalogue);

      System.out.println(trainCatalogue.compare(train));

   }

}
