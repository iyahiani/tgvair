package com.avancial.tgvair.metier;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ismael.yahiani 
 *  cette class permet d'insatancié l'ensemble des objets métier TGVAIR
 */
public class TGVAIRFactory {

	

	
	public Circulation getCirculation(String chaine) throws ParseException {

		Circulation circul = new Circulation();

		if (chaine.length() > 0) {
			
		}

		return circul;
	} 
	
	public Train getTrain(ArrayList<Circulation> listCircul) {
				
		Train train = new Train()  ;
		
		return train ;
	}
	
}
