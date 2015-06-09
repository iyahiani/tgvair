package com.avancial.tgvair.metier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * 
 * @author ismael.yahiani
 * cette class permet de transformer une date : d'un Type String vers un Type Date   
 */
public class ParsingDateCirculSSIM {

	
	public static Date toDate(String date) throws ParseException {
		
		String format = "ddMMMyy" ;
 		SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.ENGLISH) ; 
		Date d = sdf.parse(date);
		return d;
		
	}
}
