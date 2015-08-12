package com.avancial.writer;

public class FormaterLeftZero implements IFormaterFixedLength {

   public String format(String chaine, int debut, int longueur) {
      
      StringBuilder sbTemp=new StringBuilder();
      String temp="";
         for (int i = 0; i < longueur-chaine.length(); i++) {
            temp+="0";
         }
         sbTemp.append(temp+chaine);
      return sbTemp.toString();
   }

}
