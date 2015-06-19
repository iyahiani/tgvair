package com.avancial.tgvair.metier;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ismael.yahiani 
 *  cette class permet d'insatanci� l'ensemble des objets m�tier TGVAIR
 */
public class TGVAIRFactory {

	public List<Circulation> getCirculList() {

		List<Circulation> listCircul = new ArrayList<Circulation>();
		
		return listCircul;
	}

	
	public Circulation getCirculation(String chaine) throws ParseException {

		Circulation circul = new Circulation();

		if (chaine.length() > 0) {
			
		}

		return circul;
	} 
	
	public Train getTrain(ArrayList<Circulation> listCircul) {
				
		Train train = new Train()  ;
		
		
		train.setCirculation(listCircul);
		return train ;
	}
	
}
