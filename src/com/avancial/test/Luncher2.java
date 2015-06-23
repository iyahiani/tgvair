package com.avancial.test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.avancial.metier.parser.APP_enumParserSSIM;
import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.FilterSSIMTypeEnr;
import com.avancial.metier.parser.FiltreCatalogue;
import com.avancial.metier.parser.FiltreSSIMCompagnieTrain;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;
import com.avancial.reader.ReaderSSIM;
import com.avancial.socle.data.controller.dao.AbstractEntityManager;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.tgvair.DataBeans.CirculationDataBean;
import com.avancial.tgvair.metier.Circulation;
import com.avancial.tgvair.metier.ITrain;
import com.avancial.tgvair.metier.Train;
import com.avancial.tgvair.metier.TrainCatalogue;
import com.avancial.tgvair.util.ConvertDateStringToDate;
import com.avanciale.TGVAIR.DAO.CirculationDAO;

public class Luncher2 {

	public static void main(String[] args) throws IOException, ParseException {

		List<String> listCirculSSIM = new ArrayList<String>();
		List<Date> exceptions = new ArrayList<Date>();

		DateFormat df = new SimpleDateFormat("ddMMMyy", Locale.US);

		exceptions.add(df.parse("14JUL15"));
		exceptions.add(df.parse("12FEB15"));
		exceptions.add(df.parse("01MAY15"));
		String[] num_train = {"885112", "885113" };
		Circulation circul = new Circulation();

		// /////////////////////// Train Catalogue

		TrainCatalogue trainCatalogue = new TrainCatalogue();
		trainCatalogue.setNumero_Train_Cat(num_train);
		trainCatalogue.setDestination("1334");
		trainCatalogue.setException(exceptions);
		trainCatalogue.setFlight_number("");
		trainCatalogue.setNom_compagnie("AF");
		trainCatalogue.setJours_Circulation_Compagnie("12346");
		
		// Filtre Trains Catalogue
		
		IReader reader = new ReaderSSIM(
				"D:/Users/ismael.yahiani/Documents/parsOut.txt");
		IParser par = new ParserFixedLength(new FilterEncodage(
				new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(
						new FiltreCatalogue(null,
								trainCatalogue.getNumero_Train_Cat())))),
				APP_enumParserSSIM.getBegins(), APP_enumParserSSIM.getEnds(),
				APP_enumParserSSIM.getNames());

		///////////////////////////////// TRAIN ET CIRCULATION

		Train train = new Train();	
		
		List<Circulation> circulationList = new ArrayList<Circulation>();
		String s, ss;
		 
		//////////////////////////////// Tests 
		
		while ((s = reader.readLine()) != null) {
			if (!s.isEmpty()) {
				 par.parse(s);
				 System.out.println(par.getParsedResult()); 
				
			} 
			
			
		}
	}
}
