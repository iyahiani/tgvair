package com.avancial.tgvair.metier;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.avancial.metier.parser.TGVAIR_enumParserCirculation;

/**
 * 
 * @author ismael.yahiani 
 *  cette class permet d'insatancié l'ensemble des objets métier 
 */
public class TGVAIRFactory {

	public List<Circulation> getCirculList() {

		List<Circulation> listCircul = new ArrayList<Circulation>();
		
		return listCircul;
	}

	
	public Circulation getCirculation(String chaine) throws ParseException {

		Circulation circul = new Circulation();

		if (chaine.length() > 0) {

			circul.setDateDebut(ParsingDateCirculSSIM.toDate(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_DATE_DEBUT_CIRCU
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_DATE_DEBUT_CIRCU
							.getPostionFin())));
			
			circul.setDateFin(ParsingDateCirculSSIM.toDate(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_DATE_FIN_CIRCU
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_DATE_FIN_CIRCU
							.getPostionFin())));
			
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
			
			circul.setHeureDepart(Integer.parseInt(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_HEURE_DEPART
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_HEURE_DEPART
							.getPostionFin())));
			
			circul.setHeureArrivee(Integer.valueOf(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_HEURE_ARRIVER
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_HEURE_ARRIVER
							.getPostionFin())));
			
			circul.setJoursCirculation(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_JOURS_CIRCULATION
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_JOURS_CIRCULATION
							.getPostionFin()));
			
			circul.setIndicateurFer(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_INDICATEUR_FER
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_INDICATEUR_FER
							.getPostionFin()));
			
			circul.setCompagnieTrain(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_COMPAGNIE_TRAIN
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_COMPAGNIE_TRAIN
							.getPostionFin()));
			
			circul.setNumeroTrain(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_NUMERO_TRAIN
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_NUMERO_TRAIN
							.getPostionFin()));
		}

		return circul;
	} 
	
	public Train getTrain(ArrayList<Circulation> listCircul) {
				
		Train train = new Train()  ;
		String temp ; 
		for (Circulation circulation : listCircul) {
			
		}
		train.setCirculation(listCircul);
		return train ;
	}
	

}
