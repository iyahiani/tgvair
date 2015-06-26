package com.avancial.tgvair.metier;

import java.util.List;

public interface ITrainFactory {

	public ITrain createTrain ();

	public ITrain createTrainByListCirculation(List<Circulation> listCircu);
	}
