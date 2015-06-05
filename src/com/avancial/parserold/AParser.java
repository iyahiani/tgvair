package com.avancial.parserold;

import com.avancial.reader.IReader;
import com.avancial.tgvair.metier.Circulation;

/**
 * 
 * @author ismael.yahiani
 *	class abstraite contenant les constructeurs du Pattern Decorator  
 * Vous devrez étendre cette classe dans votre application afin de construire vos parsers  	
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
