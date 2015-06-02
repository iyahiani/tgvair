package com.avancial.filter;

import com.avancial.parser.IParser;
import com.avancial.parser.ParserSSIM;


/**
 * 
 * @author ismael.yahiani
 * la classe FilterSSIM permet d'appliquée des filtres sur les lignes parsées   
 */


public class FilterSSIM extends AFilter {
/** 
 * 
 @author Yahiani Ismail 
 * @param pars
 * <h1>Constructor 
 */
	public FilterSSIM(IParser pars) {
		super(pars) ;
	}
	@Override
	public String parse(String ligne) {
		
		if (this.parser!=null)
			  return this.parser.parse(ligne) ; 
		
		return ligne;
	}
	
	
}
