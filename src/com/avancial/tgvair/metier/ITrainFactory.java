package com.avancial.tgvair.metier;

import java.util.List;

public interface ITrainFactory {

	public ITrain createTrainByID() ;
	 ITrain createTrainByListCirculation(List<Circulation> circul); 
}
