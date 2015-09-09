package com.avancial.app.business.train;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

import com.avancial.app.business.compagnieAerienne.IObservableJoursCirculation;
import com.avancial.app.business.compagnieAerienne.ObservableJoursCirculation;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.business.train.circulation.IObservableCirculationSemaineBuilder;
import com.avancial.app.business.train.circulation.JourCirculation;
import com.avancial.app.business.train.circulation.ObservableCirculationBuilder;
import com.avancial.app.resources.utils.StringToDate;

public class Train implements ITrain {

   private int                          idTrain;
   protected List<String>               listeNumeros;
   protected Map<Date, JourCirculation> listeJoursCirculation;
   private Date                         dateDebutValidite;
   private Date                         dateFinValidite;
   private String                       ooperatingFlight;
   protected List<Circulation>          listeCirculations;

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

   public Train(List<String> listeNumeros2, List<Circulation> circulations, Map<Date, JourCirculation> listeJoursCirculation2, Date dateDebutValidite, Date dateFinValidite) {
      this.listeNumeros = listeNumeros2;
      this.listeCirculations = circulations;
      this.listeJoursCirculation = listeJoursCirculation2;
      this.dateDebutValidite = dateDebutValidite;
      this.dateFinValidite = dateFinValidite;
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
      Collections.sort(temp);
      Circulation circulation = null;
      ArrayList<String> jours = new ArrayList<>();
      this.listeCirculations.clear();
      IObservableCirculationSemaineBuilder builder = new ObservableCirculationBuilder();
      for (JourCirculation jourCirculation : temp) {
         if (jourCirculation.isFlagCirculation())
            builder.notifierObservateurs(jourCirculation);
      }
      this.listeCirculations.addAll(builder.getListeCirculation());
   }

