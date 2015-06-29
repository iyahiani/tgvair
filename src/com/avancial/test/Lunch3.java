package com.avancial.test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.avancial.metier.parser.APP_enumParserSSIM;
import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.FilterSSIMTypeEnr;
import com.avancial.metier.parser.FiltreCatalogue;
import com.avancial.metier.parser.FiltreSSIMCompagnieTrain;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.tgvair.metier.Circulation;
import com.avancial.tgvair.metier.ITrain;
import com.avancial.tgvair.metier.Train;
import com.avancial.tgvair.metier.TrainCatalogue;
import com.avancial.tgvair.util.ConvertDateStringToDate;

public class Lunch3 {


	
	public static void main(String[] args) throws IOException, ParseException {
		TrainCatalogue trainReference = new TrainCatalogue();
		ITrain trainSSIM = new Train();
		String[] num = { "885112", "885113" };
		Circulation circulation = new Circulation();
		
		// ////////////////////////////////////////////////////////////
		
		List<String> CataloglistCircul = new ArrayList<String>();
		List<String> SSIMlistCircul = new ArrayList<String>();
		
		// ////////////////////////////////////////////////////////////
		
		List<Circulation> catalogList = new ArrayList<Circulation>();
		List<Circulation> ssimList = new ArrayList<Circulation>();

		IParser par = new ParserFixedLength(new FilterEncodage(
				new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(
						new FiltreCatalogue(null, num)))),
				APP_enumParserSSIM.getBegins(), APP_enumParserSSIM.getEnds(),
				APP_enumParserSSIM.getNames());

		CataloglistCircul
				.add("3 SG00156601P01SEP1531DEC15     6  FRGNB07000700+0100  FRECE07050705+0100  TERB                                                           87  FSN885112");
		CataloglistCircul
				.add("3 SG00156602P01JAN1530JAN15     6  FRECE07060706+0100  FRHVS07120712+0100  TERB                                                           87  FSN885113");
		CataloglistCircul
				.add("3 SG00156603P03JUL1531AUG15     6  FRHVS07130713+0100  FRLEY07200720+0100  TERB                                                           87  FSN885113");
		CataloglistCircul
				.add("3 SG00156603P01FEB1530APR15     6  FRHVS07130713+0100  FRLEY07200720+0100  TERB                                                           87  FSN885113");
		
		SSIMlistCircul
				.add("3 SG00156601P19SEP1520NOV15     6  FRGNB07100710+0100  FRECE07050705+0100  TERB                                                           87  FSN885112");
		SSIMlistCircul
				.add("3 SG00156604P19JUL1519AUG15    56  FRLEY07210721+0100  FRHVV07250725+0100  TERB                                                           87  FSN885113");
		SSIMlistCircul
				.add("3 SG00156604P02JAN1502FEB15     6  FRLEY07060706+0100  FRHVV07200720+0100  TERB                                                           87  FSN885113");
		
		
		for (String i : CataloglistCircul) {
			
			par.parse(i);
			circulation = new Circulation();
			circulation.setDateDebut(ConvertDateStringToDate.toDate(par
					.getParsedResult()
					.get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT
							.name())));

			circulation.setPeriode(par.getParsedResult().get(
					APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT
							.name())
					+ par.getParsedResult().get(
							APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_FIN
									.name()));

			circulation.setDateFin(ConvertDateStringToDate.toDate(par
					.getParsedResult().get(
							APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_FIN
									.name())));

			circulation.setJoursCirculation(par.getParsedResult().get(
					APP_enumParserSSIM.POSITION_JOURS_CIRCULATION.name()));

			circulation.setHeureArrivee(Integer.valueOf(par.getParsedResult()
					.get(APP_enumParserSSIM.POSITION_HEURE_ARRIVER.name())));

			circulation.setHeureDepart(Integer.valueOf(par.getParsedResult()
					.get(APP_enumParserSSIM.POSITION_HEURE_DEPART.name())));
			
			circulation.setDestination(par.getParsedResult().get(
					APP_enumParserSSIM.POSITION_GARE_ARRIVER.name()));

			catalogList.add(circulation);
		}
		trainReference.setCirculations(catalogList);
		trainReference.setFlight_number("AF7257");
		
		
		for (String j : SSIMlistCircul) {

			par.parse(j);
			circulation = new Circulation();

			circulation.setDateDebut(ConvertDateStringToDate.toDate(par
					.getParsedResult()
					.get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT
							.name())));
			circulation.setDateFin(ConvertDateStringToDate.toDate(par
					.getParsedResult().get(
							APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_FIN
									.name())));
			circulation.setPeriode(par.getParsedResult().get(
					APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT
							.name())
					+ par.getParsedResult().get(
							APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_FIN
									.name()));
			circulation.setJoursCirculation(par.getParsedResult().get(
					APP_enumParserSSIM.POSITION_JOURS_CIRCULATION.name()));

			circulation.setHeureArrivee(Integer.valueOf(par.getParsedResult()
					.get(APP_enumParserSSIM.POSITION_HEURE_ARRIVER.name())));

			circulation.setHeureDepart(Integer.valueOf(par.getParsedResult()
					.get(APP_enumParserSSIM.POSITION_HEURE_DEPART.name())));

			circulation.setDestination(par.getParsedResult().get(
					APP_enumParserSSIM.POSITION_GARE_ARRIVER.name()));
			
			circulation.setOrigine(par.getParsedResult().get(
					APP_enumParserSSIM.POSITION_GARE_DEPART.name()));
			ssimList.add(circulation);
		}

		trainSSIM.setCirculation(ssimList);
		
		for (Circulation ssimcircul : trainSSIM.getCirculations()) {

			for (Circulation catalCircul : trainReference.getCirculations()) {

				if (ssimcircul.compare(catalCircul)) 
					System.out.println(ssimcircul.getChaineCircu())	;	 
					
				}
			}
		}
	}

