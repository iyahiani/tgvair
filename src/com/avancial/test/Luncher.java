package com.avancial.test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

import com.avancial.app.business.parser.APP_enumParserSSIM;
import com.avancial.app.business.parser.FilterEncodage;
import com.avancial.app.business.parser.FilterSSIMTypeEnr;
import com.avancial.app.business.parser.FiltreSSIMCompagnieTrain;
import com.avancial.app.business.reader.ReaderSSIM;
import com.avancial.app.business.train.ITrain;
import com.avancial.app.business.train.Train;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;

public class Luncher {

   public static void main(String[] args) throws IOException, ParseException, SchedulerException {

      IReader reader = new ReaderSSIM("D:/Users/ismael.yahiani/Documents/in.txt");// .txt.part.0
      // D:/Users/ismael.yahiani/Documents/in.txt.part.0.txt/in.txt.part.0.txt
      String file, chaine;
      Date date;

      File output = new File("D:/Users/ismael.yahiani/Documents/in.txt.part.0.txt");

      Map<String, String> test = new HashMap<String, String>();
      List<String> sb = new ArrayList<String>();
      int i = 0;
      IParser par = new ParserFixedLength(new FilterEncodage(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(null))), APP_enumParserSSIM.getNames(), APP_enumParserSSIM.getEnds(), APP_enumParserSSIM.getBegins());
      ITrain train = new Train();

      date = Calendar.getInstance().getTime();
      System.out.println(date);

      while ((file = reader.readLine()) != null) {
         chaine = par.parse(file);
         if (chaine.length() > 0) {
            System.out.println(chaine.substring(APP_enumParserSSIM.POSITION_RESTRICTION_TRAFIC.getPositionDebut(), APP_enumParserSSIM.POSITION_RESTRICTION_TRAFIC.getPositionFin()));
         }
      }
      //

      date = Calendar.getInstance().getTime();
      System.out.println(date);

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

