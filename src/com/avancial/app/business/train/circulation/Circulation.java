package com.avancial.app.business.train.circulation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

import com.avancial.app.resources.utils.StringToDate;

public class Circulation implements ICirculation {

   private String origine;
   private String destination;
   private int heureDepart;
   private int heureArrivee;
   private Date dateDebut;
   private Date dateFin;
   private String joursCirculation;

   private String indicateurFer;
   private String compagnieTrain;
   private String numeroTrain;
   private String periode;
   private int rangTranson;
   private String trancheFacultatif;
   private String restrictionTrafic;

   /**
    * Permet de créer une circulation à partir d'un jour de circulation
    * 
    * @author Yahiani Ismail
    * @param jourCirculation
    */
   public Circulation(JourCirculation jourCirculation) {
      this.origine = jourCirculation.getOrigine();
      this.destination = jourCirculation.getDestination();
      this.heureDepart = jourCirculation.getHeureDepart();
      this.heureArrivee = jourCirculation.getHeureArrivee();
      this.dateDebut = jourCirculation.getDateCircul();
      this.dateFin = jourCirculation.getDateCircul();
      this.joursCirculation = "       ";

      Calendar cal = new GregorianCalendar();
      cal.setTime(jourCirculation.getDateCircul());
      int index = Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal));
      this.joursCirculation = this.joursCirculation.substring(0, index - 1) + index + this.joursCirculation.substring(index + 1, 7);
      // if (!jours.get()
      // - 1).equals(" "))
      // comp = comp & String.valueOf(jour.isFlagCirculation() ? 1 :
      // 0).equals(jours.get(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal))
      // - 1));

   }

   /**
    * @author ismael.yahiani cette methode retourne une map des jours de
    *         circulation et de leurs dates
    */

   public Map<Date, JourCirculation> getDateJourCirculMap(boolean avecJoursNonCirculants) {
      Calendar dateDebut = Calendar.getInstance();
      Calendar dateFin = Calendar.getInstance();

      Map<Date, JourCirculation> mapCirucl = new TreeMap<>();

      dateDebut.setTime(this.getDateDebut());
      dateFin.setTime(this.getDateFin());
      boolean bCircule;

      while (!dateDebut.getTime().after(dateFin.getTime())) {
         // verifie si il circule
         if (this.getJoursCirculation().contains(StringToDate.JavaDays2FrenchDays(dateDebut)))
            bCircule = true;
         ///////////////////////////////
         else
            bCircule = false;

         if (avecJoursNonCirculants || (!avecJoursNonCirculants && bCircule))

            mapCirucl.put(dateDebut.getTime(), new JourCirculation(dateDebut.getTime(), this.getHeureDepart(), this.getHeureArrivee(), this.getOrigine(), this.getDestination(), bCircule));

         dateDebut.add(Calendar.DATE, 1);
      }

      return mapCirucl;
   }

   @Override
   public boolean compareCirculation(Circulation circul) {

      if (!this.getOrigine().equalsIgnoreCase(circul.getOrigine()))
         return false;
      else if (!this.getDestination().equalsIgnoreCase(circul.getDestination()))
         return false;
      else if (this.getHeureArrivee() != circul.getHeureArrivee())
         return false;
      else if (this.getHeureDepart() != circul.getHeureDepart())
         return false;
      else if (!this.getJoursCirculation().equalsIgnoreCase(circul.getJoursCirculation()))
         return false;
      else if (!this.getDateDebut().equals(circul.getDateDebut()))
         return false;
      else if (!this.getDateFin().equals(circul.getDateFin()))
         return false;
      return true;
   }
   
   
   

   public String getNumeroTrain() {
      return this.numeroTrain;
   }

   public void setNumeroTrain(String numeroTrain) {
      this.numeroTrain = numeroTrain;
   }

   public String getPeriode() {
      return this.periode;
   }

   public void setPeriode(String periode) {
      this.periode = periode;
   }

   public String getIndicateurFer() {
      return this.indicateurFer;
   }

   public void setIndicateurFer(String indicateurFer) {
      this.indicateurFer = indicateurFer;
   }

   public String getCompagnieTrain() {
      return this.compagnieTrain;
   }

   public void setCompagnieTrain(String compagnieTrain) {
      this.compagnieTrain = compagnieTrain;
   }

   public Circulation() {

   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("----------------------" + "\n");
      sb.append(this.getDateDebut() + " -- " + this.getDateFin() + " -- " + this.joursCirculation + "\n");
      sb.append(this.getOrigine() + " -- " + this.getDestination() + "\n");
      sb.append(this.getHeureDepart() + " -- " + this.getHeureArrivee() + "\n");
      // rajouter pour test
      // sb.append(this.getNumeroTrain());

      return sb.toString();
   }

   public String getChaineCircu() {

      StringBuilder sb = new StringBuilder();

      sb.append(this.getOrigine());
      sb.append("\t");
      sb.append(this.getHeureDepart());
      sb.append("\t");
      sb.append(this.getDestination());
      sb.append("\t");
      sb.append(this.getHeureArrivee());
      sb.append("\t");
      sb.append(this.getNumeroTrain());
      sb.append("\t");
      sb.append(this.getDateDebut());
      sb.append("\t");
      sb.append(this.getDateFin());
      sb.append(this.getJoursCirculation());

      return sb.toString();
   }

   public Date getDateDebut() {
      return this.dateDebut;
   }

   public void setDateDebut(Date dateDebut) {
      this.dateDebut = dateDebut;
   }

   public Date getDateFin() {
      return this.dateFin;
   }

   public void setDateFin(Date dateFin) {
      this.dateFin = dateFin;
   }

   public String getJoursCirculation() {
      return this.joursCirculation;
   }

   public void setJoursCirculation(String joursCirculation) {
      this.joursCirculation = joursCirculation;
   }

   /**
    * @return the origine
    */
   public String getOrigine() {
      return this.origine;
   }

   /**
    * @param origine
    *           the origine to set
    */
   public void setOrigine(String origine) {
      this.origine = origine;
   }

   /**
    * @return the destination
    */
   public String getDestination() {
      return this.destination;
   }

   /**
    * @param destination
    *           the destination to set
    */
   public void setDestination(String destination) {
      this.destination = destination;
   }

   /**
    * @return the heureDepart
    */
   public int getHeureDepart() {
      return this.heureDepart;
   }

   /**
    * @param heureDepart
    *           the heureDepart to set
    */
   public void setHeureDepart(int heureDepart) {
      this.heureDepart = heureDepart;
   }

   /**
    * @return the heureArrivee
    */
   public int getHeureArrivee() {
      return this.heureArrivee;
   }

   /**
    * @param heureArrivee
    *           the heureArrivee to set
    */
   public void setHeureArrivee(int heureArrivee) {
      this.heureArrivee = heureArrivee;
   }

   public int getRangTranson() {
      return this.rangTranson;
   }

   public void setRangTranson(int rangTranson) {
      this.rangTranson = rangTranson;
   }

   public String getTrancheFacultatif() {
      return this.trancheFacultatif;
   }

   public void setTrancheFacultatif(String trancheFacultatif) {
      this.trancheFacultatif = trancheFacultatif;
   }

   public String getRestrictionTrafic() {
      return this.restrictionTrafic;
   }

   public void setRestrictionTrafic(String restrictionTrafic) {
      this.restrictionTrafic = restrictionTrafic;
   }
};
