package com.avancial.metier.parser;

import com.avancial.parser.AParser;
import com.avancial.parser.IParser;

/**
 * 
 * @author ismael.yahiani 
 * cette class permet de parser un fichier SSIM en vue d'alimenté l'objet métier   
 *elle etend la classe abstraite AParser   
 */
public class ParserSSIM extends AParser {

	public ParserSSIM(IParser par) {
		super(par);
	}
	
	@Override
	public String parse(String ligne) {

		StringBuilder sb = new StringBuilder() ;
		if (!ligne.isEmpty()) {
			if (this.parser != null)
				this.resultat = parser.parse(ligne);
			if(!resultat.isEmpty()) 
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_TYPE_ENR.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_TYPE_ENR.getPositionFin()));
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_IDENTIFIANT_TRANCHE.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_IDENTIFIANT_TRANCHE.getPositionFin()));
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_RANG_TRANCON.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_RANG_TRANCON.getPositionFin()));
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_PERIODE_CIRCULATION.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_PERIODE_CIRCULATION.getPositionFin()));
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_JOURS_CIRCULATION.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_JOURS_CIRCULATION.getPositionFin()));
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_GARE_DEPART.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_GARE_DEPART.getPositionFin()));
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_HEURE_DEPART.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_HEURE_DEPART.getPositionFin()));
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_DIFFERENCE_GMT_DEPART.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_DIFFERENCE_GMT_DEPART.getPositionFin()));
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_GARE_ARRIVER.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_GARE_ARRIVER.getPositionFin()));
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_HEURE_ARRIVER.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_HEURE_ARRIVER.getPositionFin()));
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_DIFFERENCE_GMT_ARRIVER.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_DIFFERENCE_GMT_ARRIVER.getPositionFin()));
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_INDICATEUR_FER.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_INDICATEUR_FER.getPositionFin()));
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_COMPAGNIE_TRAIN.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_COMPAGNIE_TRAIN.getPositionFin()));
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_NUM_TRAIN.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_NUM_TRAIN.getPositionFin()));
		}
		return sb.toString() ;
	}
}
