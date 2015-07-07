package com.avancial.app.business.train;

import java.util.List;

public interface ITrainFactory {

	public ITrain createTrain ();

	public ITrain createTrainByListCirculation(List<Circulation> listCircu);
	}
