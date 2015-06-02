package com.avancial.parser;

import com.avancial.reader.IReader;
import com.avancial.tgvair.Circulation;

/**
 * 
 * @author ismael.yahiani
 *	class abstraite contenant les constructeurs du Pattern Decorator    	
 */

public abstract class AParser implements IParser {

	protected IParser parser ;
	protected String resultat ; 
	
	public AParser(IParser pars) { 
		parser = pars ;
	}
	
	public AParser() {
		
	}
	
}
