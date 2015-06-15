package com.avancial.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import com.avancial.metier.parser.APP_enumParserSSIM;
import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.FilterSSIMTypeEnr;
import com.avancial.metier.parser.FiltreSSIMCompagnieTrain;
import com.avancial.metier.parser.ParserSSIM;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;
import com.avancial.reader.ReaderSSIM;

public class Luncher {

	public static void main(String[] args) throws IOException, ParseException,
			SchedulerException {

		IReader reader = new ReaderSSIM(
				"D:/Users/ismael.yahiani/Documents/in.txt.part.0.txt");// D:/Users/ismael.yahiani/Documents/in.txt.part.0.txt/in.txt.part.0.txt
		String file, chaine;
		Date date = Calendar.getInstance().getTime();
		
		File output = new File
		 ("D:/Users/ismael.yahiani/Documents/parsOut.txt") ;
		
		Map<String, String> test = new HashMap<String, String>();
		List<String> sb = new ArrayList<String>();
		int i = 0 ; 
		IParser par =new FilterEncodage(
				new FilterSSIMTypeEnr(null));
		
		 FileWriter fw = new FileWriter(output.getAbsoluteFile());
		 BufferedWriter bw = new BufferedWriter(fw);
		 
		while ((reader!=null)&&(file = reader.readLine()) != null) {	
			chaine = par.parse(file);
			if (chaine.length() > 0 ) {
				bw.write(chaine); 
				bw.write("\n");
			}
		}
		bw.close();
		date = Calendar.getInstance().getTime();
		System.out.println(date);

	}
}

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

