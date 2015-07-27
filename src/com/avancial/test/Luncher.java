package com.avancial.test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.quartz.SchedulerException;

import com.avancial.app.business.parser.APP_enumParserSSIM;
import com.avancial.app.business.parser.FilterEncodage;
import com.avancial.app.business.parser.FilterSSIMTypeEnr;
import com.avancial.app.business.parser.FiltreCatalogue;
import com.avancial.app.business.parser.FiltreSSIMCompagnieTrain;
import com.avancial.app.business.parser.FiltreTrancheOptionnel;
import com.avancial.app.business.reader.ReaderSSIM;
import com.avancial.app.business.train.Circulation;
import com.avancial.app.business.train.Guichet;
import com.avancial.app.business.train.ITrain;
import com.avancial.app.business.train.JourCirculation;
import com.avancial.app.business.train.PointArret;
import com.avancial.app.business.train.PointsArret_OLD;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;

public class Luncher {

   public static Train getTrainSSIM(String path) throws IOException, ParseException {
      IReader reader = new ReaderSSIM(path);// "D:/Users/ismael.yahiani/Documents/ssim_6.txt"
      String chaine;
      String[] num = { "005211", "005214", "005215" };// "005211", "005211",
                                                      // "001113", "001115",
                                                      // "002222",
                                                      // "002223","001117","001111","001112","001113"
      //
      // ///////// Instantiation Des Trains SSIM

      Train trainsSSIM = new Train();
      Train trainCat = new Train();

      SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");
      IParser par = new ParserFixedLength(new FiltreTrancheOptionnel(new FilterEncodage(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(new FiltreCatalogue(null, num))))),
            APP_enumParserSSIM.getNames(), APP_enumParserSSIM.getBegins(), APP_enumParserSSIM.getEnds());

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
            circulation.setRangTranson(Integer.valueOf(chaine.substring(APP_enumParserSSIM.POSITION_RANG_TRANCON.getPositionDebut(), APP_enumParserSSIM.POSITION_RANG_TRANCON.getPositionFin())));
            circulation.setTrancheFacultatif(chaine.substring(APP_enumParserSSIM.POSITION_TRANCHE_FACULTATIF.getPositionDebut(), APP_enumParserSSIM.POSITION_TRANCHE_FACULTATIF.getPositionFin()));
            circulation.setRestrictionTrafic(chaine.substring(APP_enumParserSSIM.POSITION_RESTRICTION_TRAFIC.getPositionDebut(), APP_enumParserSSIM.POSITION_RESTRICTION_TRAFIC.getPositionFin()));
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
   
   //////////////////////////Extraction de la liste des points d'arrets et des horraire d'ouverture fermeture des guichets 

   public static List<PointArret> getListPointsArrets() {

      org.jdom.Document document;
      Element element;

      String path = "D:/Users/ismael.yahiani/Documents/points_arret.xml";
      SAXBuilder builder = new SAXBuilder();
      File xmlFile = new File(path);
      List pointArret = new ArrayList<>();

      try {
         document = (Document) builder.build(xmlFile);
         Element rootNode = document.getRootElement();
         List list = rootNode.getChildren("PointArret");
         for (int i = 0; i < list.size(); i++) {

            Element node = (Element) list.get(i);

            PointArret gare = new PointArret();
            gare.setCodeResarail(node.getChildText("CodeResarail"));
            gare.setCodeGDS(node.getChildText("CodeGDS"));
            gare.setLibelle(node.getChildText("Libelle"));

            

            List listHorraire = node.getChildren("Guichet");

            if (listHorraire.size() > 0) {
               for (int j = 0; j < listHorraire.size(); j++) {

                  Element node2 = (Element) listHorraire.get(j);
                  // System.out.println(node2.getChildren().size());
                  List mesheuresEtJour = node2.getChildren("MesHorairesOuverture");
                  if (mesheuresEtJour.size() > 0) {
                     for (int a = 0; a < mesheuresEtJour.size(); a++) {
                        Element e = (Element) mesheuresEtJour.get(a);
                        List jourEtHeure = e.getChildren("HorairesOuverture");
                        if (jourEtHeure.size() > 0)
                           for (int x = 0; x < jourEtHeure.size(); x++) {

                              Element z = (Element) jourEtHeure.get(x);
                              Guichet g = new Guichet();
                              g.setJour(z.getChildText("DayOfWeek"));
                              g.setHeureOuverture(z.getChildText("HeureOuverture"));
                              g.setHeureFermeture(z.getChildText("HeureFermeture"));
                              gare.addGuichet(g);
                           }
                     }
                  }
               }
            }
            pointArret.add(gare);
         }

      } catch (IOException io) {
         System.out.println(io.getMessage());
      } catch (JDOMException jdomex) {
         System.out.println(jdomex.getMessage());
      }

      return pointArret;
   }
   ////////////////////////////////////////////////////////////////////////////////////////     MAIN 

