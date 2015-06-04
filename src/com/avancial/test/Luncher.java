package com.avancial.test;

import java.io.IOException;

import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.FilterSSIMTypeEnr;
import com.avancial.metier.parser.ParserSSIM;
import com.avancial.metier.parser.TGVAIR_enumParserCirculation;
import com.avancial.reader.IReader;
import com.avancial.reader.ReaderSSIM;
import com.avancial.tgvair.metier.Circulation;
import com.avancial.tgvair.metier.CirculationDAO;

public class Luncher {

	public static void main(String[] args) throws IOException {
		/*
		 * IReader reader = new ReaderSSIM() ; String line =
		 * reader.readLine("D:/Users/ismael.yahiani/Documents/SN5209") ;
		 * ParserSSIM parsSSim = new ParserSSIM(new FilterSSIM(new
		 * FilterEncodage(null))); String
		 * chaine=parsSSim.parse("je suis une chaine.") ;
		 */
		// IReader read = new
		// ReaderSSIM("D:/Users/ismael.yahiani/Documents/SN5209.txt") ;

		IReader read = new ReaderSSIM(
				"D:/Users/ismael.yahiani/Documents/SN5209.txt");
		ParserSSIM par = new ParserSSIM(new FilterSSIMTypeEnr(
				new FilterEncodage(null)));
		String file;
		Circulation circul = new Circulation();
		while ((file = read.readLine()) != null) {

			String chaine = par.parse(file);
			// CirculationDAO circDAO = new CirculationDAO(circulation) ;
			// circDAO.getCirculation() ;
			// if (file.length()>0) System.out.println(file.substring(25,32));
			// System.out.println(circulation.getChaineCircu() );
			if (chaine.length() > 0) {
				circul.setDateDebut(chaine.substring(
						TGVAIR_enumParserCirculation.POSITION_DATE_DEBUT_CIRCU
								.getPostionDebut(),
						TGVAIR_enumParserCirculation.POSITION_DATE_DEBUT_CIRCU
								.getPostionFin()));
				circul.setDateFin(chaine.substring(
						TGVAIR_enumParserCirculation.POSITION_DATE_FIN_CIRCU
								.getPostionDebut(),
						TGVAIR_enumParserCirculation.POSITION_DATE_FIN_CIRCU
								.getPostionFin()));
				circul.setDestination(chaine.substring(
						TGVAIR_enumParserCirculation.POSITION_DESTINATION
								.getPostionDebut(),
						TGVAIR_enumParserCirculation.POSITION_DESTINATION
								.getPostionFin()));
				circul.setOrigine(chaine.substring(
						TGVAIR_enumParserCirculation.POSITION_ORGINE
								.getPostionDebut(),
						TGVAIR_enumParserCirculation.POSITION_ORGINE
								.getPostionFin()));
				circul.setHeureDepart(chaine.substring(
						TGVAIR_enumParserCirculation.POSITION_HEURE_DEPART
								.getPostionDebut(),
						TGVAIR_enumParserCirculation.POSITION_HEURE_DEPART
								.getPostionFin()));
				circul.setHeureArrivee(chaine.substring(
						TGVAIR_enumParserCirculation.POSITION_HEURE_ARRIVER
								.getPostionDebut(),
						TGVAIR_enumParserCirculation.POSITION_HEURE_ARRIVER
								.getPostionFin()));
				circul.setJoursCirculation(chaine.substring(
						TGVAIR_enumParserCirculation.POSITION_JOURS_CIRCULATION
								.getPostionDebut(),
						TGVAIR_enumParserCirculation.POSITION_JOURS_CIRCULATION
								.getPostionFin()));
				System.out.println(circul.getChaineCircu());
			}
		}

	}

}
