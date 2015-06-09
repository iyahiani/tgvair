package com.avancial.metier.parser;

import com.avancial.parser.AFilter;
import com.avancial.parser.IParser;

public class FiltreSSIMCompagnieTrain extends AFilter {

	
	public FiltreSSIMCompagnieTrain(IParser pars) {
		super(pars);
	}
	
	@Override
	public String parse(String ligne) {
		if (!ligne.substring(
				TGVAIR_enumParserSSIM.POSITION_COMPAGNIE_TRAIN.getPositionDebut(),
				TGVAIR_enumParserSSIM.POSITION_COMPAGNIE_TRAIN.getPositionFin())
				.equals(TGVAIR_enumParserSSIM.VALEUR_COMPAGNIE_TRAIN.getCompagnieTrain()))
			ligne = "";
		
		if (this.parser != null && !ligne.isEmpty())
			this.parser.parse(ligne);

		return ligne;
	}

}