   public static void main(String[] args) throws IOException, ParseException {

      String path = "D:/Users/ismael.yahiani/Documents/5211.txt";
      Train trainSSIM7 = getTrainSSIM("D:/new7.txt");// "D:/was_tmp/ssim_1.txt"

      // trainSSIM7.remplirJoursCirculations();
      
      Date date_deb_ssim = getSSIMPeriode(path).get("Date_Extraction");
      Date date_fin_ssim = getSSIMPeriode(path).get("Date_Fin");

      Circulation c1 = new Circulation(), c2 = new Circulation();
      c1 = TestTrain.createWithStringPeriode("01/01/2015#31/12/2015#1234567#FRLLE#FRMLW#0949#1208");
      c2 = TestTrain.createWithStringPeriode("01/07/2015#31/12/2015#1234567#FRTHP#FRAET#1000#1154");

      TrainCatalogue trainCata1 = new TrainCatalogue();
      trainCata1.addCirculation(c1);
      trainCata1.getListeNumeros().add("005211");
      trainCata1.getListeNumeros().add("005214");
      trainCata1.getListeNumeros().add("005215");

      TrainCatalogue trainCata2 = new TrainCatalogue();
      trainCata2.addCirculation(c2);
      trainCata2.getListeNumeros().add("005211");
      trainCata2.getListeNumeros().add("005214");
      trainCata2.getListeNumeros().add("005215");

      List<TrainCatalogue> listTrainsCat = new ArrayList<TrainCatalogue>();
      listTrainsCat.add(trainCata1);
      // listTrainsCat.add(trainCata2);

      System.out.println(date_deb_ssim + "" + date_fin_ssim);
      // ///////// TESTS CIRCULATION
      ArrayList<Integer> position = new ArrayList<Integer>();

      System.out.println("-------------------------SSIM1----------------------------------------");

      for (TrainCatalogue trainCat : listTrainsCat) {

         System.out.println("____________TRAIN DU CATALOGUE___________");
         Train train = trainCat.getTrain();
         train.remplirJoursCirculations();
         // System.out.println(train) ;

         train.remplirJoursCirculations();

         System.out.println("____________SSIM RESTREINT___________");

         Train trainSSIMRestreint = trainSSIM7.getTrainSSIMRestreint(train);
         // System.out.println(trainSSIM7);

         trainSSIMRestreint.remplirJoursCirculations();
         // System.out.println(trainSSIMRestreint);
         // System.out.println(trainSSIMRestreint.getCirculations().get(0).getRestrictionTrafic());

         // System.out.println("____________TRAIN APRES ADAPT___________");

         if (!train.compare(trainSSIMRestreint)) {
            train.remplirJoursCirculations();
            train.adapt(trainSSIMRestreint, date_deb_ssim, date_fin_ssim);
            System.out.println(train);
            System.out.println(train.getPeriodes()); 
            train.adaptGuichet(getListPointsArrets());
            System.out.println(train);
            trainCat.setListeJoursCirculation(train.getListeJoursCirculation());
            trainCat.setListeCirculations(train.getListeCirculations());
         }
      }
   }
}

//
// System.out.println(chaine.substring(APP_enumParserSSIM.POSITION_COMPAGNIE_TRAIN.getPositionDebut(),
// APP_enumParserSSIM.POSITION_COMPAGNIE_TRAIN.getPositionFin()));
// test = par.getSSIMResult(chaine);
/*
 * System.out.println(chaine.substring(
 * APP_enumParserSSIM.POSITION_COMPAGNIE_TRAIN .getPositionDebut(),
 * APP_enumParserSSIM.POSITION_COMPAGNIE_TRAIN .getPositionFin()));
 */

/*
 * Scheduler scheduler = new StdSchedulerFactory().getScheduler();
 * 
 * for (String groupName : scheduler.getJobGroupNames()) { for (JobKey jobKey :
 * scheduler.getJobKeys(GroupMatcher .jobGroupEquals(groupName))) {
 * 
 * @SuppressWarnings("unchecked") List<Trigger> triggers = (List<Trigger>)
 * scheduler.getTriggersOfJob(jobKey); Date nextFireTime =
 * triggers.get(0).getNextFireTime(); String jobName = jobKey.getName(); String
 * jobGroup = jobKey.getGroup(); System.out.println("[jobName] : " + jobName +
 * " [groupName] : " + jobGroup + " - " + nextFireTime); } }
 */

