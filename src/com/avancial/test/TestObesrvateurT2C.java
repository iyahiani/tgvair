package com.avancial.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.avancial.app.business.compagnieAerienne.IObservableJoursCirculation;
import com.avancial.app.business.compagnieAerienne.IObserverJoursCirculation;
import com.avancial.app.business.compagnieAerienne.ObservableJoursCirculation;
import com.avancial.app.business.compagnieAerienne.ObserverJoursCirculation;
import com.avancial.app.business.compagnieAerienne.TrainToCompagnie;
import com.avancial.app.business.export.ExportPDTByCompagnyToSSIM7;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.resources.utils.MaxMinDates;
import com.avancial.app.resources.utils.StringToDate;

public class TestObesrvateurT2C {

   @Test
   public void testObservateurT2C() throws ParseException {
      DateFormat df = new SimpleDateFormat("");
      TrainToCompagnie tc2c = new TrainToCompagnie();
      TrainToCompagnie tc2c_2 = new TrainToCompagnie();
      Train trainSSIM = new Train();
      Train train = new Train();
      Circulation c1 = new Circulation();
      Circulation c2 = new Circulation();
      Circulation c3 = new Circulation();
      Circulation c4 = new Circulation();
      Date date_deb_SSIM = StringToDate.toDate("15MAR15");
      Date date_Fin_SSIM = StringToDate.toDate("14SEP15");
      
      c1 = TestTrain.createWithStringPeriode("01/02/2015#31/12/2015#1234567#FRMLW#FRAET#0949#1127"); // TC2C
      c2 = TestTrain.createWithStringPeriode("15/03/2015#14/06/2015#123456#FRMLW#FRAET#0950#1127"); // TSSIM
      c3 = TestTrain.createWithStringPeriode("01/01/2015#14/12/2015#1234567#FRMLW#FRAET#0949#1127"); // Train
      c4 = TestTrain.createWithStringPeriode("01/04/2015#14/09/2015#1234567#FRMLW#FRAET#0949#1127");
      c1.setGMTArrivee("+0100");
      c1.setGMTDepart("+0100");
      c1.setRangTranson(01);
      c4.setGMTArrivee("+0100");
      c4.setGMTDepart("+0100");
      c4.setRangTranson(01);
      c3.setGMTArrivee("+0100");
      c3.setGMTDepart("+0100");
      c3.setRangTranson(01);
      c2.setGMTArrivee("+0100");
      c2.setGMTDepart("+0100");
      c2.setRangTranson(01);
      
      train.addNumeroTrain("005211");
      train.addNumeroTrain("005214");
      trainSSIM.addNumeroTrain("005211"); 
      
      tc2c.addNumeroTrain("005211");
      
      tc2c.setDateDebutValidite(StringToDate.toDate("01FEB15"));
      tc2c.setDateFinValidite(StringToDate.toDate("31DEC15"))  ;
      tc2c.setCodeCompagnie("AF")   ;
      tc2c.addCirculation(c1)       ;
      tc2c.setOperatingFlight("1217");
      tc2c.setMarketingFlight("AF 4215");
      tc2c.setQuota_1er(120);
      tc2c.setQuota_2em(148);
      tc2c_2.addNumeroTrain("005214");
      tc2c_2.setDateDebutValidite(StringToDate.toDate("01MAY15"));
      tc2c_2.setDateFinValidite(StringToDate.toDate("31AUG15"))  ;
      tc2c_2.setCodeCompagnie("EM")   ;
      tc2c_2.addCirculation(c4)       ;
      tc2c_2.setOperatingFlight("9655");
      tc2c_2.setMarketingFlight("AF 9655");
      train.setDateDebutValidite(StringToDate.toDate("01JAN15"));
      train.setDateFinValidite(StringToDate.toDate("14DEC15"));
      train.addCirculation(c3)   ;
      trainSSIM.addCirculation(c2)  ;
     
      Date dateDebutService = StringToDate.toDate("02FEB15"), dateFinService = StringToDate.toDate("31DEC15"), dateExtraction =StringToDate.toDate("01APR15"); //Calendar.getInstance().getTime(); 
      List<Date> listDatesDebut = new ArrayList<Date>();
      List<Date> listDatesFin = new ArrayList<Date>();
      List<TrainToCompagnie>listTrainToCompagnie = new ArrayList<>();
      listDatesDebut.add(tc2c.getDateDebutValidite());
      listDatesDebut.add(train.getDateDebutValidite());
      listDatesDebut.add(dateExtraction);
      listDatesDebut.add(dateDebutService);
      listDatesFin.add(tc2c.getDateFinValidite());
      listDatesFin.add(train.getDateFinValidite());
      listDatesFin.add(dateFinService);
      
      //listDates.add(tc2c_2.getDateDebutValidite());
      IObservableJoursCirculation iObs    =     new ObservableJoursCirculation();
      IObserverJoursCirculation iObserver =     new ObserverJoursCirculation(tc2c, train, dateDebutService, dateFinService, dateExtraction);
      IObserverJoursCirculation iObserver2 =     new ObserverJoursCirculation(tc2c_2, train, dateDebutService, dateFinService, dateExtraction);
      iObs.addObservateur(iObserver) ;

      // Il faut enrichir la liste des observateurs
      // Il faut donc récupérer la liste des TC2C
      
      train.adapt(trainSSIM, date_deb_SSIM, date_Fin_SSIM, iObs); 
      /* 
       * faut extraire les circulations des trains des compagnies dans la periode du service  
       * */ 
      System.out.println(tc2c);
      tc2c.adaptService(MaxMinDates.getMaxDate(listDatesDebut), MaxMinDates.getMinDate(listDatesFin));
       
      tc2c.calculeCirculationFromJoursCirculation();
      // Set<TrainToCompagnie> set = is.
     
      listTrainToCompagnie.add(tc2c);//listTrainToCompagnie.add(tc2c_2);
      ExportPDTByCompagnyToSSIM7  compagnyToSSIM7 = new ExportPDTByCompagnyToSSIM7(); 
     // compagnyToSSIM7.export(listTrainToCompagnie);
   }
}
