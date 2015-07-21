package com.avancial.app.business.train;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.avancial.app.business.reader.ReaderSSIM;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.reader.IReader;

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

   public Train() {
      this.listeCirculations = new ArrayList<>();
      this.listeNumeros = new ArrayList<>();
      this.listeJoursCirculation = new TreeMap<>();
   }

   public Train(List<String> listeNumeros2, List<Circulation> circulations, Map<Date, JourCirculation> listeJoursCirculation2) {
      this.listeNumeros = listeNumeros2;
      this.listeCirculations = circulations;
      this.listeJoursCirculation = listeJoursCirculation2;
   }

   @Override
   public void addCirculation(Circulation circulation) {
      this.listeCirculations.add(circulation);
      this.remplirJoursCirculations();
   }

   @Override
   public String getGareOrigine() {
      return this.getCirculations().get(0).getOrigine();
   }

   public void calculeCirculationFromJoursCirculation() {
      // On parcourt tous les jours de circulation

      List<JourCirculation> temp = new ArrayList<JourCirculation>();
      temp.addAll(this.listeJoursCirculation.values());
      Circulation circulation = null;
      ArrayList<String> jours = new ArrayList<>();
      this.listeCirculations.clear();

      for (JourCirculation jourCirculation : temp) {

         if (null == circulation) {
            circulation = this.initCirculationFromJourDeCirculation(jourCirculation);
            jours.clear();
            for (int i = 1; i <= 7; i++)
               jours.add(" ");
            Calendar cal = new GregorianCalendar();
            cal.setTime(jourCirculation.getDateCircul());

            jours.remove(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)) - 1);
            jours.add(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)) - 1, String.valueOf(jourCirculation.isFlagCirculation() ? 1 : 0));
            jours.remove(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)) - 1);
            jours.add(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)) - 1, String.valueOf(jourCirculation.isFlagCirculation() ? 1 : 0));

         }

         if (this.compareJourDeCirculation2Circulation(jourCirculation, circulation, jours)) {
            // C'est la même circulation, on enrichit
            Calendar cal = new GregorianCalendar();
            cal.setTime(jourCirculation.getDateCircul());

            jours.remove(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)) - 1);
            jours.add(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)) - 1, String.valueOf(jourCirculation.isFlagCirculation() ? 1 : 0));
            circulation.setDateFin(jourCirculation.getDateCircul());
         } else {
            // C'est une nouvelle circulation, on ajoute la précédente et on en
            // crée une nouvelle
            // Il faut mettre à jour les jours de circulation
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < jours.size(); i++) {
               if (jours.get(i).equals(" ") || jours.get(i).equals("0"))
                  builder.append(" ");
               else
                  builder.append(i + 1);
            }
            circulation.setJoursCirculation(builder.toString());

            this.addCirculation(circulation);
            circulation = this.initCirculationFromJourDeCirculation(jourCirculation);
            jours.clear();
            for (int i = 1; i <= 7; i++)
               jours.add(" ");
            Calendar cal = new GregorianCalendar();
            cal.setTime(jourCirculation.getDateCircul());
            jours.remove(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)) - 1);
            jours.add(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)) - 1, "1");
         }

      }

      this.listeCirculations.clear();
      this.listeCirculations.addAll(this.listeCirculations);

      this.listeCirculations.add(circulation);

   }

   private Circulation initCirculationFromJourDeCirculation(JourCirculation jourCirculation) {
      Circulation circulation = new Circulation();
      circulation.setDateDebut(jourCirculation.getDateCircul());
      circulation.setDateFin(jourCirculation.getDateCircul());
      circulation.setOrigine(jourCirculation.getOrigine());
      circulation.setDestination(jourCirculation.getDestination());
      circulation.setHeureDepart(jourCirculation.getHeureDepart());
      circulation.setHeureArrivee(jourCirculation.getHeureArrivee());
      return circulation;
   }

   public boolean compareJourDeCirculation2Circulation(JourCirculation jour, Circulation circulation, ArrayList<String> jours) {
      boolean comp = false;
      // On compare les heures
      comp = jour.getHeureDepart() == circulation.getHeureDepart() && jour.getHeureArrivee() == circulation.getHeureArrivee();

      // On regarde si le flag circulation est le même pour le jour concerné
      Calendar cal = new GregorianCalendar();
      cal.setTime(jour.getDateCircul());
      if (!jours.get(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)) - 1).equals(" "))
         comp = comp & String.valueOf(jour.isFlagCirculation() ? 1 : 0).equals(jours.get(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)) - 1));
      return comp;
   }

   /**
    * @author ismael.yahiani adapte les cicrculation dans les catalogues
    */

   @Override
   public boolean compare(ITrain train) {

      boolean comp = true;

      // this.remplirJoursCirculations();

      for (Entry<Date, JourCirculation> jourCirculation : this.getJoursCirculation().entrySet()) {

         if (train.getJoursCirculation().containsKey(jourCirculation.getKey())) {

            comp = jourCirculation.getValue().compare(train.getJoursCirculation().get(jourCirculation.getKey()));

            if (!comp) {
               comp = false;
               break;
            }
         }
         if (!train.getJoursCirculation().containsKey(jourCirculation.getKey())) {
            comp = false;
            break;
         }
         if (train.getJoursCirculation().containsKey(jourCirculation.getKey())
               && train.getJoursCirculation().get(jourCirculation.getKey()).isFlagCirculation() != jourCirculation.getValue().isFlagCirculation()) {
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
      this.listeJoursCirculation.clear();
      for (Circulation circul : this.listeCirculations) {

         for (Entry<Date, JourCirculation> entry : circul.getDateJourCirculMap().entrySet()) {
            // Si on ne trouve pas le jour, on ajoute
            if (!this.listeJoursCirculation.containsKey(entry.getKey()))
               this.listeJoursCirculation.put(entry.getKey(), entry.getValue());
            else {
               // Si on le trouve, on ne met à jour qu'en cas de circulation
               if (entry.getValue().isFlagCirculation())
                  this.listeJoursCirculation.put(entry.getKey(), entry.getValue());
            }
         }
      }
   }

   /**
    * @return
    */
   public List<Circulation> getCirculations() {
      return this.listeCirculations;
   }

   public void modifierListeJour(ITrain train) {

   }

   @Override
   public void adapt(ITrain train, Date date_deb_SSIM, Date date_fin_SSIM) {

      // this.remplirJoursCirculations();

      boolean a = true;

      for (Entry<Date, JourCirculation> jourCirculation : this.listeJoursCirculation.entrySet()) {

         if ((jourCirculation.getKey().after(date_deb_SSIM)) || (jourCirculation.getKey().equals(date_deb_SSIM)) && (jourCirculation.getKey().before(date_fin_SSIM))
               || (jourCirculation.getKey().equals(date_fin_SSIM)))

            if (train.getJoursCirculation().containsKey(jourCirculation.getKey())) { // verification
               // concordance
               // Jours
               // veriifer si les heure et les OD ne sont pas concordantes et
               // que
               // le train circule

               if (!jourCirculation.getValue().compare(train.getJoursCirculation().get(jourCirculation.getKey())) && jourCirculation.getValue().isFlagCirculation())

                  this.listeJoursCirculation.put(jourCirculation.getKey(), train.getJoursCirculation().get(jourCirculation.getKey()));
               else if ((jourCirculation.getValue().compare(train.getJoursCirculation().get(jourCirculation.getKey())) && jourCirculation.getValue().isFlagCirculation() != train.getJoursCirculation()
                     .get(jourCirculation.getKey()).isFlagCirculation())) {
                  jourCirculation.getValue().setFlagCirculation(train.getJoursCirculation().get(jourCirculation.getKey()).isFlagCirculation());
                  this.listeJoursCirculation.put(jourCirculation.getKey(), jourCirculation.getValue());
               }
            } else {
               if (!train.getJoursCirculation().containsKey(jourCirculation.getKey()) && (jourCirculation.getKey().after(date_deb_SSIM) || (jourCirculation.getKey().equals(date_deb_SSIM)))
                     && (jourCirculation.getKey().before(date_fin_SSIM) || (jourCirculation.getKey().equals(date_fin_SSIM))))

               {
                  jourCirculation.getValue().setFlagCirculation(false);
               }
            }

      }

      // this.calculeCirculationFromJoursCirculation() ;
   }

   /**
    * @author ismael.yahiani récupére le train référencé dans le catalogue à
    *         partir de la SSIM
    * @throws ParseException
    */

   public static boolean compNumTrain(Circulation c, List<String> nums) {

      boolean comp = false;

      for (String num : nums) {
         if (num.equalsIgnoreCase(c.getNumeroTrain()))
            return true;
      }
      return comp;
   }

   @Override
   public Train getTrainSSIMRestreint(Train trainCatalogue) {

      Train train = new Train();

      train.listeNumeros.addAll(trainCatalogue.listeNumeros);
      String gare = "";
      Circulation circulation = null;

      for (Circulation circulSSIM : this.getCirculations()) {

         // for (String num : train.listeNumeros) {

         if (compNumTrain(circulSSIM, trainCatalogue.listeNumeros)) {

            if (circulSSIM.getOrigine().equalsIgnoreCase(trainCatalogue.getGareOrigine()) && circulation == null) { // &&
                                                                                                                    // !
                                                                                                                    // circulSSIM.getOrigine().equalsIgnoreCase(gare)
               circulation = new Circulation();
               circulation.setHeureDepart(circulSSIM.getHeureDepart());
               circulation.setOrigine(circulSSIM.getOrigine());
               circulation.setJoursCirculation(circulSSIM.getJoursCirculation());
               circulation.setNumeroTrain(circulSSIM.getNumeroTrain());

            }

            if (circulSSIM.getDestination().equalsIgnoreCase(trainCatalogue.getGareDestination()) && circulation != null) {
               circulation.setDateFin(circulSSIM.getDateFin());
               circulation.setDateDebut(circulSSIM.getDateDebut());
               circulation.setDestination(circulSSIM.getDestination());
               circulation.setJoursCirculation(circulSSIM.getJoursCirculation());
               circulation.setHeureArrivee(circulSSIM.getHeureArrivee());
               train.addCirculation(circulation);
               circulation = null;
            }
         }
      }

      return train;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      for (String num : this.listeNumeros)
         sb.append(num + "/");
      sb.append("\n");
      sb.append("------------- JOURS DE CIRCULATION -----------------------------\n");
      
      for (Map.Entry<Date, JourCirculation> entry : this.listeJoursCirculation.entrySet()) {
         sb.append(entry.getValue() + "\n");
      }

      sb.append("----------------- CIRCULATIONS ------------------------------");

      for (Circulation circulation : this.listeCirculations)
         sb.append(circulation + "\n");

      return sb.toString();
   }

   @Override
   public List<String> getTrainNumeros() {

      return null;
   }

   @Override
   public String getGareDestination() {

      return this.getCirculations().get(getCirculations().size() - 1).getDestination();
   }

   @Override
   public Train getTrainAPartirDuCatalogue(TrainCatalogue trainCatalogue) {

      return null;
   }

   public List<String> getListeNumeros() {
      return listeNumeros;
   }

   public void setListeNumeros(List<String> listeNumeros) {
      this.listeNumeros = listeNumeros;
   }

   public Map<Date, JourCirculation> getListeJoursCirculation() {
      return listeJoursCirculation;
   }

   public void setListeJoursCirculation(Map<Date, JourCirculation> listeJoursCirculation) {
      this.listeJoursCirculation = listeJoursCirculation;
   }

   public List<Circulation> getListeCirculations() {
      return listeCirculations;
   }

   public void setListeCirculations(List<Circulation> listeCirculations) {
      this.listeCirculations = listeCirculations;
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

   public void addNumeroTrain(String num_train) {
      this.listeNumeros.add(num_train);
   }

}
