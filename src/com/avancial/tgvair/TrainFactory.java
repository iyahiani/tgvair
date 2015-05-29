package com.avancial.tgvair;

import java.util.List;

public class TrainFactory implements ITrainFactory {

	/**
	 * 
	 * @return 
	 * 
	 * 
	 */
	@Override
	public ITrain createTrainByListCirculation(List<Circulation> circul) {
		
		ITrain train = new Train() ; 
		train.setCirculation(circul); 		
		return train ;
	}

	@Override
	public ITrain createTrainByID() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

	
}