   private Circulation initCirculationFromJourDeCirculation(JourCirculation jourCirculation) {
      Circulation circulation = new Circulation();
      circulation.setDateDebut(jourCirculation.getDateCircul());
      circulation.setDateFin(jourCirculation.getDateCircul());
      circulation.setOrigine(jourCirculation.getOrigine());
      circulation.setDestination(jourCirculation.getDestination());
      circulation.setHeureDepart(jourCirculation.getHeureDepart());
      circulation.setHeureArrivee(jourCirculation.getHeureArrivee());
      circulation.setGMTDepart(jourCirculation.getGMTDepart());
      circulation.setGMTArrivee(jourCirculation.getGMTArriver());
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

         if (train.getJoursCirculation().containsKey(jourCirculation.getKey()) && train.getJoursCirculation().get(jourCirculation.getKey()).isFlagCirculation() != jourCirculation.getValue().isFlagCirculation()) {
            comp = false;
            break;
         }

      }
      return comp;
   }

   /**
    * Remplit la map des jours de circulation avec tous les jours circulant ou non de toutes les listeCirculations
    */
   public void remplirJoursCirculations() {
      this.listeJoursCirculation.clear();
      for (Circulation circul : this.listeCirculations) {

         for (Entry<Date, JourCirculation> entry : circul.getDateJourCirculMap(true).entrySet()) {
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

   @Override
   public void adapt(Train train, Date date_deb_SSIM, Date date_fin_SSIM) {
      if (train.getListeCirculations().size() > 0)
         for (Entry<Date, JourCirculation> jourCirculation : this.listeJoursCirculation.entrySet()) {

            if ((jourCirculation.getKey().after(date_deb_SSIM)) || (jourCirculation.getKey().equals(date_deb_SSIM)) && (jourCirculation.getKey().before(date_fin_SSIM)) || (jourCirculation.getKey().equals(date_fin_SSIM))) {

               if (train.getJoursCirculation().containsKey(jourCirculation.getKey())) { // verification
                  // concordance Jours
                  // verifer si les heure et les OD ne sont pas concordantes et
                  // que le train circule

                  if (!jourCirculation.getValue().compare(train.getJoursCirculation().get(jourCirculation.getKey())) && jourCirculation.getValue().isFlagCirculation()) {
                     this.listeJoursCirculation.put(jourCirculation.getKey(), train.getJoursCirculation().get(jourCirculation.getKey()));

                  }

                  else if ((jourCirculation.getValue().compare(train.getJoursCirculation().get(jourCirculation.getKey())) && jourCirculation.getValue().isFlagCirculation() != train.getJoursCirculation().get(jourCirculation.getKey()).isFlagCirculation())) {

                     jourCirculation.getValue().setFlagCirculation(train.getJoursCirculation().get(jourCirculation.getKey()).isFlagCirculation());
                     this.listeJoursCirculation.put(jourCirculation.getKey(), jourCirculation.getValue());
                  }

               }
               /*
                * else {
                * 
                * if (!train.getJoursCirculation().containsKey(jourCirculation.getKey ()) && (jourCirculation.getKey().after(date_deb_SSIM) || (jourCirculation.getKey().equals(date_deb_SSIM))) && (jourCirculation.getKey().before(date_fin_SSIM) || (jourCirculation.getKey().equals(date_fin_SSIM))))
                * 
                * { jourCirculation.getValue().setFlagCirculation(false); iObs.notifierTrainToCompagnie(jourCirculation.getValue()); } }
                */
            }

         }

   }

   @Override
   public void adapt(Train train, Date date_deb_SSIM, Date date_fin_SSIM, IObservableJoursCirculation iObs) {

      // this.remplirJoursCirculations();
      if (train.getListeCirculations().size() > 0)
         for (Entry<Date, JourCirculation> jourCirculation : this.listeJoursCirculation.entrySet()) {

            if ((jourCirculation.getKey().after(date_deb_SSIM)) || (jourCirculation.getKey().equals(date_deb_SSIM)) && (jourCirculation.getKey().before(date_fin_SSIM)) || (jourCirculation.getKey().equals(date_fin_SSIM))) {
               IObservableJoursCirculation joursCirculObserv = new ObservableJoursCirculation();

               if (train.getJoursCirculation().containsKey(jourCirculation.getKey())) { // verification
                  // concordance Jours
                  // verifer si les heure et les OD ne sont pas concordantes et
                  // que le train circule

                  if (!jourCirculation.getValue().compare(train.getJoursCirculation().get(jourCirculation.getKey())) && jourCirculation.getValue().isFlagCirculation()) {
                     this.listeJoursCirculation.put(jourCirculation.getKey(), train.getJoursCirculation().get(jourCirculation.getKey()));
                     iObs.notifierTrainToCompagnie(jourCirculation.getValue());

                  }

                  else if ((jourCirculation.getValue().compare(train.getJoursCirculation().get(jourCirculation.getKey())) && jourCirculation.getValue().isFlagCirculation() != train.getJoursCirculation().get(jourCirculation.getKey()).isFlagCirculation())) {

                     jourCirculation.getValue().setFlagCirculation(train.getJoursCirculation().get(jourCirculation.getKey()).isFlagCirculation());
                     this.listeJoursCirculation.put(jourCirculation.getKey(), jourCirculation.getValue());
                     iObs.notifierTrainToCompagnie(jourCirculation.getValue());
                  }

               }
               /*
                * else {
                * 
                * if (!train.getJoursCirculation().containsKey(jourCirculation.getKey ()) && (jourCirculation.getKey().after(date_deb_SSIM) || (jourCirculation.getKey().equals(date_deb_SSIM))) && (jourCirculation.getKey().before(date_fin_SSIM) || (jourCirculation.getKey().equals(date_fin_SSIM))))
                * 
                * { jourCirculation.getValue().setFlagCirculation(false); iObs.notifierTrainToCompagnie(jourCirculation.getValue()); } }
                */
            }

         }

      // this.calculeCirculationFromJoursCirculation() ;
   }

   /**
    * @author ismael.yahiani récupére le train référencé dans le catalogue à partir de la SSIM
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

   /**
    * Retourne un train limité par les dates de validité d'un client
    * 
    * @param portef
    * @return
    */
   public Train getTrainFromPortefeuille(Date pdebut, Date pfin) {

      Date debut = new Date();
      debut.setTime(pdebut.getTime());

      Date fin = new Date();
      fin.setTime(pfin.getTime());

      Train train = new Train();
      Calendar cal = new GregorianCalendar();
      JourCirculation jour;
      while (debut.getTime() <= fin.getTime()) {
         cal.clear();
         cal.setTime(debut);
         cal.set(Calendar.HOUR, 0);
         cal.set(Calendar.MINUTE, 0);
         cal.set(Calendar.SECOND, 0);
         cal.set(Calendar.MILLISECOND, 0);
         debut.setTime(cal.getTimeInMillis());
         jour = this.getListeJoursCirculation().get(cal.getTime());

         if (null != jour)
            if (jour.isFlagCirculation())
               train.getListeJoursCirculation().put(cal.getTime(), jour);

         cal.add(Calendar.DAY_OF_MONTH, 1);
         debut.setTime(cal.getTimeInMillis());
      }

      train.calculeCirculationFromJoursCirculation();
      return train;
   }

   /**
    * @author ismael.yahiani récupére le train référencé dans le catalogue à partir de la SSIM
    */

   @Override
   public Train getTrainSSIMRestreint(Train trainCatalogue) {

      Circulation circulation = null;
      Train train = new Train();
      ArrayList<Integer> restrictionTrafic = new ArrayList<>();
      for (Circulation circulSSIM : this.getCirculations()) {

         if (compNumTrain(circulSSIM, trainCatalogue.listeNumeros)) {
            train.listeNumeros.clear();
            train.listeNumeros.addAll(trainCatalogue.listeNumeros);
            int rangTroncan = circulSSIM.getRangTranson();
            if (circulation != null && rangTroncan == 1) {
               circulation = null;
               restrictionTrafic.clear();
            }
            if (circulSSIM.getOrigine().equalsIgnoreCase(trainCatalogue.getGareOrigine()) && circulation == null) {
               circulation = new Circulation();
               circulation.setHeureDepart(circulSSIM.getHeureDepart());
               circulation.setDateFin(circulSSIM.getDateFin());
               circulation.setDateDebut(circulSSIM.getDateDebut());
               circulation.setOrigine(circulSSIM.getOrigine());
               circulation.setJoursCirculation(circulSSIM.getJoursCirculation());
               circulation.setRestrictionTrafic(circulSSIM.getRestrictionTrafic());
               circulation.setNumeroTrain(circulSSIM.getNumeroTrain());
               circulation.setGMTDepart(circulSSIM.getGMTDepart());
            }
            if (circulation != null && circulation.getRestrictionTrafic().contains("A"))
               for (int i = 0; i < circulation.getRestrictionTrafic().length(); i++) {
                  if (circulation.getRestrictionTrafic().charAt(i) == 'A')
                     restrictionTrafic.add(i + 1);
               }
            if (circulSSIM.getDestination().equalsIgnoreCase(trainCatalogue.getGareDestination()) && circulation != null) {
               circulation.setDestination(circulSSIM.getDestination());
               circulation.setHeureArrivee(circulSSIM.getHeureArrivee());
               circulation.setRangTranson(circulSSIM.getRangTranson());
               circulation.setGMTArrivee(circulSSIM.getGMTArrivee());
               // circulation.setJoursCirculation(circulSSIM.getJoursCirculation());
               // /////////////////////////////////////////////////////////////
               // TESTER SI LA DESCENTE est Intedite ou pas
               // /// charger la liste des Gare interdite à la descente

               if (!restrictionTrafic.contains(circulation.getRangTranson())) {
                  train.addCirculation(circulation);
                  restrictionTrafic.clear();
               }

               circulation = null;
            }

            /*  */
         }

      }
      // if (train.getListeCirculations().size()==0) train = null ;
      return train;
   }

   /**
    * 
    * @param List
    *           des Points d'arrets ( Gares )
    * 
    *           verfie si l'heure de départ du train n'est pas antérieure ( ou Postérieur ) aux heures d'ouverture ( ou fermeture ) des guichets
    */
   public void adaptGuichet(List<PointArret> pa) {

      Calendar calendarTrain = Calendar.getInstance();
      Calendar calendarGuichet = Calendar.getInstance();

      for (Entry<Date, JourCirculation> entry : this.listeJoursCirculation.entrySet()) {
         calendarTrain.setTime(entry.getKey());
         for (PointArret pArret : pa) {
            if (entry.getValue().getOrigine().equalsIgnoreCase(pArret.getCodeResarail())) {

               for (Guichet guichet : pArret.getGuichet()) {
                  if (guichet.getJour().equalsIgnoreCase("Monday") && calendarTrain.get(Calendar.DAY_OF_WEEK) == 2) {
                     if (entry.getValue().getHeureDepart() < Integer.valueOf(guichet.getHeureOuverture()) || (entry.getValue().getHeureDepart() > Integer.valueOf(guichet.getHeureFermeture())))
                        entry.getValue().setFlagCirculation(false);
                  }
                  ;
                  if (guichet.getJour().equalsIgnoreCase("Tuesday") && calendarTrain.get(Calendar.DAY_OF_WEEK) == 3) {
                     if (entry.getValue().getHeureDepart() < Integer.valueOf(guichet.getHeureOuverture()) || (entry.getValue().getHeureDepart() > Integer.valueOf(guichet.getHeureFermeture())))
                        entry.getValue().setFlagCirculation(false);
                  }
                  ;
                  if (guichet.getJour().equalsIgnoreCase("Wednesday") && calendarTrain.get(Calendar.DAY_OF_WEEK) == 4) {
                     if (entry.getValue().getHeureDepart() < Integer.valueOf(guichet.getHeureOuverture()) || (entry.getValue().getHeureDepart() > Integer.valueOf(guichet.getHeureFermeture())))
                        entry.getValue().setFlagCirculation(false);
                  }
                  ;
                  if (guichet.getJour().equalsIgnoreCase("Thursday") && calendarTrain.get(Calendar.DAY_OF_WEEK) == 5) {
                     if (entry.getValue().getHeureDepart() < Integer.valueOf(guichet.getHeureOuverture()) || (entry.getValue().getHeureDepart() > Integer.valueOf(guichet.getHeureFermeture())))
                        entry.getValue().setFlagCirculation(false);
                  }
                  ;
                  if (guichet.getJour().equalsIgnoreCase("Friday") && calendarTrain.get(Calendar.DAY_OF_WEEK) == 6) {
                     if (entry.getValue().getHeureDepart() < Integer.valueOf(guichet.getHeureOuverture()) || (entry.getValue().getHeureDepart() > Integer.valueOf(guichet.getHeureFermeture())))
                        entry.getValue().setFlagCirculation(false);
                  }
                  ;
                  if (guichet.getJour().equalsIgnoreCase("Saturday") && calendarTrain.get(Calendar.DAY_OF_WEEK) == 7) {
                     if (entry.getValue().getHeureDepart() < Integer.valueOf(guichet.getHeureOuverture()) || (entry.getValue().getHeureDepart() > Integer.valueOf(guichet.getHeureFermeture())))
                        entry.getValue().setFlagCirculation(false);
                  }
                  ;
                  if (guichet.getJour().equalsIgnoreCase("Sunday") && calendarTrain.get(Calendar.DAY_OF_WEEK) == 1) {
                     if (entry.getValue().getHeureDepart() < Integer.valueOf(guichet.getHeureOuverture()) || (entry.getValue().getHeureDepart() > Integer.valueOf(guichet.getHeureFermeture())))
                        entry.getValue().setFlagCirculation(false);
                  }
                  ;
               }
            }
         }
      }
   }

   /**
    * 
    * @return List<Circulation>
    * @param pas
    *           de param construit les periodes de circulation des trains à partir des jours de circulation, En 4 phases 1 - regroupe les circulations ayants des heures d'arriver/départ similaire 2 - regouper le resultat de "1" par type de jour de circulation 3 - calculer les periodes de circulation
    *           par Type de jour 4 - calculer les nouvelles periodes en fusionnant les periodes Mono-Jours vers Periodes Multio-Jours
    */

   public List<List<Circulation>> getPeriodes() {
      // /////////////////////////////////////////////////////////////
      // Declaration Variables

      Map<Date, JourCirculation> jcTemp = new LinkedHashMap<>();
      List<JourCirculation> jourCir = new ArrayList<>();
      List<JourCirculation> jourCir2 = new ArrayList<>();
      Calendar calendar = Calendar.getInstance();
      Set<Integer> jours = new HashSet<>();
      Map<Integer, List<JourCirculation>> joursGrouper = new TreeMap<>();
      Set<String> tempDates = new TreeSet<>();

      // /////////////////////////////////////////////////////////////////////////////////
      // prendre que les jours de circulation ou ca circule : Flag = C

      for (Entry<Date, JourCirculation> jc : this.getListeJoursCirculation().entrySet()) {
         if (jc.getValue().isFlagCirculation()) {
            jcTemp.put(jc.getKey(), jc.getValue());
         }
      }

      // extraire les differents heures d'arrivés/Départ existant

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

      // ////////////// regrouper les circules par heure depart et Arriver

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

      // ////////// extraire les differents jours existant

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
               // //////////////// Construction de la chaine de caractere avec
               // Heure de
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

      // ///////////////////////////////// calcule des periodes : Periodes
      // Mono-Jours ( par type de jour de circulation )

      int diff;

      Calendar dt_db = Calendar.getInstance(), compt = Calendar.getInstance(), dt_fin = Calendar.getInstance();

      Map<String, JourCirculation> maPeriode = new LinkedHashMap<>();
      List<Circulation> resultatPeriodeMonoJour = new ArrayList<Circulation>();
      int flag = 0;
      Circulation circul = new Circulation();

      for (Entry<String, Map<Integer, List<JourCirculation>>> res : resultat.entrySet()) {

         for (Entry<Integer, List<JourCirculation>> joursCircul : res.getValue().entrySet()) {
            circul = new Circulation();
            dt_db.setTime(joursCircul.getValue().get(0).getDateCircul());
            dt_fin.setTime(joursCircul.getValue().get(0).getDateCircul());

            if (joursCircul.getValue().size() > 0)

               for (int x = 0; x < joursCircul.getValue().size(); x++) {//

                  compt.setTime(joursCircul.getValue().get(x).getDateCircul());
                  flag = x;
                  diff = compt.get(Calendar.DAY_OF_YEAR) - dt_fin.get(Calendar.DAY_OF_YEAR);
                  if (diff <= 7) {
                     dt_fin.setTime(compt.getTime());
                  }

                  if (diff > 7) {
                     circul = new Circulation();
                     circul.setDateDebut(dt_db.getTime());
                     circul.setDateFin(dt_fin.getTime());
                     circul.setOrigine(joursCircul.getValue().get(x).getOrigine());
                     circul.setDestination(joursCircul.getValue().get(x).getDestination());
                     circul.setHeureDepart(joursCircul.getValue().get(x).getHeureDepart());
                     circul.setHeureArrivee(joursCircul.getValue().get(x).getHeureArrivee());
                     if (dt_db.get(Calendar.DAY_OF_WEEK) == 1)
                        circul.setJoursCirculation("7");
                     if (dt_db.get(Calendar.DAY_OF_WEEK) != 1)
                        circul.setJoursCirculation(String.valueOf(dt_db.get(Calendar.DAY_OF_WEEK) - 1));
                     resultatPeriodeMonoJour.add(circul);
                     dt_db.setTime(compt.getTime());
                     dt_fin.setTime(compt.getTime());
                  }
               }
            if (flag < joursCircul.getValue().size()) {
               circul = new Circulation();
               circul.setDateDebut(dt_db.getTime());
               circul.setDateFin(dt_fin.getTime());
               circul.setOrigine(joursCircul.getValue().get(flag).getOrigine());
               circul.setDestination(joursCircul.getValue().get(flag).getDestination());
               circul.setHeureDepart(joursCircul.getValue().get(flag).getHeureDepart());
               circul.setHeureArrivee(joursCircul.getValue().get(flag).getHeureArrivee());

               if (dt_db.get(Calendar.DAY_OF_WEEK) == 1)
                  circul.setJoursCirculation("7");
               if (dt_db.get(Calendar.DAY_OF_WEEK) != 1)
                  circul.setJoursCirculation(String.valueOf(dt_db.get(Calendar.DAY_OF_WEEK) - 1));
               if (!resultatPeriodeMonoJour.contains(circul))
                  resultatPeriodeMonoJour.add(circul);
            }
         }
      }

      // //////////////////////// Fusion des Periodes : Periodes MultiJours

      List<List<Circulation>> listGlobal = new ArrayList<>();
      List<Circulation> list = new ArrayList<>();
      String formatedJoursCirculation;
      Circulation c = new Circulation();
      Circulation periodes;
      Circulation temp = new Circulation();
      Calendar dt_db1 = Calendar.getInstance();
      Calendar dt_db2 = Calendar.getInstance();
      Calendar dt_fin1 = Calendar.getInstance();
      Calendar dt_fin2 = Calendar.getInstance();

      for (int i = 0; i < resultatPeriodeMonoJour.size(); ++i) {
         list = new ArrayList<>();
         c = resultatPeriodeMonoJour.get(0);
         if (resultatPeriodeMonoJour.size() > 1) {
            for (int a = 0; a < resultatPeriodeMonoJour.size(); a++) {
               temp = resultatPeriodeMonoJour.get(a);

               if (!temp.getJoursCirculation().equalsIgnoreCase(c.getJoursCirculation()) && temp.getHeureArrivee() == c.getHeureArrivee() && temp.getHeureDepart() == c.getHeureDepart()) {
                  periodes = new Circulation();
                  // /////////////////////////////////////// initialisation des
                  // dates de debut et de fin de la periode
                  dt_db1.setTime(c.getDateDebut());
                  dt_db2.setTime(temp.getDateDebut());
                  dt_fin1.setTime(c.getDateFin());
                  dt_fin2.setTime(temp.getDateFin());
                  // / calculer la difference en jours entre 2 circulation
                  // successives : 4 cas :

                  // /// CAS 1 :
                  if ((dt_db2.get(Calendar.DAY_OF_YEAR) - dt_db1.get(Calendar.DAY_OF_YEAR) < 7 && dt_db2.get(Calendar.DAY_OF_YEAR) - dt_db1.get(Calendar.DAY_OF_YEAR) >= 0)
                        && (dt_fin2.get(Calendar.DAY_OF_YEAR) - dt_fin1.get(Calendar.DAY_OF_YEAR) < 7 && dt_fin2.get(Calendar.DAY_OF_YEAR) - dt_fin1.get(Calendar.DAY_OF_YEAR) >= 0)

                        && !c.getJoursCirculation().contains(temp.getJoursCirculation())) {
                     periodes.setDateDebut(dt_db1.getTime());
                     periodes.setDateFin(dt_fin2.getTime());
                     periodes.setDestination(c.getDestination());
                     periodes.setOrigine(c.getOrigine());
                     periodes.setHeureDepart(c.getHeureDepart());
                     periodes.setHeureArrivee(c.getHeureArrivee());
                     periodes.setJoursCirculation(temp.getJoursCirculation() + c.getJoursCirculation());
                     list.add(periodes);
                     resultatPeriodeMonoJour.remove(a);
                     // resultatPeriodeMonoJour.remove(0);
                     resultatPeriodeMonoJour.set(0, periodes);
                     break;
                  }
                  // /////////////////////////////////////// ///// CAS 2 :
                  else if ((dt_db1.get(Calendar.DAY_OF_YEAR) - dt_db2.get(Calendar.DAY_OF_YEAR) < 7 && dt_db1.get(Calendar.DAY_OF_YEAR) - dt_db2.get(Calendar.DAY_OF_YEAR) >= 0)
                        && (dt_fin1.get(Calendar.DAY_OF_YEAR) - dt_fin2.get(Calendar.DAY_OF_YEAR) < 7 && dt_fin1.get(Calendar.DAY_OF_YEAR) - dt_fin2.get(Calendar.DAY_OF_YEAR) >= 0) && !c.getJoursCirculation().contains(temp.getJoursCirculation())) {
                     periodes.setDateDebut(dt_db2.getTime());
                     periodes.setDateFin(dt_fin1.getTime());
                     periodes.setDestination(c.getDestination());
                     periodes.setOrigine(c.getOrigine());
                     periodes.setHeureDepart(c.getHeureDepart());
                     periodes.setHeureArrivee(c.getHeureArrivee());
                     periodes.setJoursCirculation(temp.getJoursCirculation() + c.getJoursCirculation());
                     list.add(periodes);
                     resultatPeriodeMonoJour.remove(a);
                     resultatPeriodeMonoJour.set(0, periodes);
                     break;
                  }

                  // /// ///// CAS 3 :
                  else if ((dt_db1.get(Calendar.DAY_OF_YEAR) - dt_db2.get(Calendar.DAY_OF_YEAR) < 7 && dt_db1.get(Calendar.DAY_OF_YEAR) - dt_db2.get(Calendar.DAY_OF_YEAR) >= 0)
                        && (dt_fin2.get(Calendar.DAY_OF_YEAR) - dt_fin1.get(Calendar.DAY_OF_YEAR) < 7 && dt_fin2.get(Calendar.DAY_OF_YEAR) - dt_fin1.get(Calendar.DAY_OF_YEAR) >= 0) && !c.getJoursCirculation().contains(temp.getJoursCirculation())) {
                     periodes.setDateDebut(dt_db2.getTime());
                     periodes.setDateFin(dt_fin2.getTime());
                     periodes.setDestination(c.getDestination());
                     periodes.setOrigine(c.getOrigine());
                     periodes.setHeureDepart(c.getHeureDepart());
                     periodes.setHeureArrivee(c.getHeureArrivee());
                     periodes.setJoursCirculation(temp.getJoursCirculation() + c.getJoursCirculation());
                     list.add(periodes);
                     resultatPeriodeMonoJour.remove(a);

                     resultatPeriodeMonoJour.set(0, periodes);
                     break;
                  }
                  // /// CAS 4 :
                  else if ((dt_db2.get(Calendar.DAY_OF_YEAR) - dt_db1.get(Calendar.DAY_OF_YEAR) < 7 && dt_db2.get(Calendar.DAY_OF_YEAR) - dt_db1.get(Calendar.DAY_OF_YEAR) >= 0)
                        && (dt_fin1.get(Calendar.DAY_OF_YEAR) - dt_fin2.get(Calendar.DAY_OF_YEAR) < 7 && dt_fin1.get(Calendar.DAY_OF_YEAR) - dt_fin2.get(Calendar.DAY_OF_YEAR) >= 0) && !c.getJoursCirculation().contains(temp.getJoursCirculation())) {
                     periodes.setDateDebut(dt_db1.getTime());
                     periodes.setDateFin(dt_fin1.getTime());
                     periodes.setDestination(c.getDestination());
                     periodes.setOrigine(c.getOrigine());
                     periodes.setHeureDepart(c.getHeureDepart());
                     periodes.setHeureArrivee(c.getHeureArrivee());
                     periodes.setJoursCirculation(temp.getJoursCirculation() + c.getJoursCirculation());
                     list.add(periodes);
                     resultatPeriodeMonoJour.remove(a);
                     // resultatPeriodeMonoJour.remove(0);
                     resultatPeriodeMonoJour.set(0, periodes);

                     break;
                  }

               }
            }
            if (list.size() == 0) {
               list.add(c);
               listGlobal.add(list);
               resultatPeriodeMonoJour.remove(0);
            }
            i = 0;
         }
      }

      list = new ArrayList<>();
      list.add(resultatPeriodeMonoJour.get(0));

      listGlobal.add(list);

      return listGlobal;

   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();

      for (String num : this.listeNumeros)
         sb.append(num + "/");
      sb.append("\n");
      sb.append("------------- JOURS DE CIRCULATION -----------------------------\n");

      for (Map.Entry<Date, JourCirculation> entry : this.listeJoursCirculation.entrySet()) {
         if (entry.getValue().isFlagCirculation())
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

   public Date getDateDebutValidite() {
      return dateDebutValidite;
   }

   public void setDateDebutValidite(Date dateDebutValidite) {
      this.dateDebutValidite = dateDebutValidite;
   }

   public Date getDateFinValidite() {
      return dateFinValidite;
   }

   public void setDateFinValidite(Date dateFinValidite) {
      this.dateFinValidite = dateFinValidite;
   }

   public String getOoperatingFlight() {
      return ooperatingFlight;
   }

   public void setOoperatingFlight(String ooperatingFlight) {
      this.ooperatingFlight = ooperatingFlight;
   }

   public int getIdTrain() {
      return idTrain;
   }

   public void setIdTrain(int idTrain) {
      this.idTrain = idTrain;
   }

}
