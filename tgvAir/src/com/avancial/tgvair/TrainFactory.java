package com.avancial.tgvair;

public class TrainFactory implements ITrainFactory {

	@Override
	public ITrain createTrainByListPeriode() {
		
		ITrain train = new Train() ; 
		return train ;
	}

	@Override
	public ITrain createTrainByID() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
}
