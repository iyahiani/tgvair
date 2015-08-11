package com.avancial.app.resources.utils;

public class HeureFormattage {

   public static String heureToString(int heure) {
      String temp =""; 
      if(heure<1000) return "0".concat(String.valueOf(heure)); 
            else  return String.valueOf(heure) ;
   }
}
