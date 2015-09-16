package com.avancial.app.business.train.circulation;

import java.util.Date;

import com.avancial.app.business.compagnieAerienne.ObservableJoursCirculation;
import com.avancial.app.resources.utils.StringToDate;

public class JourCirculation extends ObservableJoursCirculation implements Comparable<JourCirculation> {
   private int     heureArrivee;
   private int     heureDepart;
   private String  origine;
   private String  destination;
   private Date    dateCircul;
   private boolean flagCirculation;
   private String GMTDepart ; 
   private String GMTArriver ; 

   public JourCirculation(Date dateCircul, int heureDepart, int heureArrivee, String origine, String destination, boolean flagCircul, String GMTDepart, String GMTArriver) {
      this.dateCircul = dateCircul;
      this.heureDepart = heureDepart;
      this.heureArrivee = heureArrivee;
      this.origine = origine;
      this.destination = destination;
      this.flagCirculation = flagCircul; 
      this.GMTDepart = GMTDepart ;
      this.GMTArriver = GMTArriver ;

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
      else if (!this.getDestination().equalsIgnoreCase(jourCirculation.getDestination())) // ne fonctionnera pas dans le cas de lille flandre
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
      sb.append("\t") ;
      sb.append(StringToDate.toString(this.dateCircul));  
      sb.append("\t") ;
      sb.append( this.heureDepart);
      sb.append("\t") ;
      sb.append( this.GMTDepart);
      sb.append("\t") ;
      sb.append( this.heureArrivee);
      sb.append("\t") ;
      sb.append( this.GMTArriver);
      return sb.toString();

   }

   public boolean isFlagCirculation() {
      return this.flagCirculation;
   }

   public void setFlagCirculation(boolean flagCirculation) {
      this.flagCirculation = flagCirculation;
   }

   public String getGMTDepart() {
      return GMTDepart;
   }

   public void setGMTDepart(String gMTDepart) {
      GMTDepart = gMTDepart;
   }

   public String getGMTArriver() {
      return GMTArriver;
   }

   public void setGMTArriver(String gMTArriver) {
      GMTArriver = gMTArriver;
   }

}
