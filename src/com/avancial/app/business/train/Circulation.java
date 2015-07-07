package com.avancial.app.business.train;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.avancial.app.data.model.databean.CirculationDataBean;
import com.avancial.app.resources.utils.StringToDate;

public class Circulation implements ICirculation {

   private Date dateDebut;
   private Date dateFin;
   private JourCirculation jourCirculation;
   private String joursCirculation;
   private String indicateurFer;
   private String compagnieTrain;
   private String numeroTrain;
   private String periode;

   /**
    * @author ismael.yahiani cette methode retourne une map des jours de
    *         circulation et de leurs dates
    */
   public void Circulation() {

   }

   public Map<Date, JourCirculation> getDateJourCirculMap() {
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
         // /////////////////////////////
         else
            bCircule = false;

         mapCirucl.put(dateDebut.getTime(), new JourCirculation(dateDebut.getTime(), this.jourCirculation.getHeureDepart(), this.jourCirculation.getHeureArrivee(), this.jourCirculation.getOrigine(),
               this.jourCirculation.getDestination(), bCircule));

         dateDebut.add(Calendar.DATE, 1);

      }

      return mapCirucl;
   }

   @Override
   public boolean compareCirculation(Circulation circul) {

      if (!this.jourCirculation.getOrigine().equalsIgnoreCase(circul.jourCirculation.getOrigine()))
         return false;
      else if (!this.jourCirculation.getDestination().equalsIgnoreCase(circul.jourCirculation.getDestination()))
         return false;
      else if (this.jourCirculation.getHeureArrivee() != circul.jourCirculation.getHeureArrivee())
         return false;
      else if (this.jourCirculation.getHeureDepart() != circul.jourCirculation.getHeureDepart())
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

   private CirculationDataBean circulationDataBean;

   public Circulation() {

   }

   public String getChaineCircu() {

      StringBuilder sb = new StringBuilder();

      sb.append(this.getJourCirculation().getOrigine());
      sb.append("\t");
      sb.append(this.getJourCirculation().getHeureDepart());
      sb.append("\t");
      sb.append(this.getJourCirculation().getDestination());
      sb.append("\t");
      sb.append(this.getJourCirculation().getHeureArrivee());
      sb.append("\t");
      sb.append(this.getNumeroTrain()); sb.append("\t") ;
      sb.append(this.getDateDebut());
      sb.append("\t");
      sb.append(this.getDateFin());
      // sb.append(this.getJoursCirculation());
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

   public JourCirculation getJourCirculation() {
      return jourCirculation;
   }

   public void setJourCirculation(JourCirculation jourCirculation) {
      this.jourCirculation = jourCirculation;
   }
};
