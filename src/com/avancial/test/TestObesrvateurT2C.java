package com.avancial.test;

import java.text.ParseException;

import org.junit.Test;

import com.avancial.app.business.compagnieAerienne.TrainToCompagnie;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.circulation.Circulation;

public class TestObesrvateurT2C {

   @Test 
   
   public void testObservateurT2C() throws ParseException {
      
      TrainToCompagnie t2c_1 = new TrainToCompagnie() ;
      TrainToCompagnie t2c_2 = new TrainToCompagnie() ;
      Train train = new Train();
      Circulation c1 = new Circulation();
      Circulation c2 = new Circulation(); 
      Circulation c3 = new Circulation();
      c1 = TestTrain.createWithStringPeriode("01/01/2015#14/06/2015#1234567#FRMLW#FRAET#0949#1127"); // T1
      c2 = TestTrain.createWithStringPeriode("15/06/2015#14/12/2015#1234567#FRMLW#FRAET#1249#1428"); // T2
      c3 = TestTrain.createWithStringPeriode("01/01/2015#14/12/2015#1234567#FRMLW#FRAET#0949#1200"); // T3 
      t2c_1.addCirculation(c1)         ; 
      t2c_2.addCirculation(c2)         ; 
      train.addCirculation(c3)         ;
      t2c_1.addNumeroTrain("005211")   ;
      t2c_2.addNumeroTrain("005211")   ;
      train.addNumeroTrain("005211")   ;
      t2c_1.setCodeCompagnie("AF");
      t2c_1.remplirJoursCirculations() ;
      t2c_2.remplirJoursCirculations() ;
      train.remplirJoursCirculations() ;
   }
}
