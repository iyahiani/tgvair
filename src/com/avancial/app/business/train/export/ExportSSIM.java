package com.avancial.app.business.train.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.avancial.app.business.train.Train;

public class ExportSSIM {

   protected static  int cpt = 0 ; 
   public static void main(String[] args) {

      try {

         File file = new File("D:/exportSSIM7/SSIM7_Test.txt");
 
         // if file doesnt exists, then create it
         if (!file.exists()) {
            file.createNewFile();
         }
 
         FileWriter fw = new FileWriter(file.getAbsoluteFile());
         BufferedWriter bw = new BufferedWriter(fw);
         bw.write(getEnrgType1()+"\n");
         bw.write(getEnrgType2()+"\n");
         bw.write(getEnrgType1()+"\n");
         bw.write(getEnrgType1()+"\n");
         bw.close();
         System.out.println("Done");
 
      } catch (IOException e) {
         
      }
      
   }

   public static String getEnrgType1() {
      int numeroSerie = 1 ; 
      
      StringBuilder sb =new StringBuilder() ;
      sb.append("1") ; 
      sb.append("AIRLINE STANDARD SCHEDULE DATA SET") ; 
      for (int i =  1 ; i < 157 ; i ++ ) {
         sb.append(" ") ;
      } 
      sb.append("00"+String.valueOf(numeroSerie)) ;
      sb.append("0000"+String.valueOf(cpt++)) ;
      
      return sb.toString() ;
   }
   
   public static String getEnrgType2() {
      StringBuilder sb =new StringBuilder() ; 
      sb.append("2U2C")    ;
      sb.append("0008")    ; 
      for (int i = 0 ; i < 4 ; i++)  sb.append(" ") ; 
      sb.append("0000"+String.valueOf(cpt++)) ;
       return sb.toString() ;
   }
   
   public String getEnrgType3() {
      return null ; 
   }
   public String getEnrgType4() {
      return null ; 
   }
   public String getEnrgType5() {
      return null ; 
   } 
   
   
   public void exportSSIM7() {
      
   } 
   
   public List<Train> getTrainsTest() {
      
      List<Train> trains  = new ArrayList<>() ; 
            
      return trains ;
   }
}
