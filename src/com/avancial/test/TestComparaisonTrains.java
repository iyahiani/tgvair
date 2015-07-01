package com.avancial.test;

import java.text.ParseException;

import org.junit.Test;

import com.avancial.tgvair.metier.Circulation;
import com.avancial.tgvair.metier.ITrain;
import com.avancial.tgvair.metier.ITrainCatalogue;
import com.avancial.tgvair.metier.Train;
import com.avancial.tgvair.metier.TrainCatalogue;

public class TestComparaisonTrains {

   @Test
   public void comparerSsimEtCatal() throws ParseException {
      Circulation circul = new Circulation();
      Circulation circul2 = new Circulation();
      Circulation circul3 = new Circulation();
      Circulation circul4 = new Circulation();
      Circulation circul5 = new Circulation() ;
      circul = TestTrain.createWithStringPeriode("01/01/2015#15/01/2015#1234567#FRLLE#FRMLW#0700#0730");
      circul2 = TestTrain.createWithStringPeriode("01/01/2015#15/01/2015#1234567#FRMLW#FRXYZ#0730#0800");
      circul4 = TestTrain.createWithStringPeriode("01/01/2015#15/01/2015#1234567#FRXYZ#FRCDG#0830#0910");
      circul3 = TestTrain.createWithStringPeriode("01/02/2015#15/03/2015#1234567#FRMLW#FRCDG#0730#0910");
      
      circul5 = TestTrain.createWithStringPeriode("01/02/2015#15/03/2015#1234 567#FRXYZ#FRCDG#0730#0910");
      
      ITrainCatalogue trainCatalogue =  new TrainCatalogue() ;
      ITrainCatalogue trainCatalogueSSIM=  new TrainCatalogue() ; 
      ITrain train = new Train() ;
      ITrain trainSSIM = new Train(); 
      trainCatalogueSSIM.addCirculation(circul5);
      trainCatalogue.addCirculation(circul3);
      trainSSIM.addCirculation(circul);
      trainSSIM.addCirculation(circul2);
      trainSSIM.addCirculation(circul4);
      train = trainSSIM.getTrainAPartirDuCatalogue(trainCatalogue);
      
   }

}
