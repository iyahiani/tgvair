package com.avancial.app.business.parser;

import java.util.HashMap;
import java.util.Map;

import com.avancial.parser.AParser;
import com.avancial.parser.IParser;

/**
 * 
 * @author ismael.yahiani cette class permet de parser un fichier SSIM en vue
 *         d'alimenté l'objet métier elle etend la classe abstraite AParser
 */
public class ParserSSIM extends AParser {

	public ParserSSIM(IParser par) {
		super(par);
	}

	/**
	 * @author ismael.yahiani
	 * @return String
	 * @param String
	 *            : la ligne a parser
	 */
	@Override
	public String parse(String ligne) {

		StringBuilder sb = new StringBuilder();

		if (!ligne.isEmpty()) {
			if (this.parser != null)
				ligne = this.parser.parse(ligne);
			if (!ligne.isEmpty()) {

				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_TYPE_ENR.getPositionDebut(),
						APP_enumParserSSIM.POSITION_TYPE_ENR.getPositionFin()));
				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_IDENTIFIANT_TRANCHE
								.getPositionDebut(),
						APP_enumParserSSIM.POSITION_IDENTIFIANT_TRANCHE
								.getPositionFin()));
				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_RANG_TRANCON
								.getPositionDebut(),
						APP_enumParserSSIM.POSITION_RANG_TRANCON
								.getPositionFin()));
				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT
								.getPositionDebut(),
						APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_DEBUT
								.getPositionFin()));
				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_FIN
								.getPositionDebut(),
						APP_enumParserSSIM.POSITION_PERIODE_CIRCULATION_FIN
								.getPositionFin()));
				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_JOURS_CIRCULATION
								.getPositionDebut(),
						APP_enumParserSSIM.POSITION_JOURS_CIRCULATION
								.getPositionFin()));
				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_GARE_DEPART
								.getPositionDebut(),
						APP_enumParserSSIM.POSITION_GARE_DEPART
								.getPositionFin()));
				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_HEURE_DEPART
								.getPositionDebut(),
						APP_enumParserSSIM.POSITION_HEURE_DEPART
								.getPositionFin()));
				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_DIFFERENCE_GMT_DEPART
								.getPositionDebut(),
						APP_enumParserSSIM.POSITION_DIFFERENCE_GMT_DEPART
								.getPositionFin()));
				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_GARE_ARRIVER
								.getPositionDebut(),
						APP_enumParserSSIM.POSITION_GARE_ARRIVER
								.getPositionFin()));
				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_HEURE_ARRIVER
								.getPositionDebut(),
						APP_enumParserSSIM.POSITION_HEURE_ARRIVER
								.getPositionFin()));
				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_DIFFERENCE_GMT_ARRIVER
								.getPositionDebut(),
						APP_enumParserSSIM.POSITION_DIFFERENCE_GMT_ARRIVER
								.getPositionFin()));
				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_INDICATEUR_FER
								.getPositionDebut(),
						APP_enumParserSSIM.POSITION_INDICATEUR_FER
								.getPositionFin()));
				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_COMPAGNIE_TRAIN
								.getPositionDebut(),
						APP_enumParserSSIM.POSITION_COMPAGNIE_TRAIN
								.getPositionFin()));
				sb.append(ligne.subSequence(
						APP_enumParserSSIM.POSITION_NUM_TRAIN
								.getPositionDebut(),
						APP_enumParserSSIM.POSITION_NUM_TRAIN.getPositionFin()));
			}
		}
		return sb.toString();
	}

	/**
	 * @author ismael.yahiani
	 * @param chaine
	 * @return une Map de l'ensemble des propriétés SSIM : Key = Nom Propriété
	 *         SSIM, VALUE = valeur propriété SSIM
	 * 
	 */
	public Map<String, String> getSSIMResult(String chaine) {

		Map<String, String> result = new HashMap<String, String>();

		result.put(APP_EnumPropSSIM.TYPE_ENREGISTREMENT.toString(), chaine
				.substring(
						APP_enumParserCirculation.POSITION_TYPE_ENREGISTREMENT
								.getPostionDebut(),
						APP_enumParserCirculation.POSITION_TYPE_ENREGISTREMENT
								.getPostionFin()));
		result.put(APP_EnumPropSSIM.IDENTIFIANT_TRANCHE.toString(), chaine
				.substring(
						APP_enumParserCirculation.POSITION_IDENTIFIANT_TRANCHE
								.getPostionDebut(),
						APP_enumParserCirculation.POSITION_IDENTIFIANT_TRANCHE
								.getPostionFin()));
		result.put(APP_EnumPropSSIM.RANG_TRONCON.toString(),
				chaine.substring(
						APP_enumParserCirculation.POSITION_RANG_TRANCON
								.getPostionDebut(),
						APP_enumParserCirculation.POSITION_RANG_TRANCON
								.getPostionFin()));
		result.put(APP_EnumPropSSIM.DATE_DEBUT_CIRCULATION.toString(), chaine
				.substring(APP_enumParserCirculation.POSITION_DATE_DEBUT_CIRCU
						.getPostionDebut(),
						APP_enumParserCirculation.POSITION_DATE_DEBUT_CIRCU
								.getPostionFin()));
		result.put(APP_EnumPropSSIM.DATE_FIN_CIRCULATION.toString(), chaine
				.substring(APP_enumParserCirculation.POSITION_DATE_FIN_CIRCU
						.getPostionDebut(),
						APP_enumParserCirculation.POSITION_DATE_FIN_CIRCU
								.getPostionFin()));
		result.put(APP_EnumPropSSIM.GARE_DEPART.toString(), chaine.substring(
				APP_enumParserCirculation.POSITION_ORGINE.getPostionDebut(),
				APP_enumParserCirculation.POSITION_ORGINE.getPostionFin()));
		result.put(APP_EnumPropSSIM.HEURE_DEPART.toString(),
				chaine.substring(
						APP_enumParserCirculation.POSITION_HEURE_DEPART
								.getPostionDebut(),
						APP_enumParserCirculation.POSITION_HEURE_DEPART
								.getPostionFin()));
		result.put(APP_EnumPropSSIM.DIFFERENCE_GMT_DEPART.toString(), chaine
				.substring(APP_enumParserCirculation.POSITION_DIFF_GMT_DEPART
						.getPostionDebut(),
						APP_enumParserCirculation.POSITION_DIFF_GMT_DEPART
								.getPostionFin()));
		result.put(APP_EnumPropSSIM.GARE_ARRIVER.toString(), chaine.substring(
				APP_enumParserCirculation.POSITION_DESTINATION
						.getPostionDebut(),
				APP_enumParserCirculation.POSITION_DESTINATION.getPostionFin()));

		result.put(APP_EnumPropSSIM.HEURE_ARRIVER.toString(), chaine.substring(
				APP_enumParserCirculation.POSITION_HEURE_ARRIVER
						.getPostionDebut(),
				APP_enumParserCirculation.POSITION_HEURE_ARRIVER
						.getPostionFin()));
		result.put(APP_EnumPropSSIM.DIFFERENCE_GMT_ARRIVER.toString(), chaine
				.substring(APP_enumParserCirculation.POSITION_DIFF_GMT_ARRIVER
						.getPostionDebut(),
						APP_enumParserCirculation.POSITION_DIFF_GMT_ARRIVER
								.getPostionFin()));
		result.put(APP_EnumPropSSIM.INDICATEUR_FER.toString(), chaine
				.substring(APP_enumParserCirculation.POSITION_INDICATEUR_FER
						.getPostionDebut(),
						APP_enumParserCirculation.POSITION_INDICATEUR_FER
								.getPostionFin()));
		result.put(APP_EnumPropSSIM.COMPAGNIE_TRAIN.toString(), chaine
				.substring(APP_enumParserCirculation.POSITION_COMPAGNIE_TRAIN
						.getPostionDebut(),
						APP_enumParserCirculation.POSITION_COMPAGNIE_TRAIN
								.getPostionFin()));
		result.put(APP_EnumPropSSIM.NUMERO_TRAIN.toString(),
				chaine.substring(
						APP_enumParserCirculation.POSITION_NUMERO_TRAIN
								.getPostionDebut(),
						APP_enumParserCirculation.POSITION_NUMERO_TRAIN
								.getPostionFin()));

		return result;
	}

	@Override
	public Map<String, String> getParsedResult() {

		return null;
	}
}
