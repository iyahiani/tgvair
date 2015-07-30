package com.avancial.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.circulation.Circulation;

public class TrainFactoryTest {

   /**
    * Crée un train SSIM factice qui circule entre 4 gares AAA,BBB,CCC,DDD
    * 
    * @return
    * @throws ParseException
    */
   public static Train createTrainSSIM() throws ParseException {

      Circulation circul1 = createWithStringPeriode("01/01/2015#15/01/2015#1234567#FRAAA#FRBBBW#0700#0730");
      Circulation circul2 = createWithStringPeriode("01/01/2015#15/01/2015#1234567#FRBBB#FRCCC#0735#0845");
      Circulation circul3 = createWithStringPeriode("01/01/2015#15/01/2015#1234567#FRCCC#FRDDD#0850#0950");
      List<Circulation> listeCirculation = new ArrayList<>();
      listeCirculation.add(circul1);
      listeCirculation.add(circul2);
      listeCirculation.add(circul3);

      List<String> listeNumero = new ArrayList<>();
      listeNumero.add("1000");
      Train train = new Train(listeNumero, listeCirculation);

      return train;
   }

   public static Circulation createWithStringPeriode(String periode) throws ParseException {

      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      Circulation circulation = new Circulation();
      int heureArrivee = Integer.valueOf(periode.split("#")[6]);
      int heureDepart = Integer.valueOf(periode.split("#")[5]);
      String origine = periode.split("#")[3];
      String destination = periode.split("#")[4];
      Date dateCircul = sdf.parse(periode.split("#")[0]);
      // JourCirculation joursCircul = new JourCirculation(dateCircul, heureDepart, heureArrivee, origine, destination, true);
      // circulation.setJourCirculation(joursCircul);

      circulation.setDateDebut(sdf.parse(periode.split("#")[0]));
      circulation.setDateFin(sdf.parse(periode.split("#")[1]));
      circulation.setJoursCirculation(periode.split("#")[2]);

      return circulation;
   }

}
