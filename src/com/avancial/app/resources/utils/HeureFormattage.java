package com.avancial.app.resources.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HeureFormattage {

   public static String heureToString(int heure) {     
      if(heure<10) 
    	  return "0".concat(String.valueOf(heure)); 
      
      return String.valueOf(heure) ;
   }
   
   public static Date intToDate(final int heureDepart) throws ParseException {
		String heure = String.valueOf(heureDepart);
		String newHeure = "";
		
		if (heure.length() == 1) {
			newHeure += "00:0" + heure.substring(0, 1);
		}
		else if (heure.length() == 2) {
			newHeure += "00:" + heure.substring(0, 2);
		} else if (heure.length() == 3) {
			newHeure += heure.substring(0, 1) + ":" +heure.substring(1, 3);
		} else if (heure.length() == 4) {
			newHeure += heure.substring(0, 2) + ":" +heure.substring(2, 4);
		}			
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		return dateFormat.parse(newHeure);
	} 
   
   
   
}
