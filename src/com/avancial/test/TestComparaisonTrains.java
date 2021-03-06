package com.avancial.test;

import java.text.ParseException;

import org.junit.Test;

import com.avancial.app.business.train.ITrain;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.business.train.circulation.Circulation;

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
      
      TrainCatalogue trainCatalogue =  new TrainCatalogue() ;
      TrainCatalogue trainCatalogueSSIM=  new TrainCatalogue() ; 
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
