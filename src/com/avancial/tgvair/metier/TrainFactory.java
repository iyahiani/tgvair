package com.avancial.tgvair.metier;

import java.util.List;

import com.avanciale.TGVAIR.DAO.TrainDAO;

public class TrainFactory implements ITrainFactory {

	/**
	 * @return la Factory de la classe train
	 *  
	 */
	@Override
	public ITrain createTrainByListCirculation(List<Circulation> circul) {
		
		TrainDAO train = new TrainDAO() ; 
		train.setCirculation(circul);  		
		return train ;
	}
									
	@Override
	public ITrain createTrainByID() {
		
		return null;
	}	
}
