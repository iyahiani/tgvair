package com.avancial.test;

import com.avancial.app.resources.utils.Encrypte;
import com.avancial.app.resources.utils.HashGenerationException;

public class TestEncrypte {

   public static void main(String[] args) {

      String test="ismail" ;
      String rslt ;
      try {
         rslt = Encrypte.generateSHA1(test) ;
         System.out.println(rslt);
      } catch (HashGenerationException e) {
         
        System.out.println(e.getMessage());
      }
      
   }

}
