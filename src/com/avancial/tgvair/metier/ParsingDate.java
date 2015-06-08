package com.avancial.tgvair.metier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ParsingDate {

	
	public static Date toDate(String date) throws ParseException {
		
		String format = "dd MMM yy" ;
 		SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.ENGLISH) ; 
		Date d = sdf.parse("15 APR 15") ;
		return d ;
		
	}
}
