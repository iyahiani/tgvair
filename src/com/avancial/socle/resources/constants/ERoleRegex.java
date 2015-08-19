package com.avancial.socle.resources.constants;

/**
 * 
 * @author caglar.erdogan
 *
 */
public enum ERoleRegex implements IRegex {
	/*Vide*/;

	private String expression;

	private ERoleRegex(String expression, String libelle) {
		this.expression = expression;
	}
	
	@Override
    public String getExpression() {
	    return null;
    }

}