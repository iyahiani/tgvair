package com.avancial.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.avancial.metier.parser.APP_enumParserSSIM;
import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.FilterSSIMTypeEnr;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.tgvair.metier.Circulation;
import com.avancial.tgvair.metier.ITrain;
import com.avancial.tgvair.metier.ITrainFactory;
import com.avancial.tgvair.metier.TrainCatalogue;
import com.avancial.tgvair.metier.TrainFactory;

public class TestTrain {

	// @Test
	public void testTrainsIdentiques() {
		ITrain train1;
		ITrain train2;

		Circulation circul1 = new Circulation();
		Circulation circul2 = new Circulation();
		circul1.setDateDebut(new Date("01JAN15"));
		circul1.setDateFin(new Date("30JAN15"));
		circul1.setOrigine("CDG");
		circul1.setDestination("MAR");
		circul1.setHeureArrivee(1500);
		circul1.setHeureDepart(1515);
		circul1.setJoursCirculation("123");

		circul2.setDateDebut(new Date("01JAN15"));
		circul2.setDateFin(new Date("30JAN15"));
		circul2.setOrigine("CDG");
		circul2.setDestination("MAR");
		circul2.setHeureArrivee(1500);
		circul2.setHeureDepart(1515);
		circul2.setJoursCirculation("123");

		List<Circulation> listCircu = new ArrayList<Circulation>();
		listCircu.add(circul1);
		listCircu.add(circul2);

		ITrainFactory factory = new TrainFactory();
		train1 = factory.createTrainByListCirculation(listCircu);
		train2 = factory.createTrainByListCirculation(listCircu);
		Assert.assertTrue(train1.compare(train2));
	}

	// @Test
	@SuppressWarnings("deprecation")
	public void testTrainsDifferents() {
		ITrain train1;
		ITrain train2;

		Circulation circul1 = new Circulation();
		Circulation circul2 = new Circulation();
		circul1.setDateDebut(new Date("01JAN15"));
		circul1.setDateFin(new Date("30JAN15"));
		circul1.setOrigine("CDG");
		circul1.setDestination("MAR");
		circul1.setHeureArrivee(1500);
		circul1.setHeureDepart(1515);
		circul1.setJoursCirculation("123");

		circul2.setDateDebut(new Date("01JAN15"));
		circul2.setDateFin(new Date("30JAN15"));
		circul2.setOrigine("CDG");
		circul2.setDestination("MAR");
		circul2.setHeureArrivee(1500);
		circul2.setHeureDepart(1515);
		circul2.setJoursCirculation("123");

		List<Circulation> listCircu = new ArrayList<Circulation>();
		List<Circulation> listCircu2 = new ArrayList<Circulation>();
		listCircu.add(circul1);
		listCircu.add(circul2);

		circul1 = new Circulation();
		circul2 = new Circulation();
		circul1.setDateDebut(new Date("01JAN15"));
		circul1.setDateFin(new Date("30JAN15"));
		circul1.setOrigine("CDG");
		circul1.setDestination("MAR");
		circul1.setHeureArrivee(1500);
		circul1.setHeureDepart(1515);
		circul1.setJoursCirculation("123");

		circul2.setDateDebut(new Date("01JAN15"));
		circul2.setDateFin(new Date("30JAN15"));
		circul2.setOrigine("CDG");
		circul2.setDestination("MAR");
		circul2.setHeureArrivee(1500);
		circul2.setHeureDepart(1515);
		circul2.setJoursCirculation("1234");

		listCircu2.add(circul1);
		listCircu2.add(circul2);

		ITrainFactory factory = new TrainFactory();
		train1 = factory.createTrainByListCirculation(listCircu);
		train2 = factory.createTrainByListCirculation(listCircu2);

		// System.out.println(train1.getChaineCompare());
		// System.out.println(train2.getChaineCompare());
		Assert.assertFalse(train1.compare(train2));

	}

	@Test
	public void testTrainCatalogue() {

		List<String> listCircul = new ArrayList<String>();

		listCircul
				.add("3 SG00156601P19SEP1519SEP15     6  FRGNB07000700+0100  FRECE07050705+0100  TERB                                                           87  FSN885112");
		listCircul
				.add("3 SG00156602P19SEP1519SEP15     6  FRECE07060706+0100  FRHVS07120712+0100  TERB                                                           87  FSN885113");
		listCircul
				.add("3 SG00156603P19SEP1519SEP15     6  FRHVS07130713+0100  FRLEY07200720+0100  TERB                                                           87  FSN885113");
		listCircul
				.add("3 SG00156604P19SEP1519SEP15     6  FRLEY07210721+0100  FRHVV07250725+0100  TERB                                                           87  FSN885113");
		listCircul
				.add("3 SG00156605P19SEP1519SEP15     6  FRHVV07260726+0100  FRHVX07340734+0100  TERB                                                           87  FSN885112");

		int[] num_train = { 885112, 885113 };
		List<Date> exceptions  = new ArrayList<Date>() ;
		exceptions.add(new Date(121214)) ;
		exceptions.add(new Date(120115)) ;
		exceptions.add(new Date(120215)) ;
		exceptions.add(new Date(120315)) ;
		TrainCatalogue trainCatalogue = new TrainCatalogue();
		trainCatalogue.setNumero_Train_Cat(num_train);
		trainCatalogue.setDepart("1334");
		trainCatalogue.setException(exceptions);
		trainCatalogue.getFlight_number();
		trainCatalogue.setNom_compagnie("AF");
		trainCatalogue.setJours_Circulation_Compagnie("12346");

		IParser parser = new ParserFixedLength((null),
				APP_enumParserSSIM.getBegins(), APP_enumParserSSIM.getEnds(),
				APP_enumParserSSIM.getNames());

		for (Integer i : trainCatalogue.getNumero_Train_Cat()) {

			for (String s : listCircul)
				Assert.assertEquals(i,
						Integer.valueOf(s.substring(
								APP_enumParserSSIM.POSITION_NUM_TRAIN
										.getPositionDebut(),
								APP_enumParserSSIM.POSITION_NUM_TRAIN
										.getPositionFin())));
		}
	}
}
