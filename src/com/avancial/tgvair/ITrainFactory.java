package com.avancial.tgvair;

import java.util.List;

public interface ITrainFactory {

	public ITrain createTrainByID() ;
	 ITrain createTrainByListCirculation(List<Circulation> circul); 
}
