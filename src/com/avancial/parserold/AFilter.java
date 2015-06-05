package com.avancial.parserold;


/**
 * 
 * @author ismael.yahiani    
 *  
 * La classe AFilter vous permet d'implémenter des filtres qui seront appliqués sur les lignes parsées.
 *
 */
public abstract class AFilter extends AParser  {

	public AFilter(IParser pars) {
		super(pars);
	}

	
}
