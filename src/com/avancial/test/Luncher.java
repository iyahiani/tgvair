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
import java.util.TreeMap;

import org.quartz.SchedulerException;

import com.avancial.app.business.parser.APP_enumParserSSIM;
import com.avancial.app.business.parser.FilterEncodage;
import com.avancial.app.business.parser.FilterSSIMTypeEnr;
import com.avancial.app.business.parser.FiltreCatalogue;
import com.avancial.app.business.parser.FiltreSSIMCompagnieTrain;
import com.avancial.app.business.reader.ReaderSSIM;
import com.avancial.app.business.train.Circulation;
import com.avancial.app.business.train.ITrain;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;

public class Luncher {

   public static ITrain getTrainSSIM(String path) throws IOException, ParseException {
      IReader reader = new ReaderSSIM(path);// "D:/Users/ismael.yahiani/Documents/ssim_6.txt"
      String chaine;
      String[] num = {"005211","005214","005215" };//"005211", "005211", "001113", "001115", "002222", "002223","001117","001111","001112","001113"
      //
      // ///////// Instantiation Des Trains SSIM

      Train trainsSSIM = new Train()   ;
      Train trainCat = new Train()  ;

      SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy")   ;
      IParser par = new ParserFixedLength(new FilterEncodage(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(new FiltreCatalogue(null, num)))), APP_enumParserSSIM.getNames(),
            APP_enumParserSSIM.getBegins(), APP_enumParserSSIM.getEnds());

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
            trainsSSIM.addNumeroTrain(circulation.getNumeroTrain());
            trainsSSIM.addCirculation(circulation);
         }
      }
      
      return trainsSSIM;

   }
 public static Map<String, Date> getSSIMPeriode(String path) throws IOException, ParseException  {
      
      Map<String,Date> datesSSIMExtraction = new TreeMap<String, Date>() ; 
      String date_deb="", date_fin=""; 
      IReader reader = new ReaderSSIM(path); 
      String chaine ; 
      
      while((chaine=reader.readLine())!=null)
      if(chaine.startsWith("2")) {
         date_deb = chaine.substring(28,35) ;
         date_fin = chaine.substring(21,28) ;break ;
      }
 
      datesSSIMExtraction.put("Date_Extraction", StringToDate.toDate(date_deb) ) ; 
      datesSSIMExtraction.put("Date_Fin", StringToDate.toDate(date_fin)) ;
      return datesSSIMExtraction ;
 }
  

   public static void main(String[] args) throws IOException, ParseException {

      String path = "D:/Users/ismael.yahiani/Documents/new.txt" ;
      ITrain trainSSIM7 = getTrainSSIM(path);//"D:/was_tmp/ssim_1.txt"
       
      Date date_deb_ssim = getSSIMPeriode(path).get("Date_Extraction") ; 
      Date date_fin_ssim = getSSIMPeriode(path).get("Date_Fin") ;
      
      Circulation c1 = new Circulation(), c2 = new Circulation();
      c1 = TestTrain.createWithStringPeriode("01/01/2015#31/12/2015#1234567#FRMLW#FRACL#0949#1208");
      //c2 = TestTrain.createWithStringPeriode("01/07/2015#31/12/2015#1234567#FRMLW#FRACL#1000#1154");
     
      TrainCatalogue trainCata1 = new TrainCatalogue();
      trainCata1.addCirculation(c1);
      trainCata1.getListeNumeros().add("005211");
      trainCata1.getListeNumeros().add("005214");
      trainCata1.getListeNumeros().add("005215");
      
      TrainCatalogue trainCata2 = new TrainCatalogue();
     // trainCata2.addCirculation(c2);
      //trainCata2.getListeNumeros().add("001113");
      
      List<TrainCatalogue> listTrainsCat = new ArrayList<TrainCatalogue>();
      listTrainsCat.add(trainCata1);
     // listTrainsCat.add(trainCata2);
     
      System.out.println(date_deb_ssim+""+date_fin_ssim);
      System.out.println(trainSSIM7);
      
      System.out.println("-------------------------SSIM1----------------------------------------");
      
    for (TrainCatalogue trainCat : listTrainsCat) {
         
           System.out.println("____________TRAIN DU CATALOGUE___________") ;
           Train train = trainCat.getTrain() ;
           train.remplirJoursCirculations()  ;
         //  System.out.println(train) ;
           
          train.remplirJoursCirculations()  ;

        System.out.println("____________SSIM RESTREINT___________")  ; 
         
        Train trainSSIMRestreint = trainSSIM7.getTrainSSIMRestreint(train) ;
         //System.out.println(trainSSIM7);
        System.out.println(trainSSIMRestreint);
        trainSSIMRestreint.remplirJoursCirculations();

        // System.out.println("____________TRAIN APRES ADAPT___________");

         if (!train.compare(trainSSIMRestreint)) {
            train.remplirJoursCirculations();
            train.adapt(trainSSIMRestreint,date_deb_ssim,date_fin_ssim);
            //   System.out.println(train);
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
 * System.out.println(chaine.substring( APP_enumParserSSIM.POSITION_COMPAGNIE_TRAIN .getPositionDebut(), APP_enumParserSSIM.POSITION_COMPAGNIE_TRAIN .getPositionFin()));
 */

/*
 * Scheduler scheduler = new StdSchedulerFactory().getScheduler();
 * 
 * for (String groupName : scheduler.getJobGroupNames()) { for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher .jobGroupEquals(groupName))) {
 * 
 * @SuppressWarnings("unchecked") List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey); Date nextFireTime = triggers.get(0).getNextFireTime(); String jobName = jobKey.getName(); String jobGroup = jobKey.getGroup(); System.out.println("[jobName] : " + jobName +
 * " [groupName] : " + jobGroup + " - " + nextFireTime); } }
 */

