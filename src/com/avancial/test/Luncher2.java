package com.avancial.test;

import java.io.IOException;

import com.avancial.metier.parser.APP_enumParserSSIM;
import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.FilterSSIMTypeEnr;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;
import com.avancial.reader.ReaderSSIM;

public class Luncher2 {

	public static void main(String[] args) throws IOException {

		IReader reader = new ReaderSSIM(
				"D:/Users/ismael.yahiani/Documents/SN5209.txt");
		IParser par = new ParserFixedLength(new FilterEncodage(
				new FilterSSIMTypeEnr(null)), APP_enumParserSSIM.getBegins(),
				APP_enumParserSSIM.getEnds(), APP_enumParserSSIM.getNames());
		String file;
		String chaine;
		
		while ((file = reader.readLine()) != null) {
			chaine = par.parse(file);
			System.out.println(par.getParsedResult());
		}
		
		
	}

}
