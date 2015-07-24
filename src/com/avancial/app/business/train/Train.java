package com.avancial.app.business.train;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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

      List<JourCirculation> temp = new ArrayList<>();
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
            // C'est la m�me circulation, on enrichit
            Calendar cal = new GregorianCalendar();
            cal.setTime(jourCirculation.getDateCircul());

            jours.remove(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)) - 1);
            jours.add(Integer.parseInt(StringToDate.JavaDays2FrenchDays(cal)) - 1, String.valueOf(jourCirculation.isFlagCirculation() ? 1 : 0));
            circulation.setDateFin(jourCirculation.getDateCircul());
         } else {
            // C'est une nouvelle circulation, on ajoute la pr�c�dente et on en
            // cr�e une nouvelle
            // Il faut mettre � jour les jours de circulation
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

      // On regarde si le flag circulation est le m�me pour le jour concern�
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
               // Si on le trouve, on ne met � jour qu'en cas de circulation
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

   @Override
   public void adapt(ITrain train, Date date_deb_SSIM, Date date_fin_SSIM) {

      // this.remplirJoursCirculations();

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
    * @author ismael.yahiani r�cup�re le train r�f�renc� dans le catalogue �
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
      Circulation circulation = null;

      for (Circulation circulSSIM : this.getCirculations()) {

         // for (String num : train.listeNumeros) {

         if (compNumTrain(circulSSIM, trainCatalogue.listeNumeros)) {

            if (circulSSIM.getOrigine().equalsIgnoreCase(trainCatalogue.getGareOrigine()) && circulation == null) { // &&
                                                                                                                    // !
                                                                                                                    // circulSSIM.getOrigine().equalsIgnoreCase(gare)
               circulation = new Circulation();
               circulation.setHeureDepart(circulSSIM.getHeureDepart());
               circulation.setDateFin(circulSSIM.getDateFin());
               circulation.setDateDebut(circulSSIM.getDateDebut());
               circulation.setOrigine(circulSSIM.getOrigine());
               circulation.setJoursCirculation(circulSSIM.getJoursCirculation());

               circulation.setRangTranson(circulSSIM.getRangTranson());

               circulation.setNumeroTrain(circulSSIM.getNumeroTrain());

            }

            if (circulSSIM.getDestination().equalsIgnoreCase(trainCatalogue.getGareDestination()) && circulation != null) {

               circulation.setDestination(circulSSIM.getDestination());

               circulation.setHeureArrivee(circulSSIM.getHeureArrivee());
               train.addCirculation(circulation);
               circulation = null;
            }
         }
      }

      return train;
   }

   public Map<String, JourCirculation> getPeriodes() {
      Map<Date, JourCirculation> jcTemp = new LinkedHashMap<>();
      // Multimap<Integer, Integer> dates = ArrayListMultimap.create() ;

      List<JourCirculation> jourCir = new ArrayList<>();
      List<JourCirculation> jourCir2 = new ArrayList<>();
      Calendar calendar = Calendar.getInstance();
      Set<Integer> jours = new HashSet<>();

      Map<Integer, List<JourCirculation>> joursGrouper = new TreeMap<>();

      for (Entry<Date, JourCirculation> jc : this.getListeJoursCirculation().entrySet()) {
         if (jc.getValue().isFlagCirculation()) {
            jcTemp.put(jc.getKey(), jc.getValue());
         }
      }
      Set<String> tempDates = new TreeSet<>();

      for (Entry<Date, JourCirculation> jc : jcTemp.entrySet()) {
         String sbDep, sbArr;
         sbDep = String.valueOf(jc.getValue().getHeureDepart());
         sbArr = String.valueOf(jc.getValue().getHeureArrivee());

         if (jc.getValue().getHeureDepart() / 1000 == 0)
            sbDep = "0".concat(String.valueOf(jc.getValue().getHeureDepart()));
         if (jc.getValue().getHeureArrivee() / 1000 == 0)
            sbArr = "0".concat(String.valueOf(jc.getValue().getHeureArrivee()));

         tempDates.add(sbDep.concat(sbArr));
      }

                  // //////////////List circulation Regrouper par heures

      for (String dt : tempDates) {

         for (Entry<Date, JourCirculation> jc : jcTemp.entrySet()) {

            String sbDep, sbArr, heureDepArr;
            sbDep = String.valueOf(jc.getValue().getHeureDepart());
            sbArr = String.valueOf(jc.getValue().getHeureArrivee());

            if (jc.getValue().getHeureDepart() / 1000 == 0)
               sbDep = "0".concat(String.valueOf(jc.getValue().getHeureDepart()));
            if (jc.getValue().getHeureArrivee() / 1000 == 0)
               sbArr = "0".concat(String.valueOf(jc.getValue().getHeureArrivee()));
            heureDepArr = sbDep.concat(sbArr);
            if (dt.equalsIgnoreCase(heureDepArr)) {
               jourCir.add(jc.getValue());

            }
         }
      }

               // ////////// Set regroupant l'ensembles des jours de circulation du train

      for (JourCirculation j : jourCir) {
         calendar.setTime(j.getDateCircul());
         if (calendar.get(Calendar.DAY_OF_WEEK) == 1)
            jours.add(7);
         else
            jours.add(calendar.get(Calendar.DAY_OF_WEEK) - 1);
      }

                  // ///// list regroupante les circulation par heure de depart/Arriver et
                  // par jour de circulation

      Map<String, Map<Integer, List<JourCirculation>>> resultat = new TreeMap<>();

      List<JourCirculation> jour = new ArrayList<>();

      for (String dt : tempDates) {
         for (int i : jours) {
            for (JourCirculation j : jourCir) {
                        // //////////////// Monter le chaine de caractere avec Heure de
                        // depart et heure d'arriver
               String sbDep, sbArr, heureDepArr;
               sbDep = String.valueOf(j.getHeureDepart());
               sbArr = String.valueOf(j.getHeureArrivee());
                        // ///////////// si l'heure est sous forme xxx on la remet sous
                        // forme 0xxx exemple : 9h00 =900 deviens 0900
               if (j.getHeureDepart() / 1000 == 0)
                  sbDep = "0".concat(String.valueOf(j.getHeureDepart()));
               if (j.getHeureArrivee() / 1000 == 0)
                  sbArr = "0".concat(String.valueOf(j.getHeureArrivee()));
               heureDepArr = sbDep.concat(sbArr);
               calendar.setTime(j.getDateCircul());
               // /// remplir la map
               if (dt.equalsIgnoreCase(heureDepArr))
                  if ((calendar.get(Calendar.DAY_OF_WEEK) - 1) == i || (calendar.get(Calendar.DAY_OF_WEEK) == 1 && i == 7)) {
                     jourCir2.add(j);
                     jour.add(j);
                     joursGrouper.put(i, jour);
                     resultat.put(dt, joursGrouper);
                  }
            }
                        // reinitialiser la iste des jours de circulation
            jour = new ArrayList<>();

         }
                        // reinitialiser la liste des jours regrouper par heures
         joursGrouper = new TreeMap<>();
      }

                     // ///////////////////////////////// calcule des periodes
      int diff;

      Calendar dt_db = Calendar.getInstance(), 
            compt = Calendar.getInstance(), 
            dt_fin = Calendar.getInstance();

      Map<String, JourCirculation> maPeriode = new LinkedHashMap<>();
      int flag = 0;
      for (Entry<String, Map<Integer, List<JourCirculation>>> res : resultat.entrySet()) {

         for (Entry<Integer, List<JourCirculation>> joursCircul : res.getValue().entrySet()) {

            dt_db.setTime(joursCircul.getValue().get(0).getDateCircul());
            dt_fin.setTime(joursCircul.getValue().get(0).getDateCircul());

            if (joursCircul.getValue().size() == 1)
               maPeriode.put(dt_db.getTime().toString().concat(dt_fin.getTime().toString()), joursCircul.getValue().get(0));

            else

               for (int x = 1; x < joursCircul.getValue().size(); x++) {

                  compt.setTime(joursCircul.getValue().get(x).getDateCircul());
                  flag = x;
                  diff = compt.get(Calendar.DAY_OF_YEAR) - dt_fin.get(Calendar.DAY_OF_YEAR);

                  if (diff <= 7)
                     dt_fin.setTime(compt.getTime());

                  else {
                     maPeriode.put(dt_db.getTime().toString().concat(dt_fin.getTime().toString()), joursCircul.getValue().get(x));
                     dt_db.setTime(compt.getTime());
                     dt_fin.setTime(compt.getTime());
                  }
               }
            maPeriode.put(dt_db.getTime().toString().concat(dt_fin.getTime().toString()), joursCircul.getValue().get(flag));
         }

      }
                  // //////////////////////// Fusion des Periodes

      return maPeriode;
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
      return this.listeNumeros;
   }

   public void setListeNumeros(List<String> listeNumeros) {
      this.listeNumeros = listeNumeros;
   }

   public Map<Date, JourCirculation> getListeJoursCirculation() {
      return this.listeJoursCirculation;
   }

   public void setListeJoursCirculation(Map<Date, JourCirculation> listeJoursCirculation) {
      this.listeJoursCirculation = listeJoursCirculation;
   }

   public List<Circulation> getListeCirculations() {
      return this.listeCirculations;
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
