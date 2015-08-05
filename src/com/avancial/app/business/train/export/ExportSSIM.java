package com.avancial.app.business.train.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.avancial.app.business.train.Train;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.test.Lunch3;
import com.avancial.test.Luncher;

public class ExportSSIM {
   
   private String path = "D:/Users/ismael.yahiani/Documents/5211.txt";
   private Date date_courant,date_deb_ssim,date_fin_ssim ;
   
   public ExportSSIM() throws IOException, ParseException {
      
      this.date_courant = Luncher.getSSIMPeriode(path).get("Date_courante") ;
      this.date_deb_ssim = Luncher.getSSIMPeriode(path).get("Date_Extraction")   ;
      this.date_fin_ssim = Luncher.getSSIMPeriode(path).get("Date_Fin") ;
   }

   protected static  int cpt = 0 ; 
   
   public static void main(String[] args) throws ParseException, IOException {
      ExportSSIM export = new ExportSSIM() ;
      System.out.println(StringToDate.toString(export.date_fin_ssim));
      try {

         File file = new File("D:/exportSSIM7/SSIM7_Test.txt");
         
         // if file doesnt exists, then create it
         if (!file.exists()) {
            file.createNewFile();
         }
 
         FileWriter fw = new FileWriter(file.getAbsoluteFile());
         BufferedWriter bw = new BufferedWriter(fw);
         bw.write(export.getEnrgType1()+"\n")   ;
         bw.write(export.getEnrgType2()+"\n")   ;
        
         bw.close();
         System.out.println("Done");
 
      } catch (IOException e) {       
   }
      
   }

   public  String getEnrgType1() {
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
   
   public  String getEnrgType2() {
      StringBuilder sb =new StringBuilder() ; 
      sb.append("2U2C")    ;
      sb.append("0008")    ; 
      for (int i = 0 ; i < 4 ; i++)  sb.append(" ") ; 
      sb.append("0000"+String.valueOf(cpt++)) ; 
      
      sb.append(StringToDate.toString(this.date_courant)+StringToDate.toString(this.date_fin_ssim)) ;
      sb.append(StringToDate.toString(this.date_deb_ssim)) ; 
      sb.append(" ") ; 
      sb.append("1010"); 
      
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

   public Date getDate_courant() {
      return date_courant;
   }

   public void setDate_courant(Date date_courant) {
      this.date_courant = date_courant;
   }

   public Date getDate_deb_ssim() {
      return date_deb_ssim;
   }

   public void setDate_deb_ssim(Date date_deb_ssim) {
      this.date_deb_ssim = date_deb_ssim;
   }

   public Date getDate_fin_ssim() {
      return date_fin_ssim;
   }

   public void setDate_fin_ssim(Date date_fin_ssim) {
      this.date_fin_ssim = date_fin_ssim;
   }
}
