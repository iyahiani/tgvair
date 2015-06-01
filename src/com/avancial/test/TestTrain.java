package com.avancial.test;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.avancial.tgvair.Circulation;
import com.avancial.tgvair.ITrain;
import com.avancial.tgvair.ITrainFactory;
import com.avancial.tgvair.TrainFactory;


public class TestTrain {

	   
	   @Test
	   public void testTrainsIdentiques() {
		   ITrain train1 ;
		   ITrain train2; 
		   
		   Circulation circul1 = new Circulation() ; 
		   Circulation circul2 = new Circulation();
		   circul1.setDateDebut("01JAN15");
		   circul1.setDateFin("30JAN15");
		   circul1.setOrigine("CDG");
		   circul1.setDestination("MAR");
		   circul1.setHeureArrivee("1500"); 
		   circul1.setHeureDepart("1515");
		   circul1.setJoursCirculation("123");
		   
		   circul2.setDateDebut("01JAN15");
		   circul2.setDateFin("30JAN15");
		   circul2.setOrigine("CDG");
		   circul2.setDestination("MAR");
		   circul2.setHeureArrivee("1500"); 
		   circul2.setHeureDepart("1515");
		   circul2.setJoursCirculation("123");
		   
		   List<Circulation> listCircu = new ArrayList<Circulation>() ; 
		   listCircu.add(circul1); 
		   listCircu.add(circul2); 		   
		   
		   ITrainFactory factory =new TrainFactory();
		   train1=factory.createTrainByListCirculation(listCircu);
		   train2=factory.createTrainByListCirculation(listCircu);
		   Assert.assertTrue(train1.compare(train2));
		   
	   }

	   
	   @Test
	   public void testTrainsDifferents() {
		   ITrain train1 ;
		   ITrain train2; 
		   
		   Circulation circul1 = new Circulation() ; 
		   Circulation circul2 = new Circulation();
		   circul1.setDateDebut("01JAN15");
		   circul1.setDateFin("30JAN15");
		   circul1.setOrigine("CDG");
		   circul1.setDestination("MAR");
		   circul1.setHeureArrivee("1500"); 
		   circul1.setHeureDepart("1515");
		   circul1.setJoursCirculation("123");
		   
		   circul2.setDateDebut("01JAN15");
		   circul2.setDateFin("30JAN15");
		   circul2.setOrigine("CDG");
		   circul2.setDestination("MAR");
		   circul2.setHeureArrivee("1500"); 
		   circul2.setHeureDepart("1515");
		   circul2.setJoursCirculation("123");
		   
		   List<Circulation> listCircu = new ArrayList<Circulation>() ;
		   List<Circulation> listCircu2 = new ArrayList<Circulation>() ;
		   listCircu.add(circul1); 
		   listCircu.add(circul2); 		   
		   
		    circul1 = new Circulation() ; 
		    circul2 = new Circulation();
		   circul1.setDateDebut("01JAN15");
		   circul1.setDateFin("30JAN15");
		   circul1.setOrigine("CDG");
		   circul1.setDestination("MAR");
		   circul1.setHeureArrivee("1500"); 
		   circul1.setHeureDepart("1515");
		   circul1.setJoursCirculation("123");
		   
		   circul2.setDateDebut("01JAN15");
		   circul2.setDateFin("30JAN15");
		   circul2.setOrigine("CDG");
		   circul2.setDestination("MAR");
		   circul2.setHeureArrivee("1500"); 
		   circul2.setHeureDepart("1515");
		   circul2.setJoursCirculation("1234");
		   
		   listCircu2.add(circul1); 
		   listCircu2.add(circul2);
		   
		   ITrainFactory factory =new TrainFactory();
		   train1=factory.createTrainByListCirculation(listCircu);
		   train2=factory.createTrainByListCirculation(listCircu2);
		   
		  // System.out.println(train1.getChaineCompare());
		//   System.out.println(train2.getChaineCompare());
		   Assert.assertFalse(train1.compare(train2));
		   
		   
		   
	   }
	   
	   
}
