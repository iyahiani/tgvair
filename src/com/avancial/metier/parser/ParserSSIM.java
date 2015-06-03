package com.avancial.metier.parser;

import com.avancial.parser.AParser;
import com.avancial.parser.IParser;

/**
 * 
 * @author ismael.yahiani 
 * cette class permet de parser un fichier SSIM en vue d'alimenté l'objet métier   
 *elle etend la classe abstraite AParser   
 */
public class ParserSSIM extends AParser {

	public ParserSSIM(IParser par) {
		super(par);
	}

	@Override
	public String parse(String ligne) {

		StringBuilder sb = new StringBuilder() ;
		if (!ligne.isEmpty()) {
			if (this.parser != null)
				this.resultat = parser.parse(ligne);
			if(!resultat.isEmpty()) 
				sb.append(resultat.subSequence(TGVAIR_enumParserSSIM.POSITION_TYPE_ENR.getPositionDebut(), TGVAIR_enumParserSSIM.POSITION_TYPE_ENR.getPositionFin()));
				sb.append(resultat.subSequence(13, 27));
				/*sb.append(resultat.subSequence(35, 40));
				sb.append(resultat.subSequence(40, 44));
				sb.append(resultat.subSequence(55, 60));
				sb.append(resultat.subSequence(142, 152)); 	*/
			  
		}
		return sb.toString() ;
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