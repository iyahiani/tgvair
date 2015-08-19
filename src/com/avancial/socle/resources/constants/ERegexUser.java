package com.avancial.socle.resources.constants;

/**
 * 
 * @author caglar.erdogan
 *
 */
public enum ERegexUser implements IRegex {
	CODE_POSTAL("^\\p{Digit}{5}$"),
	COMMENTAIRE("^.*$"),
	EMAIL("^[\\w-\\+]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w]+)*(\\.[\\p{Alpha}]{2,})$"),
	FAX("^0\\p{Digit}{9}$"),
	LOGIN("^\\p{Alnum}+"),
	NOM("^\\p{Alpha}+(\\s\\p{Alpha}+)*$"),
	NUMERO("^\\p{Digit}{1,4}$"),
	PRENOM("^\\p{Alpha}+(\\s\\p{Alpha}+)*$"),
	RUE("^\\p{Alpha}+(\\s\\p{Alpha}+)*$"),
	TELEPHONE("^0\\p{Digit}{9}$"),
	VILLE("^\\p{Alpha}+(\\s\\p{Alpha}+)*$");

	private String expression;

	private ERegexUser(String expression) {
		this.expression = expression;
	}

	@Override
    public String getExpression() {
		return this.expression;
	}
}