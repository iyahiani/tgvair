package com.avancial.tgvair.metier;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.avancial.tgvair.DataBeans.CirculationDataBean;

public class Circulation implements ICirculation {

   private Date dateDebut;
   private Date dateFin;
   private int heureArrivee;
   private int heureDepart;
   private String origine;
   private String destination;
   private String joursCirculation;
   private String indicateurFer;
   private String compagnieTrain;
   private int numeroTrain;
   private String periode;

   public int getNumeroTrain() {
      return numeroTrain;
   }

   public void setNumeroTrain(int numeroTrain) {
      this.numeroTrain = numeroTrain;
   }

   public String getPeriode() {
      return periode;
   }

   public void setPeriode(String periode) {
      this.periode = periode;
   }

   public String getIndicateurFer() {
      return indicateurFer;
   }

   public void setIndicateurFer(String indicateurFer) {
      this.indicateurFer = indicateurFer;
   }

   public String getCompagnieTrain() {
      return compagnieTrain;
   }

   public void setCompagnieTrain(String compagnieTrain) {
      this.compagnieTrain = compagnieTrain;
   }

   private CirculationDataBean circulationDataBean;

   public Circulation() {

   }

   public String getChaineCircu() {

      StringBuilder sb = new StringBuilder();
      SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);

      // sb.append(this.getDateDebut());
      // sb.append(this.getDateFin());
      sb.append(this.getHeureDepart());
      sb.append(this.getHeureArrivee());
      sb.append(this.getOrigine());
      sb.append(this.getDestination());
      sb.append(this.getJoursCirculation());
      // sb.append(this.dateDebut) ;
      // sb.append(this.dateFin) ;
      return sb.toString();
   }

   public Date getDateDebut() {
      return dateDebut;
   }

   public void setDateDebut(Date dateDebut) {
      this.dateDebut = dateDebut;
   }

   public Date getDateFin() {
      return dateFin;
   }

   public void setDateFin(Date dateFin) {
      this.dateFin = dateFin;
   }

   public int getHeureArrivee() {
      return heureArrivee;
   }

   public void setHeureArrivee(int heureArrivee) {
      this.heureArrivee = heureArrivee;
   }

   public int getHeureDepart() {
      return heureDepart;
   }

   public void setHeureDepart(int heureDepart) {
      this.heureDepart = heureDepart;
   }

   public String getOrigine() {
      return origine;
   }

   public void setOrigine(String origine) {
      this.origine = origine;
   }

   public String getDestination() {
      return destination;
   }

   public void setDestination(String destination) {
      this.destination = destination;
   }

   public String getJoursCirculation() {
      return joursCirculation;
   }

   public void setJoursCirculation(String joursCirculation) {
      this.joursCirculation = joursCirculation;
   }

   public CirculationDataBean getCirculationDatabean(Circulation circulation) {
      circulationDataBean = new CirculationDataBean();
      circulationDataBean.setDestination(circulation.getDestination());
      // circulationDataBean.setHeureArriver(circulation.getHeureArrivee());
      // circulationDataBean.setHeureDepart(circulation.getHeureDepart());
      return circulationDataBean;
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

      return true;
   }
}