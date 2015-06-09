package com.avancial.metier.parser;

import com.avancial.parser.AFilter;
import com.avancial.parser.IParser;

public class FiltreSSIMCompagnieTrain extends AFilter {

	public FiltreSSIMCompagnieTrain(IParser pars) {
		super(pars);
		
	}

	@Override
	public String parse(String ligne) {
		if (ligne.substring(TGVAIR_enumParserSSIM.POSITION_TYPE_ENR.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_TYPE_ENR.getPositionFin()).equals(TGVAIR_enumParserSSIM.VALEUR_TYPE4.getTypeEnr()))
	         ligne = "";
	      // Si parser est null ou que la chaine est vide, on interrompt le
	      // process
	      if (this.parser != null && !ligne.isEmpty())
	         this.parser.parse(ligne);

	      return ligne;
		
		return null;
	}

}
