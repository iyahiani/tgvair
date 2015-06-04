package com.avancial.tgvair.metier;

import java.io.IOException;

import com.avancial.metier.parser.TGVAIR_enumParserCirculation;

public class CirculationDAO {

	private Circulation circul;

	public CirculationDAO(Circulation circulation) {
		this.circul = circulation;
	}

	public Circulation getCirculation(String chaine) throws IOException {

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
		}

		return this.circul;
	}
	
	public Circulation findCircul() {
		return null ;
	}
	
	
}
