package com.avancial.test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.FilterSSIMTypeEnr;
import com.avancial.metier.parser.FiltreSSIMCompagnieTrain;
import com.avancial.metier.parser.ParserSSIM;
import com.avancial.reader.IReader;
import com.avancial.reader.ReaderSSIM;
import com.avancial.tgvair.metier.Circulation;
import com.avancial.tgvair.metier.TGVAIRFactory;

public class Luncher {

	public static void main(String[] args) throws IOException, ParseException {

		IReader reader = new ReaderSSIM(
				"D:/Users/ismael.yahiani/Documents/SN5209.txt");
		String file, chaine;

		Map<String, String> test = new HashMap<String, String>() ; 
		ParserSSIM par = new ParserSSIM(new FilterEncodage(
				new FiltreSSIMCompagnieTrain(new FilterSSIMTypeEnr(null))));
		while ((file = reader.readLine()) != null) {
			chaine = par.parse(file);
			if (chaine.length() > 0) {
			test = par.getSSIMResult(chaine);
			System.out.println(test);
			}
		}
	}
}

// System.out.println(circulation.getChaineCircu());