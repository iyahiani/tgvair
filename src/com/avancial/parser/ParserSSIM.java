package com.avancial.parser;
/**
 * 
 * @author ismael.yahiani
 *
 */
public class ParserSSIM extends AParser {

	private StringBuilder sb ;
	public ParserSSIM() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String parse(String ligne) {
		
		sb = new StringBuilder() ; 		
		if(!ligne.isEmpty()) {
		ligne.replaceAll("[^a-zA-Z 0-9]", "");	
		sb.append(ligne.subSequence(13,27)) ;
		sb.append(ligne.subSequence(28,34)) ;
		sb.append(ligne.subSequence(35,40)) ;
		sb.append(ligne.subSequence(40,44)) ;
		sb.append(ligne.subSequence(55,60)) ;
		sb.append(ligne.subSequence(142,152)) ;
		}
		return sb.toString() ;
	}
}
