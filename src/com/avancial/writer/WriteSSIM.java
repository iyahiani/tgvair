package com.avancial.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * 
 * @author ismael.yahiani
 *
 *  Ecriture dans un fichier
 *  imlemente l'interface IWriter 
 */
public class WriteSSIM implements IWriter{

   private static Logger log = Logger.getLogger(WriteSSIM.class); 
   String chemin ; 
   FileWriter fw ; 
   File fichier ;
   BufferedWriter bw ;
  
   public WriteSSIM(String chemin) { 
      
      this.fichier = new File(chemin);

      // if file doesnt exists, then create it
      if (!this.fichier.exists()) {
         try {
            this.fichier.createNewFile();
         } catch (IOException e) {

            log.error("erreur de creation de fichier destination"+e.getMessage());
         }
      }
      try {
         this.fw = new FileWriter(this.fichier.getAbsoluteFile());
      } catch (IOException e) {

         log.error("erreur d'instantiation du File Writer"+e.getMessage());
      }
      this.bw = new BufferedWriter(this.fw);
   }
   
   public void write(StringBuilder sb) { 
      try {
         this.fw.write(sb.toString());
      } catch (IOException e) {
      
         log.error("erreur d'ecriture de fichier "+e.getMessage());
      }
   }

   public void close(FileWriter fw) {
      try {
         fw.close();
      } catch (IOException e) {
        
         log.error(e.getMessage());
      }
   }

   public FileWriter getFileWriter() {
      
      return this.fw;
   }

   public void write(ArrayList<String> liste) throws IOException {
   }

   public void close() throws IOException {
   }

   public IFormaterStrategy getFormaterStrategy() {
      
      return null;
   }

   public void setFormaterStrategy(IFormaterStrategy formaterStrategy) {
   }

}
