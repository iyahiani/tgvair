package com.avancial.parser;

/**
 * 
 * @author ismael.yahiani
 *
 */
public class ParserSSIM extends AParser {

	public ParserSSIM(IParser par) {
		super(par);
	}

	@Override
	public String parse(String ligne) {
		
		if (!ligne.isEmpty()) {
			// ligne.replaceAll("[^a-zA-Z 0-9]", "");
			if (this.parser != null)
				this.resultat = parser.parse(ligne);
			if(!resultat.isEmpty())
			   this.resultat.concat(";") ;
		}
		return resultat ;
	}
}


/*
 * 
 * 			sb.append(resultat.subSequence(13, 27));
			sb.append(resultat.subSequence(28, 34));
			sb.append(resultat.subSequence(35, 40));
			sb.append(resultat.subSequence(40, 44));
			sb.append(resultat.subSequence(55, 60));
			sb.append(resultat.subSequence(142, 152));
 */