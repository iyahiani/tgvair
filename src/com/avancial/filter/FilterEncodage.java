package com.avancial.filter;

import com.avancial.parser.AParser;
import com.avancial.parser.IParser;

public class FilterEncodage extends  AParser {

	public FilterEncodage(IParser pars) {
		super(pars) ;
	}
	@Override
	public String parse(String ligne) {
		
		return ligne+"Je suis le filtre encodage.";
	}

}
