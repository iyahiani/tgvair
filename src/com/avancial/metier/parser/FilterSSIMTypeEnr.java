package com.avancial.metier.parser;

import com.avancial.parser.AFilter;
import com.avancial.parser.IParser;

/**
 * 
 * @author ismael.yahiani Permet de filter la ssim en enlevant les
 *         enregistrements de type 4
 * 
 */

public class FilterSSIMTypeEnr extends AFilter {
	public FilterSSIMTypeEnr(IParser pars) {
		super(pars);
	}
	@Override
	public String parse(String ligne) {
		String chaine = "";
		if (this.parser != null)
			chaine = this.parser.parse(ligne);
		if (chaine.substring(
				TGVAIR_enumParserSSIM.POSITION_TYPE_ENR.getPositionDebut(),
				TGVAIR_enumParserSSIM.POSITION_TYPE_ENR.getPositionFin())
				.equals(TGVAIR_enumParserSSIM.VALEUR_TYPE4.getTypeEnr()))
			chaine=""; 
		return chaine;
	}
}
// /TGVAIR_enumParserSSIM.VALEUR_TYPE4