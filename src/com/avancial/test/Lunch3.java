package com.avancial.test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.avancial.metier.parser.APP_enumParserSSIM;
import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.FilterSSIMTypeEnr;
import com.avancial.metier.parser.FiltreCatalogue;
import com.avancial.metier.parser.FiltreSSIMCompagnieTrain;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;
import com.avancial.reader.ReaderSSIM;
import com.avancial.tgvair.metier.Circulation;
import com.avancial.tgvair.metier.Train;
import com.avancial.tgvair.metier.TrainCatalogue;
import com.avancial.tgvair.util.ConvertDateStringToDate;

public class Lunch3 {

	public static void main(String[] args) throws IOException, ParseException {

		Train trainReference = new Train();
		Train trainSSIM = new Train();
		String[] num = { "885112", "885113" };
		Circulation circulation = new Circulation();
		List<String> CataloglistCircul = new ArrayList<String>();
		List<Circulation> list = new ArrayList<Circulation>();
		IParser par = new ParserFixedLength(new FilterEncodage(
				new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(
						new FiltreCatalogue(null, num)))),
				APP_enumParserSSIM.getBegins(), APP_enumParserSSIM.getEnds(),
				APP_enumParserSSIM.getNames());

		CataloglistCircul
				.add("3 SG00156601P13SEP1531DEC15     6  FRGNB07000700+0100  FRECE07050705+0100  TERB                                                           87  FSN885112");
		CataloglistCircul
				.add("3 SG00156602P01JAN1519MAR15     6  FRECE07060706+0100  FRHVS07120712+0100  TERB                                                           87  FSN885113");
		CataloglistCircul
				.add("3 SG00156603P20MAR1519AUG15     6  FRHVS07130713+0100  FRLEY07200720+0100  TERB                                                           87  FSN885113");
		CataloglistCircul
				.add("3 SG00156604P20AUG1512SEP15     6  FRLEY07210721+0100  FRHVV07250725+0100  TERB                                                           87  FSN885113");

		List<String> SSIMlistCircul = new ArrayList<String>();

		SSIMlistCircul
				.add("3 SG00156601P19SEP1520NOV15     6  FRGNB07000700+0100  FRECE07050705+0100  TERB                                                           87  FSN885112");
		SSIMlistCircul
				.add("3 SG00156602P13DEC1525FEB15     6  FRECE07060706+0100  FRHVS07120712+0100  TERB                                                           87  FSN885113");
		SSIMlistCircul
				.add("3 SG00156603P03SEP1518SEP15     6  FRHVS07230723+0100  FRLEY07300730+0100  TERB                                                           87  FSN885113");
		SSIMlistCircul
				.add("3 SG00156604P19JUL1519AUG15     6  FRLEY07210721+0100  FRHVV07250725+0100  TERB                                                           87  FSN885113");

		trainReference.setNumTrain(num);
		trainSSIM.setNumTrain(num);

		for (String i : CataloglistCircul) {
			par.parse(i);
			circulation.setDateDebut(ConvertDateStringToDate.toDate(par
					.getParsedResult()
					.get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT
							.name())));
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

			list.add(circulation);
			trainReference.setCirculation(list);
		}
		for (String i : SSIMlistCircul) {
			par.parse(i);
			circulation.setDateDebut(ConvertDateStringToDate.toDate(par
					.getParsedResult()
					.get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT
							.name())));
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

			list.add(circulation);			
			trainSSIM.setCirculation(list);
		}

		boolean adapte = false	;
		
		for (Circulation c : trainSSIM.getCirculations()) {
			
			for (Circulation cc : trainReference.getCirculations()) {
				
				if ((c.getDateDebut().after(cc.getDateDebut()) || c
						.getDateDebut().equals(cc.getDateDebut()))
						|| (c.getDateFin().before(cc.getDateFin()) || c
								.getDateDebut().equals(cc.getDateFin()))) {
					
					if (!cc.compareJoursCircul(c.getJoursCirculation())) {
						
						System.out.println("jours circul modfif");
						break;
					}
					if (!cc.compareDestination(c.getDestination())) {
						
						System.out.println("destination modifié");
						break;
					}
					if (!cc.compareHeureArriver(c.getHeureArrivee())) {
						
						System.out.println("heureArrive Modif");
						break;
					}
					if (!cc.compareHeuredepart(c.getHeureDepart())) {
						
						System.out.println("jours HeureDepart modif");
						break;
					}
				}
			}
		}
	}
}
