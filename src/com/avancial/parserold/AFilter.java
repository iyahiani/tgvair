package com.avancial.parserold;


/**
 * 
 * @author ismael.yahiani    
 *  
 * La classe AFilter vous permet d'impl�menter des filtres qui seront appliqu�s sur les lignes pars�es.
 *
 */
public abstract class AFilter extends AParser  {

	public AFilter(IParser pars) {
		super(pars);
	}

	
}
