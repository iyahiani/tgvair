package com.avancial.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

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
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;
import com.avancial.socle.utils.StringToDate;

public class bruno {

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
      c1 = TestTrain.createWithStringPeriode("01/07/2015#31/08/2015#1234567#FRAAA#FRBBB#0800#1000");
      c2 = TestTrain.createWithStringPeriode("25/07/2015#30/07/2015#1234567#FRAAA#FRBBB#0800#1000");
      c3 = TestTrain.createWithStringPeriode("01/07/2015#31/12/2015#1234567#FRAAA#FRBBB#0800#1000");
      // c4 = TestTrain.createWithStringPeriode("01/09/2015#31/12/2015#123#FRCCC#FRBBB#0623#0854");

      // //////////// Creation Des Trains Catalogues et i,initialisation des
      // Valeurs

      // Train Catalogue de r�f�rence
      TrainCatalogue trainCata1 = new TrainCatalogue();
      trainCata1.addCirculation(c1);
      trainCata1.getListeNumeros().add("001111");
      trainCata1.getListeNumeros().add("001112");

      // Train AF avec des date de validit�
      TrainCatalogue trAF = new TrainCatalogue();
      trAF.addCirculation(c2);
      trAF.getListeNumeros().add("001111");
      trAF.getListeNumeros().add("001112");

      Calendar cal = new GregorianCalendar();
      cal.clear();
      cal.set(2015, Calendar.JULY, 25);
      trAF.setDateDebutValidite(cal.getTime());
      cal.clear();
      cal.set(2015, Calendar.JULY, 30);
      trAF.setDateFinValidite(cal.getTime());

      // Train catalogue r�duit � la p�riode AF
      Train trAf1 = trainCata1.getTrainFromPortefeuille(trAF.getDateDebutValidite(), trAF.getDateFinValidite());

      // Maintenant on cr�e le train SSIM

      Train ssim = new TrainCatalogue();
      ssim.addCirculation(c3);
      ssim.getListeNumeros().add("001111");
      ssim.getListeNumeros().add("001112");

      cal.set(2015, Calendar.JULY, 26);

      ssim.getListeJoursCirculation().remove(cal.getTime());

      System.out.println(ssim.toString());

      Train ssimReduit = ssim.getTrainFromPortefeuille(trAF.getDateDebutValidite(), trAF.getDateFinValidite());

      System.out.println(ssimReduit.toString());

      System.out.println(trAf1.compare(ssimReduit));

      // trainCata1.adapt(train, date_deb_SSIM, date_fin_SSIM);

      // TrainCatalogue trainCata2 = new TrainCatalogue();
      // trainCata2.addCirculation(c2);
      // trainCata2.getListeNumeros().add("001113");
      //
      // TrainCatalogue trainCata3 = new TrainCatalogue();
      // trainCata3.addCirculation(c3);
      // trainCata3.getListeNumeros().add("001115");
      // trainCata3.getListeNumeros().add("002222");
      // trainCata3.getListeNumeros().add("002223");
      //
      // TrainCatalogue trainCata4 = new TrainCatalogue();
      // trainCata4.addCirculation(c4);
      // trainCata4.getListeNumeros().add("001117");

      // List<StringBuilder> trains = new ArrayList<StringBuilder> () ;
      // trains.add(numTrain1);trains.add(numTrain2);trains.add(numTrain3);trains.add(numTrain4);
      //
      // List<TrainCatalogue> listTrainsCat = new ArrayList<TrainCatalogue>();
      // listTrainsCat.add(trainCata1);
      // // listTrainsCat.add(trainCata2);
      // // listTrainsCat.add(trainCata3);
      // // listTrainsCat.add(trainCata4);
      //
      // // ////////////////////////////////////////////////// Construction Map des
      // // Train Catalogue
      //
      // // ////////////////////////////////////////////////// Construction Map des
      // // Train Catalogue
      //
      // Map<Date, JourCirculation> listTrainsAdapte = new TreeMap<>();
      //
      // for (TrainCatalogue t : listTrainsCat) {
      // listTrainsAdapte.putAll(t.getJoursCirculation());
      // }
      // // //////////////AFFICHAGE Map Trains catalogue
      //
      // for (Map.Entry<Date, JourCirculation> entryCatalog : listTrainsAdapte.entrySet()) {
      //
      // // System.out.println( entryCatalog.getValue());
      // }
      //
      // System.out.println("---------------------------------TRAIN REFERENTIEL------------------------------------------");
      // for (TrainCatalogue trainCat : listTrainsCat) {
      //
      //
      //
      // }
      //
      // System.out.println("-----------------------Train SSIM----------------------------------");
      //
      // for (TrainCatalogue trainCat : listTrainsCat) {
      //
      // Train train = new Train();
      // train = trainSSIM.getTrainSSIMRestreint(trainCat);
      // System.out.println(train);
      // }
      //
      // System.out.println("-------------------------TRAIN ADAPTES----------------------------------------");
      //
      // for (TrainCatalogue trainCat : listTrainsCat) {
      //
      // System.out.println("____________TRAIN DU CATALOGUE___________");
      // Train train = trainCat.getTrain();
      // train.remplirJoursCirculations();
      // System.out.println(train);
      //
      // train.remplirJoursCirculations();
      //
      // System.out.println("____________SSIM RESTREINT___________");
      // Train trainSSIMRestreint = trainSSIM.getTrainSSIMRestreint(trainCat);
      // System.out.println(trainSSIMRestreint);
      // trainSSIMRestreint.remplirJoursCirculations();
      //
      // System.out.println("____________TRAIN APRES ADAPT___________");
      //
      // if (!train.compare(trainSSIMRestreint)) {
      //
      // Calendar cal = new GregorianCalendar(2015, 01, 01);
      // Calendar cal2 = new GregorianCalendar(2015, 12, 31);
      //
      // // train.adapt(trainSSIMRestreint, cal.getTime(), cal2.getTime());
      // train.calculeCirculationFromJoursCirculation();
      // System.out.println(train);
      //
      // }
      // }
      //
   }
}
