package com.avancial.app.business.train.circulation;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.avancial.app.resources.utils.StringToDate;

public class CirculationSemaineBuilder implements ICirculationSemaineBuilder {
   private Circulation circulation = new Circulation();
   int[] jours = { 0, 0, 0, 0, 0, 0, 0 };

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
      this.jours[day - 1] = jourCirculation.isFlagCirculation() ? 1 : 0;
      this.circulation.setDateFin(cal.getTime());
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
      // if (!jours.get(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal))
      // - 1).equals(" "))
      // comp = comp & String.valueOf(jour.isFlagCirculation() ? 1 :
      // 0).equals(jours.get(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal))
      // - 1));
      return comp;

   }

   @Override
   public boolean refresh(JourCirculation jourCirculation) {
      boolean processed = false;
      // On va vérifier que le jour est bien dans la semaine concernée
      // On calcule les dates théoriques de début et de fin
      Calendar cal = new GregorianCalendar();
      cal.setTime(this.circulation.getDateDebut());
      int day = Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal));
      int offset = 1 - day;
      cal.add(Calendar.DAY_OF_WEEK, offset);
      Calendar dateDebut = new GregorianCalendar();
      dateDebut.setTime(cal.getTime());
      Calendar dateFin = new GregorianCalendar();
      cal.add(Calendar.DAY_OF_WEEK, 6);
      dateFin.setTime(cal.getTime());

      if (!(jourCirculation.getDateCircul().before(dateDebut.getTime()) || jourCirculation.getDateCircul().after(dateFin.getTime()))) {
         // On va vérifier que ce jour de circulation concerne bien la
         // circulation en cours
         if (this.compareJourDeCirculation2Circulation(jourCirculation)) {
            // C'est ok, on rajoute le jour
            processed = true;
            cal.setTime(jourCirculation.getDateCircul());
            day = Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal));
            this.jours[day - 1] = jourCirculation.isFlagCirculation() ? 1 : 0;
            this.circulation.setDateFin(jourCirculation.getDateCircul());
         }
      }
      return processed;
   }

   @Override
   public Circulation getCirculation() {
      // Il faut calculer les jours de circulation avant de renvoyer la
      // circulation
      // On parcours le tableau des jours
      StringBuilder sb = new StringBuilder();
      int taille = this.jours.length;
      for (int i = 0; i < taille; i++) {
         sb.append(this.jours[i] == 1 ? i + 1 : " ");
      }
      this.circulation.setJoursCirculation(sb.toString());
      return this.circulation;
   }

}
