package com.avancial.app.business.train.circulation;

import java.util.Date;

public class JourCirculation implements Comparable<JourCirculation> {
   private int     heureArrivee;
   private int     heureDepart;
   private String  origine;
   private String  destination;
   private Date    dateCircul;
   private boolean flagCirculation;

   public JourCirculation(Date dateCircul, int heureDepart, int heureArrivee, String origine, String destination, boolean flagCircul) {
      this.dateCircul = dateCircul;
      this.heureDepart = heureDepart;
      this.heureArrivee = heureArrivee;
      this.origine = origine;
      this.destination = destination;
      this.flagCirculation = flagCircul;

   }

   public JourCirculation() {
   }

   private String getChaine(JourCirculation jour) {
      String hDepart;
      String hArrivee;
      String date;

      hDepart = String.format("%04d", jour.heureDepart);
      hArrivee = String.format("%04d", jour.heureArrivee);
      date = String.format(" %1$tY%1$tm%1$td", jour.dateCircul);

      StringBuilder sb = new StringBuilder();

      sb.append(date);
      sb.append(hDepart);
      sb.append(hArrivee);

      return sb.toString();
   }

   @Override
   public int compareTo(JourCirculation j) {
      String chaine1 = this.getChaine(this);
      String chaine2 = this.getChaine(j);

      System.out.println(String.format("%s -- %s -- %s", chaine1, chaine2, chaine1.compareToIgnoreCase(chaine2)));

      return chaine1.compareToIgnoreCase(chaine2);

   }

   public int getHeureArrivee() {
      return this.heureArrivee;
   }

   public void setHeureArrivee(int heureArrivee) {
      this.heureArrivee = heureArrivee;
   }

   public int getHeureDepart() {
      return this.heureDepart;
   }

   public void setHeureDepart(int heureDepart) {
      this.heureDepart = heureDepart;
   }

   public String getOrigine() {
      return this.origine;
   }

   public void setOrigine(String origine) {
      this.origine = origine;
   }

   public String getDestination() {
      return this.destination;
   }

   public void setDestination(String destination) {
      this.destination = destination;
   }

   public Date getDateCircul() {
      return this.dateCircul;
   }

   public void setDateCircul(Date dateCircul) {
      this.dateCircul = dateCircul;
   }

   public boolean compare(JourCirculation jourCirculation) {

      if (!this.getOrigine().equalsIgnoreCase(jourCirculation.getOrigine()))
         return false;
      else if (!this.getDestination().equalsIgnoreCase(jourCirculation.getDestination()))
         return false;
      else if (this.getHeureArrivee() != jourCirculation.getHeureArrivee())
         return false;
      else if (this.getHeureDepart() != jourCirculation.getHeureDepart())
         return false;

      return true;
   }

   @Override
   public String toString() {

      StringBuilder sb = new StringBuilder();
      sb.append(this.flagCirculation ? "-C-" : "-N-");
      sb.append("Date de circulation : " + this.dateCircul.toString());
      sb.append("Heure de d�part : " + this.heureDepart);
      sb.append("Gare Depart : " + this.origine);
      sb.append("Heure d'arriv�e : " + this.heureArrivee);
      sb.append("Gare Arriver : " + this.destination);

      return sb.toString();

   }

   public boolean isFlagCirculation() {
      return this.flagCirculation;
   }

   public void setFlagCirculation(boolean flagCirculation) {
      this.flagCirculation = flagCirculation;
   }

}