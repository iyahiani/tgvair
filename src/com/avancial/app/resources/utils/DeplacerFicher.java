package com.avancial.app.resources.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DeplacerFicher {

   public static boolean copierFile(File source, File dest){
      try{
         // Declaration et ouverture des flux
         java.io.FileInputStream sourceFile = new java.io.FileInputStream(source);
    
         try{
            java.io.FileOutputStream destinationFile = null;
    
            try{
               destinationFile = new FileOutputStream(dest);
    
               // Lecture par segment de 0.5Mo 
               byte buffer[] = new byte[512 * 1024];
               int nbLecture;
    
               while ((nbLecture = sourceFile.read(buffer)) != -1){
                  destinationFile.write(buffer, 0, nbLecture);
               }
            } finally {
               destinationFile.close();
            }
         } finally {
            sourceFile.close();
         }
      } catch (IOException e){
         e.printStackTrace();
         return false; // Erreur
      }
    
      return true; // Résultat OK  
   } 
   
}
