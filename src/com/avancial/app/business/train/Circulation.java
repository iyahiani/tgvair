package com.avancial.app.business.train;

import java.util.Date;

import com.avancial.app.data.model.databean.CirculationDataBean;

public class Circulation implements ICirculation {

   private Date   dateDebut;
   private Date   dateFin;
   private int    heureArrivee;
   private int    heureDepart;
   private String origine;
   private String destination;
   private String joursCirculation;
   private String indicateurFer;
   private String compagnieTrain;
   private String numeroTrain;
   private String periode;

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
      // sb.append(this.getDateDebut());
      // sb.append(this.getDateFin());
      sb.append(this.getOrigine());

<<<<<<< HEAD
      sb.append("\t") ;
      sb.append(this.getHeureDepart());sb.append("\t") ;
      sb.append(this.getDestination());sb.append("\t") ;
      sb.append(this.getHeureArrivee());sb.append("\t") ;
      sb.append(this.getNumeroTrain()); sb.append("\t") ;
      sb.append(this.getDateDebut()) ; sb.append("\t") ;
      sb.append(this.getDateFin()) ; 
      //sb.append(this.getJoursCirculation());

=======
      sb.append("\t");
>>>>>>> f219dc85a30245e988cb2553770233fb0d45a137
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
      // sb.append(this.getJoursCirculation());

<<<<<<< HEAD

=======
>>>>>>> f219dc85a30245e988cb2553770233fb0d45a137
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

   public String getJoursCirculation() {
      return this.joursCirculation;
   }

   public void setJoursCirculation(String joursCirculation) {
      this.joursCirculation = joursCirculation;
   }

   public CirculationDataBean getCirculationDatabean(Circulation circulation) {
      this.circulationDataBean = new CirculationDataBean();
      this.circulationDataBean.setDestination(circulation.getDestination());
      // circulationDataBean.setHeureArriver(circulation.getHeureArrivee());
      // circulationDataBean.setHeureDepart(circulation.getHeureDepart());
      return this.circulationDataBean;
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
};
