package com.avancial.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class TestJobArchivage {

   public static void main(String[] args) {

      String path = "d:/testFiles" ; 
      File repertoir = new File(path); 
      File[] listFichier = repertoir.listFiles() ; 
      List<Date> list = new ArrayList<>() ;   
      List<File> listFichi= Arrays.asList(listFichier) ; 
      
      
      
      for (File file : listFichier) {
         if (file.isFile()) list.add(new Date(file.lastModified()));
      }
      
      Collections.sort(listFichi);
      
      for (int i = 0 ; i < listFichi.size()-4; i++) {
         listFichi.get(i).delete() ;
      }
        
   }

}
 


/*
 * 
 * TreeSet<Date> list = new TreeSet<>();
      File[] listFichier = repertoir.listFiles() ; 
      
      for (File file : listFichier) {
         if (file.isFile()) list.add(new Date(file.lastModified()));
      } 
       
      for (int i = 0 ; i < list.size()-4 ; i++) {
         list.pollFirst() ;
      }
      
      for (int i = 0 ; i < list.size(); i++) {
         System.out.println(list.);
      }
 */
