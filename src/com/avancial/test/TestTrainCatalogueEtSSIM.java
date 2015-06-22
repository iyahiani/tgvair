package com.avancial.test;

import org.junit.Test;

import com.avancial.tgvair.metier.Circulation;
import com.avancial.tgvair.metier.Train;
import com.avancial.tgvair.metier.TrainCatalogue;

public class TestTrainCatalogueEtSSIM {

	@Test 
	
	public void compareJoursCircul() {
		TrainCatalogue trainCatalogue = new TrainCatalogue() ;
		Train train = new Train() ; 
		
		trainCatalogue.setPeriodeValidité("01JAN1530JUL15"); 
		trainCatalogue.setDestination("CDG");
		trainCatalogue.setOrigine("Lile") ; 
		trainCatalogue.setJours_Circulation_Compagnie("12346"); 
		
		Circulation cir = new  Circulation() ; 
		cir.setPeriode("03JAN1515JAN15");
		cir.setOrigine("Lile");
		cir.setDestination("CDG");
		cir.setJoursCirculation("1234");
		
	}
}
