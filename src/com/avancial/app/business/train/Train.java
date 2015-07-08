package com.avancial.app.business.train;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.avancial.app.resources.utils.StringToDate;

public class Train implements ITrain {

   protected List<String> listeNumeros;
   protected Map<Date, JourCirculation> listeJoursCirculation;
   protected List<Circulation> listeCirculations;

   /**
    * @author Yahiani Ismail
    * @param num
    * @param circul
    */
   public Train(List<String> num, List<Circulation> circul) {
      this.listeCirculations = circul;
      this.listeJoursCirculation = new TreeMap<>();
      this.listeNumeros = num;
   }

   @Override
   public void addCirculation(Circulation circultation) {
      this.listeCirculations.add(circultation);
      this.remplirJoursCirculations();
   }

   @Override
   public String getGareOrigine() {
      return this.getCirculations().get(0).getOrigine();
   }

   public void calculeCirculationFromJoursCirculation() {
      // On parcourt tous les jours de circulation
      this.remplirJoursCirculations();
      Circulation circulation = null;
      ArrayList<String> jours = new ArrayList<>();
      for (JourCirculation jourCirculation : this.listeJoursCirculation.values()) {
         if (null == circulation && jourCirculation.isFlagCirculation()) {
            circulation = this.initCirculationFromJourDeCirculation(jourCirculation);
            jours.clear();
            for (int i = 1; i <= 7; i++)
               jours.add(" ");
            Calendar cal = new GregorianCalendar();
            cal.setTime(jourCirculation.getDateCircul());
            jours.remove(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)));
            jours.add(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)), "1");
         } else if (this.compareJourDeCirculation2Circulation(jourCirculation, circulation, jours)) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(jourCirculation.getDateCircul());
            // C'est la m�me circulation, on enrichit
            jours.add(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)), "1");
         } else {
            // C'est une nouvelle circulation, on ajoute la pr�c�dente et on en
            // cr�e une nouvelle
            // Il faut mettre � jour les jours de circulation
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < jours.size(); i++) {
               if (jours.get(i).equals("") || jours.get(i).equals("0"))
                  builder.append(" ");
               else
                  builder.append(i);
            }
            circulation.setJoursCirculation(builder.toString());
            this.addCirculation(circulation);
            circulation = this.initCirculationFromJourDeCirculation(jourCirculation);
            jours.clear();
            for (int i = 1; i <= 7; i++)
               jours.add(" ");
            Calendar cal = new GregorianCalendar();
            cal.setTime(jourCirculation.getDateCircul());
            jours.remove(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)));
            jours.add(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)), "1");
         }

      }
   }

   private Circulation initCirculationFromJourDeCirculation(JourCirculation jourCirculation) {
      Circulation circulation = new Circulation();
      circulation.setDateDebut(jourCirculation.getDateCircul());
      circulation.setOrigine(jourCirculation.getOrigine());
      circulation.setHeureDepart(jourCirculation.getHeureDepart());
      circulation.setHeureArrivee(jourCirculation.getHeureArrivee());
      return circulation;
   }

   public boolean compareJourDeCirculation2Circulation(JourCirculation jour, Circulation circulation, ArrayList<String> jours) {
      boolean comp = false;
      // On compare les heures
      comp = jour.getHeureDepart() == circulation.getHeureDepart() && jour.getHeureArrivee() == circulation.getHeureArrivee();

      // On regarde si le flag circulation est le m�me pour le jour concern�
      Calendar cal = new GregorianCalendar();
      cal.setTime(jour.getDateCircul());
      if (!jours.get(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal))).equals(" "))
         comp = comp & String.valueOf(jour.isFlagCirculation()).equals(jours.get(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal))));
      return comp;

   }

   @Override
   public String getGareDestination() {

      return this.getCirculations().get(getCirculations().size() - 1).getDestination();
   }

   /**
    * @author ismael.yahiani adapte les cicrculation dans les catalogues
    */

   @Override
   public boolean compare(ITrain train) {
      boolean comp = true;

      for (Entry<Date, JourCirculation> jourCirculation : this.getJoursCirculation().entrySet()) {

         if (train.getJoursCirculation().containsKey(jourCirculation.getKey())) {

            comp = jourCirculation.getValue().compare(train.getJoursCirculation().get(jourCirculation.getKey()));
            if (!comp)
               break;
         } else {
            comp = false;
            break;
         }
      }
      return comp;

   }

   /**
    * Remplit la map des jours de circulation avec tous les jours circulant ou
    * non de toutes les listeCirculations
    */
   public void remplirJoursCirculations() {

      for (Circulation circul : this.listeCirculations) {
         this.listeJoursCirculation.putAll(circul.getDateJourCirculMap());
      }
   }

   /**
    * @return
    */
   public List<Circulation> getCirculations() {
      return this.listeCirculations;
   }

   @Override
   public void adapt(ITrain train) {

      for (Entry<Date, JourCirculation> jourCirculation : this.getJoursCirculation().entrySet()) {

         if (train.getJoursCirculation().containsKey(jourCirculation.getKey())) {

            if (!jourCirculation.getValue().compare(train.getJoursCirculation().get(jourCirculation.getKey())))
               this.listeJoursCirculation.put(jourCirculation.getKey(), train.getJoursCirculation().get(jourCirculation.getKey()));
         }

      }

   }

   @Override
   public Map<Date, JourCirculation> getJoursCirculation() {
      return this.listeJoursCirculation;
   }

   /**
    * @param joursCirculation
    */
   public void setJoursCirculation(Map<Date, JourCirculation> joursCirculation) {
      this.listeJoursCirculation = joursCirculation;
   }

   @Override
   public String toString() {

      StringBuilder sb = new StringBuilder();
      for (String num : this.listeNumeros)
         sb.append(num + "/");
      sb.append("\n");
      for (Map.Entry<Date, JourCirculation> entry : this.listeJoursCirculation.entrySet()) {
         sb.append(entry.getValue() + "\n");
      }

      return sb.toString();
   }

   @Override
   public List<String> getTrainNumeros() {

      return null;
   }

   @Override
   public Train getTrainAPartirDuCatalogue(ITrainCatalogue trainCatalogue) {

      return null;
   }

}
