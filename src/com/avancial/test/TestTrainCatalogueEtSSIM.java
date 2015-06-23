package com.avancial.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.avancial.tgvair.metier.Circulation;
import com.avancial.tgvair.metier.Train;
import com.avancial.tgvair.metier.TrainCatalogue;

public class TestTrainCatalogueEtSSIM {

	@Test
	public void compareJoursCircul() throws ParseException {

		Calendar c = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("ddmmyy");
		TrainCatalogue trainCatalogue = new TrainCatalogue();
		Train train = new Train();

		boolean adapt = false;

		trainCatalogue.setPeriodeValiditeDebut(sdf.parse("010115"));
		trainCatalogue.setPeriodeValiditeFin(sdf.parse("300715"));
		trainCatalogue.setDestination("CDG");
		trainCatalogue.setOrigine("Lile");
		trainCatalogue.setJours_Circulation_Compagnie("12346");

		Circulation cir = new Circulation();
		cir.setDateDebut(sdf.parse("030115"));
		cir.setDateFin(sdf.parse("150115"));
		cir.setOrigine("Lile");
		cir.setDestination("CDG");
		cir.setJoursCirculation("12346");
		train.addCirculation(cir);

		Date temp = trainCatalogue.getPeriodeValiditeDebut();
		Date temp2 = cir.getDateDebut();

		c.setTime(temp);
		c2.setTime(temp2);

		while (c.getTime().before(cir.getDateDebut())) {
			c.add(Calendar.DAY_OF_MONTH, 1);	
		}	

		while (c.getTime().before(cir.getDateFin())) {

			if (!cir.compareJoursCircul(trainCatalogue
					.getJours_Circulation_Compagnie())) {
				
				adapt = true;
				break;
			}

			
			c.add(Calendar.DATE, 1);
		}
		Assert.assertTrue(adapt);
	}
}
