package com.avancial.metier.parser;

import java.util.List;
import java.util.Map;

import com.avancial.parser.AParser;
import com.avancial.parser.IParser;

public class FiltreCatalogue extends AParser {

	private int[] numTrains;
	
	
	public FiltreCatalogue(IParser pars) {
		super(pars);
	}

	public FiltreCatalogue(IParser pars, int[] numTrains) {
		super(pars);
		this.numTrains = numTrains;
	}

	@Override
	public String parse(String line) {

		if (!line.equals(""))
			if (this.parser != null)
				line = this.parser.parse(line);

		for (int i : this.numTrains) {
			if (i == Integer.valueOf(line.substring(
					APP_enumParserSSIM.POSITION_NUM_TRAIN.getPositionDebut(),
					APP_enumParserSSIM.POSITION_NUM_TRAIN.getPositionFin())))
				return line; 
		}
		
		return "";
	}

	@Override
	public Map<String, String> getParsedResult() {

		return null;
	}

}
