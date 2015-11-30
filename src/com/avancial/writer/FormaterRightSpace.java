package com.avancial.writer;


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
