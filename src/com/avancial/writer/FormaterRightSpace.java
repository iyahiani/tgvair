package com.avancial.writer;

import java.util.ArrayList;

public class FormaterRightSpace implements IFormaterFixedLength {

   public String format(String chaine, int debut, int longueur) {
      
      StringBuilder sbTemp=new StringBuilder();
      String temp="";
         for (int i = 0; i < longueur-chaine.length(); i++) {
            temp=" "+temp;
         }
         
      return sbTemp.toString();
   }

   

}
