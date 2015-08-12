package com.avancial.writer;

public class FormaterLeftSpaces implements IFormaterFixedLength {

   public String format(String chaine, int debut, int longueur) {
      
      StringBuilder sbTemp=new StringBuilder();
      String temp=""; 
     
         for (int i = 0; i < longueur-chaine.length(); i++) {
            temp+=" ";
         }
      if (longueur >= chaine.length())
         sbTemp.append(chaine+temp);// else sbTemp.append(chaine+temp);if(debut>=longueur) 
      else sbTemp.append(temp+chaine);
        return sbTemp.toString();
   }
}
