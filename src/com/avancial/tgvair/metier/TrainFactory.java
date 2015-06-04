package com.avancial.tgvair.metier;

import java.util.List;

import com.avanciale.metier.DAO.TrainDAO;

public class TrainFactory implements ITrainFactory {

	/**
	 * @return 
	 *  
	 */
	@Override
	public ITrain createTrainByListCirculation(List<Circulation> circul) {
		
		ITrain train = new TrainDAO() ; 
		train.setCirculation(circul); 		
		return train ;
	}
									
	@Override
	public ITrain createTrainByID() {
		
		return null;
	}	
}
