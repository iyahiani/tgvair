package com.avancial.tgvair;

public class TrainFactory implements ITrainFactory {

	@Override
	public ITrain createTrainByListPeriode() {
		
		ITrain train = new Train() ; 
		return train ;
	}

	
}
