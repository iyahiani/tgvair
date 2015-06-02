package com.avancial.filter;

import com.avancial.parser.AParser;
import com.avancial.parser.IParser;

public class FilterEncodage extends AFilter {

	public FilterEncodage(IParser pars) {
		super(pars) ;
	}
	@Override
	public String parse(String ligne) {
		
		if (this.parser!=null)
		  return this.parser.parse(ligne) +"Je suis le filtre encodage."; 
		   
		return ligne+"Je suis le filtre encodage.";
	}

}
