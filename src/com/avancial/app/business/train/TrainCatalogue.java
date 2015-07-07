package com.avancial.app.business.train;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TrainCatalogue extends Train implements ITrainCatalogue {

   private String Nom_compagnie;
   private String flight_number;

   public TrainCatalogue() {
      super();

   }

   public String getNom_compagnie() {
      return Nom_compagnie;
   }

   public void setNom_compagnie(String nom_compagnie) {
      Nom_compagnie = nom_compagnie;
   }

   public String getFlight_number() {
      return flight_number;
   }

   public void setFlight_number(String flight_number) {
      this.flight_number = flight_number;
   }

   @Override
   public String getGareOrigine() {

      return this.getCirculations().get(0).getJourCirculation().getOrigine();
   }

   @Override
   public String getGareDestination() {

      return getCirculations().get(getCirculations().size() - 1).getJourCirculation().getDestination();
   }

   @Override
   public String[] getAllTrainID() {

      return null;
   }

   @Override
   public void compareSSIM(ITrain train) {

   }

   public ITrain getTrainCatalogueComplet() {

      Train train = new TrainCatalogue();

      return train;
   }

   @Override
   public Train getTrainAPartirDuCatalogue(TrainCatalogue trainCatalogue) {

      return null;
   }

   @Override
   public List<String> getTrainNumeros() {
      
      return this.;
   }
}
