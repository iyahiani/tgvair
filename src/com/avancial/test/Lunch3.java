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
		List<String> listCircul = new ArrayList<String>();
		List<Circulation> list = new ArrayList<Circulation>();
		IParser par = new ParserFixedLength(new FilterEncodage(
				new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(
						new FiltreCatalogue(null, num)))),
				APP_enumParserSSIM.getBegins(), APP_enumParserSSIM.getEnds(),
				APP_enumParserSSIM.getNames());
		listCircul
				.add("3 SG00156601P13SEP1531DEC15     6  FRGNB07000700+0100  FRECE07050705+0100  TERB                                                           87  FSN885112");
		listCircul
				.add("3 SG00156602P01JAN1519MAR15     6  FRECE07060706+0100  FRHVS07120712+0100  TERB                                                           87  FSN885113");
		listCircul
				.add("3 SG00156603P20MAR1519AUG15     6  FRHVS07130713+0100  FRLEY07200720+0100  TERB                                                           87  FSN885113");
		listCircul
				.add("3 SG00156604P20AUG1512SEP15     6  FRLEY07210721+0100  FRHVV07250725+0100  TERB                                                           87  FSN885113");

		List<String> listCircul2 = new ArrayList<String>();

		listCircul2
				.add("3 SG00156601P19SEP1520NOV15    56  FRGNB07000700+0100  FRECE07050705+0100  TERB                                                           87  FSN885112");
		listCircul2
				.add("3 SG00156602P02JAN1525FEB15    56  FRECE07060706+0100  FRHVS07120712+0100  TERB                                                           87  FSN885113");
		listCircul2
				.add("3 SG00156603P03SEP1520OCT15    56  FRHVS07130713+0100  FRLEY07200720+0100  TERB                                                           87  FSN885113");
		listCircul2
				.add("3 SG00156604P19JUL1519AUG15    56  FRLEY07210721+0100  FRHVV07250725+0100  TERB                                                           87  FSN885113");
		listCircul2
				.add("3 SG00156605P19SEP1503DEC15    56  FRHVV07260726+0100  FRHVX07340734+0100  TERB                                                           87  FSN885112");

		trainReference.setNumTrain(num);
		trainSSIM.setNumTrain(num);

		for (String i : listCircul) {
			par.parse(i);
			circulation.setDateDebut(ConvertDateStringToDate.toDate(par
					.getParsedResult()
					.get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT
							.name()))); 
			circulation.setDateFin(ConvertDateStringToDate.toDate(par
					.getParsedResult()
					.get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_FIN
							.name()))); 
			circulation.setJoursCirculation(par
					.getParsedResult()
					.get(APP_enumParserSSIM.POSITION_JOURS_CIRCULATION.name())); 
			
		list.add(circulation); 
		trainReference.setCirculation(list);
		} 
		for (String i : listCircul2) {
			par.parse(i);
			circulation.setDateDebut(ConvertDateStringToDate.toDate(par
					.getParsedResult()
					.get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT
							.name()))); 
			circulation.setDateFin(ConvertDateStringToDate.toDate(par
					.getParsedResult()
					.get(APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_FIN
							.name()))); 
			circulation.setJoursCirculation(par
					.getParsedResult()
					.get(APP_enumParserSSIM.POSITION_JOURS_CIRCULATION.name())); 
			
		list.add(circulation); 
		trainSSIM.setCirculation(list);
		}  
		
		
	}
}
