package com.avancial.app.business.train;

import java.util.List;

import com.avancial.app.business.train.circulation.Circulation;

public interface ITrainFactory {

	public ITrain createTrain ();

	public ITrain createTrainByListCirculation(List<Circulation> listCircu);
	}
