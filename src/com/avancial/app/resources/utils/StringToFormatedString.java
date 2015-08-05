package com.avancial.app.resources.utils;

public class StringToFormatedString {

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
   
}
