package com.avancial.app.business.train.circulation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.avancial.app.resources.utils.StringToDate;

public class CirculationSemaineBuilder implements ICirculationSemaineBuilder {
   private Circulation circulation = new Circulation();
   int[]               jours       = { 0, 0, 0, 0, 0, 0, 0 };

   /**
    * 
    * @author bruno
    * @param jourCirculation
    */
   public CirculationSemaineBuilder(JourCirculation jourCirculation) {

      this.circulation.setOrigine(jourCirculation.getOrigine());
      this.circulation.setDestination(jourCirculation.getDestination());
      this.circulation.setHeureDepart(jourCirculation.getHeureDepart());
      this.circulation.setHeureArrivee(jourCirculation.getHeureArrivee());

      // Il faut déterminer la date de début qui commence toujours le lundi
      Calendar cal = new GregorianCalendar();
      cal.setTime(jourCirculation.getDateCircul());
      // on calcule le delta par rapport à Lundi
      int day = Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal));
      // int offset = 1 - day;
      // cal.add(Calendar.DAY_OF_WEEK, offset);
      this.circulation.setDateDebut(cal.getTime());
      // On calcule la date de la fin de la semaine
      // cal.add(Calendar.DAY_OF_WEEK, 6);
      // this.circulation.setDateFin(cal.getTime());
      this.jours[day - 1] = 1;
      this.circulation.setDateFin(cal.getTime());
      this.setJoursCirculation();
   }

   /**
    * @param jour
    * @param circulation
    * @return
    */
   public boolean compareJourDeCirculation2Circulation(JourCirculation jour) {
      boolean comp = false;
      // On compare les heures
      comp = jour.getHeureDepart() == this.circulation.getHeureDepart() && jour.getHeureArrivee() == this.circulation.getHeureArrivee();

      // On regarde si le flag circulation est le même pour le jour concerné
      Calendar cal = new GregorianCalendar();
      cal.setTime(jour.getDateCircul());
         return comp;

   }

   @Override
   public boolean refresh(Circulation circulation) {

      // On regarde si les circulations sont compatibles

      if (this.circulation.getHeureDepart() != circulation.getHeureDepart() || this.circulation.getHeureArrivee() != circulation.getHeureArrivee())
         return false;

      // On va vérifier que la circulation est fusionnable cad que la fusion n'ajoute pas de jours indésirables

      // On décompose les deux circulation en jours

      Map<Date, JourCirculation> listeJoursDes2Circulations = new TreeMap<>();
      Set<String> jourCircul = new TreeSet<>();

      Circulation circulation1 = this.circulation;
      Circulation circulation2 = circulation;

      listeJoursDes2Circulations.putAll(circulation1.getDateJourCirculMap(false));
      listeJoursDes2Circulations.putAll(circulation2.getDateJourCirculMap(false));

      // Array listeJoursFusion

      Circulation fusionCirculation = new Circulation();
      fusionCirculation.setOrigine(circulation1.getOrigine());
      fusionCirculation.setDestination(circulation1.getDestination());
      fusionCirculation.setHeureDepart(circulation1.getHeureDepart());
      fusionCirculation.setHeureArrivee(circulation1.getHeureArrivee());

      fusionCirculation.setDateDebut(circulation1.getDateDebut().before(circulation2.getDateDebut()) ? circulation1.getDateDebut() : circulation2.getDateDebut());
      fusionCirculation.setDateFin(circulation1.getDateFin().after(circulation2.getDateFin()) ? circulation1.getDateFin() : circulation2.getDateFin());

      fusionCirculation.setJoursCirculation(jourCirculationUtil.fusionne(circulation1.getJoursCirculation(), circulation2.getJoursCirculation()));

      for (JourCirculation jourCirculation : fusionCirculation.getDateJourCirculMap(false).values()) {
         if (!listeJoursDes2Circulations.containsKey(jourCirculation.getDateCircul()))
            return false;
      }

      this.circulation = fusionCirculation;
      return true;
   }

   private boolean isCirculationsFusionnable(Circulation circulation1, Circulation circulation2) {

      // On décompose les deux circulation en jours

      Map<Date, JourCirculation> listeJoursDes2Circulations = new TreeMap<>();
      Set<String> jourCircul = new TreeSet<>();
      listeJoursDes2Circulations.putAll(circulation1.getDateJourCirculMap(false));
      listeJoursDes2Circulations.putAll(circulation2.getDateJourCirculMap(false));

      // Array listeJoursFusion

      Circulation fusionCirculation = new Circulation();
      fusionCirculation.setOrigine(circulation1.getOrigine());
      fusionCirculation.setDestination(circulation1.getDestination());
      fusionCirculation.setHeureDepart(circulation1.getHeureDepart());
      fusionCirculation.setHeureArrivee(circulation1.getHeureArrivee());

      fusionCirculation.setDateDebut(circulation1.getDateDebut().before(circulation2.getDateDebut()) ? circulation1.getDateDebut() : circulation2.getDateDebut());
      fusionCirculation.setDateFin(circulation1.getDateFin().after(circulation2.getDateFin()) ? circulation1.getDateFin() : circulation2.getDateFin());
      jourCircul.add(circulation1.getJoursCirculation());
      jourCircul.add(circulation2.getJoursCirculation());
      fusionCirculation.setJoursCirculation(jourCircul.toString());

      jourCirculationUtil jour = new jourCirculationUtil();

      fusionCirculation.setJoursCirculation(jourCirculationUtil.fusionne(circulation1.getJoursCirculation(), circulation2.getJoursCirculation()));

      for (JourCirculation jourCirculation : fusionCirculation.getDateJourCirculMap(false).values()) {
         if (!listeJoursDes2Circulations.containsKey(jourCirculation.getDateCircul()))
            return false;
      }

      return true;
   }

   @Override
   public Circulation getCirculation() {
      // Il faut calculer les jours de circulation avant de renvoyer la
      // circulation
      // On parcours le tableau des jours
      // this.setJoursCirculation();
      return this.circulation;
   }

   private void setJoursCirculation() {
      StringBuilder sb = new StringBuilder();
      int taille = this.jours.length;
      for (int i = 0; i < taille; i++) {
         sb.append(this.jours[i] == 1 ? i + 1 : " ");
      }
      this.circulation.setJoursCirculation(sb.toString());
   }

}
