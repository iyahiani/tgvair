package com.avancial.test;

import org.junit.Test;

public class TestFormatedString {

   public char[] formatterString(String chaine) {

      String temp="" ; 
      char[] temp2 = {' ',' ',' ',' ',' ',' ',' '};
      
      for (int i = 0; i < chaine.length(); i++) {
         switch (chaine.charAt(i)) {
         case '1':
            temp2[0] ='1';
         case '2':
            temp2[1] ='2';
         case '3':
            temp2[2] ='3';
         case '4':
            temp2[3] ='4';
         case '5':
            temp2[4] ='5';
         case '6':
            temp2[5] ='6';
         case '7':
            temp2[6] ='7';

         default : break ;
            
         }

      }
      
       
      return temp2 ; 
            
   }

   @Test
   public void test() {

      System.out.println(formatterString("17"));

   }

}
