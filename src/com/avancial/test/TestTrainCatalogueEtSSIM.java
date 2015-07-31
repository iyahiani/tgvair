package com.avancial.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.avancial.app.business.train.Circulation;
import com.avancial.app.business.train.Train;
import com.avancial.app.business.train.TrainCatalogue;

public class TestTrainCatalogueEtSSIM {

	@Test
	public void compareJoursCircul() throws ParseException {

		Calendar c = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("ddmmyy");
		TrainCatalogue trainCatalogue = new TrainCatalogue();
		Train train = new Train();
		List<Circulation> circulationList = new ArrayList<Circulation>(); 
		boolean adapt = false;

		
		Circulation cir = new Circulation();
		cir.setDateDebut(sdf.parse("030115"));
		cir.setDateFin(sdf.parse("150115"));
		cir.setOrigine("Lile");
		cir.setDestination("CDG");
		cir.setJoursCirculation("12346");
		circulationList.add(cir);
		trainCatalogue.addCirculation(cir); 
		
		circulationList = new ArrayList<Circulation>();
		cir = new Circulation();
		cir.setDateDebut(sdf.parse("030115"));
		cir.setDateFin(sdf.parse("150115"));
		cir.setOrigine("Lile");
		cir.setDestination("CDG");
		cir.setJoursCirculation("12346");
		train.addCirculation(cir);

		Date temp = trainCatalogue.getCirculations().get(0).getDateDebut() ;
		Date temp2 = cir.getDateDebut();
		c.setTime(temp);
		c2.setTime(temp2);

		while (c.getTime().before(cir.getDateDebut())) {
			c.add(Calendar.DAY_OF_MONTH, 1);	
		}	

		while (c.getTime().before(cir.getDateFin())) {

			if (!cir.compareCirculation(trainCatalogue
					.getCirculations().get(0))) {
				
				adapt = true;
				break;
			}

			
			c.add(Calendar.DATE, 1);
		}
		Assert.assertTrue(adapt);
	}
}
