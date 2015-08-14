package com.avancial.app.resources.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/**
 * 
 * @author ismael.yahiani
 * cette class permet de transformer une date : d'un Type String vers un Type Date   
 */
public class StringToDate {

	
	public static Date toDate(String date) throws ParseException {
		
		String format = "ddMMMyy" ; 
 		SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.ENGLISH) ; 
		Date d = sdf.parse(date);
		return d;
	}
	
	public static String JavaDays2FrenchDays (Calendar date) {
	   
	   String chaine;
	   switch (date.get(Calendar.DAY_OF_WEEK)) {
      case 1:
            chaine="7";
         break;
      default:
         chaine=String.valueOf(date.get(Calendar.DAY_OF_WEEK)-1);
         break;
      } 
	   
	  return chaine; 
	}
	
	public static String toString(Date date) { 
	   //DateFormat df = new SimpleDateFormat("ddMMMyy") ; 
	   String format = "ddMMMyy" ; 
      SimpleDateFormat df = new SimpleDateFormat(format,Locale.ENGLISH) ;
	   String myDate = df.format(date);  
	      
	   return myDate ; 
	} 
	
	
	
}
