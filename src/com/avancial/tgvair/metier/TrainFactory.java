package com.avancial.tgvair.metier;

import java.util.List;

import com.avanciale.TGVAIR.DAO.TrainDAO;

/**
 * 
 * @author ismael.yahiani
 *la factory Train permet de creer des objets de type train 
 */
public class TrainFactory implements ITrainFactory {

	@Override
	public ITrain createTrain() {
		
		ITrain train = new Train() ; 
		return train ; 
	}

	@Override
	public ITrain createTrainByListCirculation(List<Circulation> listCircu) {
		
		return null;
	}
	
}
