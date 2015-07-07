package com.avancial.tgvair.metier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TrainCatalogue extends Train implements ITrainCatalogue {

   private String Nom_compagnie;
   private List<String> numero_Train_Cat;
   private String flight_number;
   

   public TrainCatalogue() {
      super();
      numero_Train_Cat = new ArrayList<String>() ;
   }

   public String getNom_compagnie() {
      return Nom_compagnie;
   }

   public void setNom_compagnie(String nom_compagnie) {
      Nom_compagnie = nom_compagnie;
   }

   public List<String> getNumero_Train_Cat() {
      return numero_Train_Cat;
   }

   public void setNumero_Train_Cat(String numero_Train_Cat) {
      this.numero_Train_Cat.add(numero_Train_Cat) ;
   }

   public String getFlight_number() {
      return flight_number;
   }

   public void setFlight_number(String flight_number) {
      this.flight_number = flight_number;
   }

  
   @Override
   public String getGareOrigine() {

      return this.getCirculations().get(0).getOrigine();
   }

   @Override
   public String getGareDestination() {

      return getCirculations().get(getCirculations().size() - 1).getDestination();
   }

   @Override
   public String[] getAllTrainID() {

      return null;
   }

   @Override
   public void compareSSIM(ITrain train) { 
     
   }

   public TrainCatalogue buildCirculation() {
      
      return null;
   }


}
