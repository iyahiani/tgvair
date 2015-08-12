package com.avancial.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

public class WriterTxt implements IWriter {

   private String fileName;
   private File fichier;
   private BufferedWriter bw;
   FileWriter fw;
   protected IFormaterStrategy formaterStrategy;

   public WriterTxt(String fileName) {
      this.setFileName(fileName);

      this.fichier = new File(fileName);

      // if file doesnt exists, then create it
      if (!this.fichier.exists()) {
         try {
            this.fichier.createNewFile();
         } catch (IOException e) {

            e.printStackTrace();
         }
      }
      try {
         this.fw = new FileWriter(this.fichier.getAbsoluteFile());
      } catch (IOException e) {

         e.printStackTrace();
      }
      this.bw = new BufferedWriter(this.fw);

   }

   public String getFileName() {
      return this.fileName;
   }

   public void setFileName(String fileName) {
      this.fileName = fileName;
   }

   protected void writeLine(String chaine) throws IOException {
      this.fw.write(chaine);
   }

   public void write(ArrayList<String> liste) throws IOException {
      if (null != this.formaterStrategy)
         this.writeLine(this.formaterStrategy.format(liste));
      else
         this.writeLine(this.getListeAsString(liste));
   }

   private String getListeAsString(ArrayList<String> liste) {
      StringBuilder sb = new StringBuilder();

      for (String chaine : liste) {
         sb.append(chaine);
      }
      return sb.toString();
   }

   public void close() throws IOException {
      this.bw.flush();
      this.bw.close();
      this.fw.close();

   }

   public IFormaterStrategy getFormaterStrategy() {
      return this.formaterStrategy;
   }

   public void setFormaterStrategy(IFormaterStrategy formaterStrategy) {
      this.formaterStrategy = formaterStrategy;
   }

}
