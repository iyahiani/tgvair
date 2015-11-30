package com.avancial.app.resources.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
	
	public static Date getDateFromString(String date, String format) throws ParseException {
	   
      SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.ENGLISH) ; 
      Date d = sdf.parse(date);
      return d;
	}
	/**
    * 
    * @param date
    * @return  date sous format jjMMMyy
    * @throws ParseException 
    */
   
	public static String toString(Date date) { 
	   //DateFormat df = new SimpleDateFormat("ddMMMyy") ; 
	   String format = "ddMMMyy" ; 
      SimpleDateFormat df = new SimpleDateFormat(format,Locale.ENGLISH) ;
	   String myDate = df.format(date);  
	      
	   return myDate.toUpperCase() ; 
	} 
	public static String toFormatedString(Date date) { 
      //DateFormat df = new SimpleDateFormat("ddMMMyy") ; 
      String format = "HHmm" ; 
      SimpleDateFormat df = new SimpleDateFormat(format,Locale.ENGLISH) ;
      String myDate = df.format(date);  
         
      return myDate; 
   } 
	/**
    * 
    * @param date
    * @return  date sous format HH:mm
    */
	public static String toFormatedString2(Date date) { 
      //DateFormat df = new SimpleDateFormat("ddMMMyy") ; 
      String format = "HH:mm" ; 
      SimpleDateFormat df = new SimpleDateFormat(format,Locale.ENGLISH) ;
      String myDate = df.format(date);  
         
      return myDate; 
   }  
	/**
	 * 
	 * @param date
	 * @return  date sous format 
	 */
	public static String toFormatedString3(Date date) { 
      //DateFormat df = new SimpleDateFormat("ddMMMyy") ; 
      String format = "HH:mm" ; 
      SimpleDateFormat df = new SimpleDateFormat(format,Locale.ENGLISH) ;
      String myDate = df.format(date);  
         
      return myDate; 
   }  
	/**
	 * 
	 * @param date
	 * @return date en chaine de caratctere dd/MM/yyyy
	 */
	public static String toFormatedStringddMMyyyy(Date date) { 
      //DateFormat df = new SimpleDateFormat("ddMMMyy") ; 
      String format = "dd/MM/yyyy" ; 
      SimpleDateFormat df = new SimpleDateFormat(format,Locale.ENGLISH) ;
      String myDate = df.format(date);  
         
      return myDate; 
   }  
	/**
	 * 
	 * @param date
	 * @param format
	 * @return date sous format : 
	 * dateBySlashSansHeure   dd/MM/yyyy
	 * dateSlashAvecHeure  dd/MM/yyyy HH:mm 
	 * dateTireSansHeure   dd-MM-yyyy HH:mm
	 * dateFrenchSansHeure dd MMM yyyy
	 * dateEnglishSansHeure   dd MMM yyyy
	 * dateEnglishAvecHeure   dd MMM yyyy HH:mm
	 * dateFrenchAffichage MMM yyyy
	 * dateSansSeparateurs ddMMyyyy
	 * @throws Exception
	 */
	
	public static String toStringByFormat(Date date, String format) throws Exception {
		SimpleDateFormat formatDate = null;
		// dd/mm/yyyy
		if (format.equals("dateBySlashSansHeure")) {			
			formatDate = new SimpleDateFormat("dd/MM/yyyy");
		} else if (format.equals("dateSlashAvecHeure")) {
			formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		} else if (format.equals("dateTireSansHeure")) {
			formatDate = new SimpleDateFormat("dd-MM-yyyy");
		} else if (format.equals("dateTireAvecHeure")) {
			formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		} else if (format.equals("dateFrenchSansHeure")) {
			formatDate = new SimpleDateFormat("dd MMM yyyy", Locale.FRENCH);
		}else if (format.equals("dateFrenchAvecHeure")) {
			formatDate = new SimpleDateFormat("dd MMM yyyy : HH:mm", Locale.FRENCH);
		} else if (format.equals("dateEnglishSansHeure")) {
			formatDate = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
		} else if (format.equals("dateEnglishAvecHeure")) {
			formatDate = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.ENGLISH);
		} else if (format.equals("dateFrenchAffichage")) {
			formatDate = new SimpleDateFormat("MMM yyyy", Locale.FRENCH);
		} else if  (format.equals("jour")) {
		   formatDate = new SimpleDateFormat("dd", Locale.FRENCH);
		} else if  (format.equals("dateSansSeparateurs")) {
         formatDate = new SimpleDateFormat("ddMMyyyy", Locale.FRENCH);
      }else {
			return null;
		}
		
		String myDate = formatDate.format(date);  
		
		return myDate;		 		
	}
	/**
	 * 
	 * @param date
	 * @param mois
	 * @return le mois +1
	 */
	public static Date moisSuivant(final Date date, final int mois) {
		try {
			GregorianCalendar gc = new GregorianCalendar();
	        gc.setTime(date);
	        gc.add(Calendar.MONTH, mois);	        
	        return gc.getTime();   
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}                
	} 
	/**
	 * 
	 * @param date
	 * @return dd MMM yyyy : HH:mm format 
	 */
	public static Date toFormatedDate(Date date) {
	   SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy : HH:mm", Locale.FRENCH) ;
	   Date temp= new Date();
      try {
         temp = sdf.parse(date.toString());
      } catch (ParseException e) {
         
         e.printStackTrace();
      }
	   return temp ;
	}
}
