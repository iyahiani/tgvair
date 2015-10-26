package com.avancial.app.business.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.avancial.reader.IReader;

/**
 * 
 * @author ismael.yahiani class contenant les methodes pour la Lecture du fichier SSIM
 */
public class ReaderSSIM implements IReader {

   /**
    * 
    * @param fileName
    *           lecture du fichier SSIM ligne par ligne
    **/
   String         path;
   BufferedReader br;

   public ReaderSSIM(String fileName) throws IOException {
      this.path = fileName;
      this.br = new BufferedReader(new FileReader(path));
   }

   @Override
   public String readLine() throws IOException {

      String ligne, temp;

      ligne = this.br.readLine();
      if (ligne != null && ligne.length() < 12 && ligne.charAt(0) == '3') {
         temp = this.br.readLine();
         ligne = ligne.concat(temp);
         return ligne;
      }
      return ligne;
   } 
   
   public void closeReader(){
      try {
         this.br.close();
      } catch (IOException e) {
         
         e.printStackTrace();
      }
   }
}
