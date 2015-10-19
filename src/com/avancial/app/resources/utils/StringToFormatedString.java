package com.avancial.app.resources.utils;

import java.util.ArrayList;
import java.util.List;

public class StringToFormatedString {

   /**
    * 
    * @param chaine
    * @return  un regime de circulation du  train selon le format : 1234567
    */
   public static String formatterString(String chaine) {      
      
      char[] temp = chaine.toCharArray() ; 
      char[] temp2 = {' ',' ',' ',' ',' ',' ',' '} ;
      String s = new String();
      for (int i = 0; i < temp.length; i++) {
         char v = chaine.charAt(i);
          
         if (v=='1')  temp2[0] ='1';
         if (v=='2')  temp2[1] ='2';
         if (v=='3')  temp2[2] ='3';
         if (v=='4')  temp2[3] ='4';
         if (v=='5')  temp2[4] ='5';
         if (v=='6')  temp2[5] ='6';
         if (v=='7')  temp2[6] ='7';
      }  
      return s.copyValueOf(temp2);
   } 
   
  /**
   * 
   * @param String joursCircul 
   * @return la list des jours de circulation du train selectionné 
   */
   public static List<String> getRegimeCirculFromSelectedTrain(String joursCircul){
     
      List<String> temp = new ArrayList<>() ; 
      for (int i = 0; i < joursCircul.length(); i++) { 
         temp.add(String.valueOf(joursCircul.charAt(i))) ;
      }
      return temp ; 
   }
   
   public static String formaterQuotas(String chaine) {
      
      int temp = Integer.valueOf(chaine) ;
      if(temp<10) return "00".concat(String.valueOf(temp)); 
      else if (temp < 100) return "0".concat(String.valueOf(temp)) ; 
      
      return String.valueOf(temp) ;
   }
    /**
     * 
     * @param s
     * @return String  
     * 
     * formatter le marketingFilght de AFXXXX vers AF XXXX 
     */
   public static String formaterMatketingFlight(String s) {
      
      StringBuilder sb = new StringBuilder() ;
      sb.append(s); 
      sb.insert(2, " ") ;
      
      return sb.toString() ;
   }
   
}
