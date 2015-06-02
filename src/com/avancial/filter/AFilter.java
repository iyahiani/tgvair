package com.avancial.filter;

import com.avancial.parser.AParser;
import com.avancial.parser.IParser;

/**
 * 
 * @author ismael.yahiani    
 * class abstraite qui permet la mise en place du Design Pattern Decorator 
 *
 */
public abstract class AFilter extends AParser  {

	public AFilter(IParser pars) {
		super(pars);
	}

	
}
