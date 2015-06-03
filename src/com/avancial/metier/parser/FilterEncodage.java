package com.avancial.metier.parser;

import com.avancial.parser.AFilter;
import com.avancial.parser.IParser;

public class FilterEncodage extends AFilter {

	public FilterEncodage(IParser pars) {
		super(pars) ;
	}
	@Override
	public String parse(String ligne) {
		
		if (this.parser!=null)
		  return this.parser.parse(ligne).replaceAll("[^a-zA-Z 0-9]", ""); 
		return ligne.replaceAll("[^a-zA-Z 0-9 +]", "");
	}

}
