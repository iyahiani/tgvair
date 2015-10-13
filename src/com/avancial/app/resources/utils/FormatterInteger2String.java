package com.avancial.app.resources.utils;


/**
 * classe utilitaire de formatage d'entiers vers chaine caractéres 
 * @author ismael.yahiani
 *
 */
public class FormatterInteger2String {

   
   /**
    * methode static permettant de formatter un entier sur 4 chiffre ou moins vers une chaine de caractéres   
    * @param valeur
    * @return String 
    */
   public static String getFormatedIntToStr(int valeur) {
      
      String temp = ""; 
      if(valeur>=1000) return String.valueOf(valeur) ;
      if (valeur < 1000 && valeur >=100) return "0".concat(String.valueOf(valeur)) ;
      if (valeur < 100 && valeur >=10) return "00".concat(String.valueOf(valeur)) ;
      if (valeur < 100 && valeur >=0) return "000".concat(String.valueOf(valeur)) ; 
      return temp ;
   }
   
   
}
