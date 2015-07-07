package com.avancial.app.business.parser;

import java.util.Map;

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

		if (!ligne.substring(
				APP_enumParserSSIM.POSITION_TYPE_ENR.getPositionDebut(),
				APP_enumParserSSIM.POSITION_TYPE_ENR.getPositionFin()).equals(
				APP_enumParserSSIM.VALEUR_TYPE3.getTypeEnr()))
			ligne = "";
		

		if (this.parser != null && !ligne.isEmpty())
			return this.parser.parse(ligne);

		return ligne;
	}

	@Override
	public Map<String, String> getParsedResult() {

		return null;
	}
}
