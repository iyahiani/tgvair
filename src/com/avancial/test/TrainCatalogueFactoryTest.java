package com.avancial.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.business.train.circulation.Circulation;

public class TrainCatalogueFactoryTest {

   public static TrainCatalogue create(String periode) throws ParseException {

      ArrayList<String> listeNumeros = new ArrayList<>();
      listeNumeros.add("1000");

      Circulation circul = createWithStringPeriode(periode);
      ArrayList<Circulation> listeCirculations = new ArrayList<>();
      listeCirculations.add(circul);

      TrainCatalogue trainCatalogue = new TrainCatalogue(listeNumeros, listeCirculations);

      return trainCatalogue;
   }

   public static Circulation createWithStringPeriode(String periode) throws ParseException {

      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      Circulation circulation = new Circulation();
      int heureArrivee = Integer.valueOf(periode.split("#")[6]);
      int heureDepart = Integer.valueOf(periode.split("#")[5]);
      String origine = periode.split("#")[3];
      String destination = periode.split("#")[4];
      Date dateDebut = sdf.parse(periode.split("#")[0]);
      Date dateFin = sdf.parse(periode.split("#")[1]);

      circulation.setDateDebut(dateDebut);
      circulation.setDateFin(dateFin);
      circulation.setHeureDepart(heureDepart);
      circulation.setHeureArrivee(heureArrivee);
      circulation.setOrigine(origine);
      circulation.setDestination(destination);
      circulation.setJoursCirculation(periode.split("#")[2]);

      return circulation;
   }

}
